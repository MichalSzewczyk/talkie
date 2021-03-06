package com.talkie.controllers;

import com.talkie.interfaces.WebExecutionResult;
import com.talkie.interfaces.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TalkieController {
    private static final String REQUEST_INFO = "Request with parameter: %s";
    private static final String GRAPH_QL_ERROR = "Error: %s, while executing graphQl query for request body: %s";
    private static final String GRAPH_QL_RESULT = "Result of request: %s";
    private final Logger logger = LoggerFactory.getLogger(TalkieController.class);
    private final WebService webService;

    @Autowired
    public TalkieController(WebService webService) {
        this.webService = webService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "${crossOrigin.port}")
    @ResponseBody
    public Object graphTest(@RequestBody String requestBody) {
        logger.info(String.format(REQUEST_INFO, requestBody));

        WebExecutionResult result = webService.execute(requestBody);

        logger.info(String.format(GRAPH_QL_RESULT, result.getData()));

        if (!result.getErrors().isEmpty()) {
            logger.error(String.format(GRAPH_QL_ERROR, result.getErrors(), requestBody));
        }
        return result.getData();
    }

}
