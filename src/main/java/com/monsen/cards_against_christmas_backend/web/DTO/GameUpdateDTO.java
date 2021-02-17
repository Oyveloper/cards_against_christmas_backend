package com.monsen.cards_against_christmas_backend.web.DTO;

import com.monsen.cards_against_christmas_backend.game.CACGame;
import com.monsen.cards_against_christmas_backend.game.Player;
import com.monsen.cards_against_christmas_backend.game.Round;

import java.util.ArrayList;
import java.util.List;

public class GameUpdateDTO {
    private String gameId;
    private List<Player> players;
    private List<Round> rounds;
    private Round currentRound;
    private String type;

    public GameUpdateDTO(CACGame game, String type) {
        this.gameId = game.getId();
        this.players = game.getPlayers();
        this.rounds = new ArrayList<>(game.getRounds());
        this.currentRound = game.getCurrentRound();
        this.type = type;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

