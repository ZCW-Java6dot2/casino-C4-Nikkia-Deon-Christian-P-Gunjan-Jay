package io.zipcoder.casino.utlities;
import io.zipcoder.casino.Player;
import java.util.ArrayList;

import io.zipcoder.casino.utilities.Menu;
import org.junit.Assert;
import org.junit.Test;


public class MenuTest {


    @Test
    public void storePlayers(){
        //Given
        Player player = new Player();
        player.setName("gunjan");
        player.setBalance(4500D);
        Menu menu = new Menu();
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(player);

        //When
        menu.storePlayers(player.getName(),player.getBalance());
        //Then
        Assert.assertEquals(expectedPlayers.size(), menu.getPlayers().size());
    }

    @Test
    public void getPlayers(){
        //Given
        Player player = new Player();
        player.setName("gunjan");
        player.setBalance(4500D);
        Menu menu = new Menu();
        ArrayList<Player> expectedPlayers = new ArrayList<Player>();
        expectedPlayers.add(player);
        menu.storePlayers(player.getName(),player.getBalance());
        //When
        String actualName = menu.getPlayers().get(0).getName();
        //Then
        Assert.assertEquals(expectedPlayers.get(0).getName(),actualName );
    }
  /*  @Test
    public void selectMenu() {

        Menu menu = new Menu();
        String actual =menu.selectCardOrDice();
       Assert.assertEquals("****************************\n" +
               "1.  Card Game\n" +
               "2.  Dice Game\n" +
               "3.  Exit\n" +
               "****************************\n" +
               "Please select the game you want to play: ",actual);
    }*/


}
