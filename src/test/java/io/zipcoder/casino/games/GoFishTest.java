package io.zipcoder.casino.games;
import io.zipcoder.casino.Player;
import java.util.ArrayList;
import java.util.Collections;


import io.zipcoder.casino.cardclasses.Card;
import io.zipcoder.casino.games.GoFish;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GoFishTest {

    @Test
    public void CheckPackTest(){
        //Given
        Player player1 = new Player();
        player1.setName("gunjan");
        player1.setBalance(4500D);
        Card card1 = new Card("Spades","4");
        Card card2 = new Card("Clubs","4");
        Card card3 = new Card("Hearts","4");
        Card card4 = new Card("Diamond","4");
        Card card5 = new Card("Diamond","Jack");
        Card card6 = new Card("Spades","10");
        Card card7 = new Card("Clubs","2");
        Card card8 = new Card("Hearts","2");
        Card card9 = new Card("Diamond","8");
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        cardForPlayer1.add(card2);
        cardForPlayer1.add(card3);
        cardForPlayer1.add(card4);
        cardForPlayer1.add(card5);
        cardForPlayer1.add(card6);
        cardForPlayer1.add(card7);
        cardForPlayer1.add(card8);
        cardForPlayer1.add(card9);
        //cardForPlayer1.add(card5);
        Collections.sort(cardForPlayer1);
        player1.setHand(cardForPlayer1);
        player1.setPlayerNumber(1);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);

        GoFish gofish = new GoFish(players);
        //When
        Integer actual = gofish.checkPack(player1);

        //Then
        Assert.assertEquals(Integer.valueOf("3"),actual );

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


    @Test
    public void RemovePackTest(){
        //Given
        Player player1 = new Player();
        player1.setName("gunjan");
        player1.setBalance(4500D);
        Card card1 = new Card("Spades","4");
        Card card2 = new Card("Clubs","4");
        Card card3 = new Card("Hearts","4");
        Card card4 = new Card("Diamond","4");
        Card card5 = new Card("Diamond","Jack");
        Card card6 = new Card("Spades","10");
        Card card7 = new Card("Clubs","2");
        Card card8 = new Card("Hearts","2");
        Card card9 = new Card("Diamond","8");
        ArrayList cardForPlayer1 = new ArrayList<Card>();
        cardForPlayer1.add(card1);
        cardForPlayer1.add(card2);
        cardForPlayer1.add(card3);
        cardForPlayer1.add(card4);
        cardForPlayer1.add(card5);
        cardForPlayer1.add(card6);
        cardForPlayer1.add(card7);
        cardForPlayer1.add(card8);
        cardForPlayer1.add(card9);
        //cardForPlayer1.add(card5);
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
        ArrayList<Player> players=new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        GoFish gofish = new GoFish(players);
        Boolean actualCard = gofish.askForCard(player1,player2,"2");
        //Then
        Assert.assertEquals(true,actualCard );

    }

    @Test
    public void askForCardTestPopFromDeck(){
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
        ArrayList<Player> players=new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        GoFish gofish = new GoFish(players);
        //When
        Boolean actualCard = gofish.askForCard(player1,player2,"9");
        //Then
        Assert.assertEquals(false,actualCard );

    }

}
