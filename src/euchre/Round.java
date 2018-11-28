/*
Contains all the necessary functionality specific to each round played in a Euchre game
*/
package euchre;


/**
 *
 * @author Joe
 */
public class Round {
    private String trump;
    private int dealerPosition;
    private int leaderPosition;
    private int currentPosition;
    private boolean trumpCalled;
    private Card shownCard;
    private Trick trick;
    //Constructor
    public Round(int d) {
        trump = "None";
        trumpCalled = false;
        dealerPosition = d;
        if(d<4)
            leaderPosition = d+1;
        else
            leaderPosition = 1;
        currentPosition = leaderPosition;
        trick = new Trick();
    }
    //Mutators
    public void setTrump(String s) {
        trump = s;
        trumpCalled = true;
        trick.setTrump(s);
    }
    public void setSuitLed(String s) {
        trick.setSuitLed(s);
    }
    public void setDealerPosition(int a) {
        dealerPosition = a;
    }
    public void setLeaderPosition(int a) {
        leaderPosition = a;
    }
    public void setCurrentPosition(int a) {
        currentPosition = a;
    }
    public void setShownCard(Card c) {
        shownCard = c;
    }
    //Accessors
    public String getTrump() {
        return trump;
    }
    public String getSuitLed() {
        return trick.getSuitLed();
    }    
    public int getDealerPosition() {
        return dealerPosition;
    }
    public int getLeaderPosition() {
        return leaderPosition;
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
    public Card getShownCard() {
        return shownCard;
    }
    public Trick getTrick() {
        return trick;
    }
    public Player getTrickWinner() {
        return trick.getWinner();
    }
    //Methods
    public boolean isTrumpCalled() {
        return trumpCalled;
    }
    public void nextPlayer() {
        currentPosition++;
        if(currentPosition>4)
            currentPosition = 1;
    }
    public void reset() {
        trump = "None"; //Trump is set back to null
        if(dealerPosition<4) //If the dealer is not Opponent 2, increases dealer position by 1
            dealerPosition++;
        else //Else, dealer position is set to Player
            dealerPosition = 1;
        leaderPosition = dealerPosition + 1; //Leader of the round is set to the person immediately following the dealer
        if(leaderPosition>4) //If the dealer is Opponent 2, sets the player as the leader
            leaderPosition = 1;
        currentPosition = leaderPosition; //Current position is updated to leader position
        trick.setSuitLed("None"); //Suit led is reset to being empty
        trick.setTrump("None");
        trumpCalled = false;
    }            
}
