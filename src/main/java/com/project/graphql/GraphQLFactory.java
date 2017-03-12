package com.project.graphql;

import com.project.graphql.interfaces.GraphQLStrategy;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import static graphql.schema.GraphQLObjectType.newObject;

@Service
public final class GraphQLFactory {
    private final Logger logger = LoggerFactory.getLogger(GraphQLFactory.class);

    private final GraphQLStrategy graphQLStrategy;
    private static final String GRAPH_QL_CREATION_INFO = "Creating GraphQL bean for strategy: %s";

    @Autowired
    public GraphQLFactory(GraphQLStrategy graphQLStrategy) {
        this.graphQLStrategy = graphQLStrategy;
    }

    @Bean
    public GraphQL getGraphQL() {
        logger.info(String.format(GRAPH_QL_CREATION_INFO, graphQLStrategy.getClass().getName()));

        GraphQLObjectType query = newObject()
                .name(graphQLStrategy.getQueryName())
                .fields(graphQLStrategy.getFields()).build();

        GraphQLSchema loginSchema = GraphQLSchema.newSchema()
                .query(query)
                .build();

        return new GraphQL(loginSchema);
    }


}
