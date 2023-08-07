/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamerunner;

/**
 *
 * @author shena
 */

   import java.util.List;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HumanPlayer implements Player {
    private String name;
    private List<Card> hand;

    public HumanPlayer(String name) {
        this.name = name;
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
        if (!hasCards()) {
            return null;
        }
        return hand.remove(0);
    }

    public Card getCardWithRank(Rank rank) {
        for (Card card : hand) {
            if (card.getRank() == rank) {
                return card;
            }
        }
        return null;
    }
    @Override
    public int getHandSize() {
        return hand.size();
    }

    @Override
    public Card getCardAtIndex(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.get(index);
        }
        return null;
    }
    @Override
    public void addCards(Collection<Card> cards) {
        hand.addAll(cards);
    }
}
