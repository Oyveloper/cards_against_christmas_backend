package com.monsen.cards_against_christmas_backend.game;

import java.util.HashMap;
import java.util.Map;

public class Round {

    private final Player judge;
    private final BlackCard blackCard;
    private Player winner;
    private Map<Player, Card> playedCards = new HashMap<>();

    public Round(Player judge, BlackCard blackCard) {
        this.judge = judge;
        this.blackCard = blackCard;
    }

    public Player getWinner() {
        return winner;
    }

    public void pickWinnerCard(Card card) {
        this.winner = this.getPlayerFromCard(card);
    }

    public Player getPlayerFromCard(Card card) {
        for (Player player : this.playedCards.keySet()) {
            if (this.playedCards.get(player).equals(card)) {
                return player;
            }
        }

        return null;
    }

    public Player getJudge() {
        return judge;
    }

    public com.monsen.cards_against_christmas_backend.game.BlackCard getBlackCard() {
        return blackCard;
    }

    public Map<Player, Card> getPlayedCards() {
        return playedCards;
    }

    public void playCard(Player player, Card card) throws IllegalStateException {
        if (!this.playedCards.containsKey(player)) {
            this.playedCards.put(player, card);
        } else {
            throw new IllegalStateException("This player has already played a card this round");
        }
    }
}
