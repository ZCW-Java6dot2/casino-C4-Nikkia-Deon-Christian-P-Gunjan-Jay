package io.zipcoder.casino;

import io.zipcoder.casino.cardclasses.Card;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {

    /*public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

     */
    @Test
    public void testDefaultConstructor() {
        String expectedName = "";
        Double expectedBalance = 0d;
        ArrayList<Card> expectedHand = new ArrayList<>();

        Player player = new Player();

        String actualName = player.getName();
        Double actualBalance = player.getBalance();
        ArrayList<Card> actualHand = player.getHand();

        Assert.assertEquals(actualName, expectedName);
        Assert.assertEquals(actualBalance, expectedBalance);
        Assert.assertEquals(actualHand, expectedHand);
    }

    @Test
    public void testConstructorWithName() {
        String expectedName = "Mike";

        Player player = new Player(expectedName);


        //player.setName(expectedName);
        String actualName = player.getName();

        Assert.assertEquals(actualName, expectedName);
    }

    @Test
    public void testConstructorWithNameBalance() {
        String expectedName = "Mike";
        Double expectedBalance = 500.0;

        Player player = new Player(expectedName, expectedBalance);


        //player.setName(expectedName);
        String actualName = player.getName();
        Double actualBalance = player.getBalance();

        Assert.assertEquals(actualName, expectedName);
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testConstructorWithNameBalanceHand() {
        String expectedName = "Mike";
        Double expectedBalance = 500.0;
        ArrayList<Card> expectedHand = new ArrayList<>();
        Card card = new Card("Spades","4");
        expectedHand.add(card);

        Player player = new Player(expectedName, expectedBalance, expectedHand);


        String actualName = player.getName();
        Double actualBalance = player.getBalance();
        ArrayList<Card> actualHand = player.getHand();

        Assert.assertEquals(actualName, expectedName);
        Assert.assertEquals(expectedBalance, actualBalance);
        Assert.assertEquals(expectedHand, actualHand);
    }

    @Test
    public void SetNameTest() {
        Player player = new Player();
        String expectedName = "Mike";

        player.setName(expectedName);
        String actualName = player.getName();

        Assert.assertEquals(actualName, expectedName);
    }

    @Test
    public void PlayerWagerTest() {
        Player player = new Player();
        Double expectedWager = 60.0;

        Double actualWager = player.playerWager(expectedWager);

        Assert.assertEquals(expectedWager, actualWager);
    }

    @Test
    public void SetBalanceTest() {
        Player player = new Player();
        Double expectedBalance = 100.0;

       player.setBalance(expectedBalance);
       Double actualBalance = player.getBalance();

        Assert.assertEquals(actualBalance, expectedBalance);
    }

    @Test
    public void SetPlayerNumberTest() {
        Player player = new Player();
       Integer expectedNumber = 5;

        player.setPlayerNumber(expectedNumber);
        Integer actualNumber = player.getPlayerNumber();

        Assert.assertEquals(actualNumber, expectedNumber);
    }

    @Test
    public void SetHandTest() {
        Player player = new Player();

        ArrayList<Card> expectedHand = new ArrayList<>();
        Card card = new Card("Spades","4");
        expectedHand.add(card);

        player.setHand(expectedHand);
        ArrayList<Card> actualHand = player.getHand();

        Assert.assertEquals(expectedHand, actualHand);
    }

    @Test
    public void SetCardTest() {
        Player player = new Player();

        Card expectedCard = new Card("Spades","4");


        player.setCard(expectedCard);
        Card actualCard = player.getCard();

        Assert.assertEquals(expectedCard, actualCard);
    }

    @Test
    public void AddCardTest() {
        Player player = new Player();

        Card cardToAddToHand = new Card("Spades","4");
        player.addCard(cardToAddToHand);

        ArrayList<Card> actualCardInHand = player.getHand();

        Assert.assertTrue(actualCardInHand.contains(cardToAddToHand));
    }

    @Test
    public void RemoveCardTest() {
        Player player = new Player();

        Card cardToAddToHand = new Card("Spades","4");
        player.addCard(cardToAddToHand);
        player.removeCard(cardToAddToHand);
        ArrayList<Card> actualCardInHand = player.getHand();

        Assert.assertFalse(actualCardInHand.contains(cardToAddToHand));
    }



}
