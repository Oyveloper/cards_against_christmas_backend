package com.monsen.cards_against_christmas_backend.game;

import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CACGameTest {

    private CACGame getGame() {
        return new CACGame("id");
    }

    @Test
    void drawCard() {
        CACGame game = getGame();

        WhiteCard card = game.drawCard();
        WhiteCard card2 = game.drawCard();

        // Checking that the cards are not equal
        Assertions.assertNotEquals(card, card2);

    }

    @Test
    void newRound() {
        CACGame game = getGame();
        game.addPlayer(new Player("p1", false));
        game.addPlayer(new Player("p2", false));

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