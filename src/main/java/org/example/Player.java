package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private List<Card> hand;
    private int balance;
    private int bet;

    public Player(String name, int balance) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.balance = balance;
        this.bet = 0;
    }

    public void placeBet(int amount) {
        if (amount <= balance && amount % 5 == 0) {
            this.bet = amount;
            this.balance -= amount;
        } else {
            System.out.println("Invalid bet amount. Bets must be in increments of 5 and within your balance.");
        }
    }

    public void doubleDown() {
        if (bet * 2 <= balance) {
            balance -= bet;
            bet *= 2;
        } else {
            System.out.println("Insufficient balance to double down.");
        }
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int calculateHandValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            value += card.getValue().getValue();
            if (card.getValue() == Values.ACE) {
                aces++;
            }
        }
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }
        return value;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getBet() {
        return bet;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void printHand() {
        for (Card card : hand) {
            System.out.println(card);
        }
    }
}
