/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public Card flipCard() {
        if (hasCards()) {
            Random random = new Random();
            int size = getCards().size();
            if (size > 0) {
                int index = random.nextInt(size);
                return getCards().get(index);
            }
        }
        return null;
    }
}