package io.zipcoder.casino;

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




}
