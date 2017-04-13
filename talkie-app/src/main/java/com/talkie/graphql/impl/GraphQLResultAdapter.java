package com.talkie.graphql.impl;

import com.talkie.interfaces.RequestError;
import com.talkie.interfaces.WebExecutionResult;
import graphql.ExecutionResult;

import java.util.List;
import java.util.stream.Collectors;

public class GraphQLResultAdapter implements WebExecutionResult {
    private final ExecutionResult executionResult;

    public GraphQLResultAdapter(ExecutionResult executionResult) {
        this.executionResult = executionResult;
    }

    @Override
    public List<RequestError> getErrors() {
        return executionResult.getErrors().stream().map(GraphQLRequestErrorAdapter::new).collect(Collectors.toList());
    }

    @Override
    public Object getData() {
        return executionResult.getData();
    }
}
