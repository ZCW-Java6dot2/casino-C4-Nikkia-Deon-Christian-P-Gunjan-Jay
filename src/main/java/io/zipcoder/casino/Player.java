package io.zipcoder.casino;

import io.zipcoder.casino.cardclasses.Card;

import java.util.ArrayList;

public class Player {

    private double balance;
    private ArrayList<Card> hand;
    private String name;
    private Card card;
    public Player(){
        hand = new ArrayList<Card>();
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

    public Card getCard(){
    return card;
    }

    public void setCard(Card card){
    this.card = card;
    }







}
