package io.zipcoder.casino.cardclasses;

import io.zipcoder.casino.utilities.Player;

import java.util.ArrayList;

public class CardGame {
    ArrayList<Player> players;
    CardDeck deck;

    public CardGame(ArrayList<Player> players) {
        this.players = players;
        deck = new CardDeck();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void initialCardsGiven(int gameType) {
        switch (gameType){
            case 1: //GoFish
                for (Player p : players){
                    for (int i = 0; i < 7; i++) {
                        p.addCard(deck.getDeck().pop());
                    }
                }
        }

    }

    public void addPlayer(Player newPlayer) {
        players.add(newPlayer);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
