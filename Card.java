/**
 * Card class - Creates a Uno card object
 * which has a colour and a value or is a wild card,
 * and contains all the info of the card which
 * can be accessed when needed.
 * 
 * @see Colour
 * @see Value
 * @see Wild
 */
public class Card {
    private Colour colour; //a Uno card's colour.
    private Value value; // the number written on a uno card.
    private Wild wildValue;// variable to hold a wild card.
    private boolean wildCheck;// boolean varliable, the value of which is contingent on the card being wild or not.
    /**
     * This is constructor for a normal uno card
     * @param colour a normal card has colour so this constructor takes colour
     * @param value a normal card also has a value 
     */ 
    public Card(Colour colour, Value value) { 
        this.colour = colour;
        this.value = value;
        this.wildValue = Wild.NONE;
        this.wildCheck = false;
    }
    /**
     * This is a constructor for a wild uno card
     * @param wildValue a wild card has a wild value like plustwo or plus four
     */ 
    public Card(Wild wildValue) {
        this.colour = Colour.NONE;
        this.value = Value.NONE;
        this.wildValue = wildValue;
        this.wildCheck = true;
    }
    
    /**
     * The colour of the card is accessed through this method.
     * 
     * @return colour the colour of the Uno card.
     */
    public Colour getColour() {
        return colour;
    }
    
    /**
     * This method tells which number is written on the Uno card.
     * 
     * @return value the value of the card.
     */
    public Value getValue() {
        return value;
    }
    
    /**
     * The value of a wild card is accessed through this method.
     * 
     * @return value of the wild card.
     */
    public Wild getWildValue() {
        return wildValue;
    }
    
    /**
     * Checks if the card is wild or not.
     * 
     * @return wildValue, true if wild and false if not.
     */ 
    public boolean isWild() {
        return wildCheck;
    }
    
    /** 
     * Prints a Uno Card.
     * Very simplified text version of Uno card for  a console game.
     * Just prints the colour and number of the card.
     * For wild card, prints the card's name.
     * 
     * @return String representation of the card.
     */
    public String toString() {
        if(wildCheck == true) {
            return  this.wildValue.toString();
        }
        else {
            return this.colour.toString()+ "-" + this.value.toString();
        }
    }
}