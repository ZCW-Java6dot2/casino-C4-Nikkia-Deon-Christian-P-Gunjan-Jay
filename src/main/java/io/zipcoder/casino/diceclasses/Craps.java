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

public class Craps extends DiceGame {
    private boolean inSession = true;
    private boolean comeOutRoll = true;
    private boolean crapsOut = false;
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

    public Craps(ArrayList<Player> players) {
        super(2, players); // craps is traditionally played with 2 dice
    }

    public static void main(String args[]) {
        Player player1 = new Player("Marty", 50.00);
        Player player2 = new Player("Christian", 30.00);
        ArrayList<Player> testps = new ArrayList<Player>();
        testps.add(player1);
        testps.add(player2);
        Craps craps = new Craps(testps);
        craps.playCraps();
    }

    public void playCraps() {
        inSession = true;

        comeOutRoll = true;
        while (inSession) {
            for (int i = 0; i < players.size(); i++) {
                // take turns among players, each one will be the shooter at some point
                rollNumber = 0;
                pointNumber = 0;
                crapsOut = false;
                shooter = players.get(i);
                emptyTurnQueue();
                while (crapsOut == false) {
                    comeOutRoll = true;
                    console.println("Player " + players.get(i).getName() + ": you are the shooter.");
                    fillTurnQueue(i); // make sure the current shooter is first, followed by the rest
                    for (int j = 0; j < turnQueue.size(); j++) { // go in order and take everyone's bets
                        // should I use playerWager here?
                        Player currentPlayer = turnQueue.get(j);
                        console.println("%s: ", currentPlayer.getName());
                        takeBet(currentPlayer); //
                    }
                    rollNumber = roll();
                    console.println(shooter.getName() + " rolled a " + rollNumber);
                    if (rollNumber == 7 || rollNumber == 11){
                        console.println("Shooter won the comeout!");
                        handleAllIndividualBets();
                        continue; // go to top of the loop, we have the same shooter
                    }
                    if (rollNumber == 2 || rollNumber == 3 || rollNumber == 12){
                        console.println("Shooter lost the comeout.");
                        handleAllIndividualBets();
                        continue; // go to the top of the loop, we have the same shooter
                    }

                    //enter pointRoll loop
                    pointNumber = rollNumber;
                    comeOutRoll = false;
                    boolean pointRolling = true;
                    while (pointRolling) {
                        // we go until the shooter rolls a 7 before rolling point
                        console.println("Point number is now " + pointNumber);
                        for (int j = 0; j < turnQueue.size(); j++) { // go in order and take everyone's bets
                            // should I use playerWager here?
                            Player currentPlayer = turnQueue.get(j);
                            console.println("%s: ", currentPlayer.getName());
                            takeBet(currentPlayer); //
                        }
                        rollNumber = roll();
                        console.println(shooter.getName() + " rolled a " + rollNumber);
                        if (rollNumber == pointNumber) {
                            console.println("Shooter hit the point number!");  //loop back, same shooter
                            handleAllIndividualBets();
                            pointRolling = false;
                            continue;
                        }
                        if (rollNumber == 7) {
                            console.println("Shooter hit craps."); // new shooter
                            crapsOut = true;
                            pointRolling = false;
                            //handleAllIndividualBets();
                        }
                        handleAllIndividualBets();
                    }
                }
            }
        }
    }


    public void emptyTurnQueue() {
        turnQueue.clear();
    }

    public void fillTurnQueue(int startingPosition) {
        int i = startingPosition;
        while (turnQueue.size() < players.size()) {
            turnQueue.add(players.get(i));
            if (i == players.size() - 1) {
                i = 0;
                continue;
            }
            i++;
        }
    }

    public Integer roll() {
        Integer sum = 0;
        for (Die d : gameDice) {
            sum += d.rollDie();
        }
        return sum;
    }


    public void handlePayOut(HashMap<Player, Double> area, Double multiplier) {
        for (Player p : area.keySet()) {
            Double value = area.get(p) * multiplier;
            payOut(p, value);
            console.println(p.getName() + " won " + value);
        }
        area.clear(); // is this necessary?
    }

    public void handleLoss(HashMap<Player, Double> area, String areaName) {
        for (Player p : area.keySet()) {
            console.println(p.getName() + " lost a bet of " + area.get(p) + " on the " + areaName);
        }
        area.clear();
    }

    public void refund(HashMap<Player, Double> area) {
        for (Player p : area.keySet()) {
            Double value = area.get(p);
            console.println(p.getName() + " got their " + value + " back");
            payOut(p, value);
        }
    }


    public void payOut(Player p, Double amount) {
        p.setBalance(p.getBalance() + amount);
        console.println(p.getName() + "received a payout of " + amount);
    }

