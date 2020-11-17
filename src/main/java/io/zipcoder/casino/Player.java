package io.zipcoder.casino;

import java.util.ArrayList;

public class Player {

    private double balance;
    //private ArrayList<Cards> hand;
    private String name;

    public double playerWager(double wager){
        return wager;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBalance(){
        return balance;
    }

    /*
    public void setHand(ArrayList<Cards> hand){

        this.hand = hand;

   }

   public ArrayList<Cards> getHand() {
        return hand;
    }

    public void addCard(){}

    public void removeCard(){}

    public Card getCard(){
    return card;
    }

    public void setCard(Card card){
    this.card = card;
    }

     */



}
