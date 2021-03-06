package com.monsen.cards_against_christmas_backend.game;

import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;
import com.monsen.cards_against_christmas_backend.util.GameIdGenerator;

import java.util.HashMap;
import java.util.Map;

public class CACGameManager {
    private Map<String, CACGame> games = new HashMap<>();
    private GameIdGenerator idGenerator = new GameIdGenerator();
    private HashMap<String, WhiteCard> knownCards = new HashMap<>();


    public CACGame getGame(String id) {
        if (!this.games.containsKey(id)) {
            throw new IllegalStateException("Game" + " with id " + id + " does not exist");
        }
        return this.games.get(id);
    }

    public boolean doesGameExist(String id) {
        return this.games.containsKey(id);
    }

    public CACGame createGame() throws IllegalStateException {
        String id = idGenerator.generateId();
        while (this.games.containsKey(id)) {
            id = idGenerator.generateId();
        }
        CACGame game = new CACGame(id);
        this.games.put(id, game);
        return game;
    }

    public void registerCard(WhiteCard card) {
        if (!this.knownCards.containsKey(card.getId())) {
            this.knownCards.put(card.getId(), card);
        }
    }

    public WhiteCard getCardById(String id) {
        return this.knownCards.get(id);
    }
}

