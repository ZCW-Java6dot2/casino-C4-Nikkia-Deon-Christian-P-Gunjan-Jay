//This is a dummy class for testing purposes!!
//Feel free to delete if there is a merge conflict.
//-Christian
//==================================================
package io.zipcoder.casino;
import io.zipcoder.casino.cardclasses.Card;
import java.util.ArrayList;
public class Player {
    ArrayList<Card> hand;
    public Player(){
        hand = new ArrayList<Card>();
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public ArrayList<Card> getHand(){
        return hand;
    }
}
// end dummy class ================================
