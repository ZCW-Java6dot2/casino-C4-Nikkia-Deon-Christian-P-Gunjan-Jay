package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.Console;
import java.util.Collections;
import java.util.ArrayList;

public class GoFish extends CardGame{

   private Card card;
   private static Console console = new Console(System.in, System.out);

    public GoFish() {
        super();
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
        //display menu to select player
        for (int i = 0; i < players.size(); i++) {
           opponent= console.getStringInput(players.get(i).getName().toUpperCase() + " Please enter the other player name: ");
            Boolean flagPlayerfound=false;
           for(int k=0;k<players.size();k++)
           {

               if(players.get(k).getName().equalsIgnoreCase(opponent))
                   flagPlayerfound=true;
                   else
                   flagPlayerfound=false;
           }
           if(flagPlayerfound) {
               opponentValue = console.getStringInput("Please enter the value of the card: ");
               //add if to check if the opponent asked is same as player 1
               for (int j = 0; j < players.size(); j++) {
                   if (players.get(j).getName().equalsIgnoreCase(opponent)) {
                       this.askForCard(players.get(i), players.get(j), opponentValue);
                   }

               }
           }
           else
               System.out.println("Player not found , Please try again..");

        }
        // use arraylist of players
        // askForCard
        // after every turn check player hand for pack
        // if pack found  remove those cards
        // increment pack counter
    }

    public void askForCard(Player dealerPlayer ,Player opponentPlayer,String opponentValue) {
      ArrayList<Card> opponentPlayerHand=opponentPlayer.getHand();
      Boolean cardFound=false;
      //  System.out.println("Before"+ opponentPlayerHand);;
      Collections.sort(opponentPlayerHand);
      //  System.out.println("After"+ opponentPlayerHand);;

        for (int i = 0; i < opponentPlayerHand.size(); i++) {
            if (opponentPlayerHand.get(i).getValue().equalsIgnoreCase(opponentValue)) {
                cardFound=true;
                //updateHand(dealerPlayer, opponentPlayerHand, i);
                //check if pack
                 addCardToHand(dealerPlayer ,opponentPlayerHand.get(i));
                 removeCardFromPlayer(opponentPlayerHand.get(i),opponentPlayer);

            }
        }
          if(!cardFound)
            {
                    System.out.println("Go Fish...." + dealerPlayer.getName().toUpperCase());
                    dealerPlayer.addCard(deck.getDeck().pop());
            }
               checkPack(dealerPlayer);

        }

    public void removeCardFromPlayer(Card card, Player opponentPlayer) {
        opponentPlayer.getHand().remove(card);
    }

    public void addCardToHand(Player dealerPlayer, Card card) {

        dealerPlayer.addCard(card);
      //  return dealerPlayer;

    }
    // player asks for specific card -DONE
        // compare players hand to look for card -DONE
        // if player doesnt have card draw from deck -DONE
        // card  = drawCardFromDeck(Player 1)
        // updateHand with card
        //ELSE BELOW
        // update hand player 1 and/or  2

    //}
//    private void updateHand(Player dealerPlayer, ArrayList<Card> checkCardinHand, int i) {
//        System.out.println("The card of " + checkCardinHand.get(i).getValue() +" "+checkCardinHand.get(i).getSuit() + " has been given to " + dealerPlayer.getName().toUpperCase());
//        //System.out.println("The card of " + checkCardinHand.get(i).getValue() +" "+checkCardinHand.get(i).getSuit() + " has been removed from);
//      //  dealerPlayer.addCard(checkCardinHand.get(i));
//       // checkCardinHand.remove(i);
//    }

    // public Card drawCardFromDeck(Player player) {
   //     // POP a card from DECK and return it

  //  }


    public Integer checkPack(Player dealerPlayer){
        Collections.sort(dealerPlayer.getHand());
        Integer packCounter=0;
        for (int i=0 ; i < dealerPlayer.getHand().size();i++)
        {

            //try using iterator
         //   if(dealerPlayer.getHand().get(i).getValue() == dealerPlayer.getHand().get(i+1).getValue())
                packCounter++;
               //remove from dealer hand

        }

        return packCounter;
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
