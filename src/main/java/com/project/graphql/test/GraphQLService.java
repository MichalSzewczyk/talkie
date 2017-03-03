package com.project.graphql.test;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLSchema.newSchema;

@Service
public class GraphQLService {
    @Autowired
    private Person person;

    public GraphQLObjectType personGraphQLObject = GraphQLObjectType.newObject()
            .name("person")
            .field(newFieldDefinition()
                    .name("id")
                    .type(GraphQLInt)
                    .staticValue(0).build())
            .field(newFieldDefinition()
                    .name("name")
                    .type(GraphQLString)
                    .staticValue("Foo")
                    .build())
            .field(newFieldDefinition()
                    .name("surname")
                    .type(GraphQLString)
                    .staticValue("Bar")
                    .build())
            .build();

    public GraphQLObjectType personQuery = GraphQLObjectType.newObject()
            .name("personQuery")
            .field(newFieldDefinition()
                    .name("id")
                    .type(personGraphQLObject).build())
            .build();

    public GraphQLObjectType personEditor = GraphQLObjectType.newObject()
            .name("personEditor")
            .field(newFieldDefinition()
                    .name("changeId")
                    .type(personGraphQLObject)
                    .argument(newArgument()
                            .name("newNumber")
                            .type(GraphQLInt).build())
                    .dataFetcher(new DataFetcher() {
                        @Override
                        public Object get(DataFetchingEnvironment environment) {
                            Integer newId = environment.getArgument("newId");
                            person.setId(newId);
                            return person;
                        }
                    }).build())
            .field(newFieldDefinition()
                    .name("failToChangeTheNumber")
                    .type(personGraphQLObject)
                    .argument(newArgument()
                            .name("newNumber")
                            .type(GraphQLInt).build())
                    .dataFetcher((r) -> person.getName()).build()).build();

    public GraphQLSchema schema = newSchema()
            .query(personQuery)
            .mutation(personEditor)
            .build();



}
