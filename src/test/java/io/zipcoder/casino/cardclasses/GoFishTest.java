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
        gofish.removeCardFromPlayer(card1,player);
        //Then
        Assert.assertEquals(player.getHand().get(0).getValue() ,"8" );
        Assert.assertEquals(player.getHand().size() ,1);
    }

}
