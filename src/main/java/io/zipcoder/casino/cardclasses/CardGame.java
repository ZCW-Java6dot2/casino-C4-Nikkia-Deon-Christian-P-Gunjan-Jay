package io.zipcoder.casino.cardclasses;


import io.zipcoder.casino.Player;
import io.zipcoder.casino.Interfaces.Game;

import java.util.ArrayList;
//implements Game
public class CardGame implements Game{
   protected ArrayList<Player> players;
    CardDeck deck;

    public CardGame(ArrayList<Player> players) {
        this.players = players;
        deck = new CardDeck();
    }

    public CardGame() {

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void initialCardsGiven(int gameType) {
        switch (gameType) {
            case 1: //GoFish
                goFishDecideByPlayers();
        }
        }

    private void goFishDecideByPlayers() {
        if (players.size() == 2) {
            for (Player p : players) {
                for (int i = 0; i < 7; i++) {
                    p.addCard(deck.getDeck().pop());

                 }
               deck.shuffleDeck();
              }
           }
        else {
             for (Player p : players) {
                for (int i = 0; i < 5; i++) {
                    p.addCard(deck.getDeck().pop());
                }
               deck.shuffleDeck();
            }
        }
    }



    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void displayHand(Player player){
        StringBuilder output = new StringBuilder(player.getName() + "'s hand looks like: ");
        for (Card card: player.getHand()) {
            output.append(card.getValue()).append("of").append(card.getSuit());
        }
        System.out.println(output.toString());
    }




}
