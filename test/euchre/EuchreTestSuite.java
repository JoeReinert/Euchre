/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchre;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Joe
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({euchre.RoundTest.class,
    euchre.PlayerTest.class,
    euchre.euchreGUITest.class,
    euchre.setupFrameTest.class,
    euchre.TrickTest.class,
    euchre.EuchreTest.class,
    euchre.GameTest.class,
    euchre.CardTest.class,
    euchre.DeckTest.class,
    euchre.TeamTest.class})
public class EuchreTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
