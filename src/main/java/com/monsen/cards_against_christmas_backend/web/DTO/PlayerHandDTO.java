package com.monsen.cards_against_christmas_backend.web.DTO;

import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;

import java.util.List;

public class PlayerHandDTO {
    private List<WhiteCard> cards;

    public PlayerHandDTO(List<WhiteCard> cards) {
        this.cards = cards;
    }

    public List<WhiteCard> getCards() {
        return cards;
    }

    public void setCards(List<WhiteCard> cards) {
        this.cards = cards;
    }
}
