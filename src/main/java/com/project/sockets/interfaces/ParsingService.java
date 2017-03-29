package com.project.sockets.interfaces;

import com.project.sockets.model.MessageType;
import com.project.utils.Tuple;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public interface ParsingService {
    Tuple<Object, MessageType> parseSocketMessage(String json) throws IOException;

    Optional<String> serialize(Serializable object);

    <T> Optional<T> deserialize(String json, Class<T> type);
}
