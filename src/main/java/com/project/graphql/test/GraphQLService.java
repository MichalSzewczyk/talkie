package com.project.graphql.test;

import graphql.GraphQL;
import graphql.schema.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

@Configuration
public class GraphQLService {
    private final Logger logger = LoggerFactory.getLogger(GraphQLService.class);

    @Autowired
    private Login login;

    private GraphQLObjectType buildLoginGraphQLObject() {
        return GraphQLObjectType.newObject()
                .name("person")
                .field(newFieldDefinition()
                        .name("login")
                        .type(GraphQLString)
                        .staticValue(login.getLogin())
                        .build())
                .field(newFieldDefinition()
                        .name("password")
                        .type(GraphQLString)
                        .staticValue(login.getPassword())
                        .build())
                .build();
    }

    @Bean
    public GraphQL getGraphQL() {
        logger.info("Creating GraphQL bean.");
        //TODO: Sub selection required for type Login

        GraphQLObjectType personType = newObject()
                .name("Login")
                .description("personal data for logging")
                .field(newFieldDefinition()
                        .name("login")
                        .description("Login of the user.")
                        .type(GraphQLString).build())
                .field(newFieldDefinition()
                        .name("password")
                        .description("Password of the user.")
                        .type(GraphQLString).build())
                .build();


        GraphQLObjectType queryType = newObject()
                .name("QueryType")
                .field(newFieldDefinition()
                        .name("login")
                        .type(personType)
                        .argument(Arrays.asList(newArgument()
                                        .name("login")
                                        .type(GraphQLString)
                                        .build(),
                                newArgument()
                                        .name("password")
                                        .type(GraphQLString)
                                        .build()))
                        .dataFetcher(new StaticDataFetcher(login)).build())
                .build();

        GraphQLSchema loginSchema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();
        return new GraphQL(loginSchema);
    }
}
