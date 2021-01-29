package com.monsen.cards_against_christmas_backend.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class CACGame {

    private List<Player> players = new ArrayList<>();
    private List<Card> deck = new ArrayList<>();
    private List<BlackCard> blackCardDeck = new ArrayList<>();
    private Stack<Round> rounds = new Stack<>();
    private String id;

    public CACGame(String id) {
        initializeDeck();
        this.id = id;
    }

    private void initializeDeck() {
        // Magic for creating the initial deck here

        for (int i = 0; i < 100; i++) {
            this.deck.add(new Card("Card " + i, String.valueOf(i)));
            this.blackCardDeck.add(new BlackCard("Card ___" + i, String.valueOf(i), 1));
        }
    }

    public void addPlayer(Player player) throws IllegalStateException {
        if (this.players.stream().anyMatch(p -> p.getName().equals((player.getName())))) {
            throw new IllegalStateException("The player is already added");
        }
        this.players.add(player);
    }

    public Card drawCard() {
        int index = (new Random()).nextInt(this.deck.size());

        Card card = this.deck.get(index);
        this.deck.remove(index);
        return card;
    }

    public void newRound() {
        Player judge;
        if (this.rounds.size() == 0) {
            judge = this.players.get(0);
        } else {
            judge = this.players.get((
                                         this.players.indexOf(
                                             this.rounds.peek()
                                                 .getJudge()) + 1) % this.players.size()
            );
        }
        Round round = new Round(judge, this.drawBlackCard());
        this.rounds.add(round);
    }

    public Collection<Round> getRounds() {
        return new ArrayList<>(this.rounds);
    }

    public Round getCurrentRound() {
        return this.rounds.size() > 0
                ? this.rounds.lastElement()
                : null;
    }

    public BlackCard drawBlackCard() {
        int index = (new Random()).nextInt(this.deck.size());

        BlackCard card = this.blackCardDeck.get(index);
        this.blackCardDeck.remove(index);
        return card;
    }

    public List<Card> dealHand(int handSize) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < handSize; i++) {
            hand.add(this.drawCard());
        }

        return hand;
    }

    public List<Card> dealHand() {
        return this.dealHand(7);
    }

    public String getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }


}
