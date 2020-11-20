package io.zipcoder.casino.diceclasses;

import javax.swing.*;

public class CrapsDisplay {

    public CrapsDisplay(){

    }
    JFrame frame = new JFrame("title");
    Integer frameSizeX = 600;
    Integer frameSizeY = 300;
    JPanel panel = new JPanel();
    JLabel user = new JLabel("user");
    JLabel passLineBet = new JLabel("pass line");
    JLabel comeBet = new JLabel("come bet");
    JLabel place4Bet = new JLabel("place 4 bet");


    public void initializeFrame(){
            frame.setSize(frameSizeX, frameSizeY);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(panel);
            panel.setLayout(null);
            user.setBounds(10, 20, 100, 25);
            panel.add(user);
                passLineBet.setBounds(110, 20, 100, 25);
                panel.add(passLineBet);
                comeBet.setBounds(210, 20, 100, 25);
                panel.add(comeBet);
                place4Bet.setBounds(310, 20, 100, 25);
                panel.add(place4Bet);
    }

    public void updateDisplay(String user, String passLineBet, String comeBet, String place4Bet){
        this.user.setText(user);
        this.passLineBet.setText("Pass line: " + passLineBet);
        this.comeBet.setText("Come: " + comeBet);
        this.place4Bet.setText("Place 4: " + place4Bet);
    }


}
