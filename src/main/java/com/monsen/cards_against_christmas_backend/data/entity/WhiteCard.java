package com.monsen.cards_against_christmas_backend.data.entity;

import java.util.Objects;

public class WhiteCard {

    private String text;
    private String id;

    public WhiteCard() {
    }


    public WhiteCard(String id, String text) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WhiteCard whiteCard = (WhiteCard) o;
        return Objects.equals(text, whiteCard.text) && Objects.equals(id, whiteCard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, id);
    }
}
