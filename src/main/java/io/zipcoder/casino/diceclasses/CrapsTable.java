package io.zipcoder.casino.diceclasses;

import io.zipcoder.casino.Player;
import io.zipcoder.casino.utilities.Console;

import java.util.HashMap;

public class CrapsTable {

    protected HashMap<Player, Double> passLineBet = new HashMap<Player, Double>();
    protected HashMap<Player, Double> dontPassBet = new HashMap<Player, Double>();
    protected HashMap<Player, Double> oddsBet = new HashMap<Player, Double>();
    protected HashMap<Player, Double> passOddsBet = new HashMap<Player, Double>();
    protected HashMap<Player, Double> fieldBet = new HashMap<Player, Double>();
    protected HashMap<Player, Double> comeBet = new HashMap<Player, Double>();
    protected HashMap<Player, Double> dontComeBet = new HashMap<Player, Double>();
    protected HashMap<Player, Double> place4 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> place5 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> place6 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> place8 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> place9 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> place10 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> placeToLose4 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> placeToLose5 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> placeToLose6 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> placeToLose8 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> placeToLose9 = new HashMap<Player, Double>();
    protected HashMap<Player, Double> placeToLose10 = new HashMap<Player, Double>();





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
        area.clear();
    }

    public void payOut(Player p, Double amount) {
        p.setBalance(p.getBalance() + amount);
    }

