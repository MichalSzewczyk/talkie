package com.talkie.dialect.parser.interfaces;

import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.SocketMessage;
import com.talkie.dialect.utils.Tuple;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;



public interface ParsingService {
    /**
     * Method is responsible for parsing json to socket messages
     *
     * @param  json is a json format of socket message to parse
     * @return tuple where key is parsed socket message and value is
     * type of that message according to MessageType enum.
     *
     */
    Tuple<? extends SocketMessage, MessageType> parseSocketMessage(String json) throws IOException;

    /**
     * Method is responsible for serializing object to json
     *
     * @param  object is object to serialize
     * @return json representation of serialized object
     *
     */
    Optional<String> serialize(Serializable object);

    /**
     * Method is responsible for deserialization object from json
     *
     * @param  json is string representation of serialized object
     * @param  type is class to which we want to deserialize json
     * @return deserialized object
     *
     */
    <T> Optional<T> deserialize(String json, Class<T> type);
}
