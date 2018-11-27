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
public class Card {
    private int suit, value;
    private final String[] cardSuit = {"Spades", "Diamonds", "Hearts", "Clubs"};
    private final String[] cardValue = {"9", "10", "Jack", "Queen", "King", "Ace", "Jack", "Jack"};//2, 3, 4, 5, 6, 7, and 8 are unused in this game
    
    //Constructors
    public Card() {
    }
    public Card(int cSuit, int cValue) {
        suit = cSuit; 
        value = cValue;
    } 
    //Accessors
    public int getSuit() {
        return suit;
    }
    public String getSuitString() {
        return cardSuit[suit];
    }
    public int getValue() {
        return value;
    }
    public String getImage() {
        if(value!=6) //Standard image draw 
            return "/euchre/" + cardValue[value] + cardSuit[suit] + ".png";
        else { //Because the 2nd Jack changes suit, this becomes necessary to draw it correctly once its played
            switch(suit) {
                case 0: //Jack of Clubs when Spades is trump
                    return "/euchre/" + cardValue[value] + "Clubs.png";
                case 1: //Jack of Hearts when Diamonds is trump
                    return "/euchre/" + cardValue[value] + "Hearts.png";
                case 2: //Jack of Diamonds when Hearts is trump
                    return "/euchre/" + cardValue[value] + "Diamonds.png";
                case 3: //Jack of Spades when Clubs is trump
                    return "/euchre/" + cardValue[value] + "Spades.png";
            }
        }
        return null;
    }
    //Mutators
    public void setSuit(int a) {
        suit = a;
    }
    public void setValue(int a) {
        value = a;
    }
    //Methods
    public boolean isSameSuit(String s) {
        if (s.equals(""))
            return true;
        return s.equals(cardSuit[suit]);
    }
    public String toString() {
        if(value !=6) //If card is not the 2nd highest jack
            return cardValue[value] + " of " + cardSuit[suit]; //Normal return
        else
            return cardValue[value] + " of " + cardSuit[3-suit]; //Returns true suit of 2nd highest jack
    }
}
