package io.zipcoder.casino.games;
import io.zipcoder.casino.Interfaces.GamblingGameInterface;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.Card;
import io.zipcoder.casino.cardclasses.CardDeck;
import io.zipcoder.casino.cardclasses.CardGame;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.ConsoleOutput;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
public class BlackJack extends CardGame implements GamblingGameInterface, Game {
    private Player dealer = new Player("Dealer");
    private ConsoleOutput consoleOutput = new ConsoleOutput();
    private Console console = consoleOutput.getConsole();
    private String currentPlayer = null;
    private CardDeck cardDeck = new CardDeck();
    private Card Card;
    private ArrayList<Player> playersAtTable;
    private HashMap<Player, Double> playersBet = new HashMap<Player, Double>();
    private boolean inSession = true;
    public BlackJack(ArrayList<Player> players) {
        super(players);
    }
    public void runGame(){
        playersAtTable = players;
        while(inSession) {
            cardDeck.shuffleDeck();
            takeAllBets();
            printDelay();
            dealingTheCardsOut();
            askAllPlayersHitOrStand();
            findWinner();
            clearCards();
            isDeckEmpty();
            exitGame();
            //method to break loop (inSession = false);
            // method to return true or false if player wants to exit the game
        }
    }
    public void takeAllBets() {
        for (int i = 0; i < playersAtTable.size(); i++) {
            console.println("Welcome to the BlackJack Table " + playersAtTable.get(i).getName(), System.in);
            boolean hasEnough = false;
            Double amount = 0.0;
            do {
                amount = console.getDoubleInput("How much would you like to bet?");
                Player p = playersAtTable.get(i);
                if (confirmTransaction(p, amount)) {
                    hasEnough = true;
                } else {
                    console.println("Insufficient funds. Please bet a lower amount.");
                    exitGame();
                }
            } while (!hasEnough);
            this.playersBet.put(playersAtTable.get(i), amount);
        }
    }
    public void dealingTheCardsOut(){
        dealer.addCard(cardDeck.drawACard());
        printDelay();
        displayHand(dealer);
        for (Player currentPlayer : playersAtTable)
        {
            printDelay();
            currentPlayer.addCard(cardDeck.drawACard());
            displayHand(currentPlayer);
        }
        printDelay();
        dealer.addCard(cardDeck.drawACard());
        printDelay();
        console.println("Dealer draws a Card");
        printDelay();
        for (Player currentPlayer : playersAtTable)
        {
            currentPlayer.addCard(cardDeck.drawACard());
            printDelay();
            displayHand(currentPlayer);
        }
        dealer.addCard(cardDeck.drawACard());
        displayHand(dealer);
    }
    public Integer getCardValue(Card card){
        card.getValue();
        if (card.getValue().equals("Jack") || card.getValue().equals("Queen") || card.getValue().equals("King")){
            return 10;
        } else if (card.getValue().equals("Ace")){
            return 11; // Figure out how to add "Ace" as 1 as well
        } else {
            return Integer.valueOf(card.getValue());
        }
    }
    public Integer getHandValue(Player player){
        Integer answer = 0;
        for (Card card: player.getHand()) {
            answer += getCardValue(card);
        }
        return answer;
    }
    public void displayHand(Player player){
        StringBuilder output = new StringBuilder("Looks like " + player.getName() + " has: ");
        for (Card card: player.getHand()) {
            output.append(card.getValue()).append(" of ").append(card.getSuit() + " ");
        }
        console.println(output.toString());
        printDelay();
    }
    public enum handState{
        UNDER,
        BLACKJACK,
        BUST
    }
    public handState stateOfTheHand(Integer handValue){
        printDelay();
        if(handValue < 21){
            return handState.UNDER;
        } else if (handValue == 21){
            return handState.BLACKJACK;
        } else
            return handState.BUST;
    }
    public void askPlayerHitOrStand(Player player) {
        displayHand(player);
        if (stateOfTheHand(getHandValue(player)).equals(handState.UNDER)) {
            String hitOrStand = console.getStringInput("Do you want to hit or stand?", System.in);
            if (hitOrStand.equalsIgnoreCase("Hit")) {
                player.addCard(cardDeck.drawACard());
                askPlayerHitOrStand(player);
            } else if (hitOrStand.equalsIgnoreCase("Stand")) {
                displayHand(player);
                // Do nothing
            } else {
                console.println("You must type Hit or Stand");
                askPlayerHitOrStand(player);
            }
        } else if(stateOfTheHand(getHandValue(player)).equals(handState.BLACKJACK)){
            console.println(player.getName() + " hit BlackJack!");
        }
    }
    public void askAllPlayersHitOrStand(){
        printDelay();
        for (Player player: playersAtTable) {
            askPlayerHitOrStand(player);
        }
    }
    public void findWinner(){
        console.println("Dealer had " + getHandValue(dealer));
        //iterate over players
        for (Player p : players) {
            console.println(p.getName() + " had " + getHandValue(p)); // just added
            //if player's hand is greater than dealer's but less than or equal to 21
            if ((getHandValue(p) > getHandValue(dealer)) && getHandValue(p) <= 21){
                printDelay();
                console.println(p.getName() + " is a winner!");
                printDelay();
                Double payout = playersBet.get(p).doubleValue() * 2 ;
                console.println(p.getName() + " won " + payout); // How to add for each player how much they won
                p.setBalance(p.getBalance() + payout);
                console.println(p.getName() + " balance is " + p.getBalance());
                console.println(p.getName(), 0.0);
                console.println("Current balance: " + p.getBalance());
                printDelay();
            } else if ((getHandValue(dealer) > getHandValue(p)) && getHandValue(dealer) <= 21){
                printDelay();
                console.println("The dealer wins");
                printDelay();
            } else if (((getHandValue(p) < getHandValue(dealer)) && getHandValue(p) <= 21) && getHandValue(dealer) > 21 ){
                printDelay();
                console.getStringInput(p.getName() + " is a winner!");
                Double payout = playersBet.get(p).doubleValue() * 2 ;
                console.println(p.getName() + " won " + payout); // How to add for each player how much they won
                p.setBalance(p.getBalance() + payout);
                console.println(p.getName() + " balance is " + p.getBalance());
                console.println(p.getName(), 0.0);
                console.println("Current balance: " + p.getBalance());
            }
            //that player wins
        }
    }
    public void exitGame(){ // Trying to remove player if he has no funds
        for (Player p : playersAtTable){
            if (p.getBalance() < p.playerWager(0.0)){
                console.println(p.getName() + ", I am so sorry you are out of funds.");
            }
        }
    }
    public void clearCards(){
        for (Player player : playersAtTable){
            player.getHand().clear();
        }
        dealer.getHand().clear();
    }
    public void printDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public boolean confirmTransaction(Player p, Double amt) {
        if (amt > p.getBalance()) {
            return false;
        }
        p.setBalance(p.getBalance() - amt);
        return true;
    }
    public void addPlayer(Player player) { }
    public void payout(Double amount, Player player) { }
    public void addPlayer(){ }
    public void removePlayer() { }
    public void takeBets(Double amount) { }
    public void getPlayerAction(ArrayList<Card> dealersHand){ }
}