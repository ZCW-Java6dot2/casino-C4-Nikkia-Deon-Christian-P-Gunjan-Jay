package io.zipcoder.casino.utilities;
import io.zipcoder.casino.Player;
import java.util.ArrayList;
public class Menu {

    public Menu() {
    }

    ArrayList <Player> players = new ArrayList<Player>();
    Player singlePlayer = new Player();
    private ConsoleInput consoleInput= new ConsoleInput();

    public void storePlayers(String playerName , Double balance)
    {
        this.singlePlayer.setName(playerName);
        this.singlePlayer.setBalance(balance);
        this.players.add(singlePlayer);
    }

    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }
    public void displayCardOrDiceMenu(Integer gameSelection)
    {
      if(gameSelection.equals(1))
      {
          consoleInput.displayCardMenu();
      }
      else
      {
       consoleInput.displayDiceMenu();
      }
    }


    public void callBlackJackOrGoFish(Integer cardGameChoice) {
        System.out.println("Would call the blackJack or Go Fish");
    }

    public void callCrapsOrShootinDice(Integer diceGameChoice) {
        System.out.println("Would call the CrapsORShootin");

    }
}
