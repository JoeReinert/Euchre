/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchre;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jr155
 */
public class CardTest {
    
    public CardTest() {
    }

    /**
     * Test of getSuit method, of class Card.
     */
    @Test
    public void testGetSuit() {
        System.out.println("getSuit");
        Card instance = new Card(0,0);
        int expResult = 0;
        int result = instance.getSuit();
        assertEquals(expResult, result);

    }

    /**
     * Test of getSuitString method, of class Card.
     */
    @Test
    public void testGetSuitString() {
        System.out.println("getSuitString");
        Card instance = new Card(0,0);
        String expResult = "Spades";
        String result = instance.getSuitString();
        assertTrue(expResult.equals(result));
        instance = new Card(1,0);
        expResult = "Diamonds";
        result = instance.getSuitString();
        assertTrue(expResult.equals(result));
        instance = new Card(2,0);
        expResult = "Hearts";
        result = instance.getSuitString();
        assertTrue(expResult.equals(result));
        instance = new Card(3,0);
        expResult = "Clubs";
        result = instance.getSuitString();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getValue method, of class Card.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Card instance;
        int expResult;
        int result;
        for(int i=0;i<7;i++) {
            instance = new Card(0,i);
            expResult = i;
            result = instance.getValue();
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of getImage method, of class Card.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Card instance = new Card(0,0);
        String expResult = "/euchre/9Spades.png";
        String result = instance.getImage();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of setSuit method, of class Card.
     */
    @Test
    public void testSetSuit() {
        System.out.println("setSuit");
        int a = 0;
        Card instance = new Card(1,0);
        instance.setSuit(a);
    }

    /**
     * Test of setValue method, of class Card.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int a = 0;
        Card instance = new Card(0,1);
        instance.setValue(a);
    }

    @Test
    public void testIsSameSuit() {
        System.out.println("isSameSuit");
        String comparisonString = "Spades";
        Card instance = new Card(0,1);
        boolean result = instance.isSameSuit(comparisonString);
        assertTrue(result);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Card instance = new Card(3,5);
        String expResult = "Ace of Clubs";
        String result = instance.toString();
        assertTrue(expResult.equals(result));
    }
    
}
