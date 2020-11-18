package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.*;
import io.zipcoder.casino.utilities.Console;
import java.util.Collections;
import java.util.ArrayList;

public class GoFish extends CardGame{

   private Card card;
   private static Console console = new Console(System.in, System.out);

    public GoFish() {
        super();
      //this.card=card;
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
       // String opponentSuite ="";
        for (int i = 0; i < players.size(); i++) {
           opponent= console.getStringInput(players.get(i).getName().toUpperCase() + " Please enter the other player name: ");
           opponentValue= console.getStringInput("Please enter the value of the card: ");
          //  opponentSuite= console.getStringInput("Please enter the suite of the card: ");
           //String opponentTotal = opponentSuite +" of " + opponentValue;
           //add if to check if the opponent asked is same as player 1
            for(int j= 0 ; j<players.size();j++)
            {
               // if(players.get(i).getName().equalsIgnoreCase(opponent))
                if(players.get(j).getName().equalsIgnoreCase(opponent))
                {

                    this.askForCard(players.get(i),players.get(j) ,opponentValue);
                }
            }

        }
        // use arraylist of players
        // askForCard
        // after every turn check player hand for pack
        // if pack found  remove those cards
        // increment pack counter
    }

    public void askForCard(Player dealerPlayer ,Player opponentPlayer,String opponentValue) {
      ArrayList<Card> checkCardinHand=opponentPlayer.getHand();
      Boolean cardFound=false;
      Collections.sort(checkCardinHand);
        for (int i = 0; i < checkCardinHand.size(); i++) {
            if (checkCardinHand.get(i).getValue().equalsIgnoreCase(opponentValue)) {
                cardFound=true;
                updateHand(dealerPlayer, checkCardinHand, i);
                //check if pack

            }
        }
          if(!cardFound)
            {
                    System.out.println("Go Fish...." + dealerPlayer.getName().toUpperCase());
                    dealerPlayer.addCard(deck.getDeck().pop());
            }
               checkPack();

        }


         // player asks for specific card -DONE
        // compare players hand to look for card -DONE
        // if player doesnt have card draw from deck -DONE
        // card  = drawCardFromDeck(Player 1)
        // updateHand with card
        //ELSE BELOW
        // update hand player 1 and/or  2

    //}
    private void updateHand(Player dealerPlayer, ArrayList<Card> checkCardinHand, int i) {
        dealerPlayer.addCard(checkCardinHand.get(i));
        checkCardinHand.remove(i);
    }

    // public Card drawCardFromDeck(Player player) {
   //     // POP a card from DECK and return it
  //  re
  //  }


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
