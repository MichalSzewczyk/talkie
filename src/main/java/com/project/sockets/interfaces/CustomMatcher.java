package com.project.sockets.interfaces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public interface CustomMatcher {
    String getValue(String input);
}
