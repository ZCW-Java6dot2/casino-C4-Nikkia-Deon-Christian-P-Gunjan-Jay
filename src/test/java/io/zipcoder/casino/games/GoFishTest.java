package io.zipcoder.casino.games;
import io.zipcoder.casino.Player;
import java.util.ArrayList;
import java.util.Collections;


import io.zipcoder.casino.cardclasses.Card;
import io.zipcoder.casino.cardclasses.CardDeck;
import io.zipcoder.casino.games.GoFish;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GoFishTest {

    private ArrayList<Card> cardForPlayer1 = new ArrayList<Card>();
    ArrayList cardForPlayer2 = new ArrayList<Card>();
    private ArrayList<Card> cardForPlayer3 = new ArrayList<Card>();
    private ArrayList<Player> players = new ArrayList<Player>();
    Player player1 = new Player();
    Player player2 = new Player();
    Player player3 = new Player();
    Card card1 = new Card("Spades","4");
    Card card2 = new Card("Clubs","4");
    Card card3 = new Card("Hearts","4");
    Card card4 = new Card("Diamond","4");
    Card card5 = new Card("Diamond","Jack");
    Card card6 = new Card("Spades","10");
    Card card7 = new Card("Clubs","2");
    Card card8 = new Card("Hearts","2");
    Card card9 = new Card("Diamond","8");
    Card card10 = new Card("Diamond","Queen");
    @Before
    public void startup()
    {
      //  ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        cardForPlayer1.add(card2);
        cardForPlayer1.add(card3);
        cardForPlayer1.add(card4);
        cardForPlayer1.add(card5);
        cardForPlayer1.add(card6);
        cardForPlayer1.add(card7);
        cardForPlayer1.add(card8);
        cardForPlayer1.add(card9);
        Collections.sort(cardForPlayer1);
        player1.setName("GUNJAN");
        player1.setBalance(4500D);
        player1.setHand(cardForPlayer1);
        player1.setPlayerNumber(1);
        players.add(player1);
        player2.setName("DAKSH");
        player2.setBalance(7800D);
        cardForPlayer2.add(card2);
        cardForPlayer2.add(card3);
        player2.setHand(cardForPlayer2);
        players.add(player1);
        players.add(player2);
    }

    @Test
    public void testConstructor(){
        GoFish gofish = new GoFish(players);

        //Then
        Assert.assertEquals(player1,gofish.getPlayers().get(0));
        //Assert.assertEquals(expectedCardPresent, actualCardPresent);
    }



    @Test
    public void CheckPackTest(){
        //Given
        GoFish gofish = new GoFish(players);
        //When
        Integer actual = gofish.checkPack(player1);
        //Then
        Assert.assertEquals(Integer.valueOf("3"),actual );

    }

    @Test
    public void checkIncrementPackCounter(){
        //Given
      GoFish gofish = new GoFish(players);
        //When
         gofish.incrementBin(1);

        //Then
        Assert.assertEquals(Integer.valueOf("1"),gofish.getBin(1));

    }

    @Test
    public void RemovePackTest(){
        //Given
        Collections.sort(cardForPlayer1);
        player1.setHand(cardForPlayer1);
        player1.setPlayerNumber(1);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        ArrayList packCards = new ArrayList<Card>();
        packCards.add(card1);
        packCards.add(card2);
        packCards.add(card3);
        packCards.add(card4);

        GoFish gofish = new GoFish(players);
        //When
       Collections.sort(packCards);
        gofish.removePackFromHand(packCards,player1);
        //Then
        Assert.assertEquals(5,player1.getHand().size() );
    }

    @Test
    public void askForCardTestTrue(){
//        //Given

        GoFish gofish = new GoFish(players);
        Boolean actualCard = gofish.askForCard(player1,player2,"4");
        //Then
        Assert.assertEquals(true,actualCard );
    }

    @Test
    public void askForCardTestPopFromDeck(){
        //Given

        GoFish gofish = new GoFish(players);
        //When
        Boolean actualCard = gofish.askForCard(player1,player2,"9");
        //Then
        Assert.assertEquals(false,actualCard );
    }

    @Test
    public void giveMoreCardsTestSizeNotZero(){
        //Given

        GoFish gofish = new GoFish(players);
        //When
         gofish.giveMoreCards(player1);
        gofish.giveMoreCards(player2);
        //Then
       Assert.assertEquals(9 , player1.getHand().size());
     }

    @Test
    public void giveMoreCardsTestSizeZero(){
        //Given
        GoFish gofish = new GoFish(players);
        //When
        gofish.giveMoreCards(player3);
        //Then
        Assert.assertEquals(5 , player3.getHand().size());

    }


    @Test
    public void extractCardsForRemovalTest(){
        //Given
        GoFish gofish = new GoFish(players);
        //When
        Boolean actual = gofish.extractCardsForRemoval(player1,player2,"4",cardForPlayer2,false,cardForPlayer3);
        //Then
        Assert.assertEquals(true,actual);

    }
    @Test
    public void removeCardFromPlayerTest(){
        //Given
        GoFish gofish = new GoFish(players);
        //When
        gofish.removeCardFromPlayer(player1,cardForPlayer2);
        //Then
        Assert.assertEquals(7,player1.getHand().size());

    }

    @Test
    public void addCardToPlayerTest(){
        //Given
        GoFish gofish = new GoFish(players);
        //When
        gofish.addCardToHand(player1,card10);
        //Then
        Assert.assertEquals(10,player1.getHand().size());

    }


}
