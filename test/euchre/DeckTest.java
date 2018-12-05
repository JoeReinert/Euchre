/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchre;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joe
 */
public class DeckTest {
    
    public DeckTest() {
    }

    /**
     * Test of shuffle method, of class Deck.
     */
    @Test
    public void testShuffle() {
        System.out.println("shuffle");
        Deck instance = new Deck();
        instance.shuffle();
    }

    /**
     * Test of get method, of class Deck.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int a = 0;
        Deck instance = new Deck();
        Card expResult = new Card(0,0);
        Card result = instance.get(a);
        assertTrue(expResult.getSuit()==result.getSuit() && expResult.getValue()==result.getValue());
    }
    
}
