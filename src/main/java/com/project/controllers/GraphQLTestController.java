package com.project.controllers;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GraphQLTestController {
    private final Logger logger = LoggerFactory.getLogger(GraphQLTestController.class);
    private static final String REQUEST_INFO = "Request with parameter: %s";
    private static final String GRAPH_QL_ERROR = "Error: %s, while executing graphQl query for request body: %s";

    @Autowired
    private GraphQL graphQL;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @CrossOrigin(origins = "${crossOrigin.port}")
    @ResponseBody
    public Object graphTest(@RequestBody String requestBody) {
        logger.info(REQUEST_INFO, requestBody);
        ExecutionResult result = graphQL.execute(requestBody);
        if (result.getErrors().size() > 0) {
            logger.error(String.format(GRAPH_QL_ERROR, result.getErrors(), requestBody));
        }
        return result.getData();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/sample")
    public String foo(@RequestBody String id) {
        logger.info("Request with parameter: " + id);
        return (String) graphQL.execute(id).getData();
    }

}
