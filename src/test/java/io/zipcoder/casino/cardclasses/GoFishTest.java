package io.zipcoder.casino.cardclasses;
import io.zipcoder.casino.Player;
import java.util.ArrayList;


import org.junit.Assert;
import org.junit.Test;


public class GoFishTest {
GoFish gofish = new GoFish();

    @Test
    public void removeCardFromPlayerTest(){
        //Given
        Player player = new Player();
        player.setName("gunjan");
        player.setBalance(4500D);
        Card card1 = new Card("Spades","4");
        Card card2 = new Card("Diamond","8");
        //List<Card> cards = Arrays.asList(card1,card2);
        ArrayList cards = new ArrayList<Card>();
        cards.add(card1);
        cards.add(card2);
        player.setHand(cards);
         //When
        player.getHand().remove(card1);
        gofish.removeCardFromPlayer(player,cards);
        //Then
        Assert.assertEquals(player.getHand().get(0).getValue() ,"8" );
        Assert.assertEquals(player.getHand().size() ,0);
    }

    @Test
    public void addCardToPlayerTest(){
        //Given
        Player player = new Player();
        player.setName("gunjan");
        player.setBalance(4500D);
        Card card1 = new Card("Spades","4");
        Card card2 = new Card("Diamond","8");
        //List<Card> cards = Arrays.asList(card1,card2);
        ArrayList cards = new ArrayList<Card>();
        cards.add(card1);
        player.setHand(cards);
        //When
         gofish.addCardToHand(player,card2);
        //Then
        Assert.assertEquals(player.getHand().get(1).getValue() ,"8" );
        Assert.assertEquals(player.getHand().size() ,2);
    }

    @Test
    public void askForCardTestTrue(){
        //Given
        Player player1 = new Player();
        player1.setName("gunjan");
        player1.setBalance(4500D);
        Card card1 = new Card("Spades","8");
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        player1.setHand(cardForPlayer1);

        Player player2 = new Player();
        player2.setName("Nikki");
        player2.setBalance(7800D);
        Card card2 = new Card("Diamond","2");
        Card card3 = new Card("Spades","2");
        ArrayList cardForPlayer2 = new ArrayList<Card>();
        cardForPlayer2.add(card2);
        cardForPlayer2.add(card3);
        player2.setHand(cardForPlayer2);
        //When
        Boolean actualCard = gofish.askForCard(player1,player2,"2");
        //Then
        Assert.assertEquals(true,actualCard );

    }

    @Test
    public void askForCardTestFalse(){
        //Given
        Player player1 = new Player();
        player1.setName("gunjan");
        player1.setBalance(4500D);
        Card card1 = new Card("Spades","8");
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        player1.setHand(cardForPlayer1);

        Player player2 = new Player();
        player2.setName("Nikki");
        player2.setBalance(7800D);
        Card card2 = new Card("Diamond","2");
        ArrayList cardForPlayer2 = new ArrayList<Card>();
        cardForPlayer2.add(card2);
        player2.setHand(cardForPlayer2);
        //When
        Boolean actualCard = gofish.askForCard(player1,player2,"9");
        //Then
        Assert.assertEquals(false,actualCard );

    }

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
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        cardForPlayer1.add(card2);
        cardForPlayer1.add(card3);
        cardForPlayer1.add(card4);
        cardForPlayer1.add(card5);
        player1.setHand(cardForPlayer1);

        //When
        Integer actual = gofish.checkPack(player1);

        //Then
        Assert.assertEquals(Integer.valueOf("3"),actual );

    }

    @Test
    public void removePackFromHandTest(){
        //Given
        Player player1 = new Player();
        player1.setName("gunjan");
        player1.setBalance(4500D);
        Card card1 = new Card("Spades","8");
        Card card2 = new Card("Clubs","8");
        Card card3 = new Card("Clubs","8");
        Card card4 = new Card("Diamond","8");
        Card card5 = new Card("Spades","Jack");
        Card card6 = new Card("Clubs","5");
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        cardForPlayer1.add(card2);
        cardForPlayer1.add(card3);
        cardForPlayer1.add(card4);
        cardForPlayer1.add(card5);
        cardForPlayer1.add(card6);
        player1.setHand(cardForPlayer1);

        ArrayList cardinPack = new ArrayList<Card>();
        cardinPack.add(card1);
        cardinPack.add(card2);
        cardinPack.add(card3);
        cardinPack.add(card4);

        //When
        gofish.removePackFromHand(3,cardinPack,player1);
        //Then
        Assert.assertEquals(2,player1.getHand().size());

    }

}
