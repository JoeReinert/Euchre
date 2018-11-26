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
    
    public Game(euchreGUI g, Deck d) {
        gui = g;
        deck = d;
        Random rand = new Random();
        round = new Round(rand.nextInt(4)+1);
        waiting = false;
        state = "Pick it up";
    }
    //Accessors
    public Team getPlayerTeam() {
        return playerTeam;
    }
    public Team getOppTeam() {
        return oppTeam;
    }
    public int getPlayerTeamScore() {
        return playerTeam.getScore();
    }
    public int getOppTeamScore() {
        return oppTeam.getScore();
    }
    public int getPlayerTrickCount() {
        return playerTeam.getTrickCount();
    }
    public int getOppTrickCount() {
        return oppTeam.getTrickCount();
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
    //Mutators
    public void setWaiting(boolean b) {
        waiting = b;
    }
    public void setState(String s) {
        state = s;
    }
    public void setPassCount(int a) {
        passCount = a;
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
        passCount = 0;
        playCount = 0;
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
                        play();
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
                        play();
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
        System.out.println("Now in \"Pick-it-up\" Phase"); //Remove after debugged
        Random rand = new Random(); //Used for random elements below
        System.out.println("PassCount: " + passCount + "  Current Position: " + round.getCurrentPosition()); //Remove after debugged
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
                    pickItUp();
                    return;
                }
                else //90% chance that opponent 1 will pass
                    gui.updateMessageLabel("Opponent 1 chose to pass");
                break;
            case 3: //Partner
                if(rand.nextInt(10)==0) { //10% chance that partner will tell dealer to pick up shown card
                    round.setTrump(round.getShownCard().getSuitString());
                    gui.updateMessageLabel("Partner chose \"Pick-it-up\"");
                    playerTeam.calledTrump();
                    pickItUp();
                    return;
                } 
                else //90% chance that partner will pass
                    gui.updateMessageLabel("Partner chose to pass");
                break;
            case 4: //Opponent 2
                if(rand.nextInt(10)==0){ //10% chance that opponent 2 will tell dealer to pick up shown card
                    round.setTrump(round.getShownCard().getSuitString()); 
                    gui.updateMessageLabel("Opponent 2 chose \"Pick-it-up\"");
                    oppTeam.calledTrump();
                    pickItUp();
                    return;
                }
                else //90% chance that opponent 2 will pass
                    gui.updateMessageLabel("Opponent 2 chose to pass");
                break;
        }
        playerPassed();
    }
    public void pickItUp() {
        switch(round.getDealerPosition()) {
            case 1:
                gui.updateMessageLabel("Please select a card to discard");
                state = "Discarding";
                waiting = true;
                return;
            case 2:
                oppTeam.getPlayer1().discardCard(round.getShownCard(), round.getTrump());
                break;
            case 3:
                playerTeam.getPlayer2().discardCard(round.getShownCard(), round.getTrump());
                break;
            case 4:
                oppTeam.getPlayer2().discardCard(round.getShownCard(), round.getTrump());
                break;
                
        }
        gui.discardDraw();
        gui.newTrumpInfo(round.getTrump());
        passOrPlay();
    }
    public void selectTrumpOrPass() {//Logic that determines whether or not a trump suit is selected after shown card is discarded
        state = "Select Trump"; //State set for logic purposes in other methods
        System.out.println("Now in \"Select Trump\" Phase"); //Remove after debugged
        Random rand = new Random(); //Used for random elements below
        System.out.println("Pass Count: " + passCount + " Current Position: " + round.getCurrentPosition()); //Remove after debugged
        switch(round.getCurrentPosition()) { 
            case 1: //Player
                gui.updateMessageLabel("Please select a trump suit or pass");
                gui.enableButtons(); //Enables player buttons to choose
                waiting = true;
                return;
            case 2: //Opponent 1
                if(rand.nextInt(10)==0) {     
                    round.setTrump(oppTeam.getPlayer1().selectTrump());
                    gui.newTrumpInfo(round.getTrump());
                    gui.updateMessageLabel("Opponent 1 chose " + round.getTrump() + " as trump");
                    return;
                }
                else
                    gui.updateMessageLabel("Opponent 1 chose to pass");
                break;
            case 3: //Partner
                if(rand.nextInt(10)==0) {
                    round.setTrump(playerTeam.getPlayer2().selectTrump());
                    gui.newTrumpInfo(round.getTrump());
                    gui.updateMessageLabel("Partner chose " + round.getTrump() + " as trump");
                    return;
                } 
                else
                    gui.updateMessageLabel("Partner chose to pass");
                break;
            case 4: //Opponent 2
                if(rand.nextInt(10)==0){ 
                    round.setTrump(oppTeam.getPlayer2().selectTrump());
                    gui.newTrumpInfo(round.getTrump());
                    gui.updateMessageLabel("Opponent 2 chose " + round.getTrump() + " as trump");
                    return;
                }
                else
                    gui.updateMessageLabel("Opponent 2 chose to pass");
                break;
        }
        playerPassed();
    }
    public void play() { //Main loop for the game
        System.out.println("Now in \"Play\" Phase"); //Remove after debugged
        ActionListener taskPerformed = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(playerTeam.getTrickCount()+oppTeam.getTrickCount()<5) { //Checks if 5 tricks have been taken yet, signaling the end of the round
                    if(playCount<4) { //Checks if every player has played in the trick
                        if(round.getCurrentPosition()!=1) { //If the current player is a computer
                            System.out.println("Play Count: " + playCount + " Current Position: " + round.getCurrentPosition()); //Remove after debugged
                            computerPlay(); //Run logic for computer to select a card to play
                            playCount++;
                        }
                        else {
                            playTimer.stop();
                            gui.updateMessageLabel("Please select a card to play");
                            waiting = true;
                            return;
                        }
                        if(waiting) {
                            playTimer.stop();
                            return;
                        }
                    }
                    else { //Every player has played a card in the trick
                        playCount = 0; //Reset playCount for next trick
                        int p = round.getTrickWinner().getPosition(); //Determines which team gets the trick from winner position p
                        round.setLeaderPosition(p); //Winner of this trick is leader of next trick
                        round.setCurrentPosition(p); //Also needs to be set to current position
                        if(p%2==1)
                            playerTeam.takeTrick(); //Player team trick count will increase
                        else
                            oppTeam.takeTrick(); //Opposing team trick count will increase
                        gui.updateTrickCounts();
                        gui.clearPlayedCards();
                    }
                    if(waiting) //Exits timer until player selects card to play
                        playTimer.stop();
                }                 
                else { //A team has won the round
                    endOfRound();
                    playTimer.stop();
                }
            }
        };
        playTimer = new Timer(1000, taskPerformed);
        playTimer.start();
    }
    public void playerPassed() {
        round.nextPlayer();
        passCount++;
    }
    public void iteratePlayCount() {
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
    public void computerPlay() {
        System.out.println("Computer choosing card"); //Remove after debugged
        Card card = new Card();
        switch(round.getCurrentPosition()) {
            case 2:
                card = oppTeam.getPlayer1().chooseCard(round.getSuitLed());
                break;
            case 3:
                card = playerTeam.getPlayer2().chooseCard(round.getSuitLed());
                break;
            case 4:
                card = oppTeam.getPlayer2().chooseCard(round.getSuitLed());
                break;
        }
        gui.drawComputerPlayedCard(round.getCurrentPosition(),card);
        round.getTrick().playCard(round.getCurrentPosition(),card);
        playerPassed();
    }
}
