package com.monsen.cards_against_christmas_backend.web.service;

import com.monsen.cards_against_christmas_backend.game.CACGameManager;
import org.springframework.beans.factory.annotation.Autowired;

public class GameService {
    private final CACGameManager manager;

    @Autowired
    public GameService(CACGameManager manager) {
        this.manager = manager;
    }

    /**
     * Check wether a game with the given id exists already
     * @param gameId - the id you want to check
     * @return True if a game with 'gameId' exists
     */
    public boolean gameExists(String gameId) {
        return this.manager.doesGameExist(gameId);
    }

    /**
     * Creates a new game managed by the GameManager
     * @param gameId the id for the game you wish to create
     */
    public void createGame(String gameId) {
        if (this.manager.doesGameExist(gameId)) {
            throw new IllegalStateException("The game with this id already exists");
        }
        this.manager.addGame(gameId);
    }


}
