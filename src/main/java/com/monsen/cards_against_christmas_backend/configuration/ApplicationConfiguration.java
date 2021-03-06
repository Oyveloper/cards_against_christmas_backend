package com.monsen.cards_against_christmas_backend.configuration;

import com.monsen.cards_against_christmas_backend.game.CACGameManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @Scope("singleton")
    public CACGameManager CACGAmeManagerSingleton() {
        return new CACGameManager();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

}