/*
if we're doing a point roll, what are the possible outcomes?
    - have hitting the point as a separate thing
    - place/don't place bets
    -2
        -come bet loses
        -don't come wins
        -field bet wins
    -3
        -come bet loses
        -don't come wins
        field bet wins
    -4
        -come bet becomes place bet on 4
        -don't come bet becomes place to lose bet
        -field bet wins
    -5
        -come bet becomes place bet on 5
        -don't come bet becomes place to lose bet on 5
        -field bet loses
    -6
        -come bet becomes place bet on 6
        -don't come bet becomes place to lose bet on 6
        -field bet loses
    -7
        -come bet wins
        -dont pass line wins
        - pass odds wins
        - pass line loses
        - odds bets lose
    -8
        -come bet becomes place bet on 8
        -don't come bet becomes place to lose bet on 8
        -field bet loses
    -9
        -come bet becomes place bet on 8
        -don't come bet becomes place to lose bet on 9
        -field bet wins
    -10
        -come bet becomes place bet on 10
        -don't come bet becomes place to lose bet on 10
        -field bet wins
    -11
        -come bet wins
        -don't come loses
        -field bet wins
    -12
        -come bet ties
        -don't come bet ties
        -field bet wins
 */

    public void handleAllIndividualBets(boolean comeOutRoll, int rollNumber, int pointNumber) {
        console.println("=============================================");
        if (comeOutRoll) { //come out roll
            handleBetsComeOut(rollNumber);
        }
        else {
            handlePassDontPassOddsPassOdds(rollNumber, pointNumber);
            handlePlaceBets(rollNumber);
            handleBetsComeDontCome(rollNumber);
            handleBetsField(rollNumber);
        }
        console.println("=============================================");
    }

    private void handleBetsHitPoint() {
        handlePayOut(passLineBet, 2.0, "pass line");//pass line wins
        handlePayOut(oddsBet, 2.0, "odds bet");// odds wins
        handleLoss(passOddsBet, "pass odds"); // pass odds lose
        handleLoss(dontPassBet, "don't pass line");//      Pass bets lose
    }

    private void handleBetsField(int rollNumber){
        switch (rollNumber){
            case 3: case 4: case 9: case 10: case 11:
                handlePayOut(fieldBet, 2.0, "field bet");
                break;
            case 2: case 12:
                handlePayOut(fieldBet, 4.0, "field bet");
                break;
            default:
                handleLoss(fieldBet, "field bet");
                break;
        }
    }

    private void handlePlaceBets(int rollNumber){
        switch (rollNumber){
            case 4:
                handlePayOut(place4, 2.0, "place 4");
                handleLoss(placeToLose4, "place to lose 4");
                break;
            case 5:
                handlePayOut(place5, 2.0, "place 5");
                handleLoss(placeToLose5, "place to lose 5");
                break;
            case 6:
                handlePayOut(place6, 2.0, "place 6");
                handleLoss(placeToLose6, "place to lose 6");
                break;
            case 7:
                handlePayOut(placeToLose4, 2.0, "place to lose 4");
                handlePayOut(placeToLose5, 2.0, "place to lose 5");
                handlePayOut(placeToLose6, 2.0, "place to lose 6");
                handlePayOut(placeToLose8, 2.0, "place to lose 8");
                handlePayOut(placeToLose9, 2.0, "place to lose 9");
                handlePayOut(placeToLose10, 2.0, "place to lose 10");
                handleLoss(place4, "place 4");
                handleLoss(place5, "place 5");
                handleLoss(place6, "place 6");
                handleLoss(place8, "place 8");
                handleLoss(place9, "place 9");
                handleLoss(place10, "place 10");
                break;
            case 8:
                handlePayOut(place8, 2.0, "place 8");
                handleLoss(placeToLose8, "place to lose 8");
                break;
            case 9:
                handlePayOut(place9, 2.0, "place 9");
                handleLoss(placeToLose9, "place to lose 9");
                break;
            case 10:
                handlePayOut(place9, 2.0, "place 10");
                handleLoss(placeToLose9, "place to lose 10");
                break;
        }

    }

    private void handleBetsComeDontCome(int rollNumber){
        switch (rollNumber) {
            case 2:
            case 3:
                handleLoss(comeBet, "come bet");
                handlePayOut(dontComeBet, 2.0, "don't come bet");
                break;
            case 4: case 5: case 6: case 8: case 9: case 10:
                moveComeBets(rollNumber);
                moveDontComeBets(rollNumber);
                break;
            case 7:
            case 11:
                handleLoss(dontComeBet, "don't come bet");
                handlePayOut(comeBet, 2.0, "comeBet");
                break;
            case 12:
                refund(comeBet);
                refund(dontComeBet);
                break;
        }
    }

    private void moveComeBets(int rollNumber){
        //come bets
        for (Player p : comeBet.keySet()){
            Double value = comeBet.get(p);
            switch (rollNumber){
                case 4: placeBet("place 4", value, p); break;
                case 5: placeBet("place 5", value, p); break;
                case 6: placeBet("place 6", value, p); break;
                case 8: placeBet("place 8", value, p); break;
                case 9: placeBet("place 9", value, p); break;
                case 10: placeBet("place 10", value, p); break;
            }
        }
        comeBet.clear();
    }

    private void moveDontComeBets(int rollNumber){
        for (Player p : dontComeBet.keySet()){
            Double value = dontComeBet.get(p);
            switch (rollNumber){
                case 4: placeBet("place to lose 4", value, p); break;
                case 5: placeBet("place to lose5", value, p); break;
                case 6: placeBet("place to lose 6", value, p); break;
                case 8: placeBet("place to lose 8", value, p); break;
                case 9: placeBet("place to lose 9", value, p); break;
                case 10: placeBet("place to lose 10", value, p); break;
            }
        }
        dontComeBet.clear();
    }

    private void handlePassDontPassOddsPassOdds(int rollNumber, int pointNumber){
        if (rollNumber == pointNumber){
            handlePayOut(passLineBet, 2.0, "pass line");
            handlePayOut(oddsBet, 2.0, "odds");
            handleLoss(dontPassBet, "don't pass line");
            handleLoss(passOddsBet, "pass odds");
        } else if (rollNumber == 7){
            handlePayOut(dontPassBet, 2.0, "don't pass line"); // don't pass wins
            handlePayOut(passOddsBet, 2.0, "pass odds"); // pass odds wins
            handleLoss(passLineBet, "pass line"); // pass line loses
            handleLoss(oddsBet, "odds"); // odds loses
        }
    }

    private void handleBetsComeOut(int rollNumber) {
        if (rollNumber == 7 || rollNumber == 11) { //7 or 11 on the comeout wins passline and loses for dontpass
            handlePayOut(passLineBet, 2.0, "pass line");
            handleLoss(dontPassBet, "don't pass line");
        } else if (rollNumber == 2 || rollNumber == 3 || rollNumber == 12) { // 2 or 3 win for don't pass line and lose for pass line, 12 loses for pass line but ties for don't pass
            // passLineBet.clear(); Why was this here?
            if (rollNumber != 12) {
                handlePayOut(dontPassBet, 2.0, "don't pass line");
            } else {
                refund(dontPassBet); // 12 is a tie on don'tPass bets
            }
            handleLoss(passLineBet, "pass line");
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
                        + "pass line / don't pass / odds / pass odds / field / come / don't come \n"
                        + "place 4 / place 5 / place 6 / place 8 / place 9 / place 10 / don't bet");

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
                } else {// prevent from increasing pass/don't pass
                    if (where.equals("pass line") || where.equals("don't pass")){
                        console.println("Players cannot increase the pass line or don't pass bet outside of the come out roll.");
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

            case "place 4": putter(place4, amount, p);
                return true;

            case "place 5": putter(place5, amount, p);
                return true;

            case "place 6": putter(place6, amount, p);
                return true;

            case "place 8": putter(place8, amount, p);
                return true;

            case "place 9": putter(place9, amount, p);
                return true;

            case "place 10": putter(place10, amount, p);
                return true;

            case "place to lose 4": putter(placeToLose4, amount, p);
                return true;

            case "place to lose 5": putter(placeToLose5, amount, p);
                return true;

            case "place to lose 6": putter(placeToLose6, amount, p);
                return true;

            case "place to lose 8": putter(placeToLose8, amount, p);
                return true;

            case "place to lose 9": putter(placeToLose9, amount, p);
                return true;

            case "place to lose 10": putter(placeToLose10, amount, p);
                return true;


            case "don't bet": // don't do anything
                return true;

            default:
                return false;
        }
    }

}

