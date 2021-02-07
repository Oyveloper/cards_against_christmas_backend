package com.monsen.cards_against_christmas_backend.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "WHITE_CARDS")
public class WhiteCard {

    @Column(name = "text")
    private String text;

    @Id
    @Column(name = "white_card_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

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
}
