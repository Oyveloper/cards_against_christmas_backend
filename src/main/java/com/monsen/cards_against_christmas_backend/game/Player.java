package com.monsen.cards_against_christmas_backend.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    private String name;
    private int score = 0;
    private boolean isHost;

    public Player(String name, boolean isHost) {
        this.name = name;
        this.isHost = isHost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int amount) {
        this.score += amount;
    }

    @JsonProperty("isHost")
    public boolean isHost() {
        return isHost;
    }

    @JsonProperty("isHost")
    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }
}
