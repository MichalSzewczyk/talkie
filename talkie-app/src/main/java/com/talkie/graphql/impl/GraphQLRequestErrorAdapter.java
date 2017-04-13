package com.talkie.graphql.impl;

import com.talkie.interfaces.RequestError;
import graphql.GraphQLError;

public class GraphQLRequestErrorAdapter implements RequestError {
    private final GraphQLError graphQLError;

    public GraphQLRequestErrorAdapter(GraphQLError graphQLError) {
        this.graphQLError = graphQLError;
    }

    @Override
    public String getMessage() {
        return graphQLError.getMessage();
    }

    @Override
    public String toString() {
        return graphQLError.getMessage();
    }
}
