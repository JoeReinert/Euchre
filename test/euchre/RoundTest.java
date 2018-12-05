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
public class RoundTest {
    
    public RoundTest() {
    }

    /**
     * Test of setTrump method, of class Round.
     */
    @Test
    public void testSetTrump() {
        System.out.println("setTrump");
        String s = "Spades";
        Round instance = new Round(1);
        instance.setTrump(s);
    }

    /**
     * Test of setSuitLed method, of class Round.
     */
    @Test
    public void testSetSuitLed() {
        System.out.println("setSuitLed");
        String s = "Spades";
        Round instance = new Round(1);
        instance.setSuitLed(s);
    }

    /**
     * Test of setDealerPosition method, of class Round.
     */
    @Test
    public void testSetDealerPosition() {
        System.out.println("setDealerPosition");
        int a = 2;
        Round instance = new Round(1);
        instance.setDealerPosition(a);
    }

    /**
     * Test of setLeaderPosition method, of class Round.
     */
    @Test
    public void testSetLeaderPosition() {
        System.out.println("setLeaderPosition");
        int a = 2;
        Round instance = new Round(1);
        instance.setLeaderPosition(a);
    }

    /**
     * Test of setCurrentPosition method, of class Round.
     */
    @Test
    public void testSetCurrentPosition() {
        System.out.println("setCurrentPosition");
        int a = 2;
        Round instance = new Round(1);
        instance.setCurrentPosition(a);
    }

    /**
     * Test of setShownCard method, of class Round.
     */
    @Test
    public void testSetShownCard() {
        System.out.println("setShownCard");
        Card c = new Card(0,3);
        Round instance = new Round(1);
        instance.setShownCard(c);
    }

    /**
     * Test of getTrump method, of class Round.
     */
    @Test
    public void testGetTrump() {
        System.out.println("getTrump");
        Round instance = new Round(1);
        String expResult = "None";
        String result = instance.getTrump();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getSuitLed method, of class Round.
     */
    @Test
    public void testGetSuitLed() {
        System.out.println("getSuitLed");
        Round instance = new Round(1);
        String expResult = "None";
        String result = instance.getSuitLed();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getDealerPosition method, of class Round.
     */
    @Test
    public void testGetDealerPosition() {
        System.out.println("getDealerPosition");
        Round instance = new Round(1);
        int expResult = 1;
        int result = instance.getDealerPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLeaderPosition method, of class Round.
     */
    @Test
    public void testGetLeaderPosition() {
        System.out.println("getLeaderPosition");
        Round instance = new Round(1);
        int expResult = 2;
        int result = instance.getLeaderPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentPosition method, of class Round.
     */
    @Test
    public void testGetCurrentPosition() {
        System.out.println("getCurrentPosition");
        Round instance = new Round(1);
        int expResult = 2;
        int result = instance.getCurrentPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getShownCard method, of class Round.
     */
    @Test
    public void testGetShownCard() {
        System.out.println("getShownCard");
        Round instance = new Round(1);
        Card expResult = null;
        Card result = instance.getShownCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTrick method, of class Round.
     */
    @Test
    public void testGetTrick() {
        System.out.println("getTrick");
        Round instance = new Round(1);
        Trick result = instance.getTrick();
    }

    /**
     * Test of getTrickWinner method, of class Round.
     */
    @Test
    public void testGetTrickWinner() {
        System.out.println("getTrickWinner");
        Round instance = new Round(1);
        Player expResult = null;
        Player result = instance.getTrickWinner();
        assertEquals(expResult, result);
    }

    /**
     * Test of isTrumpCalled method, of class Round.
     */
    @Test
    public void testIsTrumpCalled() {
        System.out.println("isTrumpCalled");
        Round instance = new Round(1);
        boolean expResult = false;
        boolean result = instance.isTrumpCalled();
        assertEquals(expResult, result);
    }

    /**
     * Test of nextPlayer method, of class Round.
     */
    @Test
    public void testNextPlayer() {
        System.out.println("nextPlayer");
        Round instance = new Round(1);
        instance.nextPlayer();
    }

    /**
     * Test of reset method, of class Round.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Round instance = new Round(1);
        instance.reset();
    }
    
}
