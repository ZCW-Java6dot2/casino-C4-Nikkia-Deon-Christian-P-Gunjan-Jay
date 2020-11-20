package io.zipcoder.casino.BlackJackTest;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.cardclasses.Card;
import io.zipcoder.casino.games.BlackJack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
public class BlackJackTest {
    //    private static final Integer BUST = ;
    @Before
    public void setup() {
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
    }
    @Test
    public void constructorTest() {
        //Given
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
        //When
        BlackJack blackJack = new BlackJack(expectedPlayers);
        ArrayList<Player> actualPlayers = blackJack.getPlayers();
        //Then
        Assert.assertEquals(expectedPlayers, actualPlayers);
    }
    @Test
    public void getCardValueTest() {
        //Given
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
        BlackJack blackJack = new BlackJack(expectedPlayers);
        Card card = new Card("Hearts", "Queen");
        Integer expectedValue = 10;
        //When
        Integer actualValue = blackJack.getCardValue(card);
        //Then
        Assert.assertEquals(expectedValue, actualValue);
    }
    @Test
    public void getCardValueTest2() {
        //Given
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
        BlackJack blackJack = new BlackJack(expectedPlayers);
        Card card = new Card("Diamonds", "2");
        Integer expectedValue = 2;
        //When
        Integer actualValue = blackJack.getCardValue(card);
        //Then
        Assert.assertEquals(expectedValue, actualValue);
    }
    @Test
    public void askPlayerToHitTest() {
        //Given
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
        BlackJack blackJack = new BlackJack(expectedPlayers);
        Card card1 = new Card("Diamonds", "5");
        Card card2 = new Card("Hearts", "King");
        Card card3 = new Card("Hearts", "King");
        Integer expectedValue = 25;
        BlackJack.handState expectedState = BlackJack.handState.BUST;
        //When
        BlackJack.handState actualValue = blackJack.stateOfTheHand(expectedValue);
        //Then
        Assert.assertEquals(expectedState, actualValue);
    }
    @Test
    public void askPlayerToHitTest2() {
        //Given
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
        BlackJack blackJack = new BlackJack(expectedPlayers);
        Card card1 = new Card("Diamonds", "5");
        Card card2 = new Card("Hearts", "King");
        Card card3 = new Card("Hearts", "6");
        Integer expectedValue = 21;
        BlackJack.handState expectedState = BlackJack.handState.BLACKJACK;
        //When
        BlackJack.handState actualValue = blackJack.stateOfTheHand(expectedValue);
        //Then
        Assert.assertEquals(expectedState, actualValue);
    }
    @Test
    public void askPlayerToHitTest3() {
        //Given
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
        BlackJack blackJack = new BlackJack(expectedPlayers);
        Card card1 = new Card("Diamonds", "3");
        Card card2 = new Card("Hearts", "Ace");
        Card card3 = new Card("Hearts", "4");
        Integer expectedValue = 18;
        BlackJack.handState expectedState = BlackJack.handState.UNDER;
        //When
        BlackJack.handState actualValue = blackJack.stateOfTheHand(expectedValue);
        //Then
        Assert.assertEquals(expectedState, actualValue);
    }
    @Test
    public void askPlayerToHitTestAce() {
        //Given
        Player p1 = new Player("Christian", 0.0);
        Player p2 = new Player("Deon", 0.0);
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(p1);
        expectedPlayers.add(p2);
        BlackJack blackJack = new BlackJack(expectedPlayers);
        Card card1 = new Card("Clubs", "King");
        Card card2 = new Card("Hearts", "Ace");
        Integer expectedValue = 21;
        BlackJack.handState expectedState = BlackJack.handState.BLACKJACK;
        //When
        BlackJack.handState actualValue = blackJack.stateOfTheHand(expectedValue);
        //Then
        Assert.assertEquals(expectedState, actualValue);
    }
}