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
public class euchreGUITest {
    
    public euchreGUITest() {
    }
    

    /**
     * Test of main method, of class euchreGUI.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        euchreGUI.main(args);
    }

    /**
     * Test of setGame method, of class euchreGUI.
     */
    @Test
    public void testSetGame() {
        System.out.println("setGame");
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
    }

    /**
     * Test of updateScores method, of class euchreGUI.
     */
    @Test
    public void testUpdateScores() {
        System.out.println("updateScores");
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
        g.startGame(1,1,1);
        instance.updateScores();
    }

    /**
     * Test of updateTrickCounts method, of class euchreGUI.
     */
    @Test
    public void testUpdateTrickCounts() {
        System.out.println("updateTrickCounts");
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
        g.startGame(1,1,1);
        instance.updateTrickCounts();
    }

    /**
     * Test of redrawTable method, of class euchreGUI.
     */
    @Test
    public void testRedrawTable() {
        System.out.println("redrawTable");
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
        g.startGame(1,1,1);
        instance.redrawTable();
    }

    /**
     * Test of updateDealer method, of class euchreGUI.
     */
    @Test
    public void testUpdateDealer() {
        System.out.println("updateDealer");
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
        instance.updateDealer();
    }

    /**
     * Test of discardDraw method, of class euchreGUI.
     */
    @Test
    public void testDiscardDraw() {
        System.out.println("discardDraw");
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
        instance.discardDraw();
    }

    /**
     * Test of updateMessageLabel method, of class euchreGUI.
     */
    @Test
    public void testUpdateMessageLabel() {
        System.out.println("updateMessageLabel");
        String s = "Test Message";
        euchreGUI instance = new euchreGUI();
        instance.updateMessageLabel(s);
    }

    /**
     * Test of enableButtons method, of class euchreGUI.
     */
    @Test
    public void testEnableButtons() {
        System.out.println("enableButtons");
        euchreGUI instance = new euchreGUI();
        instance.enableButtons();
    }

    /**
     * Test of disableButtons method, of class euchreGUI.
     */
    @Test
    public void testDisableButtons() {
        System.out.println("disableButtons");
        euchreGUI instance = new euchreGUI();
        instance.disableButtons();
    }

    /**
     * Test of drawComputerPlayedCard method, of class euchreGUI.
     */
    @Test
    public void testDrawComputerPlayedCard() {
        System.out.println("drawComputerPlayedCard");
        int a = 3;
        Card c = new Card(0,3);
        euchreGUI instance = new euchreGUI();
        instance.drawComputerPlayedCard(a, c);
    }

    /**
     * Test of cardClicked method, of class euchreGUI.
     */
    @Test
    public void testCardClicked() {
        System.out.println("cardClicked");
        int a = 0;
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
        instance.cardClicked(a);
    }

    /**
     * Test of suitSelected method, of class euchreGUI.
     */
    @Test
    public void testSuitSelected() {
        System.out.println("suitSelected");
        int a = 0;
        euchreGUI instance = new euchreGUI();
        Game g = new Game(instance, new Deck());
        instance.setGame(g);
        g.startGame(1,1,1);
        instance.suitSelected(a);
    }

    /**
     * Test of newTrumpInfo method, of class euchreGUI.
     */
    @Test
    public void testNewTrumpInfo() {
        System.out.println("newTrumpInfo");
        String suit = "Spades";
        euchreGUI instance = new euchreGUI();
        instance.newTrumpInfo(suit);
    }

    /**
     * Test of clearPlayedCards method, of class euchreGUI.
     */
    @Test
    public void testClearPlayedCards() {
        System.out.println("clearPlayedCards");
        euchreGUI instance = new euchreGUI();
        instance.clearPlayedCards();
    }
    
}
