package com.project;

import com.project.database.impl.DatabaseAccessFacade;
import com.project.database.model.User;
import com.project.database.repositories.UserRepository;
import com.project.graphql.GraphQLFactory;
import com.project.graphql.impl.MainGraphQLStrategy;
import com.project.utils.GraphQLUtils;
import graphql.GraphQL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GraphQLTest {
    private static final String QUERY =
            "            query{" +
                    "              %s(login:\"%s\", password:\"%s\"){" +
                    "                success" +
                    "              }" +
                    "            }";
    private static final String LOGIN = "user1";
    private static final String PASSWORD = "foo";

    private GraphQL graphQLService;
    private User tmpUser;

    @Before
    public void setupTestingEnvironment() {
        UserRepository userRepository = mock(UserRepository.class);
        tmpUser = new User(LOGIN, PASSWORD);

        when(userRepository.findOneByLogin(LOGIN)).thenReturn(tmpUser);
        when(userRepository.save(tmpUser)).thenReturn(tmpUser);

        this.graphQLService = new GraphQLFactory(new MainGraphQLStrategy(new GraphQLUtils(), new DatabaseAccessFacade(userRepository))).getGraphQL();
    }

    @Test
    public void testIfRegisterQueryIsExecutedProperly() {
        Assert.assertEquals("false", ((Map) ((Map) graphQLService.execute(String.format(QUERY, "register", LOGIN, PASSWORD)).getData()).get("register")).get("success"));
    }

    @Test
    public void testIfLoginQueryIsExecutedProperly() {
        Assert.assertEquals("true", ((Map) ((Map) graphQLService.execute(String.format(QUERY, "login", LOGIN, PASSWORD)).getData()).get("login")).get("success"));
    }
}
