package com.project.controllers;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GraphQLTestController {
    @Autowired
    private GraphQL graphQL;

    @RequestMapping("/graphQLTest")
    public String graphTest(@RequestBody String query) {
        return (String) graphQL.execute(query).getData();
    }
}
