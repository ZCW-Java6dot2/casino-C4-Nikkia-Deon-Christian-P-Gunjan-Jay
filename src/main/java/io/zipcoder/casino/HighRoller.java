package io.zipcoder.casino;

import io.zipcoder.casino.diceclasses.Die;
import io.zipcoder.casino.utilities.Console;

import java.util.Scanner;

public class HighRoller {
    Scanner scanner = new Scanner;
    Die dice;
    Player player;
    HighRollerNpc npc1;
    HighRollerNpc npc2;
    HighRollerNpc npc3;
    private static Console console = new Console(System.in, System.out);
    public HighRoller(Player player, HighRollerNpc npc1, HighRollerNpc npc2, HighRollerNpc npc3) {
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
        } catch (Exception e){
            System.out.println("Dealer throws out player");
            gameStart();
        }


    }
    public Integer diceRoll(){

    }


}
