package io.zipcoder.casino;

import io.zipcoder.casino.diceclasses.Die;
import io.zipcoder.casino.utilities.Console;

import java.util.Random;


public class HighRoller {
    Die dice;
    HighRollerPlayer player;
    HighRollerNpc npc1;
    HighRollerNpc npc2;
    HighRollerNpc npc3;
    Double prizePool = 0.0;
    private static Console console = new Console(System.in, System.out);
    public HighRoller(HighRollerPlayer player, HighRollerNpc npc1, HighRollerNpc npc2, HighRollerNpc npc3) {
        dice = new Die(6);
        this.player = player;
        this.npc1 = npc1;
        this.npc2 = npc2;
        this.npc2 = npc3;
    }

    public void gameStart(){
        try {
            System.out.println("Welcome to High Roller");
            Double userInput = console.getDoubleInput("Place your bet");
            if (userInput <= 0) {
                System.out.println("Nah fam not today");
                gameStart();
            }
            player.getPlayer().setBalance(player.getPlayer().getBalance() - userInput);
            prizePool += userInput;
            System.out.println("Npcs place your bet");
            prizePool += npcBet(npc1);
            prizePool += npcBet(npc2);
            prizePool += npcBet(npc3);
            System.out.println("Roll your dice");
            player.setCurrentRoll(diceRoll());
            System.out.println(player.getPlayer().getName() + "Rolled " + player.getCurrentRoll());
            npc1.setCurrentRoll(diceRoll());
            System.out.println("Rolled " + npc1.getCurrentRoll());
            npc2.setCurrentRoll(diceRoll());
            System.out.println("Rolled " + npc2.getCurrentRoll());
            npc3.setCurrentRoll(diceRoll());
            System.out.println("Rolled " + npc3.getCurrentRoll());
        } catch (Exception e){
            System.out.println("Dealer throws out player");
            gameStart();
        }

    }
    public Double npcBet(HighRollerNpc npc){
        double x = (Math.random()*((10-50)+1))+10;
        npc.setWallet(npc.getWallet() - x);
        return x;
    }

    public Integer diceRoll(){
        Integer value = dice.rollDie() + dice.rollDie();
        return value;
    }
    public String getWinner(){
        String winner = "";

    }




}
