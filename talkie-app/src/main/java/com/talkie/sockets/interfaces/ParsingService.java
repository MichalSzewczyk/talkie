package com.talkie.sockets.interfaces;

import com.talkie.dialect.MessageType;
import com.talkie.utils.Tuple;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

public interface ParsingService {
    Tuple<Object, MessageType> parseSocketMessage(String json) throws IOException;

    Optional<String> serialize(Serializable object);

    <T> Optional<T> deserialize(String json, Class<T> type);
}
