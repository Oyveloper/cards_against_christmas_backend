package com.monsen.cards_against_christmas_backend.game;

import com.monsen.cards_against_christmas_backend.data.entity.BlackCard;
import com.monsen.cards_against_christmas_backend.data.entity.WhiteCard;

import java.util.*;

public class CACGame {

    private List<Player> players = new ArrayList<>();
    private List<WhiteCard> deck = new ArrayList<>();
    private List<BlackCard> blackCardDeck = new ArrayList<>();
    private Stack<Round> rounds = new Stack<>();
    private String id;

    public CACGame(String id) {
        this.id = id;
    }

    public void addPlayer(Player player) throws IllegalStateException {
        if (this.players.stream().anyMatch(p -> p.getName().equals((player.getName())))) {
            throw new IllegalStateException("The player is already added");
        }
        this.players.add(player);
    }

    public WhiteCard drawCard() {
        int index = (new Random()).nextInt(this.deck.size());

        WhiteCard card = this.deck.get(index);
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

    public List<WhiteCard> dealHand(int handSize) {
        List<WhiteCard> hand = new ArrayList<>();
        for (int i = 0; i < handSize; i++) {
            hand.add(this.drawCard());
        }

        return hand;
    }

    public List<WhiteCard> dealHand() {
        return this.dealHand(4);
    }

    public String getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setWhiteCardDeck(Collection<WhiteCard> deck) {
        this.deck = new ArrayList<>(deck);
    }

    public void setBlackCardDeck(Collection<BlackCard> deck) {
        this.blackCardDeck = new ArrayList<>(deck);
    }

    public void addWhiteCardToDeck(WhiteCard card) {
        this.deck.add(card);
    }


    /**
     * Returns the player that has the given name, if any
     *
     * @param name - the name of the player you want
     * @return The player with the given name
     * @Throws NoSuchElementException
     */
    public Player getPlayerByName(String name) throws NoSuchElementException {
        return this.players.stream().filter(p -> p.getName().equals(name)).findFirst().orElseThrow();

    }


}
