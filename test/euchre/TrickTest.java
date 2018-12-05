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
public class TrickTest {
    
    public TrickTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of setTeams method, of class Trick.
     */
    @Test
    public void testSetTeams() {
        System.out.println("setTeams");
        Team t1 = new Team(new Player(1), new Player(1));
        Team t2 = new Team(new Player(1), new Player(1));
        Trick instance = new Trick();
        instance.setTeams(t1, t2);
    }

    /**
     * Test of setSuitLed method, of class Trick.
     */
    @Test
    public void testSetSuitLed() {
        System.out.println("setSuitLed");
        String s = "Spades";
        Trick instance = new Trick();
        instance.setSuitLed(s);
    }

    /**
     * Test of setTrump method, of class Trick.
     */
    @Test
    public void testSetTrump() {
        System.out.println("setTrump");
        String s = "Spades";
        Trick instance = new Trick();
        instance.setTrump(s);
    }

    /**
     * Test of getWinner method, of class Trick.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Trick instance = new Trick();
        Player expResult = null;
        Player result = instance.getWinner();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSuitLed method, of class Trick.
     */
    @Test
    public void testGetSuitLed() {
        System.out.println("getSuitLed");
        Trick instance = new Trick();
        String expResult = "None";
        String result = instance.getSuitLed();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getWinningCard method, of class Trick.
     */
    @Test
    public void testGetWinningCard() {
        System.out.println("getWinningCard");
        Trick instance = new Trick();
        Card expResult = null;
        Card result = instance.getWinningCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of playCard method, of class Trick.
     */
    @Test
    public void testPlayCard() {
        System.out.println("playCard");
        int position = 0;
        Card c = new Card(0,4);
        Trick instance = new Trick();
        instance.playCard(position, c);
    }

    /**
     * Test of checkAgainstLeader method, of class Trick.
     */
    @Test
    public void testCheckAgainstLeader() {
        System.out.println("checkAgainstLeader");
        Card c = new Card(0,4);
        Trick instance = new Trick();
        boolean expResult = true;
        boolean result = instance.checkAgainstLeader(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of reset method, of class Trick.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Trick instance = new Trick();
        instance.reset();
    }
    
}
