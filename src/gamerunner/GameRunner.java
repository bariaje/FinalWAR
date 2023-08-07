/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gamerunner;

import java.util.Scanner;

/**
 *
 * @author shena
 */
// Main.java

// GameRunner.java





public class GameRunner {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome To WarGame.\nInstructions:\n");
        System.out.print("Enter Player 1 name: ");
        String playerName1 = scanner.nextLine();

       
        String playerName2 = "Computer";

        WarGame warGame = new WarGame(playerName1, playerName2);
       
                warGame.playGame();

        scanner.close();
}
    

   private static Rank getValidCardRank(Scanner scanner) {
    while (true) {
        System.out.print("Enter card rank (JOKER, ACE, KING, QUEEN, etc.): ");
        String rankStr = scanner.nextLine().toUpperCase().trim();
        try {
            Rank rank = Rank.valueOf(rankStr);
            return rank;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid card rank. Please enter a valid rank.");
        }
    }
}





}
