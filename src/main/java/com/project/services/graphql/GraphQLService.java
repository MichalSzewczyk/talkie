package com.project.services.graphql;

import com.project.graphql.impl.GraphQLResultAdapter;
import com.project.interfaces.WebExecutionResult;
import com.project.interfaces.WebService;
import graphql.GraphQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class GraphQLService implements WebService {
    private final GraphQL graphQL;
    private final Logger logger = LoggerFactory.getLogger(GraphQLService.class);
    private static final String CREATION_MESSAGE = "Creating bean: %s";
    @Autowired
    public GraphQLService(GraphQL graphQL) {
        logger.info(String.format(CREATION_MESSAGE, GraphQLService.class.getName()));
        this.graphQL = graphQL;
    }

    public WebExecutionResult execute(String requestBody){
        return new GraphQLResultAdapter(graphQL.execute(requestBody));
    }
}
