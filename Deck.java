import java.util.ArrayList;
import java.util.Collections;
/** 
 * Deck class - Creates a deck full of uno cards.
 * Contains several methods for processing the deck for a game.
 * @see Card
 */
public class Deck {
    private ArrayList<Card> cards; // deck's name.
    
    public Deck() {
        this.cards = new ArrayList<Card>();
    }
    
    /**
     * Fills an empty deck with Uno card Objects,
     * which have a colour and a value,
     * or a wild value.
     */
    public void fillDeck(){
        for(int i =0; i < Colour.values().length-1; i++) {
            for(int m = 0; m < 2; m++) {
                for(int j = 0; j < Value.values().length - 2; j++) {
                    this.cards.add(new Card (Colour.values()[i], Value.values()[j]));
                }
            }
        }
        
        for(int i =0; i < Colour.values().length-1; i++) {
            this.cards.add(new Card (Colour.values()[i], Value.ZERO));
        }
        for (int m = 0; m < 2; m++) {
            for(int i=0; i <Wild.values().length-1; i++) {
                this.cards.add(new Card(Wild.values()[i]));
            }
        }
    }
    
    /**
     * Shuffles the deck of Uno cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    /**
     * Draws a uno card from the deck.
     * If a player in Uno card game, does not have any card
     * in their hand which matches the card on play,
     * they draw a card from the main deck. 
     * This method removes the top card (last card in the ArrayList)
     * from the deck and returns it.
     * 
     * @return the last card object of the ArrayList of cards.
     */
    public Card drawCard() {
        return cards.remove(cards.size()-1);
    }
    
    /**
     * Checks if a deck has any cards left.
     * 
     * @return true, if the deck has no card left;
     *         false, if the deck has at least one card in it.
     */
    public boolean checkEmpty() {
        
        if(cards.size() == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Fills this main deck with cards of another deck.
     * In Uno game if the main deck becomes empty,
     * then the played cards become the main deck.
     * 
     * @param playedCards the deck which will replace the main deck. 
     */
    public void restoreDeck(ArrayList<Card> playedCards) {
        
        int restoreCardsSize = playedCards.size(); // the size of main deck becomes the size of played cards 
        
        for(int i = 0; i < restoreCardsSize; i++) {
            this.cards.add(playedCards.get(i));
        }
        
        for(int i = 0; i < restoreCardsSize; i++) {
            playedCards.remove(i);
        }
    }
}