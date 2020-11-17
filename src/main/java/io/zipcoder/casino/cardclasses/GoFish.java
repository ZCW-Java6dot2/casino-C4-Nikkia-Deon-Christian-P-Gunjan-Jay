package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.*;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;

public class GoFish extends CardGame{

   //private Card card;
   private static Console console = new Console(System.in, System.out);

    public GoFish() {
        super();
        //   card=new Card();
       deck = new CardDeck();
    }

    public GoFish(ArrayList<Player> players) {
        super(players);
    }

   public void runGoFish()
   {
       //List of methods
       super.initialCardsGiven(1);
       this.initialHand();
   }

   public void initialHand(){
       for (int i = 0; i < players.size(); i++) {
           System.out.println(super.getPlayers().get(i).getHand());
       }

       this.playerTurn();
    }

    public void playerTurn(){
         String opponent = "";
         String opponentValue ="";
        String opponentSuite ="";
        for (int i = 0; i < players.size(); i++) {
           opponent= console.getStringInput("Please enter the Player name: ");
           opponentValue= console.getStringInput("Please enter the value of the card: ");
            opponentSuite= console.getStringInput("Please enter the suite of the card: ");
           String opponentTotal = opponentSuite +" of " + opponentValue;
           //add if to check if the opponent asked is same as player 1
            for(int j= 0 ; j<players.size();j++)
            {
                if(players.get(j).getName().equalsIgnoreCase(opponent))
                {
                    this.askForCard(players.get(j) , opponentTotal);
                }
            }

        }
        // use arraylist of players
        // askForCard
        // after every turn check player hand for pack
        // if pack found  remove those cards
        // increment pack counter
    }

    public void askForCard(Player opponentPlayer, String ){
        System.out.println(opponentPlayer.getHand().contains(opponentTotal));

        // player asks for specific card
        // compare players hand to look for card
        // if player doesnt have card draw from deck
        // card  = drawCardFromDeck(Player 1)
        // updateHand with card
        //ELSE BELOW
        // update hand player 1 and/or  2
    }

   // public Card drawCardFromDeck(Player player) {
   //     // POP a card from DECK and return it
  //  re
  //  }


    public void updateHand(){
        // player.addCard and player.removeCard
        // check
    }

    public void checkPack(){
        // check for 4 cards of same
        // do this when player.addCard is used
    }

    public void packCounter(){
        // counts the number of packs per player
        // if there is a pack found increment number of packs
        // update hand to remove all packs
    }

    public void declareWinner(){
        //check for each player the pack count

    }



}
