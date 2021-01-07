package com.monsen.cards_against_christmas_backend.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CACGameTest {

    private CACGame getGame() {
        return new CACGame("id");
    }

    @Test
    void drawCard() {
        CACGame game = getGame();

        Card card = game.drawCard();
        Card card2 = game.drawCard();

        // Checking that the cards are not equal
        Assertions.assertNotEquals(card, card2);

    }

    @Test
    void newRound() {
        CACGame game = getGame();
        game.addPlayer(new Player("p1"));
        game.addPlayer(new Player("p2"));

        assertEquals(game.getRounds().size(), 0);

        game.newRound();

        assertEquals(game.getRounds().size(), 1);
        Player judge = game.getCurrentRound().getJudge();

        game.newRound();
        assertNotEquals(judge, game.getCurrentRound().getJudge());
        assertEquals(game.getRounds().size(), 2);

    }

    @Test
    void drawBlackCard() {
    }
}