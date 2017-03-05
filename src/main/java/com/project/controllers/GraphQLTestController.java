package com.project.controllers;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@Controller
public class GraphQLTestController {
    private final Logger logger = LoggerFactory.getLogger(GraphQLTestController.class);
    @Autowired
    private GraphQL graphQL;

    @RequestMapping("/graphQLTest")
    public String graphTest(@RequestBody String query) {
        return (String) graphQL.execute(query).getData();
    }

    @RequestMapping("/slaby")
    public String graphTest123(@RequestBody String query) {

        GraphQLFieldDefinition newQuery = newFieldDefinition()
                .name("hello")
                .type(GraphQLString)
                .staticValue("world")
                .build();

        GraphQLSchema schema = GraphQLSchema
                .newSchema()
                .query(newObject()
                        .name("RootQueryType")
                        .field(newQuery)
                        .build()) // must be provided
                .build();

        GraphQL graphql = new GraphQL(schema);

        ExecutionResult result = graphql.execute(query);
        if (result.getErrors().size() > 0) {
            System.out.println("QUERY ERROR!: \n" + result.getErrors());
        }
        System.out.println("RESULT \n" + result.getData());

        return "TEST";
    }
}
