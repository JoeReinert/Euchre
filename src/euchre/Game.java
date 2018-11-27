/*
Contains all the functionality necessary to begin and maintain
a game of Euchre
 */
package euchre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Joe
 */
public class Game {
    private Team playerTeam;
    private Team oppTeam;
    private final euchreGUI gui;
    private final Deck deck;
    private final Round round;
    private Timer firstTimer;
    private Timer playTimer;
    private boolean waiting;
    private String state;
    private int passCount;
    private int playCount;
    //Constructor
    public Game(euchreGUI g, Deck d) {
        gui = g;
        deck = d;
        Random rand = new Random();
        round = new Round(rand.nextInt(4)+1);
        waiting = false;
        state = "Pick it up";
        passCount = 0;
        playCount = 0;
    }
    //Accessors
    public Team getPlayerTeam() {
        return playerTeam;
    }
    public Team getOppTeam() {
        return oppTeam;
    }
    public Card getPlayerCard(int a) {
        return playerTeam.getPlayer1().getCard(a);
    }
    public Card getShownCard() {
        return deck.get(20);
    }
    public Round getRound() {
        return round;
    }
    public int getCurrentPosition() {
        return round.getCurrentPosition();
    }
    public String getState() {
        return state;
    }
    public int getPassCount() {
        return passCount;
    }
    public boolean getWaiting() {
        return waiting;
    }
    //Mutators
    public void setWaiting(boolean b) {
        waiting = b;
    }
    public void setState(String s) {
        state = s;
    }
    //Methods
    public void startGame(int partnerDifficulty, int opp1Difficulty, int opp2Difficulty) {
        Player player = new Player(0);
        player.setPosition(1);
        Player partner = new Player(partnerDifficulty);
        partner.setPosition(3);
        Player opp1 = new Player(opp1Difficulty);
        opp1.setPosition(2);
        Player opp2 = new Player(opp2Difficulty);
        opp2.setPosition(4);
        playerTeam = new Team(player, partner);
        oppTeam = new Team(opp1, opp2);
        gui.setGame(this);
        dealCards();
        gui.updateDealer();
        gui.redrawTable();
        gui.setVisible(true);
        gui.setAlwaysOnTop(true); //For some reason, the window doesn't appear on top when set to visible
        gui.setAlwaysOnTop(false); //So, these two statements set the window to be on top
        round.getTrick().setTeams(playerTeam, oppTeam);
        passOrPlay();

    }
    public void passOrPlay() { //Loop where players choose to pass or play
        ActionListener taskPerformed = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(passCount<4) { //Checks if any player wants to make the shown card's suit the trump suit
                    if(!round.isTrumpCalled()) //Trump has not been called yet
                        pickItUpOrPass();
                    else { //Trump has been called
                        firstTimer.stop();
                        state = "Playing"; //State changes for logic in other methods
                        round.setCurrentPosition(round.getLeaderPosition()); //Resets current position to appropriate place
                        changeJackValues();
                        playLoop();
                    }
                    if(waiting) //Stops timer if player is the one making the choice
                        firstTimer.stop();
                }
                else if(passCount<8) { //Checks if any player wants to call trump
                    if(!round.isTrumpCalled()) { //Trump has not been called yet
                        gui.discardDraw(); //Turns over the shown card
                        selectTrumpOrPass();
                    }
                    else { //Trump has been called
                        firstTimer.stop();
                        state = "Playing"; //State changes for logic in other methods
                        round.setCurrentPosition(round.getLeaderPosition()); //Resets current position to appropriate place
                        changeJackValues();
                        playLoop();
                    }
                    if(waiting) //Stops timer if player is the one making the choice
                        firstTimer.stop();
                }
                else { //If no one calls trump, a new round starts
                    firstTimer.stop();
                    endOfRound();
                }
            }
        };
        firstTimer = new Timer(2000, taskPerformed);
        firstTimer.start();
    }
    public void dealCards() { //Shuffles and deals cards to players and makes new shown card
        deck.shuffle();
        for(int i=0;i<20;i++) {
            switch (i%4) {
                case 0:
                    playerTeam.getPlayer1().addCard(deck.get(i));
                    break;
                case 1:
                    oppTeam.getPlayer1().addCard(deck.get(i));
                    break;
                case 2:
                    playerTeam.getPlayer2().addCard(deck.get(i));
                    break;
                case 3:
                    oppTeam.getPlayer2().addCard(deck.get(i));
                    break;
                default:
                    break;
            }
        }
        round.setShownCard(deck.get(20));
    }
    public void pickItUpOrPass() { //Logic that determines whether or not the shown card is picked up
        Random rand = new Random(); //Used for random elements below
        switch(round.getCurrentPosition()) {
            case 1: //Player
                gui.updateMessageLabel("Please choose to pick it up or pass");
                gui.enableButtons();
                waiting = true;
                return;
            case 2: //Opponent 1
                if(rand.nextInt(10)==0) { //10% chance that opponent 1 will tell dealer to pick up shown card
                    gui.updateMessageLabel("Opponent 1 chose \"Pick-it-up\"");
                    round.setTrump(round.getShownCard().getSuitString());
                    oppTeam.calledTrump();
                    System.out.println("Opponent 1 chose \"Pick-it-up\""); //Remove after debugged
                    System.out.println(round.getTrump() + " is trump for the round");
                    pickItUp();
                    return;
                }
                else { //90% chance that opponent 1 will pass
                    gui.updateMessageLabel("Opponent 1 chose to pass");
                    System.out.println("Opponent 1 chose to pass"); //Remove after debugged
                }
                break;
            case 3: //Partner
                if(rand.nextInt(10)==0) { //10% chance that partner will tell dealer to pick up shown card
                    round.setTrump(round.getShownCard().getSuitString());
                    gui.updateMessageLabel("Partner chose \"Pick-it-up\"");
                    System.out.println("Partner chose \"Pick-it-up\""); //Remove after debugged
                    playerTeam.calledTrump();
                    pickItUp();
                    return;
                } 
                else {//90% chance that partner will pass
                    gui.updateMessageLabel("Partner chose to pass");
                    System.out.println("Partner chose to pass"); //Remove after debugged
                }
                break;
            case 4: //Opponent 2
                if(rand.nextInt(10)==0){ //10% chance that opponent 2 will tell dealer to pick up shown card
                    round.setTrump(round.getShownCard().getSuitString()); 
                    gui.updateMessageLabel("Opponent 2 chose \"Pick-it-up\"");
                    System.out.println("Opponent 2 chose \"Pick-it-up\""); //Remove after debugged
                    oppTeam.calledTrump();
                    pickItUp();
                    return;
                }
                else { //90% chance that opponent 2 will pass
                    gui.updateMessageLabel("Opponent 2 chose to pass");
                    System.out.println("Opponent 2 chose to pass"); //Remove after debugged
                }
                break;
        }
        playerPassed();
    }
    public void pickItUp() { //Logic for getting dealer of the round to discard a card from their hand
        switch(round.getDealerPosition()) {
            case 1: //Player is the dealer and must discard
                gui.updateMessageLabel("Please select a card to discard");
                state = "Discarding";
                waiting = true;
                return;
            case 2: //Opponent 1 must discard
                oppTeam.getPlayer1().discardCard(round.getShownCard(), round.getTrump());
                System.out.println("Opponent 1 discarded"); //Remove after debugging
                break;
            case 3: //Partner must discard
                playerTeam.getPlayer2().discardCard(round.getShownCard(), round.getTrump());
                System.out.println("Partner discarded"); //Remove after debugging
                break;
            case 4: //Opponent 2 must discard
                oppTeam.getPlayer2().discardCard(round.getShownCard(), round.getTrump());
                System.out.println("Opponent 2 discarded"); //Remove after debugging
                break;   
        }
        gui.discardDraw();
        gui.newTrumpInfo(round.getTrump());
        passOrPlay();
    }
    public void selectTrumpOrPass() {//Logic that determines whether or not a trump suit is selected after shown card is discarded
        state = "Select Trump"; //State set for logic purposes in other methods
        Random rand = new Random(); //Used for random elements below
        switch(round.getCurrentPosition()) { 
            case 1: //Player
                gui.updateMessageLabel("Please select a trump suit or pass");
                gui.enableButtons(); //Enables player buttons to choose
                waiting = true;
                return;
            case 2: //Opponent 1
                if(rand.nextInt(10)==0) {     
                    round.setTrump(oppTeam.getPlayer1().selectTrump());
                    oppTeam.calledTrump();
                    gui.newTrumpInfo(round.getTrump());
                    gui.updateMessageLabel("Opponent 1 chose " + round.getTrump() + " as trump");
                    System.out.println("Opponent 1 chose " + round.getTrump() + " as trump"); //Remove after debugged                    
                    return;
                }
                else {
                    gui.updateMessageLabel("Opponent 1 chose to pass");
                    System.out.println("Opponent 1 chose to pass again"); //Remove after debugged
                }
                break;
            case 3: //Partner
                if(rand.nextInt(10)==0) {
                    round.setTrump(playerTeam.getPlayer2().selectTrump());
                    playerTeam.calledTrump();
                    gui.newTrumpInfo(round.getTrump());
                    gui.updateMessageLabel("Partner chose " + round.getTrump() + " as trump");
                    System.out.println("Partner chose " + round.getTrump() + " as trump"); //Remove after debugged                    
                    return;
                } 
                else {
                    gui.updateMessageLabel("Partner chose to pass");
                    System.out.println("Partner chose to pass again"); //Remove after debugged
                }
                break;
            case 4: //Opponent 2
                if(rand.nextInt(10)==0){ 
                    round.setTrump(oppTeam.getPlayer2().selectTrump());
                    oppTeam.calledTrump();
                    gui.newTrumpInfo(round.getTrump());
                    gui.updateMessageLabel("Opponent 2 chose " + round.getTrump() + " as trump");
                    System.out.println("Opponent 2 chose " + round.getTrump() + " as trump"); //Remove after debugged
                    return;
                }
                else {
                    gui.updateMessageLabel("Opponent 2 chose to pass");
                    System.out.println("Opponent 2 chose to pass again"); //Remove after debugged
                }
                break;
        }
        playerPassed();
    }
    public void playLoop() { //Main loop for the game
        int playLoopDelay = 2000;
        ActionListener taskPerformed = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((playerTeam.getTrickCount() + oppTeam.getTrickCount())<5) { //Checks if 5 tricks have been taken yet, signaling the end of the round
                    if(playCount<4) { //Checks if every player has played in the trick
                        playCard(); //Runs logic for selecting card to play for each player
                        if(waiting) { //If the current position is at the player, exit the timer until they have selected a card
                            playTimer.stop(); 
                            gui.updateMessageLabel("Please select a card to play");
                            return;
                        }
                    }
                    else { //Every player has played a card in the trick
                        int p = round.getTrickWinner().getPosition(); //Determines which team gets the trick from winner position p
                        switch(p) {
                            case 1:
                                playerTeam.takeTrick(); //Player team trick count will increase
                                gui.updateMessageLabel("You take the trick with the " + round.getTrick().getWinningCard().toString());
                                break;
                            case 2:
                                oppTeam.takeTrick(); //Opponent team trick count will increase
                                gui.updateMessageLabel("Opponent 1 takes the trick with the " + round.getTrick().getWinningCard().toString());
                                break;
                            case 3:
                                playerTeam.takeTrick(); //Player team trick count will increase
                                gui.updateMessageLabel("Your partner takes the trick with the " + round.getTrick().getWinningCard().toString());
                                break;
                            case 4:
                                oppTeam.takeTrick(); //Opponent team trick count will increase
                                gui.updateMessageLabel("Opponent 2 takes the trick with the " + round.getTrick().getWinningCard().toString());
                                break;
                        }
                        gui.updateTrickCounts();
                        gui.clearPlayedCards();
                        round.setLeaderPosition(p); //Winner of this trick is leader of next trick
                        round.setCurrentPosition(p); //Also needs to be set to current position
                        playCount = 0; //Reset playCount for next trick
                        round.getTrick().reset();
                    }
                }                 
                else { //A team has won the round
                    playTimer.stop();
                    endOfRound();
                }
            }
        };
        playTimer = new Timer(playLoopDelay, taskPerformed);
        playTimer.start();
    }
    public void playerPassed() {
        round.nextPlayer();
        passCount++;
    }
    public void playerPlayed() {
        round.nextPlayer();
        playCount++;
    }
    public boolean checkPlayerCard(int a) {
        return playerTeam.getPlayer1().checkCard(a, round.getSuitLed());
    }
    public void endOfRound() { //Resets round
        if(playerTeam.getCalledTrump()) { //Determines which team called trump
            if(playerTeam.getTrickCount()==5) //Player team won all tricks
                playerTeam.addToScore(2);
            else if(playerTeam.getTrickCount()>2) //Player team won a majority of the tricks
                playerTeam.addToScore(1);
            else //Opposing team won a majority of the tricks
                oppTeam.addToScore(2);
        }
        else if(oppTeam.getCalledTrump()){
            if(oppTeam.getTrickCount()==5) //Opposing team won all tricks
                oppTeam.addToScore(2);
            else if(oppTeam.getTrickCount()>2) //Opposing team won a majority of the tricks
                oppTeam.addToScore(1);
            else //Player team won a majority of the tricks
                playerTeam.addToScore(2);
            
        }
        else { //This occurs if no one calls trump
            gui.updateMessageLabel("Everyone passed");
        }
        gui.updateScores();
        playerTeam.reset();
        oppTeam.reset();
        round.reset();
        dealCards();
        gui.updateDealer();
        gui.redrawTable();
        passCount = 0;
        playCount = 0;
        state = "Pick it up";
        passOrPlay();
    }
    public void updateMessage(String s) {
        gui.updateMessageLabel(s);
    }
    public void playCard() { 
        Card card = new Card();
        switch(round.getCurrentPosition()) {
            case 1:
                System.out.println("Player choosing card"); //Remove after debugged
                waiting = true;
                return;
            case 2:
                System.out.println("Opp 1 choosing card"); //Remove after debugged
                card = oppTeam.getPlayer1().chooseCard(round.getSuitLed());
                break;
            case 3:
                System.out.println("Partner choosing card"); //Remove after debugged
                card = playerTeam.getPlayer2().chooseCard(round.getSuitLed());
                break;
            case 4:
                System.out.println("Opp 2 choosing card"); //Remove after debugged
                card = oppTeam.getPlayer2().chooseCard(round.getSuitLed());
                break;
        }
        gui.drawComputerPlayedCard(round.getCurrentPosition(),card);
        round.getTrick().playCard(round.getCurrentPosition(),card);
        playerPlayed();
    }
    public void changeJackValues() { //Changes the value of Jack cards, if necessary
        int suit = 0; //Default suit value is Spades
        switch (round.getTrump()) {
            case "Diamonds":
                suit = 1;
                break;
            case "Hearts":
                suit = 2;
                break;
            case "Clubs":
                suit = 3;
                break;
            default:
                break;
        }
        playerTeam.getPlayer1().jackSwitch(suit);
        oppTeam.getPlayer1().jackSwitch(suit);
        playerTeam.getPlayer2().jackSwitch(suit);
        oppTeam.getPlayer2().jackSwitch(suit);
    }
}
