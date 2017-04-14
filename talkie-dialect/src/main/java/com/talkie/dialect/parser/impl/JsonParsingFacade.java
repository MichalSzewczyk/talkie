package com.talkie.dialect.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.SocketMessage;
import com.talkie.dialect.parser.interfaces.CustomMatcher;
import com.talkie.dialect.parser.interfaces.ParsingService;
import com.talkie.dialect.utils.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public class JsonParsingFacade implements ParsingService {
    private final Logger logger = LoggerFactory.getLogger(JsonParsingFacade.class);
    private final static String JSON_EXCEPTION = "Unable to %s object: %s";

    private final ObjectMapper objectMapper;
    private final CustomMatcher customMatcher;

    public JsonParsingFacade(ObjectMapper objectMapper, CustomMatcher customMatcher) {
        this.objectMapper = objectMapper;
        this.customMatcher = customMatcher;
    }

    private MessageType detectClass(String input) {
        String type = customMatcher.getValue(input);
        return MessageType.valueOf(type);
    }

    @Override
    public Tuple<? extends SocketMessage, MessageType> parseSocketMessage(String json) throws IOException {
        MessageType detectedClass = detectClass(json);
        return new Tuple<>(objectMapper.readValue(json, detectedClass.getSocketMessageClass()), detectedClass);
    }

    @Override
    public Optional<String> serialize(Serializable object) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(String.format(JSON_EXCEPTION, "serialize", object), e);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public <T> Optional<T> deserialize(String json, Class<T> type) {
        T result = null;
        try {
            result = objectMapper.readValue(json, type);
        } catch (IOException e) {
            logger.error(String.format(JSON_EXCEPTION, "deserialize", json), e);
        }
        return Optional.ofNullable(result);
    }
}
