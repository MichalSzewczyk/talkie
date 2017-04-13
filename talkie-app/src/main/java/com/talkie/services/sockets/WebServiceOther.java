package com.talkie.services.sockets;

import com.talkie.interfaces.WebExecutionResult;
import com.talkie.interfaces.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Lazy
@Service
public class WebServiceOther implements WebService{
    private final Logger logger = LoggerFactory.getLogger(WebServiceOther.class);
    private static final String CREATION_MESSAGE = "Creating bean: %s";
    WebServiceOther(){
        logger.info(String.format(CREATION_MESSAGE, WebServiceOther.class.getName()));
    }
    @Override
    public WebExecutionResult execute(String requestBody) {
        throw new UnsupportedOperationException();
    }
}