    public void handleAllIndividualBets() {
        if (comeOutRoll) { //come out roll
            if (rollNumber == 7 || rollNumber == 11) { //7 or 11 on the comeout wins passline and loses for dontpass
                handlePayOut(passLineBet, 2.0);
                handleLoss(dontPassBet, "don't pass line");
            } else if (rollNumber == 2 || rollNumber == 3 || rollNumber == 12) { // 2 or 3 win for don't pass line and lose for pass line, 12 loses for pass line but ties for don't pass
                passLineBet.clear();
                if (rollNumber != 12) {
                    handlePayOut(dontPassBet, 2.0);
                } else {
                    refund(dontPassBet); // 12 is a tie on don'tPass bets
                    handleLoss(passLineBet, "pass line");
                }
            }
        }
        else { // point roll
            if (rollNumber == pointNumber) { // if the player rolls the point #
                handlePayOut(passLineBet, 2.0);//pass line wins
                handlePayOut(oddsBet, 2.0);// odds wins
                handleLoss(passOddsBet, "pass odds"); // pass odds lose
                handleLoss(dontPassBet, "don't pass line");//      Pass bets lose
            }
            if (isFields(rollNumber)) {// if roll is 2, 3, 4, 9, 10, 11, or 12,
                if (rollNumber == 2 || rollNumber == 12) { // pay out the field bets (double if it's 2 or 12);
                    handlePayOut(fieldBet, 4.0);
                } else {
                    handlePayOut(fieldBet, 2.0);
                }
            } else {
                handleLoss(fieldBet, "field bet");
            }
            if (rollNumber == 7) {
                handlePayOut(dontPassBet, 2.0); // don't pass wins
                handlePayOut(passOddsBet, 2.0); // pass odds wins
                handleLoss(passLineBet, "pass line"); // pass line loses
                handleLoss(oddsBet, "odds"); // odds loses
            }
        }
    }

    public boolean isFields(int rn) {
        switch (rn) {
            case 2:
            case 3:
            case 4:
            case 9:
            case 10:
            case 11:
            case 12:
                return true;
            default:
                return false;
        }
    }





    public void takeBet(Player p) {
        boolean validInput = false;
        boolean hasEnough = false; // potential bug - players with no money will get "stuck". Make sure you kick players out after they lose all their money!"
        console.println("Your balance is " + p.getBalance());
        while (!validInput) {
            String where = console.getStringInput("Where would you like to place your bet?\n"
                    + "pass line / don't pass / odds / pass odds / field / come / don't come / place / don't bet");

            if (comeOutRoll && (!where.equals("pass line") && !where.equals("don't pass"))) { //user must bet on pass line or don't pass for the comeout roll
                console.println("All players must select either 'pass line' or 'don't pass' for come out roll.");
            } else if (where.equals("don't bet") && p == shooter && comeOutRoll) {
                console.println("The shooter must bet on either 'pass line' or 'don't pass' on the come out roll.");
            } else {
                do {
                    Double amount = console.getDoubleInput("How much would you like to bet?");
                    if (confirmTransaction(p, amount)) {
                        validInput = placeBet(where, amount, p);
                        hasEnough = true;
                    } else {
                        console.println("Insufficient funds. Please bet a lower amount.");
                    }
                } while (!hasEnough);
            }
        }
    }


    public boolean placeBet(String where, Double amount, Player p) {
        switch (where.toLowerCase()) { // we need this to actually take out the money
            case "pass line":
                passLineBet.putIfAbsent(p, 0.0);
                passLineBet.put(p, passLineBet.get(p) + amount);
                return true;

            case "don't pass":
                dontPassBet.putIfAbsent(p, 0.0);
                dontPassBet.put(p, dontPassBet.get(p) + amount);
                return true;

            case "odds":
                oddsBet.putIfAbsent(p, 0.0);
                oddsBet.put(p, oddsBet.get(p) + amount);
                return true;

            case "pass odds":
                passOddsBet.putIfAbsent(p, 0.0);
                passOddsBet.put(p, passOddsBet.get(p) + amount);
                return true;

            case "field":
                fieldBet.putIfAbsent(p, 0.0);
                fieldBet.put(p, fieldBet.get(p) + amount);
                return true;

            case "come":
                comeBet.putIfAbsent(p, 0.0);
                comeBet.put(p, comeBet.get(p) + amount);
                return true;

            case "don't come":
                dontComeBet.putIfAbsent(p, 0.0);
                dontComeBet.put(p, dontComeBet.get(p) + amount);
                return true;

            case "place":
                placeBet.putIfAbsent(p, 0.0);
                placeBet.put(p, placeBet.get(p) + amount);
                return true;

            case "don't bet":
                // don't do anything
                return true;

            default:
                return false;
        }

    }


    public boolean confirmTransaction(Player p, Double amt) {
        if (amt > p.getBalance()) {
            return false;
        }
        p.setBalance(p.getBalance() - amt);
        return true;
    }



    public void addPlayer() {

    }

    public void removePlayer() {

    }

}

