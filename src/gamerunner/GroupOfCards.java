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
import java.util.ArrayList;
import java.util.List;

public class GroupOfCards {
    private List<Card> cards;

    public GroupOfCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public int size() {
        return cards.size();
    }
}
