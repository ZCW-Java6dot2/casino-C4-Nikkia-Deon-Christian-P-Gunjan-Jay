package io.zipcoder.casino.cardclasses;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class CardDeck {
    private Stack<Card> deck;

    public CardDeck() {

        deck = new Stack<Card>();
        //fill the deck
        String[] suits = {"Diamonds", "Spades", "Hearts", "Clubs"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen", "Ace"};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card newCard = new Card(suits[i], values[j]);
                deck.add(newCard);
            }
        }

    }

    public Stack<Card> getDeck() {
        return deck;
    }


    public void shuffleDeck() {
        Random random = new Random(5);
        Collections.shuffle(deck, random);

    }
}
