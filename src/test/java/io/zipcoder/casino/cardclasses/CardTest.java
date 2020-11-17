package io.zipcoder.casino.cardclasses;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void constructorTest(){
        //Given
        String expectedSuit = "diamonds";
        String expectedValue = "king";
        Card newCard = new Card(expectedSuit, expectedValue);

        //When
        String actualSuit = newCard.getSuit();
        String actualValue = newCard.getValue();

        //Then
        Assert.assertEquals(expectedSuit, actualSuit);
        Assert.assertEquals(expectedValue, actualValue);

    }

    @Test
    public void testToString(){
        //Given
        String expectedSuit = "Diamonds";
        String expectedValue = "King";
        Card card = new Card(expectedSuit, expectedValue);
        String expectedString = "King of Diamonds";

        //When
        String actualString = card.toString();

        //Then
        Assert.assertEquals(expectedString, actualString);

    }


}
