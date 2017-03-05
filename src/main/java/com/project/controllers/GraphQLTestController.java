package com.project.controllers;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphQLTestController {
    @Autowired
    private GraphQL graphQL;

    @RequestMapping("/graphQLTest")
    public String graphTest(@RequestParam(value = "id") String id) {
        return (String) graphQL.execute(id).getData();
    }
}