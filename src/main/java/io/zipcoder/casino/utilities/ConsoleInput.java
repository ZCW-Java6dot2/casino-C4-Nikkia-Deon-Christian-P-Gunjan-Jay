package io.zipcoder.casino.utilities;

public class ConsoleInput {

    private Console console = new Console(System.in, System.out);
    Menu menu = new Menu();

    public void getPlayers()
    {

     Integer players= console.getIntegerInput("Enter the number of Players :");

     for (int i = 0; i < players; i++) {
         String playerName = console.getStringInput("Please enter name : ");
         Double balance = console.getDoubleInput("Please enter the balance : ");
         menu.storePlayers(playerName, balance);
     }


 }
}
