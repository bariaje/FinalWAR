/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamerunner;

import java.util.Collections;

/**
 *
 * @author shena
 */
class Deck extends GroupOfCards {
    public Deck() {
        // Initialize the deck with all cards
        for (Rank rank : Rank.values()) {
            if (rank != Rank.JOKER) {
                addCard(new PlayingCard(rank));
            }
        }
        // Shuffle the deck
        Collections.shuffle(super.cards);
    }
}