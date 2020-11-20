package io.zipcoder.casino.games;

import io.zipcoder.casino.Interfaces.HighRollEntrant;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.diceclasses.Die;
import io.zipcoder.casino.utilities.HighRollerNpc;
import io.zipcoder.casino.utilities.HighRollerPlayer;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;


public class HighRoller {
    private Die dice;
    private HighRollerPlayer player1;
    private HighRollerNpc npc1;
    private HighRollerNpc npc2;
    private HighRollerNpc npc3;
    private Double prizePool = 0.0;
    private ArrayList<HighRollEntrant> players;
    private ArrayList<HighRollEntrant> winners;
    private HighRollEntrant winningPlayer;
    private static Console console = new Console(System.in, System.out);

//    public static void main(String[] args) {
//        Player player = new Player();
//        HighRoller highRoller = new HighRoller(player);
//        highRoller.runHighRoller();
//    }


    public HighRoller(Player player) {
        Player player2 = new Player("Zack", 500.0);
        Player player3 = new Player("Cody", 500.0);
        Player player4 = new Player("Willow", 500.0);
        npc1 = new HighRollerNpc(player2);
        npc2 = new HighRollerNpc(player3);
        npc3 = new HighRollerNpc(player4);
        dice = new Die(6);
        this.player1 = new HighRollerPlayer(player);
        player1.setActiveRoller(true);
        npc1.setActiveRoller(true);
        npc2.setActiveRoller(true);
        npc3.setActiveRoller(true);
    }

    public void runHighRoller() {
        gameStart();
    }

    public void gameStart() {
        System.out.println("Welcome to High Roller");
        Double userInput = console.getDoubleInput("\nPlace your bet \n");
        if (userInput <= 0) {
            System.out.println("Nah fam not today");
            gameStart();
//        }else if(userInput > player1.getPlayer().){
//            System.out.println("Funny money ain't accepted here, dealer throws you out. \n");
//            gameStart();
        }else{
            player1.getPlayer().setBalance(player1.getPlayer().getBalance() - userInput);
            prizePool += userInput;
            System.out.println("Npcs place your bet \n");
            Double bet2 = npcBet(npc1);
            prizePool += bet2;
            Double bet3 = npcBet(npc2);
            prizePool += bet3;
            Double bet4 = npcBet(npc3);
            prizePool += bet4;
            System.out.println("player 1 " + userInput + "\n" +
                    "player 2 " + bet2 + "\n" +
                    "player 3 " + bet3 + "\n" +
                    "player 4 " + bet4);
            System.out.println("\nRoll your dice \n");
            diceRollResults();
        }

    }

    public Double npcBet(HighRollerNpc npc) {
        double x = Math.floor(Math.random() * ((500 - 10) + 1) + 10);
        npc.setWallet(npc.getWallet() - x);
        return x;
    }

    public Integer diceRoll() {
        Integer value = dice.rollDie() + dice.rollDie();
        return value;
    }

    public void diceRollResults() {
        player1.setCurrentRoll(0);
        npc1.setCurrentRoll(0);
        npc2.setCurrentRoll(0);
        npc3.setCurrentRoll(0);

        if (player1.getActiveRoller()) {
            player1.setCurrentRoll(diceRoll());
            System.out.println("\n" + player1.getPlayer().getName() + "rolled " + player1.getCurrentRoll());
        }
        if (npc1.getActiveRoller()) {
            npc1.setCurrentRoll(diceRoll());
            System.out.println("\n" + npc1.getPlayer().getName() + " rolled " + npc1.getCurrentRoll());
        }
        if (npc2.getActiveRoller()) {
            npc2.setCurrentRoll(diceRoll());
            System.out.println("\n" + npc2.getPlayer().getName() + " rolled " + npc2.getCurrentRoll());
        }
        if (npc3.getActiveRoller()) {
            npc3.setCurrentRoll(diceRoll());
            System.out.println("\n" + npc3.getPlayer().getName() + " rolled " + npc3.getCurrentRoll());
        }
        checkForWinner();

    }

    public void checkForWinner() {
        ArrayList<HighRollEntrant> players = new ArrayList<HighRollEntrant>();
        ArrayList<HighRollEntrant> winners = new ArrayList<HighRollEntrant>();
        players.add(player1);
        players.add(npc1);
        players.add(npc2);
        players.add(npc3);
        Integer highRoll = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCurrentRoll() > highRoll) {
                highRoll = players.get(i).getCurrentRoll();
            }

        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCurrentRoll() == highRoll) {
                players.get(i).setActiveRoller(true);
                winners.add(players.get(i));
//                System.out.println("Size is " + winners.size());
            } else {
                players.get(i).setActiveRoller(false);
            }
        }
        if (winners.size() == 1) {
            winningPlayer = winners.get(0);
            announceWinner(winningPlayer);
            payOut(winningPlayer);
        }
        if (winners.size() > 1) {
            System.out.print("\n" + "We have a Tie between ");
            for (int j = 0; j < winners.size(); j++) {
                System.out.print(winners.get(j).getPlayer().getName());

            }
            diceRollResults();
        }


    }

    public void announceWinner(HighRollEntrant player) {

        System.out.println("\nWe have a winner! " + player.getPlayer().getName() + " Wins " + prizePool);

    }

    public void payOut(HighRollEntrant player) {
        player.getPlayer().setBalance(player.getPlayer().getBalance() + prizePool);
        System.out.println();
        prizePool = 0.0;

    }


}
