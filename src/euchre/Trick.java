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
    
    public Trick() {
        suitLed = "";
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
        if(suitLed.equals("")) { //Check if this is the first card to be played
            suitLed = c.getSuitString(); //Suit led becomes same suit as played card
            return true; //Since this is the first card of the trick to be played, it's automatically in the lead
        }
        else if(c.getSuitString().equals(trump)) { //If the card played is a trump card
            if(winningCard.getSuitString().equals(trump)) //If the winning card is also trump
                return c.getValue()>winningCard.getValue(); //Return true if the card played is a higher value trump card, and false otherwise
            else
                return true; //Return true if the winning card is not a trump card
        }
        else if(c.getSuitString().equals(suitLed) && !winningCard.getSuitString().equals(trump)) //If the card played matches the suit of the suit that was led and the winning card is not a trump card
            return c.getValue()>winningCard.getValue();  //Return true if the card played is a higher value card
        else
            return false; //Return false if card meets none of the above criteria
    }
    public void reset() {
        winningCard = null;
        suitLed = "";
        leader = null;
    }
}
