package org.example;

import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player;
    private Player dealer;

    public Game(String playerName, int playerBalance) {
        this.deck = new Deck();
        this.player = new Player(playerName, playerBalance);
        this.dealer = new Player("Dealer", 0);
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");
        System.out.println(player.getName() + ", you have a balance of " + player.getBalance());

        while (true) {
            System.out.println("Enter your bet (in increments of 5): ");
            int bet = sc.nextInt();
            player.placeBet(bet);

            player.clearHand();
            dealer.clearHand();

            player.addCardToHand(deck.dealNextCard());
            player.addCardToHand(deck.dealNextCard());
            dealer.addCardToHand(deck.dealNextCard());
            dealer.addCardToHand(deck.dealNextCard());

            System.out.println("Your hand:");
            player.printHand();
            System.out.println("Dealer's up card:");
            System.out.println(dealer.getHand().get(0));

            boolean playerTurn = true;
            while (playerTurn) {
                System.out.println("Your hand value: " + player.calculateHandValue());
                System.out.println("Would you like to hit, stand, or double down? (h/s/d): ");
                String action = sc.next();

                if (action.equalsIgnoreCase("h")) {
                    player.addCardToHand(deck.dealNextCard());
                    System.out.println("Your hand:");
                    player.printHand();
                    if (player.calculateHandValue() > 21) {
                        System.out.println("You busted!");
                        playerTurn = false;
                    }
                } else if (action.equalsIgnoreCase("s")) {
                    playerTurn = false;
                } else if (action.equalsIgnoreCase("d")) {
                    player.doubleDown();
                    player.addCardToHand(deck.dealNextCard());
                    System.out.println("Your hand:");
                    player.printHand();
                    playerTurn = false;
                }
            }

            System.out.println("Dealer's hand:");
            dealer.printHand();
            while (dealer.calculateHandValue() < 17) {
                dealer.addCardToHand(deck.dealNextCard());
                System.out.println("Dealer hits:");
                dealer.printHand();
            }

            int playerValue = player.calculateHandValue();
            int dealerValue = dealer.calculateHandValue();

            if (playerValue > 21) {
                System.out.println("You busted! Dealer wins.");
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                System.out.println("You win!");
                player.placeBet(player.getBet() * 2);
            } else if (playerValue == dealerValue) {
                System.out.println("It's a push!");
                player.placeBet(player.getBet());
            } else {
                System.out.println("Dealer wins.");
            }

            System.out.println("Your balance: " + player.getBalance());
            System.out.println("Do you want to play again? (y/n): ");
            String playAgain = sc.next();
            if (!playAgain.equalsIgnoreCase("y")) {
                break;
            }
        }

        sc.close();
    }
}
