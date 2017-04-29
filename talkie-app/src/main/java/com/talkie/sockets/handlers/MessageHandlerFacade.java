package com.talkie.sockets.handlers;

import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.SocketMessage;
import com.talkie.dialect.parser.interfaces.ParsingService;
import com.talkie.dialect.utils.Tuple;
import com.talkie.utils.ResultHandler;
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
    private static final String ESTABLISHED_CONNECTION = "Established connection with user: %s";
    private static final String HANDLING_MESSAGE = "Handling socket message: %s";
    private static final String MESSAGE_HANDLING_ERROR = "Error occurred while handling message: %s";
    private final Logger logger = LoggerFactory.getLogger(MessageHandlerFacade.class);
    private final List<WebSocketSession> establishedSessions;
    private final ResultHandler handlingVisitor;
    private final AbstractHandlingService handlingService;
    private final ParsingService parsingService;

    @Autowired
    public MessageHandlerFacade(ParsingService parsingService, ResultHandler resultHandler, AbstractHandlingService handlingService) {
        this.handlingVisitor = resultHandler;
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
        handlingService.handleDisconnect(session);
        logger.info(String.format(SESSION_CLOSED_INFO, session, closeStatus.getReason()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String plainTextMessage = message.getPayload();
        Tuple<? extends SocketMessage, MessageType> result = parsingService.parseSocketMessage(plainTextMessage);
        handlingVisitor.setSession(session);

        logger.info(String.format(HANDLING_MESSAGE, message.getPayload()));

        try {

        } catch (Exception e) {
            logger.error(String.format(MESSAGE_HANDLING_ERROR, result.getKey()), e);
        }
    }
}
