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
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WarGame {
    private Player player1;
    private Player player2;
    private Deck deck;
    private GroupOfCards table;

    public WarGame(String playerName1, String playerName2) {
        this.player1 = new HumanPlayer(playerName1);
        this.player2 = new ComputerPlayer(playerName2);
        this.deck = new Deck();
        this.table = new GroupOfCards();

        // Divide the cards equally between the players
        dealCards();
    }

   private void dealCards() {
    int totalCards = deck.size();
    int cardsPerPlayer = totalCards / 2;

    for (int i = 0; i < cardsPerPlayer; i++) {
        player1.addCard(deck.getCard(i));
    }

    for (int i = cardsPerPlayer; i < totalCards; i++) {
        player2.addCard(deck.getCard(i));
    }
}


    private Rank getUserCardRank(Scanner scanner) {
        while (true) {
            System.out.println("Your available cards:");
            for (int i = 0; i < player1.getHandSize(); i++) {
                System.out.println(i + 1 + ". " + player1.getCardAtIndex(i));
            }
            System.out.print("Enter the number of the card you want to play:\n ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left in the scanner buffer
            if (index >= 1 && index <= player1.getHandSize()) {
                return player1.getCardAtIndex(index - 1).getRank();
            } else {
                System.out.println("Invalid card number. Please enter a valid card number.");
            }
        }
    }

    private Player playRound(Player currentPlayer, Rank userCardRank) {
    Card userCard = currentPlayer.getCardWithRank(userCardRank);

    if (userCard == null) {
        System.out.println(currentPlayer.getName() + " does not have a card with rank: " + userCardRank);
        return currentPlayer;
    }

    Card computerCard = null;
    if (currentPlayer == player1) {
        computerCard = player2.flipCard();
    } else {
        computerCard = player1.flipCard();
    }

    if (computerCard == null) {
        System.out.println("Computer does not have any cards left to play!");
        // Return the other player as the winner of the round
        return (currentPlayer == player1) ? player2 : player1;
    }

    System.out.println(currentPlayer.getName() + " plays: " + userCard);
    System.out.println(currentPlayer == player1 ? "Computer plays: " + computerCard : player1.getName() + " plays: " + computerCard);

    table.addCard(userCard);
    table.addCard(computerCard);

    if (userCard.getRank().ordinal() > computerCard.getRank().ordinal()) {
        currentPlayer.addCard(table.getCard(0));
        currentPlayer.addCard(table.getCard(1));
        table.removeCard(userCard);
        table.removeCard(computerCard);
        System.out.println(currentPlayer.getName() + " wins the round!");
    } else if (userCard.getRank().ordinal() < computerCard.getRank().ordinal()) {
        Player otherPlayer = (currentPlayer == player1) ? player2 : player1;
        otherPlayer.addCard(table.getCard(0));
        otherPlayer.addCard(table.getCard(1));
        table.removeCard(userCard);
        table.removeCard(computerCard);
        System.out.println(otherPlayer.getName() + " wins the round!");
    } else {
        System.out.println("WAR!");
        initiateWarPhase(currentPlayer);
    }

    // Print the number of cards left for each player
    System.out.println(player1.getName() + " has " + player1.getHandSize() + " cards left.");
    System.out.println(player2.getName() + " has " + player2.getHandSize() + " cards left.");

    return null;
}


    private void initiateWarPhase(Player currentPlayer) {
    Card userCard = currentPlayer.flipCard();
    Card computerCard = (currentPlayer == player1) ? player2.flipCard() : player1.flipCard();

    if (userCard == null || computerCard == null) {
        System.out.println("WAR: One of the players does not have enough cards!");
        return;
    }

    System.out.println("WAR: " + currentPlayer.getName() + " plays: " + userCard);
    System.out.println("WAR: " + (currentPlayer == player1 ? "Computer plays: " + computerCard : player1.getName() + " plays: " + computerCard));

    table.addCard(userCard);
    table.addCard(computerCard);

    List<Card> warCards = new ArrayList<>();
    warCards.add(userCard);
    warCards.add(computerCard);

    if (userCard.getRank().ordinal() > computerCard.getRank().ordinal()) {
        currentPlayer.addCards(warCards);
        System.out.println("WAR: " + currentPlayer.getName() + " wins the war!");
    } else if (userCard.getRank().ordinal() < computerCard.getRank().ordinal()) {
        Player otherPlayer = (currentPlayer == player1) ? player2 : player1;
        otherPlayer.addCards(warCards);
        System.out.println("WAR: " + otherPlayer.getName() + " wins the war!");
    } else {
        System.out.println("Another WAR!");
        initiateWarPhase(currentPlayer);
    }
}


    private Player getRandomStartingPlayer() {
        Random random = new Random();
        return random.nextBoolean() ? player1 : player2;
    }

    private Player playComputerTurn(Player currentPlayer) {
        if (!currentPlayer.hasCards()) {
            return currentPlayer;
        }

        Rank computerCardRank = getRandomComputerCardRank();
        // Check if computerCardRank is not null before playing the round
        if (computerCardRank != null) {
            currentPlayer = playRound(currentPlayer, computerCardRank);
        }
        return currentPlayer;
    }

    private Rank getRandomComputerCardRank() {
        Random random = new Random();
        Rank[] ranks = Rank.values();
        return ranks[random.nextInt(ranks.length)];
    }

    public void playGame() {
        Player currentPlayer = getRandomStartingPlayer();
        System.out.println("Starting player: " + currentPlayer.getName());

        Scanner scanner = new Scanner(System.in);

        while (player1.hasCards() && player2.hasCards()) {
            if (currentPlayer == player1) {
                Rank userCardRank = getUserCardRank(scanner);
                currentPlayer = playRound(currentPlayer, userCardRank);
            } else {
                currentPlayer = playComputerTurn(currentPlayer);
            }

            if (currentPlayer == null) {
                currentPlayer = getRandomStartingPlayer();
                System.out.println("Starting player: " + currentPlayer.getName());
            }
        }

        System.out.println("Game over!");

        if (player1.hasCards()) {
            System.out.println(player1.getName() + " wins the game!");
        } else if (player2.hasCards()) {
            System.out.println(player2.getName() + " wins the game!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        WarGame game = new WarGame("Player1", "Computer");
        game.playGame();
    }
}


   