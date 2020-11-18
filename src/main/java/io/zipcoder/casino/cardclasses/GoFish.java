package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.Console;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;

public class GoFish extends CardGame {

    private Card card;
    private static Console console = new Console(System.in, System.out);
    ArrayList<Integer> packTracker ;//= new ArrayList<Integer>(10);

    public GoFish() {
        super();
        deck = new CardDeck();

    }

    public GoFish(ArrayList<Player> players) {
        super(players);
        this.packTracker=new ArrayList<Integer>();
        for(int i=0 ; i <= 7 ; i ++)
        {
            this.packTracker.add(0);
        }
    }
    // The first methods which will called from Menu
    public void runGoFish() {
        super.initialCardsGiven(1);
        this.displayInitialHand();
    }

    //Display Initial hands given and go to next method playerTurn()
    public void displayInitialHand() {

        for (int i = 0; i < players.size(); i++) {
            Collections.sort(super.getPlayers().get(i).getHand());
            console.println("\u001B[36mHand for Player > %s\u001B[0m is %s", players.get(i).getName().toUpperCase(), super.getPlayers().get(i).getHand());
        }

       this.playerTurn();
    }
 // Displays hands which are manipulated during the game.
    public void printHand() {

        for (int i = 0; i < players.size(); i++) {
            Collections.sort(super.getPlayers().get(i).getHand());
            console.println("\u001B[36mHand for Player > %s\u001B[0m is %s", players.get(i).getName().toUpperCase(), super.getPlayers().get(i).getHand());
        }
    }

// List the players for selection and accept the value
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

        if(players.size()!= 0)
        {
            playerTurn();
        }

    }
//Player 1 will ask Player 2 a card of specific Value
       public Boolean askForCard(Player dealerPlayer, Player opponentPlayer, String opponentValue) {
        ArrayList<Card> opponentPlayerHand = opponentPlayer.getHand();
        Boolean cardFound = false;
        Collections.sort(opponentPlayerHand);
        ArrayList<Card> cardsToAddandRemove = new ArrayList<Card>();
        cardFound = extractCardsForRemoval(dealerPlayer, opponentPlayer, opponentValue, opponentPlayerHand, cardFound, cardsToAddandRemove);
        if (cardFound) {
            removeCardFromPlayer(opponentPlayer, cardsToAddandRemove);
        }

        popFromDeck(dealerPlayer, cardFound);
        printHand();
        checkPack(dealerPlayer,dealerPlayer.getPlayerNumber());
        return cardFound;
    }

 //Make arrayList of cards to be removed once all cards in hand has been checked
    private Boolean extractCardsForRemoval(Player dealerPlayer, Player opponentPlayer, String opponentValue, ArrayList<Card> opponentPlayerHand, Boolean cardFound, ArrayList<Card> cardsToAddandRemove) {
        for (int i = 0; i < opponentPlayerHand.size(); i++) {
            if (opponentPlayerHand.get(i).getValue().equalsIgnoreCase(opponentValue)) {
                addCardToHand(dealerPlayer, opponentPlayerHand.get(i));
                cardsToAddandRemove.add(opponentPlayer.getHand().get(i));
                cardFound = true;
            }
        }
        return cardFound;
    }

    //If card is not found , Pop one from deck and add it to the Players hand
    private void popFromDeck(Player dealerPlayer, Boolean cardFound) {
        if (!cardFound) {
            console.println("\u001B[36mGo Fish....%s", dealerPlayer.getName().toUpperCase());
            dealerPlayer.addCard(deck.getDeck().pop());
            console.println("Card %s added to your hand from the deck %s\u001B[0m" , dealerPlayer.getHand().get(dealerPlayer.getHand().size()-1), dealerPlayer.getName().toUpperCase());
            console.println("***************************************************************************************************************");
        }
    }

    public void removeCardFromPlayer(Player opponentPlayer ,ArrayList<Card> cards)  {
        for(int i=0; i<cards.size();i++) {
            opponentPlayer.getHand().remove(cards.get(i));
        }

    }

    public void addCardToHand(Player dealerPlayer, Card card) {
        console.println("The card of %s of %s has been given to %s", card.getValue(), card.getSuit(), dealerPlayer.getName().toUpperCase());
        console.println("***************************************************************************************************************");
        dealerPlayer.addCard(card);
        //  return dealerPlayer;

    }

    public Integer checkPack(Player dealerPlayer,Integer playerNumber) {
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
            }
        }
        // System.out.println(packInCard);
         removePackFromHand(countDuplicates,packInCard,dealerPlayer,playerNumber);

        return countDuplicates;
    }

    public void removePackFromHand(Integer countDuplicates , ArrayList<Card> packCards , Player dealerPlayer,Integer playerNumber){

         if(countDuplicates.equals(3)) {
               for (int i = 0; i < packCards.size(); i++) {
                if (dealerPlayer.getHand().get(0).equals(packCards.get(i))) {
                    dealerPlayer.getHand().remove(packCards.get(i));
                }

            }
         incrementBin(playerNumber);
        }
        this.removePlayerOnEmptyHand(dealerPlayer);

    }

    public void removePlayerOnEmptyHand(Player player)
    {
        if(player.getHand().size() == 0 )
        {
            removePlayer(player);
        }

    }
    public void declareWinner(){
        //check for each player the pack count

    }

    public void incrementBin(Integer indexForCounter)
    {
        Integer a=packTracker.get(indexForCounter);
        a++;
        packTracker.set(indexForCounter,a);

    }

    public Integer getBin(Integer index)
    {
        return packTracker.get(index);
    }

}
