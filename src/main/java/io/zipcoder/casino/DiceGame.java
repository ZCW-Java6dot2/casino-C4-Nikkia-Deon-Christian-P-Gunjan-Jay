package io.zipcoder.casino;

import java.util.ArrayList;

public class DiceGame<Player> implements Game, GamblingGameInterface {

    private ArrayList<Die> gameDice;
    private ArrayList<Player> players;



    public DiceGame(Integer numOfDie, ArrayList<Player> players) {
        ArrayList<Die> gameDice = new ArrayList<Die>();
        for (int i = 0; i < numOfDie; i++) {
            gameDice.add(new Die(6));


        }
        this.players = players;
    }
    public void setNoOfDice(ArrayList<Die> noOfDice) {
        this.noOfDice = noOfDice;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Die> getNoOfDice(){
        return noOfDice;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }

}
