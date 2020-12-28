package com.monsen.cards_against_christmas_backend.game;

import java.util.Objects;

public class Card {


    private String text;
    private String id;

    public Card(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(text, card.text) &&
            Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, id);
    }
}
