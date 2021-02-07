package com.monsen.cards_against_christmas_backend.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BLACK_CARDS")
public class BlackCard {

    @Id
    @Column(name = "black_card_id")
    private String id;

    @Column(name = "text")
    private String text;

    @Column(name = "number_of_blanks")
    private int numberOfBlanks;

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
