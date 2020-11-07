import java.util.ArrayList;
/** 
 * Player class - handles the cards in a player's hand
 * in the Uno card game.
 * 
 * 
 * @see Card
 */
public class Player {
    private ArrayList<Card> cardsInHand; // all cards in a player's hand.
    private String playerName; // player's name.
    
    public Player(String name) {
        this.cardsInHand = new ArrayList<Card>();
        this.playerName = name;
    }
    
    /**
     * Returns the cards in player's hand.
     * 
     * @return the ArrayList of Card objects which belongs to a player.
     */
    public ArrayList<Card> getCardsInHand() {
        return cardsInHand;
    }
    
    /**
     * Returns particular card in hands given index.
     * 
     * @param choice the index chosen by the player.
     * @return Card the card object of the selected index.
     */
    public Card getSpecificCard(int choice) {
        return cardsInHand.get(choice);
    }
    
    /**
     * Adds a card to player's hand.
     * 
     * @param aCard a card object which is added to player's hand.
     */
    public void addCardToHand(Card aCard) {
        cardsInHand.add(aCard);
    }
    
    /**
     * Plays a card from selected index.
     * Basically, this method removes the card from player's hand
     * as when it is played it is no longer in their hand.
     * 
     * @param index the position of the card in hand deck.
     * @return the card which is removed from the given index position
     *         of hand deck.
     */
    public Card playCard(int index) {
        return cardsInHand.remove(index);
    }
    
    /**
     * Checks if player has only one card in hand
     * and in that case, prints UNO.
     * In a Uno card game when a player has only one card,
     * they say Uno that means they are very close to win
     * the game as their hands become empty of cards very soon.
     */
    public void checkUno() {
        
        if (cardsInHand.size() == 1) {
            System.out.print("\n" + playerName + " says >>UNO<< ");
        }
    }
    
    /**
     * Checks if the player's has become empty 
     * and there is no card left.
     * 
     * @return true, if the player does not have any card in hand;
     *      false, if the player has at least one card in hand.
     */
    public boolean checkWin() {
        if(cardsInHand.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Prints all the cards in player's hand
     * and adds index to the card.
     * Console players will choose cards using
     * these indexes.
     */
    public void printCardsInHand() {
        for(int i = 0; i < cardsInHand.size(); i++) {
            if(cardsInHand.get(i).isWild() == true) {
                System.out.println((i+1) + " " + cardsInHand.get(i).getWildValue() + "\n");
            }
            else if(cardsInHand.get(i).isWild() == false) {
                System.out.println((i+1) + " " + cardsInHand.get(i).getColour()+ "-" + cardsInHand.get(i).getValue() +"\n");
            }
        }
    }
    
    /**
     * Returns the player's name.
     * 
     * @return the string which represents the player's name.
     */
    public String toString() {
        return this.playerName;
    }
}