package com.monsen.cards_against_christmas_backend.data.entity;

public class BlackCard {

    private String id;
    private String text;
    private int numberOfBlanks;

    public BlackCard() {
    }

    public BlackCard(String id, String text, int numberOfBlanks) {
        this.id = id;
        this.text = text;
        this.numberOfBlanks = numberOfBlanks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumberOfBlanks() {
        return numberOfBlanks;
    }

    public void setNumberOfBlanks(int numberOfBlanks) {
        this.numberOfBlanks = numberOfBlanks;
    }
}
