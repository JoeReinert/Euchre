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
        winningCard = null;
        suitLed = "None";
        leader = null;
    }
    //Mutators
    public void setTeams(Team t1, Team t2) {
        playerTeam = t1;
        oppTeam = t2;
    }
    public void setSuitLed(String s) {
        suitLed = s;
    }
    public void setTrump(String s) {
        trump = s;
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
    //Methods
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
                    System.out.println("You are the current leader");
                    break;
                case 2:
                    leader = oppTeam.getPlayer1();
                    System.out.println("Opponent 1 is the current leader");
                    break;
                case 3:
                    leader = playerTeam.getPlayer2();
                    System.out.println("Your partner is the current leader");
                    break;
                case 4:
                    leader = oppTeam.getPlayer2();
                    System.out.println("Opponent 2 is the current leader");
                    break;
            }
            winningCard = c;
            System.out.println("New winning card: " + winningCard.toString()); //Remove after debugging
        }
    }
    public boolean checkAgainstLeader(Card c) { //Determines if the card played is of higher value than the current winning card
        System.out.println("Suit Led: " + suitLed + " Turmp Suit: " + trump);
        if(suitLed.equals("None")) { //Check if this is the first card to be played
            System.out.println(c.toString() + " is the first card to be played");
            suitLed = c.getSuitString(); //Suit led becomes same suit as played card
            return true; //Since this is the first card of the trick to be played, it's automatically in the lead
        }
        else if (!winningCard.getSuitString().equals(trump) && c.getSuitString().equals(trump)) { //If the winning card is not a trump card, but the played card is a trump card
            System.out.println("Played card is trump, winning card was not trump");
            return true;
        }
        else if(winningCard.getSuitString().equals(trump) && c.getSuitString().equals(trump)) { //If both the winning card and played card are trump cards
            if(c.getValue() > winningCard.getValue()) { //Return true if the played card has a higher value than the winning card
                System.out.println("Played card is a higher value trump card");
                return true;
            }
            else {
                System.out.println("Played card is a lower value trump card");
                return false;
            }
        }
        else if(winningCard.getSuitString().equals(trump) && !c.getSuitString().equals(trump)) { //If the winning card is a trump card but the played card is not a trump card
            System.out.println("Winning card is trump, played card is not");
            return false;
        }
        else if(c.getSuitString().equals(suitLed)) { //If the played card matches the suit with the card that was led
            if(c.getValue() > winningCard.getValue()) { //Return true if the played card has a higher value than the winning card
                System.out.println("Played card is a higher value card of the same suit");
                return true;
            }
            else {
                System.out.println("Played card is a lower value card of the same suit");
                return false;
            }
        }
        else {
            System.out.println("Played card does not match suit of card that was led");
            return false; //Return false if card meets none of the above criteria
        }
    }
    public void reset() {
        winningCard = null;
        suitLed = "None";
        leader = null;
    }
}
