package com.talkie.sockets.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkie.dialect.parser.impl.JsonParsingFacade;
import com.talkie.dialect.parser.impl.MessageTypeMatcher;
import com.talkie.dialect.parser.interfaces.ParsingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {
    @Bean
    public ParsingService getParsingService() {
        return new JsonParsingFacade(new ObjectMapper(), new MessageTypeMatcher());
    }
}
