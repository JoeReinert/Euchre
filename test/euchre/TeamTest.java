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
public class TeamTest {
    
    public TeamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getCalledTrump method, of class Team.
     */
    @Test
    public void testGetCalledTrump() {
        System.out.println("getCalledTrump");
        Team instance = new Team(new Player(1), new Player(2));
        boolean expResult = false;
        boolean result = instance.getCalledTrump();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayer1 method, of class Team.
     */
    @Test
    public void testGetPlayer1() {
        System.out.println("getPlayer1");
        Player p1 = new Player(1);
        Team instance = new Team(p1, new Player(1));
        Player result = instance.getPlayer1();
        assertEquals(p1, result);
    }

    /**
     * Test of getPlayer2 method, of class Team.
     */
    @Test
    public void testGetPlayer2() {
        System.out.println("getPlayer2");
        Player p2 = new Player(1);
        Team instance = new Team(new Player(1), p2);
        Player result = instance.getPlayer2();
        assertEquals(p2, result);
    }

    /**
     * Test of getScore method, of class Team.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Team instance = new Team(new Player(1), new Player(1));
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTrickCount method, of class Team.
     */
    @Test
    public void testGetTrickCount() {
        System.out.println("getTrickCount");
        Team instance = new Team(new Player(1), new Player(1));
        int expResult = 0;
        int result = instance.getTrickCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of addToScore method, of class Team.
     */
    @Test
    public void testAddToScore() {
        System.out.println("addToScore");
        int a = 2;
        Team instance = new Team(new Player(1), new Player(1));
        instance.addToScore(a);
    }

    /**
     * Test of takeTrick method, of class Team.
     */
    @Test
    public void testTakeTrick() {
        System.out.println("takeTrick");
        Team instance = new Team(new Player(1), new Player(1));
        instance.takeTrick();
    }

    /**
     * Test of reset method, of class Team.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Team instance = new Team(new Player(1), new Player(1));
        instance.reset();
    }

    /**
     * Test of calledTrump method, of class Team.
     */
    @Test
    public void testCalledTrump() {
        System.out.println("calledTrump");
        Team instance = new Team(new Player(1), new Player(1));
        instance.calledTrump();
    }
    
}
