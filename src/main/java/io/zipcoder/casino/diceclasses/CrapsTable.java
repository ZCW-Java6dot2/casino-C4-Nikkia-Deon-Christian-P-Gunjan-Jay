package io.zipcoder.casino.diceclasses;

import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.Console;

import java.util.HashMap;

public class CrapsTable {

    private HashMap<Player, Double> passLineBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> dontPassBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> oddsBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> passOddsBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> fieldBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> comeBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> dontComeBet = new HashMap<Player, Double>();
    private HashMap<Player, Double> placeBet = new HashMap<Player, Double>();

    Console console = new Console(System.in, System.out);

    public void handlePayOut(HashMap<Player, Double> area, Double multiplier, String areaName) {
        for (Player p : area.keySet()) {
            Double value = area.get(p) * multiplier;
            payOut(p, value);
            console.println(p.getName() + " won " + value + " on the " + areaName);
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
    }



    public void handleAllIndividualBets(boolean comeOutRoll, int rollNumber, int pointNumber) {
        console.println("=============================================");
        if (comeOutRoll) { //come out roll
            handleBetsComeOut(rollNumber);
        }
        else { // point roll
            if (rollNumber == pointNumber) { // if the player rolls the point #
                handleBetsHitPoint();
            }
            if (isFields(rollNumber)) {// if roll is 2, 3, 4, 9, 10, 11, or 12,
                handleBetsFieldRoll(rollNumber);
            } else {
                handleLoss(fieldBet, "field bet");
            }
            if (rollNumber == 7) {
                handleBetsSevenOut();
            }
        }
        console.println("=============================================");
    }

    private void handleBetsHitPoint() {
        handlePayOut(passLineBet, 2.0, "pass line");//pass line wins
        handlePayOut(oddsBet, 2.0, "odds bet");// odds wins
        handleLoss(passOddsBet, "pass odds"); // pass odds lose
        handleLoss(dontPassBet, "don't pass line");//      Pass bets lose
    }

    private void handleBetsFieldRoll(int rollNumber) {
        if (rollNumber == 2 || rollNumber == 12) { // pay out the field bets (double if it's 2 or 12);
            handlePayOut(fieldBet, 4.0, "field bet");
        } else {
            handlePayOut(fieldBet, 2.0, "field bet");
        }
    }

    private void handleBetsSevenOut() {
        handlePayOut(dontPassBet, 2.0, "don't pass bet"); // don't pass wins
        handlePayOut(passOddsBet, 2.0, "pass odds"); // pass odds wins
        handleLoss(passLineBet, "pass line"); // pass line loses
        handleLoss(oddsBet, "odds"); // odds loses
    }

    private void handleBetsComeOut(int rollNumber) {
        if (rollNumber == 7 || rollNumber == 11) { //7 or 11 on the comeout wins passline and loses for dontpass
            handlePayOut(passLineBet, 2.0, "pass line");
            handleLoss(dontPassBet, "don't pass line");
        } else if (rollNumber == 2 || rollNumber == 3 || rollNumber == 12) { // 2 or 3 win for don't pass line and lose for pass line, 12 loses for pass line but ties for don't pass
            passLineBet.clear();
            if (rollNumber != 12) {
                handlePayOut(dontPassBet, 2.0, "don't pass line");
            } else {
                refund(dontPassBet); // 12 is a tie on don'tPass bets
                handleLoss(passLineBet, "pass line");
            }
        }
    }

    public boolean isFields(int rn) {
        switch (rn) {
            case 2: case 3: case 4: case 9: case 10: case 11: case 12:
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

    public void takeBet(Player p, boolean comeOutRoll, Player shooter) {
        boolean turn = true;
        boolean hasEnough = false; // potential bug - players with no money will get "stuck". Make sure you kick players out after they lose all their money!"
        console.println("Your balance is " + p.getBalance());
        while(turn) {
            boolean validInput = false;
            while (!validInput) {
                String where = console.getStringInput("Where would you like to place your bet?\n"
                        + "pass line / don't pass / odds / pass odds / field / come / don't come / place / don't bet");

                // if it's the comeOut
                if (comeOutRoll){
                    if (p == shooter && (!where.equals("pass line") && !where.equals("don't pass"))){
                        console.println("The shooter must bet on either 'pass line' or 'don't pass' on the come out roll.");
                        continue;
                    }
                    if (p != shooter && (!where.equals("pass line") && !where.equals("don't pass") && !where.equals("don't bet"))){
                        console.println("All players must select either 'pass line', 'don't pass', or 'don't bet' for come out roll.");
                        continue;
                    }
                }
                    if (!where.equals("don't bet")) {
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
                    if (where.equals("don't bet")) validInput = true;
            }
            if (p.getBalance() > 0){
            turn = askTurn();}
            else {turn = false;};
        }
    }

    public boolean askTurn(){
        while(true) {
            String resp = console.getStringInput("Make another bet? (yes/no)");
            if (resp.equalsIgnoreCase("no")) {
                return false;
            }
            if (resp.equalsIgnoreCase("yes")) {
                return true;
            }
            console.println("Invalid selection.");
        }
    }

    public void putter(HashMap<Player, Double> area, Double amount, Player p){
        area.putIfAbsent(p, 0.0);
        Double value = area.get(p);
        area.put(p, value + amount);
    }

    public boolean placeBet(String where, Double amount, Player p) {
        switch (where.toLowerCase()) { // we need this to actually take out the money
            case "pass line": putter(passLineBet, amount, p);
                return true;

            case "don't pass": putter(dontPassBet, amount, p);
                return true;

            case "odds": putter(oddsBet, amount, p);
                return true;

            case "pass odds": putter(passOddsBet, amount, p);
                return true;

            case "field": putter(fieldBet, amount, p);
                return true;

            case "come": putter(comeBet, amount, p);
                return true;

            case "don't come": putter(dontComeBet, amount, p);
                return true;

            case "place": putter(placeBet, amount, p);
                return true;

            case "don't bet": // don't do anything
                return true;

            default:
                return false;
        }
    }

}
