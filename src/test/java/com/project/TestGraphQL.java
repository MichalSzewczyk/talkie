package com.project;

import com.project.graphql.test.GraphQLService;
import com.project.graphql.test.Person;
import graphql.GraphQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GraphQLService.class, Person.class})
public class TestGraphQL {
    @Autowired
    private GraphQLService graphQLService;
    @Test
    public void testGraphQLService(){
        //TODO: getData throws null
        System.out.println(new GraphQL(graphQLService.schema).execute("{id}").getData());
    }
}
