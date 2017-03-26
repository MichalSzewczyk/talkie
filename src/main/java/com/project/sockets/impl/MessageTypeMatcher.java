package com.project.sockets.impl;

import com.project.sockets.interfaces.CustomMatcher;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageTypeMatcher implements CustomMatcher {
    private Pattern pattern;

    public MessageTypeMatcher() {
        this.pattern = Pattern.compile("\"type\":\"[A-Z_]+\"");
    }

    public String getValue(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1).replaceAll("\"type\":\"", "").replaceAll("\"", "");
        } else {
            throw new IllegalArgumentException("Not supported socket message type.");
        }
    }

}
