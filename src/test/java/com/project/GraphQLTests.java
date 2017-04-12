package com.project;

import com.project.database.impl.DatabaseAccessFacade;
import com.project.database.model.Message;
import com.project.database.model.User;
import com.project.database.repositories.MessageRepository;
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

public class GraphQLTests {
    private static final String QUERY =
            "            query{" +
                    "              %s(login:\"%s\", password:\"%s\"){" +
                    "                error" +
                    "              }" +
                    "            }";
    private static final String LOGIN = "user1";
    private static final String PASSWORD = "foo";

    private GraphQL graphQLService;
    private User tmpUser;
    private Message tmpMessage;

    @Before
    public void setupTestingEnvironment() {
        UserRepository userRepository = mock(UserRepository.class);
        MessageRepository messageRepository = mock(MessageRepository.class);
        tmpUser = new User(LOGIN, PASSWORD);
        tmpMessage = new Message();

        when(userRepository.findOneByLogin(LOGIN)).thenReturn(tmpUser);
        when(userRepository.save(tmpUser)).thenReturn(tmpUser);
        when(messageRepository.save(tmpMessage)).thenReturn(tmpMessage);

        this.graphQLService = new GraphQLFactory(new MainGraphQLStrategy(new GraphQLUtils(), new DatabaseAccessFacade(userRepository, messageRepository))).getGraphQL();
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
