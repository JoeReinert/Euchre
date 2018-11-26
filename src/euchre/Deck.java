/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchre;

/**
 *
 * @author Joe
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Joe
 */
public class Deck {
    private final ArrayList<Card> deck;
    
    public Deck() {
         deck = new ArrayList<>();
         
         for(int i = 0; i<4; i++) {
             for(int j = 0; j<6; j++)
                    deck.add(new Card(i,j));
         }
    }
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    public Card get(int a) {
        return deck.get(a);
    }
}
