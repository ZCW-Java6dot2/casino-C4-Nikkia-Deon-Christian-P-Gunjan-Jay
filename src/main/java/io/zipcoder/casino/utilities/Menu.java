package io.zipcoder.casino.utilities;

import io.zipcoder.casino.Player;

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

        for (int i = 0; i < players; i++) {
            String playerName = console.getStringInput("Please enter your name : ");
            Double balance = console.getDoubleInput("Please enter the balance : ");
            this.storePlayers(playerName, balance);
        }
        this.selectCardOrDice();


    }
    public void storePlayers(String playerName , Double balance)
    {
        this.singlePlayer.setName(playerName);
        this.singlePlayer.setBalance(balance);
        this.players.add(singlePlayer);
    }

    public String selectCardOrDice()
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
        return "****************************\n" +
                "1.  Card Game\n" +
                "2.  Dice Game\n" +
                "3.  Exit\n" +
                "****************************\n" +
                "Please select the game you want to play: ";
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
        // Menu menu = new Menu();
        System.out.println("****************************");
        System.out.println("1.  Craps ");
        System.out.println("2.  ShootinDice ");
        System.out.println("3.  Go Back to previous Menu");
        System.out.println("****************************");
        Integer diceGameChoice = console.getIntegerInput("Please select the game you want to play:  ");
        if(diceGameChoice==3)
        {
            this.selectCardOrDice();
        }
        else
        this.callCrapsOrShootinDice(diceGameChoice);
    }

    public void callBlackJackOrGoFish(Integer cardGameChoice) {
        System.out.println("Would call the blackJack or Go Fish");
    }

    public void callCrapsOrShootinDice(Integer diceGameChoice) {
        System.out.println("Would call the CrapsORShootin");

    }

}