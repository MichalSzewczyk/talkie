package com.talkie.sockets.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class BeanFactory {
    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
