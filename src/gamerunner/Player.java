/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamerunner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shena
 */
// Player.java


// Abstract class representing a player



// Abstract class representing a player

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Card> cards;
    private String name;
    List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.hand = new ArrayList<>();
    }
    

    public String getName() {
        return name;
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card flipCard() {
        if (hand.isEmpty()) {
            return null;
        }
        return hand.remove(0);
    }

    // Add a protected getter for the cards list
    protected List<Card> getCards() {
        return cards;
    }

    // Method to get a specific card from the player's hand based on rank
    public Card getCardWithRank(Rank rank) {
        for (Card card : hand) {
            if (card.getRank() == rank) {
                return card;
            }
        }
        return null;
    }
}