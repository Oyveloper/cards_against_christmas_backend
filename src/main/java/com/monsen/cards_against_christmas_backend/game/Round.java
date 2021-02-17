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
    private WhiteCard winnerCard;

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
        this.winner.increaseScore(100);
        this.setWinnerCard(card);
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

    public Map<String, WhiteCard> getPlayedCards() {
        Map<String, WhiteCard> result = new HashMap<>();
        for (Player player : playedCards.keySet()) {
            result.put(player.getName(), playedCards.get(player));
        }

        return result;
    }

    public void setPlayedCards(Map<Player, WhiteCard> playedCards) {
        this.playedCards = playedCards;
    }

    public WhiteCard getWinnerCard() {
        return winnerCard;
    }

    public void setWinnerCard(WhiteCard winnerCard) {
        this.winnerCard = winnerCard;
    }

    public void playCard(Player player, WhiteCard card) throws IllegalStateException {
        if (!this.playedCards.containsKey(player)) {
            this.playedCards.put(player, card);
        } else {
            throw new IllegalStateException("This player has already played a card this round");
        }
    }
}
