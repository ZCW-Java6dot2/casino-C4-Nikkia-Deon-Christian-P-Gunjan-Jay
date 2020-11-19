package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import java.util.ArrayList;
import java.util.Collections;


import org.junit.Assert;
import org.junit.Test;


public class GoFishTest {




    @Test
    public void CheckPackTest(){
        //Given
        Player player1 = new Player();
        player1.setName("gunjan");
        player1.setBalance(4500D);
        Card card1 = new Card("Spades","8");
        Card card2 = new Card("Clubs","8");
        Card card3 = new Card("Hearts","8");
        Card card4 = new Card("Diamond","8");
        Card card5 = new Card("Diamond","Jack");
        Card card6 = new Card("Diamond","2");
        Card card7 = new Card("Spades","2");
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        cardForPlayer1.add(card2);
        cardForPlayer1.add(card3);
        cardForPlayer1.add(card4);
        cardForPlayer1.add(card5);
        cardForPlayer1.add(card6);
        cardForPlayer1.add(card7);
        Collections.sort(cardForPlayer1);
        player1.setHand(cardForPlayer1);
        player1.setPlayerNumber(1);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);

        GoFish gofish = new GoFish(players);
        //When
        ArrayList<Card> actual = gofish.checkPack(player1);

        //Then
        Assert.assertEquals(actual, player1.getHand() );

    }

    @Test
    public void checkIncrementPackCounter(){
        //Given
        Player player1 = new Player();
        player1.setName("gunjan");
        player1.setBalance(4500D);
        Card card1 = new Card("Spades","8");
        Card card2 = new Card("Clubs","8");
        Card card3 = new Card("Hearts","8");
        Card card4 = new Card("Diamond","8");
        Card card5 = new Card("Diamond","Jack");
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        cardForPlayer1.add(card2);
        cardForPlayer1.add(card3);
        cardForPlayer1.add(card4);
        cardForPlayer1.add(card5);
        player1.setHand(cardForPlayer1);
        player1.setPlayerNumber(1);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);

        Card card6 = card1;
        Card card7 = card2;
        Card card8 = card3;
        Card card9 = card4;
        ArrayList<Card> removePack = new ArrayList<Card>();
        removePack.add(card6);
        removePack.add(card7);
        removePack.add(card8);
        removePack.add(card9);

        GoFish gofish = new GoFish(players);
        //When
         gofish.incrementBin(1);

        //Then
        Assert.assertEquals(Integer.valueOf("1"),gofish.getBin(1));

    }





}
