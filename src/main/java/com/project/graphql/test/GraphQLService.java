package com.project.graphql.test;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLSchema.newSchema;

@Configuration
public class GraphQLService {
    private final Logger logger = LoggerFactory.getLogger(GraphQLService.class);

    @Autowired
    private Person person;

    private GraphQLObjectType buildPersonGraphQLObject() {
        return GraphQLObjectType.newObject()
                .name("person")
                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLInt)
                        .staticValue(person.getId()).build())
                .field(newFieldDefinition()
                        .name("name")
                        .type(GraphQLString)
                        .staticValue(person.getName())
                        .build())
                .field(newFieldDefinition()
                        .name("surname")
                        .type(GraphQLString)
                        .staticValue(person.getSurname())
                        .build())
                .build();
    }

    private GraphQLSchema buildPersonGraphQLSchema() {
        return newSchema()
                .query(buildPersonGraphQLObject())
            .build();
    }

    @Bean("test")
    public GraphQL getGraphQL() {
        logger.info("Creating GraphQL bean.");
        return new GraphQLWrapper(buildPersonGraphQLSchema());
    }
}
