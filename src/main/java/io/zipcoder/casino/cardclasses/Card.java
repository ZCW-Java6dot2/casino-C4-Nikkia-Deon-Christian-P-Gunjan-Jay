package io.zipcoder.casino.cardclasses;

import java.util.Comparator;

public class Card implements Comparable<Card> {
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return (value + " of " + suit);
    }

    @Override
    public int compareTo(Card c){
        return this.getValue().compareTo(c.getValue());
    }

}
