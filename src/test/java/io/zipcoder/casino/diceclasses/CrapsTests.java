package io.zipcoder.casino.diceclasses;

import io.zipcoder.casino.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CrapsTests {

    ArrayList<Player> players = new ArrayList<Player>();
    Player p1 = new Player("Christian", 50.0);
    Player p2 = new Player("James", 60.0);
    Craps craps;

    @Before
    public void setup(){
        players.add(p1);
        players.add(p2);
        craps = new Craps(players);
    }


    @Test
    public void getTurnQueueTest(){
        //Given
        ArrayList<Player> expectedTurns = new ArrayList<Player>();
        expectedTurns.add(p1);
        expectedTurns.add(p2);

        //When
        craps.fillTurnQueue(0);
        ArrayList<Player> actualTurns = craps.getTurnQueue();

        //Then
        Assert.assertEquals(expectedTurns, actualTurns);

    }

    public void emptyTurnQueueTest(){
        
    }

}
