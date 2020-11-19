package io.zipcoder.casino.cardclasses;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class CardDeckTest {

    @Test
    public void testConstructor(){
        //Given
        // We want to make sure that the deck is of size 52 and there are cards in it
        Integer expectedDeckSize = 52;
        Boolean expectedCardPresent = true;
        CardDeck deck = new CardDeck();

        //When
        Integer actualDeckSize = deck.getDeck().size();
        Boolean actualCardPresent = !(deck.getDeck().pop() == null);

        //Then
        Assert.assertEquals(expectedDeckSize, actualDeckSize);
        Assert.assertEquals(expectedCardPresent, actualCardPresent);
    }

    @Test
    public void testShuffleDeck(){
        //Given
        Random seed = new Random(52);
        Integer seedGenerated = seed.nextInt(52);
        Random random = new Random(seedGenerated);

        CardDeck cardDeck = new CardDeck();
        Stack<Card> expectedDeck = (Stack<Card>) (cardDeck.getDeck().clone());
        Collections.shuffle(expectedDeck, random);

        //When
        cardDeck.shuffleDeck();
        Stack<Card> actualDeck = cardDeck.getDeck();

        //Then
        Assert.assertEquals(expectedDeck, actualDeck);
    }
}
