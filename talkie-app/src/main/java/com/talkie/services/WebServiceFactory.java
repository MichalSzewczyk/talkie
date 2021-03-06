package com.talkie.services;

import com.talkie.interfaces.WebService;
import com.talkie.services.graphql.GraphQLService;
import com.talkie.services.sockets.WebServiceOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

@Configuration
public class WebServiceFactory {
    @Value("${webservice}")
    private String webService;
    @Lazy
    @Autowired
    private GraphQLService graphQLService;
    @Lazy
    @Autowired
    private WebServiceOther webServiceOther;

    @Primary
    @Bean
    WebService getWebService() {
        switch (webService) {
            case "graphQlService":
                return graphQLService;
            case "webServiceOther":
                return webServiceOther;
            default:
                throw new UnsupportedOperationException("Not supported web service.");
        }
    }
}
