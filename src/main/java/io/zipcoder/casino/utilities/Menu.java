package io.zipcoder.casino.utilities;

import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.GoFish;
import io.zipcoder.casino.diceclasses.Craps;
import io.zipcoder.casino.games.HighRoller;

import java.util.ArrayList;
import java.util.Arrays;

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
//         if(players<2)
//         {
//             System.out.println("<<<<< Please add at least 2 Players to play >>>>>>>");
//             this.getPlayersCount();
//         }
//         else {
             Integer playNumber =1;
             for (int i = 0; i < players; i++) {
                 String playerName = console.getStringInput("Please enter your name : ");
                 Double balance = console.getDoubleInput("Please enter the balance : ");
                 this.storePlayers(playerName, balance,playNumber++);
             }
//         }
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

    public void selectCardOrDice()
    {
        System.out.println("****************************");
        System.out.println("1.  Card Game");
        System.out.println("2.  Dice Game");
        System.out.println("3.  Exit");
        System.out.println("****************************");
        Integer cardOrDice = console.getIntegerInput("Please select the game you want to play:  ");
        if(cardOrDice==3)
        {
            System.out.print("Quiting the Casino App, Program Ending");
            System.exit(0);
        }
        else
        this.displayCardOrDiceMenu(cardOrDice);

    }

    public void displayCardOrDiceMenu(Integer gameSelection)
    {
        if(gameSelection.equals(1))
        {
            this.displayCardMenu();
        }
        else
        {
            this.displayDiceMenu();
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
        System.out.println("2.  High Roller");
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
            case 1 : //blackJack method.
            case 2 :GoFish goFish=new GoFish(this.players);
                    goFish.runGoFish();
                    break;
            case 3 :
                this.selectCardOrDice();
                break;
        }

    }

    public void callCrapsOrHighRoller(Integer diceGameChoice) {
        switch (diceGameChoice) {
            case 1:
                Craps craps = new Craps(this.players);
                craps.playCraps();
                break;
            case 2:
                if (players.size() > 1) {
                    System.out.println("This is a one player game");
                } else {
                    HighRoller highRoller = new HighRoller(this.players.get(0));
                    highRoller.runHighRoller();
                    break;
                }
            case 3:
                this.selectCardOrDice();
                break;

        }
    }
    public void returnToMenu(Player player){
        ArrayList<Player> temp = new ArrayList<>();
        temp.add(player);
        if(players.size() > 1){
            for (int i = 1; i < players.size(); i++) {
                temp.add(players.get(i));
            }
            players = temp;
        }else{
            players = temp;
        }
        selectCardOrDice();
    }
    public void returnToMenu(ArrayList<Player> returningPlayers){
        players = returningPlayers;
        selectCardOrDice();
    }
}
