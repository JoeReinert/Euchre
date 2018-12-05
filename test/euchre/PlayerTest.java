/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchre;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jr155
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addCard method, of class Player.
     */
    @Test
    public void testAddAndGetCard() {
        System.out.println("addCard and getCard");
        Card c = new Card(0,0);
        Player instance = new Player(1);
        instance.addCard(c);
        assertEquals(c,instance.getCard(0));
    }

    /**
     * Test of getPosition method, of class Player.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        Player instance = new Player(1);
        int expResult = 0;
        int result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPosition method, of class Player.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        Player instance = new Player(1);
        instance.setPosition(3);
    }

    /**
     * Test of removeCard method, of class Player.
     */
    @Test
    public void testGetPlayedAndRemoveCard() {
        System.out.println("removeCard");
        Player instance = new Player(1);
        for(int i=0;i<5;i++) {
            assertTrue(!instance.getPlayed(i)); //First tests if card is not played
            instance.removeCard(i);
            assertTrue(instance.getPlayed(i)); //Then tests if card has been played after removeCard() is called
        }
    }

    /**
     * Test of reset method, of class Player.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Player instance = new Player(1);
        instance.reset();
    }

    /**
     * Test of setCard method, of class Player.
     */
    @Test
    public void testSetCard() {
        System.out.println("setCard");
        Card c = new Card(0,0);
        Card replacement = new Card(1,1);
        int a = 0;
        Player instance = new Player(1);
        instance.addCard(c);
        instance.setCard(replacement, a);
    }

    /**
     * Test of selectTrump method, of class Player.
     */
    @Test
    public void testSelectTrump() {
        System.out.println("selectTrump");
        Player instance = new Player(1);
        String result = instance.selectTrump();
        //Since the easy difficulty player randomly selects a suit, we need to include each value in our assertion test
        assertTrue(result.equals("Spades") || result.equals("Diamonds") || result.equals("Hearts") || result.equals("Clubs"));
        instance = new Player(2);
        instance.addCard(new Card(0,7));
        instance.addCard(new Card(0,6));
        instance.addCard(new Card(0,5));
        instance.addCard(new Card(0,4));
        instance.addCard(new Card(0,3)); //Since the player only has Spade cards
        String expResult = "Spades";     //and the normal dfficulty player chooses
        result = instance.selectTrump(); //the suit with the most numerous cards in
        assertEquals(expResult, result); //their hand, we should expect to get Spades
    }

    /**
     * Test of pickCard method, of class Player.
     */
    @Test
    public void testPickCard() {
        System.out.println("pickCard");
        String suitLed = "Spades";
        Player instance = new Player(1);
        instance.addCard(new Card(0,7));
        instance.addCard(new Card(1,6));
        instance.addCard(new Card(1,5));
        instance.addCard(new Card(1,4));
        instance.addCard(new Card(1,3));
        Card expResult = new Card(0,7);
        Card result = instance.pickCard(suitLed);
        assertTrue(expResult.getValue()==result.getValue() && expResult.getSuit()==result.getSuit());
    }

    /**
     * Test of chooseCard method, of class Player.
     */
    @Test
    public void testChooseCard() {
        System.out.println("chooseCard");
        String suitLed = "Spades";
        Player instance = new Player(1);
        instance.addCard(new Card(0,7));
        instance.addCard(new Card(1,6));
        instance.addCard(new Card(1,5));
        instance.addCard(new Card(1,4));
        instance.addCard(new Card(1,3));
        Card expResult = new Card(0,7);
        Card result = instance.chooseCard(suitLed);
        assertTrue(expResult.getValue()==result.getValue() && expResult.getSuit()==result.getSuit());
    }

    /**
     * Test of checkCard method, of class Player.
     */
    @Test
    public void testCheckCard() {
        System.out.println("checkCard");
        int a = 0;
        String ledSuit = "";
        Player instance = new Player(1);
        instance.addCard(new Card(0,3));
        instance.addCard(new Card(1,3));
        instance.addCard(new Card(2,3));
        instance.addCard(new Card(2,5));
        instance.addCard(new Card(0,2));
        boolean result = instance.checkCard(a, ledSuit);
        assertTrue(result); //Led suit is not yet determined
        ledSuit = "Spades";
        result = instance.checkCard(a, ledSuit);
        assertTrue(result); //Player is attempting to play a card from an incompatible suit
        ledSuit = "Diamonds";
        result = instance.checkCard(a, ledSuit);
        assertTrue(!result); //Player is attempting to play a card from an incompatible suit
        ledSuit = "Clubs";
        result = instance.checkCard(a, ledSuit);
        assertTrue(result); //Player does not have a card with the same suit as the card which was led
    }

    /**
     * Test of discardCard method, of class Player.
     */
    @Test
    public void testDiscardCard() {
        System.out.println("discardCard");
        Card c = new Card(0,5);
        String trump = "Spades";
        Player instance = new Player(1); //Easy computer
        instance.addCard(new Card(0,0));
        instance.addCard(new Card(1,4));
        instance.addCard(new Card(2,2));
        instance.addCard(new Card(3,0));
        instance.addCard(new Card(0,5));
        instance.discardCard(c, trump);
        instance = new Player(2); //Normal computer
        instance.addCard(new Card(0,0));
        instance.addCard(new Card(1,4));
        instance.addCard(new Card(2,2));
        instance.addCard(new Card(3,0));
        instance.addCard(new Card(0,5));
        instance.discardCard(c, trump);
    }

    /**
     * Test of replaceCard method, of class Player.
     */
    @Test
    public void testReplaceCard() {
        System.out.println("replaceCard");
        int a = 0;
        Card c = new Card(0,1);
        Player instance = new Player(1);
        instance.addCard(new Card(0,2));
        instance.replaceCard(a, c);
    }

    /**
     * Test of jackSwitch method, of class Player.
     */
    @Test
    public void testJackSwitch() {
        System.out.println("jackSwitch");
        int suit = 0;
        Player instance = new Player(1);
        instance.addCard(new Card(0,2));
        instance.addCard(new Card(1,4));
        instance.addCard(new Card(2,2));
        instance.addCard(new Card(3,2));
        instance.addCard(new Card(0,5));
        instance.jackSwitch(suit);
    }
    
}
