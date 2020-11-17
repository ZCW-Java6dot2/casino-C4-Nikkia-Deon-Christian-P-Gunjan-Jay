package io.zipcoder.casino.utilities;
import io.zipcoder.casino.utilities.Menu;

public class ConsoleInput {

    private static Console console = new Console(System.in, System.out);

    public void getPlayers()
    {
     Menu menu = new Menu();
     Integer players= console.getIntegerInput("Enter the number of Players :");

     for (int i = 0; i < players; i++) {
         String playerName = console.getStringInput("Please enter your name : ");
         Double balance = console.getDoubleInput("Please enter the balance : ");
         menu.storePlayers(playerName, balance);
     }
    this.selectCardOrDice();
 }

 public void selectCardOrDice()
 {
     Menu menu = new Menu();
     System.out.println("****************************");
     System.out.println("1.  Card Game");
     System.out.println("2.  Dice Game");
     System.out.println("****************************");
     Integer cardOrDice = console.getIntegerInput("Please select the game you want to play:  ");
     menu.displayCardOrDiceMenu(cardOrDice);
 }

    public void displayCardMenu()
    {
        Menu menu = new Menu();
        System.out.println("****************************");
        System.out.println("1.  BlackJack ");
        System.out.println("2.  GoFish ");
        System.out.println("3.  Go Back to previous Menu");
        System.out.println("****************************");
        Integer cardGameChoice = console.getIntegerInput("Please select the game you want to play:  ");
        menu.callBlackJackOrGoFish(cardGameChoice);
    }

    public void displayDiceMenu()
    {
        Menu menu = new Menu();
        System.out.println("****************************");
        System.out.println("1.  Craps ");
        System.out.println("2.  ShootinDice ");
        System.out.println("****************************");
        Integer diceGameChoice = console.getIntegerInput("Please select the game you want to play:  ");
        menu.callCrapsOrShootinDice(diceGameChoice);
    }


}
