package com.talkie.graphql.test;

import graphql.GraphQL;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphQLWrapper extends GraphQL {
    private final Logger logger = LoggerFactory.getLogger(GraphQLWrapper.class);

    public GraphQLWrapper(GraphQLSchema graphQLSchema) {
        super(graphQLSchema);
        logger.info("Creating GraphQL object with parameters: " + GraphQLSchema.class.getName());
    }

    public GraphQLWrapper(GraphQLSchema graphQLSchema, ExecutionStrategy executionStrategy) {
        super(graphQLSchema, executionStrategy);
        logger.info("Creating GraphQL object with parameters: " + GraphQLSchema.class.getName() + ", " + ExecutionStrategy.class.getName());
    }

}
