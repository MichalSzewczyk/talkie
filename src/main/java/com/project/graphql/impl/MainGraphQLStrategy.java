package com.project.graphql.impl;


import com.project.database.interfaces.AccessService;
import com.project.database.model.User;
import com.project.graphql.interfaces.GraphQLStrategy;
import com.project.utils.GraphQLUtils;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import graphql.schema.GraphQLList;

@Component
public final class MainGraphQLStrategy implements GraphQLStrategy {
    private final AccessService databaseAccessFacade;
    private final GraphQLUtils graphQLUtils;
    private final List<GraphQLArgument> userArguments;
    private final GraphQLObjectType personType;

    @Autowired
    public MainGraphQLStrategy(GraphQLUtils graphQLUtils, AccessService databaseAccessFacade) {
        this.graphQLUtils = graphQLUtils;
        this.databaseAccessFacade = databaseAccessFacade;
        this.userArguments = graphQLUtils.getArgumentList(GraphQLString, "login", "password", "success");
        this.personType = getUserType();

    }

    @Override
    public String getQueryName() {
        return "Query";
    }

    @Override
    public List<GraphQLFieldDefinition> getFields() {
        return Arrays.asList(getLoginField(), getRegisterField());
    }

    private GraphQLFieldDefinition getRegisterField() {
        return newFieldDefinition()
                .name("register")
                .type(personType)
                .argument(userArguments)
                .dataFetcher(fetchingEnvironment -> databaseAccessFacade
                        .registerUser(new User(fetchingEnvironment.getArgument("login"), fetchingEnvironment.getArgument("password")))).build();

    }

    private GraphQLFieldDefinition getLoginField() {
        return newFieldDefinition()
                .name("login")
                .type(personType)
                .argument(userArguments)
                .dataFetcher(fetchingEnvironment -> databaseAccessFacade
                        .loginUser(new User(fetchingEnvironment.getArgument("login"), fetchingEnvironment.getArgument("password")))).build();
    }


    private GraphQLObjectType getUserType() {
        return newObject()
                .name("UserQuery")
                .description("personal data for logging")
                .field(graphQLUtils.getFieldDefinition("login", "Login of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("password", "Password of the user.", GraphQLString))
                .field(newFieldDefinition()
                        .name("friends")
                        .description("List of user Friends")
                        .type(new GraphQLList(getFriendsType()))
                        .build())
                .field(graphQLUtils.getFieldDefinition("success", "True if user is in database.", GraphQLString))
                .build();
    }

    private GraphQLObjectType getFriendsType() {
        return newObject()
                .name("Friend")
                .description("Friend of user")
                .field(graphQLUtils.getFieldDefinition("id", "Unique id of user", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("name", "Name of user friend", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("lastName", "Last name of user friend", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("description", "User defined sentence", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("avatar", "User Avatar Link", GraphQLString))
                .build();

    }
}
