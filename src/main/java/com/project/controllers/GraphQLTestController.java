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
    @Autowired
    private GraphQL graphQL;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String graphTest(@RequestBody String id) {
        logger.info("Request with parameter: " + id);
        return (String) graphQL.execute(id).getData();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/foo")
    public String foo(@RequestBody String id) {
        logger.info("Request with parameter: " + id);
        return (String) graphQL.execute(id).getData();
    }

    @RequestMapping("/slaby")
    public String graphTest123(@RequestBody String query) {
        logger.info(query);
        ExecutionResult result = graphQL.execute(query);
        if (result.getErrors().size() > 0) {
            System.out.println("QUERY ERROR!: \n" + result.getErrors());
        }
        System.out.println("RESULT \n" + result.getData());

        return "TEST";
    }
}
