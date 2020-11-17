package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.*;

import java.util.ArrayList;

public class GoFish extends CardGame{

   //private Card card;


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


    }

    public void playerTurn(){
        // use arraylist of players
        // askForCard
        // after every turn check player hand for pack
        // if pack found  remove those cards
        // increment pack counter
    }

    public void askForCard(Player player, Card card){
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
