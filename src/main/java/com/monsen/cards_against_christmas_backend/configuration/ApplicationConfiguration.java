package com.monsen.cards_against_christmas_backend.configuration;

import com.monsen.cards_against_christmas_backend.game.CACGameManager;
import com.monsen.cards_against_christmas_backend.web.service.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @Scope("singleton")
    public CACGameManager CACGAmeManagerSingleton() {
        return new CACGameManager();
    }

}

