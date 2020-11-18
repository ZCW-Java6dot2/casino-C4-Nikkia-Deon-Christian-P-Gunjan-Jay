package io.zipcoder.casino;

import java.util.ArrayList;
import io.zipcoder.casino.cardclasses.Card;
public class Player {

    private double balance;
    private ArrayList<Card> hand;
    private String name;
    private Card card;
    //player number

    public Player(){
        hand = new ArrayList<Card>();
        name = "";
        balance =0d;
    }

    // name only constructor

    public Player(String name){
        this.name = name;
    }
    // name, balance, hand constructor
    public Player(String name, Double balance, ArrayList<Card> hand){
        this.name = name;
        this.balance = balance;
        this.hand = hand;
    }
    //name, balance constructor
    public Player(String name, Double balance){
        this.name = name;
        this.balance = balance;
    }


    public Double playerWager(Double wager){
        return wager;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public void setHand(ArrayList<Card> hand) {

        this.hand = hand;
    }
    

    public void removeCard(Card c){
        hand.remove(c);
    }

    public Card getCard(Object card){
        return this.card;
    }

    public void setCard(Card card){
        this.card = card;
    }

}
