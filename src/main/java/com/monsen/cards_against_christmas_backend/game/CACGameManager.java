package com.monsen.cards_against_christmas_backend.game;

import java.util.HashMap;
import java.util.Map;

public class CACGameManager {
    private Map<String, CACGame> games = new HashMap<>();


    public CACGame getGame(String id) {
        if (!this.games.containsKey(id)) {
            throw new IllegalStateException("Game" + " with id " + id + " does not exist");
        }
        return this.games.get(id);
    }

    public boolean doesGameExist(String id) {
        return this.games.containsKey(id);
    }

    public CACGame addGame(String id) throws IllegalStateException {
        if (this.games.containsKey(id)) {
            throw new IllegalStateException("The game already exists");
        }
        CACGame game = new CACGame(id);
        this.games.put(id, game);
        return game;
    }
}

