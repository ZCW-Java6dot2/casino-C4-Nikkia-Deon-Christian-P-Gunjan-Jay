package io.zipcoder.casino.cardclasses;


import io.zipcoder.casino.utilities.Player;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CardGameTest {



    @Test
    public void constructorTest(){
        //Given
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        Player player1 = new Player(); // this will have to be fleshed out once player is done
        expectedPlayers.add(player1);
        CardGame game = new CardGame(expectedPlayers);

        //When
        ArrayList<Player> actualPlayers = game.getPlayers();

        //Then
        Assert.assertEquals(expectedPlayers, actualPlayers);


    }

    @Test
    public void testInitialCardsGiven(){
        //Given
        ArrayList<Player> players = new ArrayList<Player>();
        Player player1 = new Player();
        Player player2 = new Player();
        players.add(player1);
        players.add(player2);
        CardGame game = new CardGame(players);
        Integer expectedCards = 7;

        //When
        game.initialCardsGiven(1); //1 will be for gameType GoFish;
        Integer actualCards1 = game.getPlayers().get(0).getHand().size();
        Integer actualCards2 = game.getPlayers().get(1).getHand().size();

        //Then
        Assert.assertEquals(expectedCards, actualCards1);
        Assert.assertEquals(expectedCards, actualCards2);

    }

    @Test
    public void addPlayerTest(){
        //Given
        ArrayList<Player> players = new ArrayList<Player>();
        CardGame game = new CardGame(players);
        Player expectedPlayer = new Player();
        game.addPlayer(expectedPlayer);

        //When
        Player actualPlayer = game.getPlayers().get(0);

        //Then
        Assert.assertEquals(expectedPlayer, actualPlayer);
    }

    public void removePlayerTest(){
        //Given
        ArrayList<Player> players = new ArrayList<Player>();
        CardGame game = new CardGame(players);
        Player expectedPlayer = new Player();
        game.addPlayer(expectedPlayer);
        Integer expectedSize = 0;

        //When
        game.removePlayer(expectedPlayer);
        Integer actualSize = game.getPlayers().size();

        //Then
        Assert.assertEquals(expectedSize, actualSize);
    }

}
