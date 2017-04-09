package com.project.graphql.impl;


import com.project.database.interfaces.AccessService;
import com.project.graphql.interfaces.GraphQLStrategy;
import com.project.utils.GraphQLUtils;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

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
        this.userArguments = graphQLUtils.getArgumentList(GraphQLString, "id", "login", "name", "lastName", "password", "avatar", "friends", "success", "message");
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
                        .registerUser(
                                fetchingEnvironment.getArgument("login"),
                                fetchingEnvironment.getArgument("name"),
                                fetchingEnvironment.getArgument("lastName"),
                                fetchingEnvironment.getArgument("password"),
                                fetchingEnvironment.getArgument("avatar"),
                                true
                        )).build();

    }

    private GraphQLFieldDefinition getLoginField() {
        return newFieldDefinition()
                .name("login")
                .type(personType)
                .argument(userArguments)
                .dataFetcher(fetchingEnvironment -> databaseAccessFacade
                        .loginUser(
                                fetchingEnvironment.getArgument("login"),
                                fetchingEnvironment.getArgument("password")
                        )).build();
    }

    private GraphQLObjectType getFriendType() {
        return newObject()
                .name("Friend")
                .description("Personal data of friend")
                .field(graphQLUtils.getFieldDefinition("id", "Unique user ID", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("name", "Name of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("lastName", "Last name of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("avatar", "Link to avatar.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("description", "Description of friend.", GraphQLString))
                .build();
    }

    private GraphQLObjectType getUserType() {
        return newObject()
                .name("User")
                .description("personal data for logging")
                .field(graphQLUtils.getFieldDefinition("id", "Id of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("login", "Login of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("name", "Name of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("lastName", "Last name of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("password", "Password of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("avatar", "Avatar of the user.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("error", "True if operation succeed.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("message", "Information about error.", GraphQLString))
                .field(graphQLUtils.getFieldDefinition("online", "Online status of the user.", GraphQLBoolean))
                .field(graphQLUtils.getFieldDefinition("friends", "List of user's friends", new GraphQLList(getFriendType())))
                .build();
    }
}
