package io.zipcoder.casino.utilities;

import io.zipcoder.casino.Player;

import io.zipcoder.casino.cardclasses.GoFish;
import io.zipcoder.casino.games.BlackJack;


import io.zipcoder.casino.diceclasses.Craps;

import io.zipcoder.casino.games.GoFish;



import java.util.ArrayList;
public class Menu {
    ArrayList <Player> players = new ArrayList<Player>();
    Player singlePlayer = new Player();
    private static Console console = new Console(System.in, System.out);
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void getPlayersCount()
    {
        System.out.println("       .-------.    ______\n" +
                "      /   o   /|   /\\     \\\n" +
                "     /_______/o|  /o \\  o  \\\n" +
                "     | o     | | /   o\\_____\\\n" +
                "     |   o   |o/ \\o   /o    /\n" +
                "     |     o |/   \\ o/  o  /\n" +
                "     '-------'     \\/____o/");
        System.out.println("♠️ ♥️♣️ ♦️ ♠️ ♥️♣️ ♠️ ♥️♣️ ♦️♠️ ♥️♣️ ♦️" );
        System.out.println("****************************************");
        System.out.println("******* \u001B[36m Welcome to our Casino  \u001B[0m ******* \u001B[36m ");
        System.out.println("\u001B[0m****************************************");
         Integer players= console.getIntegerInput("Please enter the number of Players :");

             Integer playNumber =1;

             for (int i = 0; i < players; i++) {
                 String playerName = console.getStringInput("Please enter your name : ");
                 Double balance = console.getDoubleInput("Please enter the balance : ");
                 this.storePlayers(playerName, balance,playNumber++);
             }

//         }
        //this.selectCardOrDice();


        this.selectCardOrDice();



    }
    public void storePlayers(String playerName , Double balance, Integer playerNumber)
    {

        Player player = new Player();
        player.setName(playerName);
        player.setBalance(balance);
        player.setPlayerNumber(playerNumber);
        this.players.add(player);
    }

    public void intiatiateWelcome(){
        selectCardOrDice();
        displayCardOrDiceMenu();
    }

    public String selectCardOrDice()
    {
        String selectCD1 = "****************************\n";
        String selectCD2 = "1.  Card Game\n";
        String selectCD3 = "2.  Dice Game\n";
        String selectCD4 = "3.  Exit\n";
        String selectCD5 = "****************************";
        String printToSelect = selectCD1 + selectCD2 + selectCD3 + selectCD4 + selectCD5;
        System.out.println(printToSelect);



        return printToSelect;


    }

    public void displayCardOrDiceMenu()
    {
        Integer cardOrDice = console.getIntegerInput("Please select the game you want to play:  ");
        //displayCardOrDiceMenu(cardOrDice);

        switch(cardOrDice)
        {
            case 1 : this.displayCardMenu();
                     break;
            case 2 : this.displayDiceMenu();
                     break;
            case 3 :
                System.out.print("Quiting the Casino App, Program Ending");
                System.exit(0);
            default :
                console.println("Wrong choice, select again");
                displayCardOrDiceMenu();

        }

    }

    public void displayCardMenu()
    {

        System.out.println("****************************");
        System.out.println("1.  BlackJack ");
        System.out.println("2.  GoFish ");
        System.out.println("3.  Go Back to previous Menu");
        System.out.println("****************************");
        Integer cardGameChoice = console.getIntegerInput("Please select the game you want to play:  ");

        if(cardGameChoice==3)
        {
            this.selectCardOrDice();
        }
        else
            this.callBlackJackOrGoFish(cardGameChoice);
    }

    public void displayDiceMenu()
    {
        System.out.println("****************************");
        System.out.println("1.  Craps ");
        System.out.println("2.  High Roller ");
        System.out.println("3.  Go Back to previous Menu");
        System.out.println("****************************");
        Integer diceGameChoice = console.getIntegerInput("Please select the game you want to play:  ");
        if(diceGameChoice==3)
        {
            this.selectCardOrDice();
        }
        else
        this.callCrapsOrHighRoller(diceGameChoice);
    }

    public void callBlackJackOrGoFish(Integer cardGameChoice) {
        switch(cardGameChoice)
        {
            case 1 : BlackJack blackJack = new BlackJack(this.players);
                    blackJack.runGame();
                    break;
            case 2 :GoFish goFish=new GoFish(this.players);
                    goFish.runGoFish();
                    break;
            case 3 :
                this.selectCardOrDice();
                break;
            default:
                console.println("Wrong choice, select again");
                 displayDiceMenu();
        }

    }

    public void callCrapsOrHighRoller(Integer diceGameChoice) {
        System.out.println("Would call the CrapsORHighRoller");
        switch(diceGameChoice){
            case 1 : Craps craps = new Craps(this.players);
                    craps.playCraps();
                    break;
        }
    }

}
