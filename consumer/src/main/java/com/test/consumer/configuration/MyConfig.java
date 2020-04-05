package com.test.consumer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.consumer.services.connector.ProviderConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Value("${provider.rooturi}")
    String providerRootUri;


    @Bean
    public ProviderConnector getProviderConnector(@Autowired ObjectMapper objectMapper, @Autowired RestTemplateBuilder restTemplateBuilder) {
        return new ProviderConnector(providerRootUri, restTemplateBuilder, objectMapper);
    }

}
