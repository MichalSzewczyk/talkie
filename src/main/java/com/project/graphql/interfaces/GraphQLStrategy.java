package com.project.graphql.interfaces;

import graphql.schema.GraphQLFieldDefinition;

import java.util.List;

public interface GraphQLStrategy {
    String getQueryName();
    List<GraphQLFieldDefinition> getFields();
}
