package com.project.controllers;

import graphql.GraphQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphQLTestController {
    private final Logger logger = LoggerFactory.getLogger(GraphQLTestController.class);
    @Autowired
    private GraphQL graphQL;

    @RequestMapping("/graphQLTest")
    public String graphTest(@RequestParam(value = "id") String id) {
        logger.info("Request with parameter: " + id);
        return (String) graphQL.execute(id).getData();
    }
}
