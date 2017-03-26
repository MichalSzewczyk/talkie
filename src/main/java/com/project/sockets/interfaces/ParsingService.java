package com.project.sockets.interfaces;

import com.project.sockets.model.messages.SocketMessage;
import com.project.utils.Tuple;

import java.io.IOException;

public interface ParsingService {
    Tuple<Object, Class<? extends SocketMessage>> parseSocketMessage(String json) throws IOException;
}
