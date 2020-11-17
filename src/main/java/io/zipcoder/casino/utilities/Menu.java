package io.zipcoder.casino.utilities;

public class Menu {

    ArrayList <Player> players = new ArrayList<Player>;
    Player player = new Player;
    private Console console = new Console(System.in, System.out);

    public void storePlayers(String playerName , Double balance)
    {

            player.setName(playerName);
            player.setBalance(balance);
            players.add(player);

    }


}
