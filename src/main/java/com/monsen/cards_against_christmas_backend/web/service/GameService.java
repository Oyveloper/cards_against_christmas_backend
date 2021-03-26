package com.monsen.cards_against_christmas_backend.web.service;

import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;
import com.monsen.cards_against_christmas_backend.game.CACGame;
import com.monsen.cards_against_christmas_backend.game.CACGameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final CACGameManager manager;

    @Autowired
    public GameService(CACGameManager manager) {
        this.manager = manager;
    }

    /**
     * Check wether a game with the given id exists already
     *
     * @param gameId - the id you want to check
     * @return True if a game with 'gameId' exists
     */
    public boolean gameExists(String gameId) {
        return this.manager.doesGameExist(gameId);
    }

    /**
     * Creates a new game managed by the GameManager
     *
     * @return - the newly created game
     */
    public CACGame createGame() {
        return this.manager.createGame();
    }


    public CACGame getGame(String gameId) throws IllegalStateException {
        if (!this.manager.doesGameExist(gameId)) {
            throw new IllegalStateException("No such game exists");
        }
        return this.manager.getGame(gameId);
    }

    public List<WhiteCard> drawHand(String gameId) throws IllegalStateException {
        if (!this.manager.doesGameExist(gameId)) {
            throw new IllegalStateException("No such game exists");
        }
        return this.manager.getGame(gameId).dealHand();

    }

    public WhiteCard drawCard(String gameId) throws IllegalStateException {
        if (!this.manager.doesGameExist(gameId)) {
            throw new IllegalStateException("No such game exists");
        }
        return this.manager.getGame(gameId).drawCard();

    }


}
