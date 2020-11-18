package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.Console;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class GoFish extends CardGame {

    private Card card;
    private static Console console = new Console(System.in, System.out);

    public GoFish() {
        super();
        deck = new CardDeck();
    }

    public GoFish(ArrayList<Player> players) {
        super(players);
    }

    public void runGoFish() {
        //List of methods
        super.initialCardsGiven(1);
        this.displayInitialHand();
    }

    public void displayInitialHand() {
        console.println("***************************************************************************************************************");
        for (int i = 0; i < players.size(); i++) {
            console.println("Hand for Player > %s is %s", players.get(i).getName().toUpperCase(), super.getPlayers().get(i).getHand());
        }
        console.println("***************************************************************************************************************");

        this.playerTurn();
    }

    public void playerTurn() {
        Integer opponentNumber = 0;
        String opponentValue = "";

        for (int i = 0; i < players.size(); i++) {

            console.println("Please select opponent player number from below :");
            for (int x = 0; x < players.size(); x++) {
                console.println("Number %d for %s", players.get(x).getPlayerNumber(), players.get(x).getName().toUpperCase());
            }
            opponentNumber = console.getIntegerInput("Enter the number here :  ");
            if(opponentNumber > players.size())
            {
                System.out.println("Invalid entry , select again");
                playerTurn();
            }
            opponentValue = console.getStringInput("Please enter the value of the card: ");
            this.askForCard(players.get(i), players.get(opponentNumber - 1), opponentValue);
        }

    }

    public Boolean askForCard(Player dealerPlayer, Player opponentPlayer, String opponentValue) {
        ArrayList<Card> opponentPlayerHand = opponentPlayer.getHand();
        Boolean cardFound = false;
        Collections.sort(opponentPlayerHand);

        for (int i = 0; i < opponentPlayerHand.size(); i++) {
            if (opponentPlayerHand.get(i).getValue().equalsIgnoreCase(opponentValue)) {
                cardFound = true;
                //updateHand(dealerPlayer, opponentPlayerHand, i);
                //check if pack
                addCardToHand(dealerPlayer, opponentPlayerHand.get(i));
                removeCardFromPlayer(opponentPlayerHand.get(i), opponentPlayer);
            }
        }
        if (!cardFound) {
            console.println("Go Fish....%s", dealerPlayer.getName().toUpperCase());
            dealerPlayer.addCard(deck.getDeck().pop());
            console.println("Card %s added to your hand %s from the deck" ,dealerPlayer.getHand().get(dealerPlayer.getHand().size()-1),dealerPlayer.getName().toUpperCase());
            console.println("***************************************************************************************************************");
        }
        checkPack(dealerPlayer);
        return cardFound;
    }

    public void removeCardFromPlayer(Card card, Player opponentPlayer) {
        opponentPlayer.getHand().remove(card);
    }

    public void addCardToHand(Player dealerPlayer, Card card) {
        console.println("The card of %s of %s has been given to %s", card.getValue(), card.getSuit(), dealerPlayer.getName().toUpperCase());
        console.println("***************************************************************************************************************");
        dealerPlayer.addCard(card);
        //  return dealerPlayer;

    }


    public Integer checkPack(Player dealerPlayer) {
        Collections.sort(dealerPlayer.getHand());
        ArrayList<Card> packInCard = new ArrayList<Card>();
        Integer countDuplicates = 0;
        for (int j = 0; j < dealerPlayer.getHand().size() - 1; j++) {
            if (dealerPlayer.getHand().get(j).getValue().equals(dealerPlayer.getHand().get(j + 1).getValue())) {
                countDuplicates++;
                packInCard.add(dealerPlayer.getHand().get(j));
                if(countDuplicates==3)
                {
                    packInCard.add(dealerPlayer.getHand().get(j+1));
                }
               // packInCard.add(dealerPlayer.getHand().get(j + 1));
             //   removePackFromHand(dealerPlayer, j);

            }
        }
        System.out.println(packInCard);
         removePackFromHand(countDuplicates,packInCard,dealerPlayer);


        return countDuplicates;
    }

    //public void removePackFromHand(Player dealerPlayer, int j) {
   //     dealerPlayer.getHand().remove(j);
   // }

    public void removePackFromHand(Integer countDuplicates , ArrayList<Card> packCards , Player dealerPlayer){
      //  Integer packCounter=0;
        if(countDuplicates.equals(3)) {
            //  packCounter++;
            for (int i = 0; i < packCards.size(); i++) {
                if (dealerPlayer.getHand().get(0).equals(packCards.get(i))) {
                    dealerPlayer.getHand().remove(packCards.get(i));
                    //packCounter=0;
                }

            }
        }
    }



    public void declareWinner(){
        //check for each player the pack count

    }



}
