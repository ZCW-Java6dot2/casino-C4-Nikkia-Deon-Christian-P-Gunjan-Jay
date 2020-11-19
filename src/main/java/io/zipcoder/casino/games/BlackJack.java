package io.zipcoder.casino.games;

import io.zipcoder.casino.Interfaces.GamblingGameInterface;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.Card;
import io.zipcoder.casino.cardclasses.CardDeck;
import io.zipcoder.casino.cardclasses.CardGame;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.ConsoleOutput;

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
    private HashMap<Player, Double> playersBet;

    public BlackJack(ArrayList<Player> players) {

        super(players);
    }

    public enum handState{
        UNDER,
        BLACKJACK,
        BUST
    }

    public void runGame(){
        playersAtTable = getPlayers();
        takeAllBets();
        dealingTheCardsOut();
        askAllPlayersHitOrStand();


    }



    public void payout(Double amount, Player player) {
        /* Players collect money


         */

    }

    public void takeAllBets() {
        for (int i = 0; i < playersAtTable.size(); i++) {
            Double currentBet = console.getDoubleInput("Welcome to the Table "+ playersAtTable.get(i).getName() + ", what's your bet", System.in);
            this.playersBet.put(getPlayers().get(i), currentBet);
        }

        /* Players places wagers
           Players collect money
           Players lose money
           Must be an amount that they have
           Players cannot bet with more than they have

         */
    }
    public void displayHand(Player player){
        StringBuilder output = new StringBuilder(player.getName() + "'s hand looks like: ");
        for (Card card: player.getHand()) {
            output.append(card.getValue()).append("of").append(card.getSuit());


        }
        System.out.println(output.toString());
    }
    public void dealingTheCardsOut(){
        dealer.addCard(cardDeck.drawACard());
        displayHand(dealer);
        for (Player currentPlayer : playersAtTable)
        {
            currentPlayer.addCard(cardDeck.drawACard());
            displayHand(currentPlayer);
        }
        dealer.addCard(cardDeck.drawACard());
        System.out.println("Dealer draws a Card");
        for (Player currentPlayer : playersAtTable)
        {
            currentPlayer.addCard(cardDeck.drawACard());
            displayHand(currentPlayer);
        }


    }

    public void askPlayerHitOrStand(Player player) {
        displayHand(player);
        if (stateOfTheHand(getHandValue(player)).equals(handState.UNDER)) {

            String hitOrStand = console.getStringInput("Do you want to hit or stand?", System.in);
            if (hitOrStand.equals("Hit")) {
                player.addCard(cardDeck.drawACard());
                askPlayerHitOrStand(player);
            } else if (hitOrStand.equals("Stand")) {
                displayHand(player);
            // Do nothing
            } else {
                System.out.println("You must type Hit or Stand");
                askPlayerHitOrStand(player);
        }

        } else if(stateOfTheHand(getHandValue(player)).equals(handState.BLACKJACK)){
            System.out.println("You hit BlackJack!");
            Double payout = playersBet.get(player).doubleValue() * 2 ;
            player.setBalance(player.getBalance() + payout);
            playersBet.replace(player,0.);
        } else if(stateOfTheHand(getHandValue(player)).equals(handState.BUST)){

        }

    }

    public void askAllPlayersHitOrStand(){
        for (Player player: playersAtTable) {
            askPlayerHitOrStand(player);
        }
    }


    public Integer getCardValue(Card card){
        card.getValue();
        if (card.getValue().equals("Jack") || card.getValue().equals("Queen") || card.getValue().equals("King") || card.getValue().equals("Ace")){
            return 10;
        } else if (card.getValue().equals("Ace")){
            return 1;
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

    public handState stateOfTheHand(Integer handValue){
        if(handValue < 21){
            return handState.UNDER;
        } else if (handValue == 21){
            return handState.BLACKJACK;
        } else
            return handState.BUST;
    }

    public void addPlayer(Player player) {

    }

    public void addPlayer(){
    }

    public void removePlayer() {
        /* As players leave, we remove the player from the game
           If player loses all money, he is removed from game
         */
    }
    public void takeBets(Double amount) {

        /* Players places wagers
           Players collect money
           Players lose money
           Must be an amount that they have
           Players cannot bet with more than they have

         */

    }
    public void getPlayerAction(ArrayList<Card> dealersHand){

        /* If playerHand > dealerHand && playerHand <= 21 -> playerHand wins!
           If playerHand < dealerHand && dealerHand <= 21 -> dealerHand wins!
           If playerHand == dealerHand -> push!
           If playerHand > 21 && dealerHand < 21 -> dealerHand wins
           If playerHand > 21 -> playerHand automatically loses!
           If dealerHand > 21 -> dealerHand Bust!
           If playerHand < 21 -> dealerHand > 21 -> playerHand wins, dealer bust!
           If playerHand == 21 -> playerHand automatically wins!

           If playerHand hits it adds another card++
           If playerHand stands doesn't take anymore cards

           Hit & STAND
           String method for what player wants to do
           - main logic loop
                  String command = console.getStringInput("What would you like to do?");
                  handleCommand(command);

                handleCommand(String command){
                switch (command){
                case "hit":
                //add a card to the player's hand
                whicheverPlayer'sTurnItIs.addCard(cardDeck.pop()) ---- currentPlayer
                break;
                }
         */
    }
}
