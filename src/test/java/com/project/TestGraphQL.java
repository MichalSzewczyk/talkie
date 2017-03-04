package com.project;

import com.project.graphql.test.GraphQLService;
import com.project.graphql.test.Person;
import graphql.GraphQL;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GraphQLService.class, Person.class})
public class TestGraphQL {
    @Autowired()
    private GraphQL graphQLService;
    @Test
    public void testGraphQLService(){
        Assert.assertEquals("{id=0, name=SampleName, surname=SampleSurname}", graphQLService.execute("{id, name, surname}").getData().toString());
        Assert.assertEquals("{id=0, name=SampleName, surname=SampleSurname}", graphQLService.execute("{id, name, surname}").getData().toString());
    }
}
