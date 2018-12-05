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
public class GameTest {
    
    public GameTest() {
    }

    /**
     * Test of getPlayerTeam method, of class Game.
     */
    @Test
    public void testGetPlayerTeam() {
        System.out.println("getPlayerTeam");
        Game instance = new Game(new euchreGUI(), new Deck());
        Team expResult = null;
        Team result = instance.getPlayerTeam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOppTeam method, of class Game.
     */
    @Test
    public void testGetOppTeam() {
        System.out.println("getOppTeam");
        Game instance = new Game(new euchreGUI(), new Deck());
        Team expResult = null;
        Team result = instance.getOppTeam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayerCard method, of class Game.
     */
    @Test
    public void testGetPlayerCard() {
        System.out.println("getPlayerCard");
        int a = 0;
        Game instance = new Game(new euchreGUI(), new Deck());
        Card c = new Card(0,0);
        instance.startGame(1, 1, 1);
        instance.getPlayerTeam().getPlayer1().setCard(c, a);
        Card result = instance.getPlayerCard(a);
        assertTrue(c.getSuit()==result.getSuit() && c.getValue()==result.getValue());
    }

    /**
     * Test of getShownCard method, of class Game.
     */
    @Test
    public void testGetShownCard() {
        System.out.println("getShownCard");
        Game instance = new Game(new euchreGUI(), new Deck());
        Card expResult = new Card(3,2);
        Card result = instance.getShownCard();
        assertTrue(expResult.getSuit()==result.getSuit() && expResult.getValue()==result.getValue());
    }

    /**
     * Test of getRound method, of class Game.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        Game instance = new Game(new euchreGUI(), new Deck());
        Round result = instance.getRound();
    }

    /**
     * Test of getCurrentPosition method, of class Game.
     */
    @Test
    public void testGetCurrentPosition() {
        System.out.println("getCurrentPosition");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.getRound().setCurrentPosition(2);
        int expResult = 2;
        int result = instance.getCurrentPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Game.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Game instance = new Game(new euchreGUI(), new Deck());
        String expResult = "Pick it up";
        String result = instance.getState();
        assertTrue(expResult.equals(result));
    }

    /**
     * Test of getPassCount method, of class Game.
     */
    @Test
    public void testGetPassCount() {
        System.out.println("getPassCount");
        Game instance = new Game(new euchreGUI(), new Deck());
        int expResult = 0;
        int result = instance.getPassCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWaiting method, of class Game.
     */
    @Test
    public void testGetWaiting() {
        System.out.println("getWaiting");
        Game instance = new Game(new euchreGUI(), new Deck());
        boolean expResult = false;
        boolean result = instance.getWaiting();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayerDiscarding method, of class Game.
     */
    @Test
    public void testGetPlayerDiscarding() {
        System.out.println("getPlayerDiscarding");
        Game instance = new Game(new euchreGUI(), new Deck());
        boolean expResult = false;
        boolean result = instance.getPlayerDiscarding();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWaiting method, of class Game.
     */
    @Test
    public void testSetWaiting() {
        System.out.println("setWaiting");
        boolean b = true;
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.setWaiting(b);
    }

    /**
     * Test of setState method, of class Game.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        String s = "Discarding";
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.setState(s);
    }

    /**
     * Test of setPlayerDiscarding method, of class Game.
     */
    @Test
    public void testSetPlayerDiscarding() {
        System.out.println("setPlayerDiscarding");
        boolean b = true;
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.setPlayerDiscarding(b);
    }

    /**
     * Test of startGame method, of class Game.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        int partnerDifficulty = 1;
        int opp1Difficulty = 1;
        int opp2Difficulty = 2;
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(partnerDifficulty, opp1Difficulty, opp2Difficulty);
    }

    /**
     * Test of passOrPlay method, of class Game.
     */
    @Test
    public void testPassOrPlay() {
        System.out.println("passOrPlay");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.passOrPlay();
    }

    /**
     * Test of dealCards method, of class Game.
     */
    @Test
    public void testDealCards() {
        System.out.println("dealCards");
        Game instance = new Game(new euchreGUI(), new Deck());;
        instance.startGame(1, 1, 1);
        instance.dealCards();
    }

    /**
     * Test of pickItUpOrPass method, of class Game.
     */
    @Test
    public void testPickItUpOrPass() {
        System.out.println("pickItUpOrPass");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        instance.pickItUpOrPass();
    }

    /**
     * Test of pickItUp method, of class Game.
     */
    @Test
    public void testPickItUp() {
        System.out.println("pickItUp");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        instance.pickItUp();
    }

    /**
     * Test of selectTrumpOrPass method, of class Game.
     */
    @Test
    public void testSelectTrumpOrPass() {
        System.out.println("selectTrumpOrPass");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        instance.selectTrumpOrPass();
    }

    /**
     * Test of playLoop method, of class Game.
     */
    @Test
    public void testPlayLoop() {
        System.out.println("playLoop");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.playLoop();
    }

    /**
     * Test of playerPassed method, of class Game.
     */
    @Test
    public void testPlayerPassed() {
        System.out.println("playerPassed");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.playerPassed();
    }

    /**
     * Test of playerPlayed method, of class Game.
     */
    @Test
    public void testPlayerPlayed() {
        System.out.println("playerPlayed");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.playerPlayed();
    }

    /**
     * Test of checkPlayerCard method, of class Game.
     */
    @Test
    public void testCheckPlayerCard() {
        System.out.println("checkPlayerCard");
        int a = 0;
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        String suit = instance.getPlayerCard(a).getSuitString();
        instance.getRound().setTrump(suit);
        boolean result = instance.checkPlayerCard(a);
        assertTrue(result);
    }

    /**
     * Test of endOfRound method, of class Game.
     */
    @Test
    public void testEndOfRound() {
        System.out.println("endOfRound");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        instance.endOfRound();
    }

    /**
     * Test of updateMessage method, of class Game.
     */
    @Test
    public void testUpdateMessage() {
        System.out.println("updateMessage");
        String s = "Test Message";
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.updateMessage(s);
    }

    /**
     * Test of playCard method, of class Game.
     */
    @Test
    public void testPlayCard() {
        System.out.println("playCard");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        instance.playCard();
    }

    /**
     * Test of changeJackValues method, of class Game.
     */
    @Test
    public void testChangeJackValues() {
        System.out.println("changeJackValues");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        instance.changeJackValues();
    }

    /**
     * Test of resetJacks method, of class Game.
     */
    @Test
    public void testResetJacks() {
        System.out.println("resetJacks");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.resetJacks();
    }

    /**
     * Test of beginRound method, of class Game.
     */
    @Test
    public void testBeginRound() {
        System.out.println("beginRound");
        Game instance = new Game(new euchreGUI(), new Deck());
        instance.startGame(1, 1, 1);
        instance.beginRound();
    }
    
}
