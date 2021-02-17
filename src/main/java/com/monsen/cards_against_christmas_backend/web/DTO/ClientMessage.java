package com.monsen.cards_against_christmas_backend.web.DTO;

public class ClientMessage {
    private String playerName;
    private String cardId;

    public ClientMessage() {

    }

    public ClientMessage(String playerName, String cardId) {
        this.playerName = playerName;
        this.cardId = cardId;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
