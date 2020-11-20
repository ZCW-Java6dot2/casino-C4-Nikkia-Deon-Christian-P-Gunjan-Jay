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


//TO-DO LIST
// - is it bad to define in class body?
// - what's up with console? Shouldn't I be creating the wrapper instead?
// -don't allow betting zero
// - add the rest of the bets
// - minimumBets?
// - unit testing!
public class Craps extends DiceGame {

    private CrapsDisplay display = new CrapsDisplay();

    private boolean inSession = true;
    private boolean comeOutRoll = true;
    private boolean crapsOut = false;
    private boolean pointRolling = false;

    private Player shooter;
    private ArrayList<Player> turnQueue = new ArrayList<Player>();

    private Integer rollNumber;
    private Integer pointNumber;

    private Console console = new Console(System.in, System.out);

    private CrapsTable crapsTable = new CrapsTable();

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
        display.initializeFrame();
        inSession = true;
        comeOutRoll = true;
        while (inSession) {
            for (int i = 0; i < players.size(); i++) {
                // take turns among players, each one will be the shooter at some point
                newShooterSetup(i);
                while (crapsOut == false) {
                    newRoundSetup(i);
                    betAndRoll();
                    if (rollNumber == 7 || rollNumber == 11){
                        console.println("Shooter won the comeout!");
                        crapsTable.handleAllIndividualBets(comeOutRoll, rollNumber, pointNumber);
                        continue; // go to top of the loop, we have the same shooter
                    }
                    if (rollNumber == 2 || rollNumber == 3 || rollNumber == 12){
                        console.println("Shooter lost the comeout.");
                        System.out.println("COMEOUTROLL IS " + Boolean.toString(comeOutRoll));
                        crapsTable.handleAllIndividualBets(comeOutRoll, rollNumber, pointNumber);
                        continue; // go to the top of the loop, we have the same shooter
                    }
                    //enter pointRoll loop
                    pointRollSetup();
                    while (pointRolling) {
                        rollingForPoint();
                        crapsTable.handleAllIndividualBets(comeOutRoll, rollNumber, pointNumber); // do this whether we're continuing or getting a new shooter
                    }
                    kickPlayers();
                }
            }
        }
    }

    private void updateDisplay(Player p){
        String name = p.getName();
        String passBet = crapsTable.passLineBet.getOrDefault(p, 0.0).toString();
        String comeBet = crapsTable.comeBet.getOrDefault(p, 0.0).toString();
        String place4Bet = crapsTable.place4.getOrDefault(p, 0.0).toString();
        display.updateDisplay(name, passBet, comeBet, place4Bet);
    }

    private void rollingForPoint() {
        // we go until the shooter rolls a 7 or the point
        console.println("Point number is now " + pointNumber);
        betAndRoll();
        if (rollNumber == pointNumber) {
            console.println("Shooter hit the point number!");  //loop back, same shooter
            pointRolling = false;
        }
        if (rollNumber == 7) {
            console.println("Seven out."); // new shooter
            crapsOut = true;
            pointRolling = false;
        }
    }

    private void betAndRoll() {
        playersBet();
        rollNumber = roll();
        console.println(shooter.getName() + " rolled a " + rollNumber);
    }

    private void pointRollSetup() {
        pointNumber = rollNumber;
        comeOutRoll = false;
        pointRolling = true;
    }

    private void newRoundSetup(int i) {
        comeOutRoll = true;
        console.println("Player " + players.get(i).getName() + ": you are the shooter.");
        fillTurnQueue(i); // make sure the current shooter is first, followed by the rest
    }

    private void newShooterSetup(int i) {
        rollNumber = 0;
        pointNumber = 0;
        crapsOut = false;
        shooter = players.get(i);
        emptyTurnQueue();
    }

    private void playersBet() {
        for (int j = 0; j < turnQueue.size(); j++) { // go in order and take everyone's bets
            // should I use playerWager here?
            Player currentPlayer = turnQueue.get(j);
            updateDisplay(currentPlayer);
            console.println("%s: ", currentPlayer.getName());
            crapsTable.takeBet(currentPlayer, comeOutRoll, shooter); //
        }
    }

    public void kickPlayers(){
        ArrayList<Player> kickList = new ArrayList<Player>();
        for (Player p : players){
            if (p.getBalance() == 0){
                console.println(p.getName() + " is broke! They have been removed from the game.");
                kickList.add(p);
            }
        }
        players.removeAll(kickList);
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

}

