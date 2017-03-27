package com.project.sockets.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sockets.interfaces.CustomMatcher;
import com.project.sockets.interfaces.ParsingService;
import com.project.sockets.model.*;
import com.project.sockets.model.messages.SocketMessage;
import com.project.utils.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class JsonParsingService implements ParsingService {
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
}
