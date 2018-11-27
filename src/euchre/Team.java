/*
Contains the necessary functionality for each team participating
in a game of Euchre
 */
package euchre;

/**
 *
 * @author Joe
 */
public class Team {
    private final Player player1;
    private final Player player2;
    private int score;
    private int trickCount;
    private boolean calledTrump;
    //Constructors
    public Team(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        score = 0;
        trickCount = 0;
        calledTrump = false;
    }
    //Accessors
    public boolean getCalledTrump() {
        return calledTrump;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public int getScore() {
        return score;
    }
    public int getTrickCount() {
        return trickCount;
    }
    //Methods
    public void addToScore(int a) {
        score += a;
    }
    public void takeTrick() {
        trickCount++;
    }
    public void reset() {
        trickCount = 0;
        calledTrump = false;
        player1.reset();
        player2.reset();
    }
    public void calledTrump() {
        calledTrump = true;
    }
    
}
