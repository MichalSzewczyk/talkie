package com.talkie.sockets.impl;

import com.talkie.sockets.interfaces.CustomMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageTypeMatcher implements CustomMatcher {
    private Pattern pattern;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTypeMatcher.class);
    private static final String PARSED_TYPE = "Parsed type is: %s";

    public MessageTypeMatcher() {
        this.pattern = Pattern.compile("\"type\":\"[A-Z_]+\"");
    }

    public String getValue(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String parsedType =  matcher.group(0).replaceAll("\"type\":\"", "").replaceAll("\"", "");
            LOGGER.info(String.format(PARSED_TYPE, parsedType));
            return parsedType;
        } else {
            throw new IllegalArgumentException("Not supported socket message type.");
        }
    }

}