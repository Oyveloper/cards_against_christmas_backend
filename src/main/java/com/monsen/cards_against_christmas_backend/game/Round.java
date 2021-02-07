package com.monsen.cards_against_christmas_backend.game;

import com.monsen.cards_against_christmas_backend.data.entity.BlackCard;
import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;

import java.util.HashMap;
import java.util.Map;

public class Round {

    private final Player judge;
    private final BlackCard blackCard;
    private Player winner;
    private Map<Player, WhiteCard> playedCards = new HashMap<>();

    public Round(Player judge, BlackCard blackCard) {
        this.judge = judge;
        this.blackCard = blackCard;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void pickWinnerCard(WhiteCard card) {
        this.winner = this.getPlayerFromCard(card);
    }

    public Player getPlayerFromCard(WhiteCard card) {
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

    public BlackCard getBlackCard() {
        return blackCard;
    }

    public Map<Player, WhiteCard> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCards(Map<Player, WhiteCard> playedCards) {
        this.playedCards = playedCards;
    }

    public void playCard(Player player, WhiteCard card) throws IllegalStateException {
        if (!this.playedCards.containsKey(player)) {
            this.playedCards.put(player, card);
        } else {
            throw new IllegalStateException("This player has already played a card this round");
        }
    }
}
