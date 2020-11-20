package io.zipcoder.casino.utilities;

import io.zipcoder.casino.Player;
import junit.framework.TestCase;

public class HighRollerPlayerTest extends TestCase {

    public void testGetPlayer() {
        Player player = new Player("James", 400.0);
       HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);

        Player actual = highRollerPlayer.getPlayer();
        Player expected = player;

        assertEquals(expected, actual);
    }

    public void testGetCurrentRoll() {
        Player player = new Player("James", 400.0);
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);

        highRollerPlayer.setCurrentRoll(6);
        Integer actual = highRollerPlayer.getCurrentRoll();
        Integer expected = 6;

        assertEquals(expected, actual);
    }

    public void testSetCurrentRoll() {
        //Given
        Player player = new Player();
        HighRollerPlayer roll = new HighRollerPlayer(player);

        //When
        roll.setCurrentRoll(5);
        Integer actual = roll.getCurrentRoll();
        //expected
        Integer expected = 5;


        assertEquals(expected, actual);
    }

    public void testSetActiveRoller() {
        //Given
        Player player = new Player();
        HighRollerPlayer roll = new HighRollerPlayer(player);

        //When
        roll.setActiveRoller(true);
        Boolean actual = roll.getActiveRoller();
        //expected
        Boolean expected = true;


        assertEquals(expected, actual);
    }

    public void testGetActiveRoller() {
        Player player = new Player("James", 400.0);
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);

        highRollerPlayer.setActiveRoller(true);
        Boolean actual = highRollerPlayer.getActiveRoller();
        Boolean expected = true;

        assertEquals(expected, actual);
    }
}