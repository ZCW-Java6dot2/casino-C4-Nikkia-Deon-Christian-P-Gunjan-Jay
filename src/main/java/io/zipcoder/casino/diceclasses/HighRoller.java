package io.zipcoder.casino.diceclasses;

import io.zipcoder.casino.Interfaces.HighRollEntrant;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;


public class HighRoller {
    Die dice;
    HighRollerPlayer player1;
    HighRollerNpc npc1;
    HighRollerNpc npc2;
    HighRollerNpc npc3;
    Double prizePool = 0.0;
    ArrayList<HighRollEntrant> players;
    ArrayList<HighRollEntrant> winners;
    HighRollEntrant winningPlayer;
    private static Console console = new Console(System.in, System.out);


    public HighRoller(Player player) {
        Player player2 = new Player("James",500.0);
        Player player3 = new Player("Joann",500.0);
        Player player4 = new Player("John",500.0);
        npc1 = new HighRollerNpc(player2);
        npc2 = new HighRollerNpc(player3);
        npc3 = new HighRollerNpc(player4);
        dice = new Die(6);
        this.player1 = new HighRollerPlayer(player);
    }

    public void runHighRoller(){
        gameStart();
    }

    public void gameStart(){
        //try {
            System.out.println("Welcome to High Roller");
            Double userInput = console.getDoubleInput("Place your bet");
            if (userInput <= 0) {
                System.out.println("Nah fam not today");
                gameStart();
            }
            player1.getPlayer().setBalance(player1.getPlayer().getBalance() - userInput);
            prizePool += userInput;
            System.out.println("Npcs place your bet");
            System.out.println(prizePool += npcBet(npc1));
            System.out.println(prizePool += npcBet(npc2));
            System.out.println(prizePool += npcBet(npc3));
            System.out.println("Roll your dice");
            diceRollResults();
            checkForWinner();
//            player.setCurrentRoll(diceRoll());
//            System.out.println(player.getPlayer().getName() + "Rolled " + player.getCurrentRoll());
//            npc1.setCurrentRoll(diceRoll());
//            System.out.println("Rolled " + npc1.getCurrentRoll());
//            npc2.setCurrentRoll(diceRoll());
//            System.out.println("Rolled " + npc2.getCurrentRoll());
//            npc3.setCurrentRoll(diceRoll());
//            System.out.println("Rolled " + npc3.getCurrentRoll());
        //} catch (Exception e){
            System.out.println("Dealer throws out player");
            gameStart();
       // }

    }
    public Double npcBet(HighRollerNpc npc){
        double x = (Math.random()*((10-50)+1)+10);
        npc.setWallet(npc.getWallet() - x);
        return x;
    }

    public Integer diceRoll(){
        Integer value = dice.rollDie() + dice.rollDie();
        return value;
    }

    public void diceRollResults(){
        if (player1.getActiveRoller()) {
            player1.setCurrentRoll(diceRoll());
            System.out.println(player1.getPlayer().getName() + "Rolled " + player1.getCurrentRoll());
        }
        if (npc1.getActiveRoller()) {
            npc1.setCurrentRoll(diceRoll());
            System.out.println(npc1.getPlayer().getName() + "Rolled " + npc1.getCurrentRoll());
        }
        if(npc2.getActiveRoller()){
            npc2.setCurrentRoll(diceRoll());
            System.out.println(npc2.getPlayer().getName() + "Rolled " + npc2.getCurrentRoll());
        }
        if (npc3.getActiveRoller()){
            npc3.setCurrentRoll(diceRoll());
            System.out.println(npc3.getPlayer().getName() + "Rolled " + npc3.getCurrentRoll());
        }

    }

    public Boolean checkForWinner(){
        ArrayList<HighRollEntrant> players = new ArrayList<HighRollEntrant>();
        ArrayList<HighRollEntrant> winners = new ArrayList<HighRollEntrant>();
        players.add(player1);
        players.add(npc1);
        players.add(npc2);
        players.add(npc3);
        String winner = "";
        Integer highRoll = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCurrentRoll() > highRoll) {
                highRoll = players.get(i).getCurrentRoll();
            }

        }
        for(int i = 0; i < players.size(); i++){
            if (players.get(i).getCurrentRoll() == highRoll){
                players.get(i).setActiveRoller(true);
                 winners.add(players.get(i));
            } else {
                players.get(i).setActiveRoller(false);
            }
            if (players.size() == 1){
                winningPlayer = winners.get(0);
                return true;
            } else if (players.size() > 1){
                diceRollResults();
            }
        }
        return null;

    }





}
