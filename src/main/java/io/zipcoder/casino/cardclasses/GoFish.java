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

        //   card=new Card();
       deck = new CardDeck();
       this.card = card;

        deck = new CardDeck();

    }

    public GoFish(ArrayList<Player> players) {
        super(players);
    }

   public void runGoFish()
   {
       //List of methods
       super.initialCardsGiven(1);
       this.displayInitialHand();
   }

   public void displayInitialHand(){
       console.println("***************************************************************************************************************");
       for (int i = 0; i < players.size(); i++) {
           console.println("Hand for Player > %s is %s" , players.get(i).getName().toUpperCase() , super.getPlayers().get(i).getHand());
       }
       console.println("***************************************************************************************************************");

       this.playerTurn();
    }

    public void playerTurn(){
         Integer opponentNumber = 0;
         String opponentValue ="";

        for (int i = 0; i < players.size(); i++) {

            console.println("Please select opponent player number from below :");
            for(int x=0 ;x < players.size() ;x++)
            {
                console.println("Number %d for %s" , players.get(x).getPlayerNumber() ,players.get(x).getName().toUpperCase());
            }
            opponentNumber = console.getIntegerInput("Enter the number here :  ");


               opponentValue = console.getStringInput("Please enter the value of the card: ");

                       this.askForCard(players.get(i), players.get(opponentNumber-1), opponentValue);
            }

    }

    public Boolean askForCard(Player dealerPlayer ,Player opponentPlayer,String opponentValue) {
      ArrayList<Card> opponentPlayerHand=opponentPlayer.getHand();
      Boolean cardFound=false;
      Collections.sort(opponentPlayerHand);

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
                console.println("Go Fish....%s" , dealerPlayer.getName().toUpperCase());
                console.println("***************************************************************************************************************");
                dealerPlayer.addCard(deck.getDeck().pop());
            }
               checkPack(dealerPlayer);
          return cardFound;
        }

    public void removeCardFromPlayer(Card card, Player opponentPlayer) {
        opponentPlayer.getHand().remove(card);
    }

    public void addCardToHand(Player dealerPlayer, Card card) {
        console.println("The card of %s of %s has been given to %s",card.getValue() ,card.getSuit(),dealerPlayer.getName().toUpperCase());
        console.println("***************************************************************************************************************");
        dealerPlayer.addCard(card);
      //  return dealerPlayer;

    }




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
