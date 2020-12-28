package com.monsen.cards_against_christmas_backend.game;

public class BlackCard extends Card{

    private int numberMissing;

    public BlackCard(String text, String id) {
        super(text, id);
        this.numberMissing = 1;
    }

    public BlackCard(String text, String id, int numberMissing) {
        super(text, id);
        this.numberMissing = numberMissing;
    }
}
