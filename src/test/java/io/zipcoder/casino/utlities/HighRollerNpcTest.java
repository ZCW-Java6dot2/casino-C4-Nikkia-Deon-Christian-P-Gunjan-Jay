package io.zipcoder.casino.utlities;

import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.HighRollerNpc;
import junit.framework.TestCase;
import org.junit.Test;

public class HighRollerNpcTest extends TestCase {
    HighRollerNpc npc;

    @Test
    public void testGetWallet() {
        Player player = new Player("James", 400.0);
        npc = new HighRollerNpc(player);
        Double actual = npc.getWallet();
        Double expected = 400.0;

        assertEquals(expected, actual);
    }

    public void testSetWallet() {
        //Given
        Player player = new Player();
        HighRollerNpc npc = new HighRollerNpc(player);

        //When
        npc.setWallet(500.0);
        Double actual = npc.getWallet();
        //expected
        Double expected = 500.0;

        assertEquals(expected, actual);
    }

    public void testGetPlayer() {
        Player player = new Player("James", 400.0);
        npc = new HighRollerNpc(player);

        Player actual = npc.getPlayer();
        Player expected = player;

        assertEquals(expected, actual);

    }

    public void testGetCurrentRoll() {
        Player player = new Player("James", 400.0);
        npc = new HighRollerNpc(player);

        npc.setCurrentRoll(5);
        Integer actual = npc.getCurrentRoll();
        Integer expected = 5;

        assertEquals(expected, actual);
    }

    public void testSetCurrentRoll() {
        //Given
        Player player = new Player();
        HighRollerNpc npc = new HighRollerNpc(player);

        //When
        npc.setCurrentRoll(5);
        Integer actual = npc.getCurrentRoll();
        //expected
        Integer expected = 5;


        assertEquals(expected, actual);
    }


    public void testSetActiveRoller() {
        //Given
        Player player = new Player();
        HighRollerNpc npc = new HighRollerNpc(player);

        //When
        npc.setActiveRoller(true);
        Boolean actual = npc.getActiveRoller();
        //expected
        Boolean expected = true;


        assertEquals(expected, actual);
    }

    public void testGetActiveRoller() {
        Player player = new Player("James", 400.0);
        npc = new HighRollerNpc(player);

        npc.setActiveRoller(true);
        Boolean actual = npc.getActiveRoller();
        Boolean expected = true;

        assertEquals(expected, actual);

    }
}