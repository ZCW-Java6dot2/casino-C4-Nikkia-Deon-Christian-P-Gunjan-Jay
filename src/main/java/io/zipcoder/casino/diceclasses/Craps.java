package io.zipcoder.casino.diceclasses;

import io.zipcoder.casino.Interfaces.GamblingGameInterface;
import io.zipcoder.casino.Interfaces.Game;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.Console;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Craps extends DiceGame{
    private boolean inSession = true;
    private boolean comeOutRoll = true;
    private Player shooter;
    private Integer rollNumber;
    private Integer pointNumber;
    private ArrayList<Player> turnQueue = new ArrayList<Player>();
    Console console = new Console(System.in, System.out);

    private HashMap<Player, Double> passLineBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> dontPassBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> oddsBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> passOddsBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> fieldBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> comeBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> dontComeBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> placeBet = new HashMap<Player, Double>();

    public Craps(ArrayList<Player> players){
        super(2, players); // craps is traditionally played with 2 dice
    }

    public static void main(String args[]){
        Player player1 = new Player("Marty", 50.00);
        Player player2 = new Player("Christian", 30.00);
        ArrayList<Player> testps = new ArrayList<Player>();
        testps.add(player1);
        testps.add(player2);
        Craps craps = new Craps(testps);
        craps.startPlay();
    }


    public void emptyTurnQueue(){
        turnQueue.clear();
    }

    public void fillTurnQueue(int startingPosition){
        int offset = 0;
        int i = startingPosition;
        while (turnQueue.size() <= players.size()){
            turnQueue.add(players.get(i));
            if (i == players.size() - 1){
                i = 0;
                continue;
            }
            i++;
        }
    }

    public Integer roll(){
        Integer sum = 0;
        for (Die d : gameDice){
            sum += d.rollDie();
        }
        return sum;
    }


    public void handlePayOut(HashMap<Player, Double> area, Double multiplier){
        for (Player p : area.keySet()){
            payOut(p, (area.get(p) * multiplier));
        }
    }

    public void payOut(Player p, Double amount){
        p.setBalance(p.getBalance() + amount);
        console.println(p.getName() + "received a payout of " + amount);
    }

    public void handleAllIndividualBets(){
        handlePassLineBet();
        handleDontPassBet();

    }

    public void handlePassLineBet(){
        if (comeOutRoll){
            if (rollNumber == 7 || rollNumber == 11){
                handlePayOut(passLineBet, 2.0);
            } else if (rollNumber == 2 || rollNumber == 3 || rollNumber == 12){
                passLineBet.clear();
            }
            //else, we play on
        }
        else {
            if (rollNumber == pointNumber){
                handlePayOut(passLineBet, 2.0);
            }
            else if (rollNumber == 7){
                passLineBet.clear();
            }
            // else, we play on
        }
    }

    public void handleDontPassBet(){
        if (comeOutRoll){
            if (rollNumber == 7 || rollNumber == 11){
                passLineBet.clear();
            } else if (rollNumber == 2 || rollNumber == 3){
                handlePayOut(dontPassBet, 2.0);
            } else if (rollNumber == 12){ //tie, return the money
                handlePayOut(dontPassBet, 1.0);
            }
            //else, we play on
        }
        else {
            if (rollNumber == pointNumber){
                passLineBet.clear();
            }
            else if (rollNumber == 7){
                handlePayOut(passLineBet, 2.0);
            }
            // else, we play on
        }
    }


    public void startPlay(){
        inSession = true;
        comeOutRoll = true;
        while (inSession){
            for (int i = 0; i < players.size(); i++){
                rollNumber = 0;
                pointNumber = 0;
                shooter = players.get(i);
                console.println("Player " + players.get(i).getName() + ": you are the shooter.");
                fillTurnQueue(i);
                for (int j = 0; j < turnQueue.size(); j++){
                    Player currentPlayer = turnQueue.get(j);
                    takeBet(currentPlayer);
                }
                rollNumber = roll();
                // if we get a 7 or 11
                // if we get 2, 3, or 12, we lose
                // either way...
                    //handle bets and start over with the same shooter
                //else
                    // any other # becomes point , and we bet & roll again
                    // we go until the shooter rolls a 7 before rolling point
                console.println("Point number is now " + pointNumber);







            }
        }
    }

    public void takeBet(Player p){
        boolean validInput = false;
        boolean hasEnough = false; // potential bug - players with no money will get "stuck". Make sure you kick players out after they lose all their money!"
        console.println("Your balance is " + p.getBalance());
        while (!validInput){
            String where = console.getStringInput("Where would you like to place your bet?\n"
            + "pass line / don't pass / odds / pass odds / field / come / don't come / place / don't bet");

            if (comeOutRoll && (!where.equals("pass line") && !where.equals("don't pass"))){ //user must bet on pass line or don't pass for the comeout roll
                console.println("All players must select either 'pass line' or 'don't pass' for come out roll.");
            }
            else if (where.equals("don't bet") && p == shooter && comeOutRoll){
                console.println("The shooter must bet on either 'pass line' or 'don't pass' on the come out roll.");
            } else {
                do {
                    Double amount = console.getDoubleInput("How much would you like to bet?");
                    if (confirmTransaction(p, amount)){
                        validInput = placeBet(where, amount, p);
                        hasEnough = true;
                    } else { console.println("Insufficient funds. Please bet a lower amount."); }
                    } while (!hasEnough);
                }
            }
        }


    public boolean placeBet(String where, Double amount, Player p){
    switch (where.toLowerCase()){
        case "pass line": passLineBet.put(p, 5 + amount);
        return true;

        case "don't pass": dontPassBet.put(p, dontPassBet.get(p) + amount);
        return true;

        case "odds": oddsBet.put(p, oddsBet.get(p) + amount);
        return true;

        case "pass odds": passOddsBet.put(p, passOddsBet.get(p) + amount);
        return true;

        case "field": fieldBet.put(p, fieldBet.get(p) + amount);
        return true;

        case "come": comeBet.put(p, comeBet.get(p) + amount);
        return true;

        case "don't come": dontComeBet.put(p, dontComeBet.get(p) + amount);
        return true;

        case "place": placeBet.put(p, placeBet.get(p) + amount);
        return true;

        default: return false;
    }

}



public boolean confirmTransaction(Player p, Double amt){
        if (amt > p.getBalance()){return false;}
        return true;
}


    @Override
 public void handleTurns(){

 }

 public void addPlayer(){

 }

 public void removePlayer(){

 }



}
