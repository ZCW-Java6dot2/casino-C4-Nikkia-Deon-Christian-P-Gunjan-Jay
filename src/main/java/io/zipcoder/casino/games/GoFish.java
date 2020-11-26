package io.zipcoder.casino.games;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.Card;
import io.zipcoder.casino.cardclasses.CardDeck;
import io.zipcoder.casino.cardclasses.CardGame;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;

import java.util.Collections;
import java.util.ArrayList;

public class GoFish extends CardGame {
    private Card card;
    private static Console console = new Console(System.in, System.out);
    ArrayList<Integer> packTracker;

    public GoFish() {
        super();
        deck = new CardDeck();
    }

    public GoFish(ArrayList<Player> players) {
        super(players);
        this.packTracker = new ArrayList<Integer>();
        for (int i = 0; i < players.size() + 1; i++) {
            this.packTracker.add(0);
        }
    }

    // The first method will be  called from Menu
    public void runGoFish() {
        super.initialCardsGiven(1);
        this.displayInitialHand();
    }

    //Display Initial hands given from CardDeck and go to next method playerTurn()
    public void displayInitialHand() {
        for (int i = 0; i < players.size(); i++) {
            Collections.sort(super.getPlayers().get(i).getHand());
            String playerName = players.get(i).getName().toUpperCase();
            console.println("\u001B[36mHand for Player > %s\u001B[0m is %s", playerName, super.getPlayers().get(i).getHand());
            console.println("************************************************************************************");
        }
        this.playerTurn();
    }

    // Displays hands which are manipulated during the game.
    public void printHand(Player player) {
        Collections.sort(player.getHand());
        String playerName = player.getName().toUpperCase();
        console.println("\u001B[36mHand for Player > %s\u001B[0m is %s", playerName, player.getHand());
    }

    // List the players number and declareWinner based on Deck Empty
    public void playerTurn() {
        displayPlayerNames();
        if (!isDeckEmpty()) // if deck is empty , do not continue
        {
            playerTurn();
        } else {
            declareWinner();
            Menu menu = new Menu();
            menu.selectCardOrDice();
        }
    }

    // Displays the players number for selection and asks for Value
    private void displayPlayerNames() {
        Integer opponentNumber = 0;
        String opponentValue = "";
        for (int i = 0; i < players.size(); i++) {

            console.println("%s please select opponent player number from below :", players.get(i).getName().toUpperCase());
            for (int x = 0; x < players.size(); x++) {
                checkPack(players.get(i));//On Load,check if pack is done

                if (x != i) {
                    String playerName = players.get(x).getName().toUpperCase();
                    console.println("%d for %s", players.get(x).getPlayerNumber(), playerName);
                }
            }
            printHand(players.get(i));
            opponentNumber = console.getIntegerInput("Enter the player number here :  ");
            inValidEntryCheck(opponentNumber);
            opponentValue = console.getStringInput("Please enter the value of the card: ");
            //add condition to check if the value is correct!!!
            this.askForCard(players.get(i), players.get(opponentNumber - 1), opponentValue);
        }
    }

    //Verify right player is selected
    private void inValidEntryCheck(Integer opponentNumber) {
        if (opponentNumber > players.size()) {
            System.out.println("Invalid entry , select again");
            playerTurn();
        }
    }

    //Player 1 will ask Player 2 a card of specific Value , it will be given by Player 2 OR Popped from deck
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
        this.giveMoreCards(opponentPlayer);
        this.giveMoreCards(dealerPlayer);
        checkPack(dealerPlayer); //As soon we manipulate the cards, check if Pack has been formed
        //printHand();
        return cardFound;
    }

    //Make arrayList of cards to be removed once all cards in hand has been checked
    public Boolean extractCardsForRemoval(Player dealerPlayer, Player opponentPlayer, String opponentValue, ArrayList<Card> opponentPlayerHand, Boolean cardFound, ArrayList<Card> cardsToAddandRemove) {
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
        if (!cardFound && !isDeckEmpty()) {
            console.println("\u001B[36mGo Fish....%s", dealerPlayer.getName().toUpperCase());
            dealerPlayer.addCard(deck.getDeck().pop());
            String displayCard = String.valueOf(dealerPlayer.getHand().get(dealerPlayer.getHand().size() - 1));
            String displayName = dealerPlayer.getName().toUpperCase();
            console.println("Card %s added to your hand from the deck %s\u001B[0m", displayCard, displayName);

        }
    }

    //Remove the cards from players collected by extractCardsForRemoval()
    public void removeCardFromPlayer(Player opponentPlayer, ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            opponentPlayer.getHand().remove(cards.get(i));
        }
    }

    //Add a card to hand given by opponent player
    public void addCardToHand(Player dealerPlayer, Card card) {
        String displayName = dealerPlayer.getName().toUpperCase();
        console.println("\u001B[36mThe card of %s of %s has been given to %s\u001B[0m ", card.getValue(), card.getSuit(), displayName);
        dealerPlayer.addCard(card);
    }

    //Check if the cards have pack of 4 cards with same value
    //if present increment the counter and collect the cards in an ArrayList
    public Integer checkPack(Player dealerPlayer) {
        Collections.sort(dealerPlayer.getHand());
        ArrayList<Card> packInCard = new ArrayList<Card>();
        int countDuplicates = 0;
        for (int j = 0; j < dealerPlayer.getHand().size() - 1; j++) {
            if (countDuplicates == 3)
                break;
            for (int k = j + 1; k < dealerPlayer.getHand().size(); k++) {

                if (dealerPlayer.getHand().get(j).getValue().equals(dealerPlayer.getHand().get(k).getValue())) {
                    countDuplicates++;
                    packInCard.add(dealerPlayer.getHand().get(k));
                    if (countDuplicates == 3) {
                        packInCard.add(dealerPlayer.getHand().get(j));
                        removePackFromHand(packInCard, dealerPlayer);
                        packInCard.clear();
                        break;
                    }
                } else {
                    countDuplicates = 0;
                    packInCard.clear();
                    break;
                }
            }
        }
        return countDuplicates;
    }

    //Remove the pack cards collected in the checkPack() method
    public void removePackFromHand(ArrayList<Card> packCards, Player dealerPlayer) {
        for (int i = 0; i < packCards.size(); i++) {
            dealerPlayer.getHand().remove(packCards.get(i));
        }
        incrementBin(dealerPlayer.getPlayerNumber()); //Everytime a pack is formed, increment the counter for that player
        this.giveMoreCards(dealerPlayer); //Check if hand is empty , add more cards
    }

    //If the player does not have cards, give 5 more from deck
    public void giveMoreCards(Player player) {
        if (player.getHand().size() == 0) {
            if (!isDeckEmpty()) {
                for (int i = 0; i < 5; i++) {
                    this.popFromDeck(player, false);
                }
            }
        }
    }

    public void incrementBin(Integer indexForCounter) {
        Integer a = packTracker.get(indexForCounter);
        a++;
        packTracker.set(indexForCounter, a);
    }

    public Integer getBin(Integer index) {
        return packTracker.get(index);
    }

    //declareWinner
    private void declareWinner() {
        if (packTracker.size() > 0) {
            double highest = packTracker.get(0);
            int highestIndex = 0;

            for (int s = 0; s < packTracker.size(); s++) {
                double curValue = packTracker.get(s);
                if (curValue > highest) {
                    highest = curValue;
                    highestIndex = s;
                }

            }
            if (highestIndex == 0)
                console.println("No winner.....");
            else
                console.println("The WINNER is %s ", players.get(highestIndex - 1).getName().toUpperCase());

        }
    }
}
