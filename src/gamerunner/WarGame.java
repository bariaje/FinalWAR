/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamerunner;


/**
 *
 * @author shena
 */
// WarGame.java




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WarGame {
    private String player1Name;
    private String player2Name;
    private List<Card> deck;
    private List<Card> player1Deck;
    private List<Card> player2Deck;

    public WarGame(String player1Name) {
        this.player1Name = player1Name;
        this.player2Name = "Computer";
        this.deck = generateDeck();
        distributeCards();
    }

    private List<Card> generateDeck() {
        List<Card> newDeck = new ArrayList<>();
        String[] ranks = {"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
        String[] suits = {"CLUBS", "DIAMONDS", "HEARTS", "SPADES"};
        for (String suit : suits) {
            for (String rank : ranks) {
                newDeck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(newDeck);
        return newDeck;
    }

    private void distributeCards() {
        player1Deck = new ArrayList<>(deck.subList(0, 26));
        player2Deck = new ArrayList<>(deck.subList(26, 52));
    }

    private Card drawCard(List<Card> deck) {
        return deck.remove(0);
    }

    
    private void playRound() {
    Card player1Card = drawCard(player1Deck);
    Card player2Card = drawCard(player2Deck);
    System.out.println(player1Name + " plays: " + player1Card);
    System.out.println(player2Name + " plays: " + player2Card);

    int player1RankIndex = Arrays.asList(CardUtils.RANKS).indexOf(player1Card.getRank());
    int player2RankIndex = Arrays.asList(CardUtils.RANKS).indexOf(player2Card.getRank());

    if (player1RankIndex > player2RankIndex) {
        System.out.println(player1Name + " wins the round!");
        player1Deck.add(player1Card);
        player1Deck.add(player2Card);
    } else if (player2RankIndex > player1RankIndex) {
        System.out.println(player2Name + " wins the round!");
        player2Deck.add(player2Card);
        player2Deck.add(player1Card);
    } else {
        System.out.println("It's a tie! WAR!");
        playWar();
    }

    // Print available cards and number of cards left in hands after the round
    System.out.println(player1Name + " has " + player1Deck.size() + " cards left.");
    System.out.println(player2Name + " has " + player2Deck.size() + " cards left.");
}

    private void playWar() {
        List<Card> warCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
                warCards.add(drawCard(player1Deck));
                warCards.add(drawCard(player2Deck));
            }
        }

        if (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            Card player1WarCard = drawCard(player1Deck);
            Card player2WarCard = drawCard(player2Deck);

            System.out.println(player1Name + " plays: " + player1WarCard + " (WAR)");
            System.out.println(player2Name + " plays: " + player2WarCard + " (WAR)");

            int player1RankIndex = Arrays.asList(CardUtils.RANKS).indexOf(player1WarCard.getRank());
            int player2RankIndex = Arrays.asList(CardUtils.RANKS).indexOf(player2WarCard.getRank());

            if (player1RankIndex > player2RankIndex) {
                System.out.println(player1Name + " wins the war!");
                player1Deck.addAll(warCards);
                player1Deck.add(player1WarCard);
                player1Deck.add(player2WarCard);
            } else if (player2RankIndex > player1RankIndex) {
                System.out.println(player2Name + " wins the war!");
                player2Deck.addAll(warCards);
                player2Deck.add(player2WarCard);
                player2Deck.add(player1WarCard);
            } else {
                System.out.println("It's another tie! The war continues!");
                warCards.add(player1WarCard);
                warCards.add(player2WarCard);
                playWar();
            }
        } else {
            System.out.println("Not enough cards to continue the war.");
        }
    }
     public void playGame() {
        playGame(26); // Default to 26 rounds
    }
    public void playGame(java.lang.Integer maxRounds) {
        int roundNumber = 1;
        while (roundNumber <= maxRounds && !player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            System.out.println("Round " + roundNumber + ":");
            playRound();
            roundNumber++;
        }

        if (player1Deck.size() > player2Deck.size()) {
            System.out.println(player1Name + " wins the game with " + player1Deck.size() + " cards!");
        } else if (player2Deck.size() > player1Deck.size()) {
            System.out.println(player2Name + " wins the game with " + player2Deck.size() + " cards!");
        } else {
            System.out.println("The game is a tie!");
        }
    }



    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Player 1 name: ");
    String player1Name = scanner.nextLine();
    int maxRounds = 26; // Set the maximum number of rounds based on the number of cards each player has
    WarGame game = new WarGame(player1Name);
    game.playGame();
}


   

}

class CardUtils {
    public static final String[] RANKS = {"TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
}


   