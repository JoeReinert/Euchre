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
public class Euchre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Initialize playing deck and shuffles cards
        Deck deck = new Deck();
        deck.shuffle();
        //Initializes setup and game UI
        euchreGUI e = new euchreGUI();
        //Initializes Game class
        Game game = new Game(e, deck);        
        //Initializes setup frame and makes it visible
        setupFrame s = new setupFrame(e, game);
        s.setVisible(true);
    }
    
}
