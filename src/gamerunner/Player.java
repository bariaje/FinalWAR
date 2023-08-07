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
import java.util.Collection;
import java.util.List;

public interface Player {
    String getName();
    boolean hasCards();
    Card flipCard();
    Card getCardWithRank(Rank rank);
    void addCard(Card card);
    int getHandSize(); // Add this method
    Card getCardAtIndex(int index); // Add this method
    void addCards(Collection<Card> cards);
}
