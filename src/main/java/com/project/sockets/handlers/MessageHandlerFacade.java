package com.project.sockets.handlers;

import com.project.sockets.interfaces.ParsingService;
import com.project.sockets.model.MessageType;
import com.project.sockets.model.messages.requests.FetchUserStatus;
import com.project.sockets.model.messages.requests.SendMessage;
import com.project.utils.HandlingVisitor;
import com.project.utils.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MessageHandlerFacade extends TextWebSocketHandler {
    private static final String SESSION_CLOSED_INFO = "Session %s closed due to reason: %s";
    private final Logger logger = LoggerFactory.getLogger(MessageHandlerFacade.class);

    private final List<WebSocketSession> establishedSessions;

    private static final String NOT_SUPPORTED_MESSAGE = "Not supported message type: %s";
    private static final String ESTABLISHED_CONNECTION = "Established connection with user: %s";
    private static final String HANDLING_MESSAGE = "Handling socket message: %s";
    private static final String MESSAGE_HANDLING_ERROR = "Error occurred while handling message: %s";

    private final HandlingVisitor handlingVisitor;
    private final AbstractHandlingService handlingService;
    private final ParsingService parsingService;

    @Autowired
    public MessageHandlerFacade(ParsingService parsingService, HandlingVisitor handlingVisitor, AbstractHandlingService handlingService) {
        this.handlingVisitor = handlingVisitor;
        this.parsingService = parsingService;
        this.handlingService = handlingService;
        establishedSessions = new CopyOnWriteArrayList<>();

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        establishedSessions.add(session);

        logger.info(String.format(ESTABLISHED_CONNECTION, session.getUri()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        super.afterConnectionEstablished(session);
        handlingService.handleLogout(session);
        logger.info(String.format(SESSION_CLOSED_INFO, session, closeStatus.getReason()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String plainTextMessage = message.getPayload();
        Tuple<Object, MessageType> result = parsingService.parseSocketMessage(plainTextMessage);
        handlingVisitor.setSession(session);

        logger.info(String.format(HANDLING_MESSAGE, message.getPayload()));

        try {
            switch (result.getValue()) {
                case FETCH_USER_STATUS:
                    handlingVisitor.visit((FetchUserStatus) result.getKey());
                    break;
                case SEND_MESSAGE:
                    handlingVisitor.visit((SendMessage) result.getKey());
                    break;
                default:
                    logger.error(String.format(NOT_SUPPORTED_MESSAGE, result.getValue()));
            }
        } catch (Exception e) {
            logger.error(String.format(MESSAGE_HANDLING_ERROR, result.getKey()), e);
        }
    }
}
