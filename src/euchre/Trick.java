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
public class Trick {
    private String trump;
    private String suitLed;
    private Team playerTeam;
    private Team oppTeam;
    private Player leader;
    private Card winningCard;
    
    //Constructor
    public Trick() {
        suitLed = "None";
    }
    //Mutators
    public void setTeams(Team t1, Team t2) {
        playerTeam = t1;
        oppTeam = t2;
    }
    public void setSuitLed(String s) {
        suitLed = s;
    }
    //Accessors
    public Player getWinner() {
        return leader;
    }
    public String getSuitLed() {
        return suitLed;
    }
    public Card getWinningCard() {
        return winningCard;
    }
    public void playCard(int position, Card c) {
        switch(position) { //Remove this switch case after debugged
            case 1:
                System.out.println("You played the " + c.toString());
                break;
            case 2:
                System.out.println("Opponent 1 played the " + c.toString());
                break;
            case 3:
                System.out.println("Your partner played the " + c.toString());
                break;
            case 4:
                System.out.println("Opponent 2 played the " + c.toString());
                break;
        }
        if(checkAgainstLeader(c)) {
            switch(position) {
                case 1:
                    leader = playerTeam.getPlayer1();
                    break;
                case 2:
                    leader = oppTeam.getPlayer1();
                    break;
                case 3:
                    leader = playerTeam.getPlayer2();
                    break;
                case 4:
                    leader = oppTeam.getPlayer2();
                    break;
            }
            winningCard = c;
            System.out.println("Current winning card: " + winningCard.toString()); //Remove after debugging
        }
    }
    public boolean checkAgainstLeader(Card c) { //Determines if the card played is of higher value than the current winning card
        if(suitLed.equals("None")) { //Check if this is the first card to be played
            System.out.println(c.toString() + " is the first card to be played");
            suitLed = c.getSuitString(); //Suit led becomes same suit as played card
            return true; //Since this is the first card of the trick to be played, it's automatically in the lead
        }
        else if (!winningCard.getSuitString().equals(trump) && c.getSuitString().equals(trump)) //If the winning card is not a trump card, but the played card is a trump card
            return true;
        else if(winningCard.getSuitString().equals(trump) && c.getSuitString().equals(trump)) //If both the winning card and played card are trump cards
            return c.getValue() > winningCard.getValue(); //Return true if the played card is a higher value than the winning card
        else if(winningCard.getSuitString().equals(trump) && !c.getSuitString().equals(trump)) //If the winning card is a trump card but the played card is not a trump card
            return false;
        else if(c.getSuitString().equals(suitLed)) //If the played card matches the suit with the card that was led
            return c.getValue() > winningCard.getValue();
        else
            return false; //Return false if card meets none of the above criteria
    }
    public void reset() {
        winningCard = null;
        suitLed = "None";
        leader = null;
    }
}
