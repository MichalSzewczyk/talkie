package com.project.utils;

import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

@Component
public final class GraphQLUtils {
    public GraphQLFieldDefinition getFieldDefinition(String name, String description, GraphQLOutputType scalarType) {
        return newFieldDefinition()
                .name(name)
                .description(description)
                .type(scalarType).build();
    }

    public List<GraphQLArgument> getArgumentList(GraphQLScalarType scalarType, String... argNames) {
        return Stream.of(argNames).map(argName -> newArgument()
                .name(argName)
                .type(scalarType)
                .build()).collect(Collectors.toList());
    }
}
