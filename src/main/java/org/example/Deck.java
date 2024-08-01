package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements DeckActions {
    private List<Card> myCards;
    private int numCards;

    public Deck() {
        this.myCards = new ArrayList<>();
        for (Suits suit : Suits.values()) {
            for (Values value : Values.values()) {
                this.myCards.add(new Card(suit, value));
            }
        }
        this.numCards = this.myCards.size();
        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.myCards);
    }

    @Override
    public Card dealNextCard() {
        if (this.numCards == 0) {
            return null;
        }
        this.numCards--;
        return this.myCards.remove(0);
    }

    @Override
    public void printDeck(int numToPrint) {
        for (int i = 0; i < numToPrint && i < this.myCards.size(); i++) {
            System.out.println(this.myCards.get(i));
        }
    }
}

