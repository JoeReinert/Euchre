/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euchre;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Joe
 */
public class Player {
    private final ArrayList<Card> hand;
    public boolean played[];
    private int position;
    private final int difficulty;
    
    public Player(int a) {
        hand = new ArrayList<>();
        difficulty = a;
        played = new boolean[] {false,false,false,false,false};
    }

    public void addCard(Card c) {
        hand.add(c);
    }
    public Card getCard(int i) {
       return hand.get(i);
    }
    public boolean getPlayed(int i) {
        return played[i];
    }
    public int getPosition() {
        return position;
    }  
    public void setPosition(int a) {
        position = a;
    }
    public void removeCard(int a) {
        played[a] = true;
    }
    public void reset() {
        hand.clear(); //Clears cards from hand
        played = new boolean[] {false,false,false,false,false}; //Sets all cards in hand to not played
    }
    public void setCard(Card c, int a) {
        hand.set(a, c);
    }
    public String selectTrump() {
        if(difficulty==1) { //Easy difficulty will randomly select a trump suit
            Random rand = new Random();
            int select = rand.nextInt(4);
            switch(select) {
                case 0: return "Spades";
                case 1: return "Hearts";
                case 2: return "Diamonds";
                case 3: return "Clubs";
                default: break;
            }
        }
        else{ //Normal difficulty will select the most numerous suit in the player's hand
            int spadesCount = 0;
            int heartsCount = 0;
            int diamondsCount = 0;
            int clubsCount = 0;
            for(int i=0;i<5;i++) {
                switch(getCard(i).getSuit()) {
                    case 0: spadesCount++;
                            break;
                    case 1: diamondsCount++;
                            break;
                    case 2: heartsCount++;
                            break;
                    case 3: clubsCount++;
                            break;
                }
            }
            if(spadesCount>=diamondsCount && spadesCount>=heartsCount && spadesCount>=clubsCount)
                return "Spades";
            else if(diamondsCount>=spadesCount && diamondsCount>=heartsCount && diamondsCount>=clubsCount)
                return "Diamonds";
            else if(heartsCount>=spadesCount && heartsCount>=diamondsCount && heartsCount>=clubsCount)
                return "Hearts";
            else
                return "Clubs";
        }
        return null;
    }
    public Card pickCard(String suitLed) {
        ArrayList<Card> tempHand = new ArrayList<>();
            for(int i=0;i<hand.size();i++) { //Iterates through hand
                if(hand.get(i).isSameSuit(suitLed) && !played[i]) //Gets all available cards that are in the necessary suit
                    tempHand.add(hand.get(i));
            }
        Random rand = new Random();
        if(!tempHand.isEmpty()) { //If any cards in hand match suit necessary to play
            int index;
            switch(difficulty) {
                case 1: //Logic for Easy difficulty computers
                    //Chooses from cards in same suit
                    int a = rand.nextInt(tempHand.size());
                    Card card = tempHand.get(a);
                    index = hand.indexOf(card);
                    played[index] = true;
                    return card;
                case 2: //Logic for Normal Difficulty computers
                    //Chooses highest value card possible
                    int maxValue = 0;
                    Card maxCard = new Card();
                    for(int i=0;i<tempHand.size();i++) {
                        if(tempHand.get(i).getValue()>maxValue) {
                            maxCard = tempHand.get(i);
                            maxValue = tempHand.get(i).getValue();
                        }                            
                    }
                    index = hand.indexOf(maxCard);
                    played[index] = true; //Selects and removes card from hand
                    return maxCard;   
            }
        }
        else { //Chooses random card from hand
            int a = rand.nextInt(hand.size());
            while(played[a]) { //Checks if card at index a has already been played
                a = rand.nextInt(hand.size()); //If so, generate new value for a
            }
            Card card = hand.get(a);
            played[a] = true; //Makes card unavailable
            return card;
        }
        
        return null;
    }
    public Card chooseCard(String s) {
        Card pick = pickCard(s);
        while(!checkCard(hand.indexOf(pick),s)) {
            pick = pickCard(s);
        }
        return pick;
    }
    public boolean checkCard(int a, String ledSuit) {
        if(ledSuit.isEmpty() || ledSuit.equals(hand.get(a).getSuitString())) //Checks to see if selected card matches led suit
            return true;
        for(int i=0;i<5;i++) { //Checks if any cards in hand match led suit
            if(!played[i] && ledSuit.equals(hand.get(i).getSuitString())) //If any cards not yet played match led suit, returns false
                return false;
        }
        return true; //If no cards match led suit, return true
    }
    public void discardCard(Card c, String trump) { //Logic for computer to select a card to discard
        Random rand = new Random();
        switch(difficulty) {
            case 1: //Easy Difficulty
                hand.set(rand.nextInt(5),c); //Randomly selects a card from computer's hand
                break;
            case 2: //Normal Difficulty
                for(int i=0;i<5;i++) { //Starts at lowest value
                    for(int j=0;j<5;j++) { //Checks each card in hand
                        if(hand.get(j).getValue()==i && !hand.get(j).getSuitString().equals(trump)) {
                            System.out.println("Discarding the " + hand.get(j).toString());
                            hand.set(j, c); //If the selected card is the lowest value possible and is not in the trump suit
                            return; //Replace that card with the shown card and return
                        }
                    }
                }
        }
    }
    public void replaceCard(int a, Card c) { //Logic for human player choosing a card to replace
        hand.set(a, c);
        System.out.println("Card " + a + " replaced with the " + c.toString()); //Remove after debugging
    }
    public void jackSwitch(int suit) { //Changes the values of Jack cards of same color as trump suit to top two cards
        for(int i=0;i<5;i++) {
            if(hand.get(i).getValue()==2) { //Checks if card is a Jack
                System.out.println("Jack found");
                if(hand.get(i).getSuit()==suit) { //Checks if the card matches the trump suit
                    hand.get(i).setValue(7); //Sets card value to maximum
                    System.out.println("Highest Jack set to proper value"); //Remove after debugging
                }
                else if(hand.get(i).getSuit() + suit == 3) { //Checks if the card is the same color as trump suit
                    hand.get(i).setValue(6); //Sets the card value to pentultimate value
                    hand.get(i).setSuit(suit); //Changes suit to match the trump suit
                    System.out.println("Second highest Jack set to proper value"); //Remove after debugging
                }
            }
        }
    }
}
