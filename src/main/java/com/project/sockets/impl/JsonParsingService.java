package com.project.sockets.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sockets.interfaces.CustomMatcher;
import com.project.sockets.interfaces.ParsingService;
import com.project.sockets.model.MessageType;
import com.project.utils.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Service
public class JsonParsingService implements ParsingService {
    private final Logger logger = LoggerFactory.getLogger(JsonParsingService.class);
    private final static String JSON_EXCEPTION = "Unable to %s object: %s";

    @Autowired
    private ObjectMapper objectMapper;

    @Resource(name = "messageTypeMatcher")
    private CustomMatcher customMatcher;


    private MessageType detectClass(String input){
        String type = customMatcher.getValue(input);
        return MessageType.valueOf(type);

    }

    @Override
    public Tuple<Object, MessageType> parseSocketMessage(String json) throws IOException {
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
