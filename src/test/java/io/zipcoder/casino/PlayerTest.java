package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    /*public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

     */

    @Test
    public void SetNameTest() {
        Player player = new Player();
        String expectedName = "Mike";

        player.setName(expectedName);
        String actualName = player.getName();

        Assert.assertEquals(actualName, expectedName);
    }

    @Test
    public void PlayerWagerTest() {
        Player player = new Player();
        Double expectedWager = 60.0;

        Double actualWager = player.playerWager(expectedWager);

        Assert.assertEquals(expectedWager, actualWager);
    }

    @Test
    public void SetBalanceTest() {
        Player player = new Player();
        Double expectedBalance = 100.0;

       player.setBalance(expectedBalance);
       Double actualBalance = player.getBalance();

        Assert.assertEquals(actualBalance, expectedBalance);
    }


}
