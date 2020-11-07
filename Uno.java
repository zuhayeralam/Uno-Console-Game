import java.util.ArrayList;
import java.util.Scanner;
/** 
 * Organiser Class Uno - Organises the Uno game with normal cards for two players.
 * Builds the game by creating a deck, a card on play and distributes
 * cards to the players.
 * Contains methods which process the whole game.
 * @see Card
 * @see Deck
 * @see Player
 */
public class Uno {
    private Deck cards; // main deck for the game.
    private ArrayList<Card> playedCards; // this deck contains all the cards which are played.
    private Card cardOnPlay; // the card on play which is the card the play is based on.
    private int chosenIndex; //index of the card player wants to play.
    private Player playerOne; // creates player one.
    private Player playerTwo; // creates player two.
    private Colour setColour; // Holds the colour set by the player playing wild card.
    private Scanner sc; // Scanner object.
    
    public Uno() {
        String name1; // stores player one's name.
        String name2; // stores player two's name.
        this.sc = new Scanner(System.in);
        this.cards = new Deck();
        this.cards.fillDeck();
        this.cards.shuffle();
        this.playedCards = new ArrayList<Card>();
        this.cardOnPlay = cards.drawCard();
        this.playedCards.add(cardOnPlay);
        
        System.out.println("Welcome to UNO console game!\n\n");
        System.out.print("Enter the name of the first player: ");
        name1 = sc.nextLine();
        System.out.print("Enter the name of the second player: ");
        name2 = sc.nextLine();
        this.playerOne = new Player(name1);
        this.playerTwo = new Player(name2);
        cardDistribution();
    }
    
    /**
     * Distributes 14 cards among two players,
     * That means each player gets seven cards.
     * 
     * @see Deck#drawCard()
     * @see Player#addCardToHand(Card aCard)
     */
    private void cardDistribution() {
        for(int i = 0; i < 14; i++) {
            if(i%2 != 0) {
                playerOne.addCardToHand(cards.drawCard());
            }
            else if(i%2 == 0) {
                playerTwo.addCardToHand(cards.drawCard());
            }
        }
    }
    
    /**
     * Checks if the player has any card (checks each card in hand deck)
     * of the same colour as the card on play.
     *
     * @param player a player object, the player whose card's colour is being checked.
     * @return true, if the colour of the card matches the card on play;
     *      false, if the colour of the card does not match the card on play.
     * @see Player#getCardsInHand()
     * @see Card#getColour()
     */
    private boolean hasColour(Player player) {
        for(Card eachCard: player.getCardsInHand()) {
            if(eachCard.getColour().equals(cardOnPlay.getColour())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the player has any card (checks each card in hand deck)
     *  of the same number as the card on play.
     * 
     * @param player the player whose card's number is being checked
     * @return true, if the value of the card matches the card on play;
     *         false, if the colour of the card does not match the card on play.
     * @see Player#getCardsInHand()
     * @see Card#getValue()
     */
    private boolean hasValue(Player player) {
        for(Card eachCard: player.getCardsInHand()) {
            if(eachCard.getValue().equals(cardOnPlay.getValue())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the player has any wild card (checks each card in hand deck)
     * 
     * @param player the player whose cards are being checked
     * @return true, if the player has any wild card.
     *         false, if the player does not have any wild card.
     * @see Player#getCardsInHand()
     * @see Card#isWild()
     */
    private boolean hasWild(Player player) {
        for(Card eachCard: player.getCardsInHand()) {
            if(eachCard.isWild() == true) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the player has any card (checks each card in hand deck)
     * of the same colour as the selected colour of the other player
     * who played a wild card.
     *
     * @param player a player object, the player whose card's colour is being checked.
     * @return true, if the colour of the card matches the selected colour.
     *         false, if the colour of the card does not match the selected colour.
     * @see Player#getCardsInHand()
     * @see Card#getColour()
     */
    private boolean hasSlctColour(Player player) {
        for(Card eachCard: player.getCardsInHand()) {
            if(eachCard.getColour().equals(setColour)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * prompts the player before drawing a card.
     * draws the card for the player.
     * shows the drawn card and the card on play
     * 
     * @param player the player whose cards are drawn.
     * @see Deck#drawCard()
     * @see Player#addCardToHand(Card aCard)
     */ 
    public void drawPrompt(Player player) {
        Card draw; // the new card which the player draws from the deck
        String userInput; // stores userInput for drawing the card
        System.out.print("You don't have any card which matches the colour or number. Enter D to draw a card: ");
        userInput = sc.next();
        
        if(userInput.toLowerCase().equals("d")) {
            draw = cards.drawCard();
            player.addCardToHand(draw);
            System.out.print("\nYou have got: "+draw);
            System.out.println(" and the card on play is: " + cardOnPlay +".\n");
        }
        else {
            System.out.println("Enter D to draw a card");
        }
    }
    
    /**
     * If a player does not have the same value or colour as the card on play,
     * or the selected colour of the wild card if the wild colour was played.
     * draws a card for the player and
     * shows the drawn card.
     * Displays which cards the player currently has
     * after adding the drawn card to their hand.
     *
     * @param player the player whose deck is checked for valid card availability
     * @see #hasColour(Player player)
     * @see #hasValue(Player player)
     * @see #hasWild(Player player)
     * @see #hasSlctColour(Player player)
     * @see #display(Player player) 
     */
    public void drawIfNoMatch(Player player) {
        
        if(cardOnPlay.isWild() == true) {
            while (!hasColour(player) && !hasValue(player) && !hasWild(player) && !hasSlctColour(player) ) {
                drawPrompt(player);
                System.out.println("The colour set by the previous player is: " + setColour );
                display(player);
            }
        }
        
        if(cardOnPlay.isWild() == false) {
            while (!hasColour(player) && !hasValue(player) && !hasWild(player)) {
                drawPrompt(player);
                display(player);
            }
        }
    }
    
    /**
     * Prompts the player and takes the index value of the card,
     * which the player wants to play.
     * Also, does make sure that
     * the player cannot enter anything apart from integer values.
     * Makes sure that the index is within the range of the deck,
     * by calculating the size of the deck in player's hand.
     * 
     * @param player the player who is prompted for the card index 
     * @see Player#getCardsInHand()
     */
    public void promptUser (Player player) {
        int choice = 0; // stores player's input which is mainly for the chosen index
        System.out.print("choose a card you want to play: ");
        while(!sc.hasNextInt()) { 
            System.out.println("Your choice must be an integer value. Please enter again: ");
            sc.next();
        }
        choice = sc.nextInt()-1;
        while(choice >= player.getCardsInHand().size() || choice < 0) {
            System.out.println("You don't have " + (choice+1) + " cards. Please enter a valid choice: ");
            while(!sc.hasNextInt()) { 
                System.out.println("Your choice must be an integer value. Please enter again: ");
                sc.next();
            }
            choice = sc.nextInt()-1;
        }
        chosenIndex = choice;
    }
    
    /**
     * Checks if the player's chosen card's colour or number
     * matches the colour or number of the card on play.
     * But, if the card on play is a wild card,
     * then, checks if the chosen card's colour matches the colour
     * set by the other player.
     *
     * @param player the player whose chosen card is checked.
     * @param choice the index of player's chosen card.
     * @return true, if the chosen card matches the card on play;
     *         false if the chosen card does not match the card on play.
     * @see Player#getSpecificCard(int choice)
     * @see Card#getColour()
     * @see Card#getValue()
     * @see Card#isWild()
     */
    private boolean match(Player player, int choice) {
        if(cardOnPlay.isWild() == true) {
            if(player.getSpecificCard(choice).getColour().equals(setColour)) {
                return true;
            }
            return false;
        }
        
        else if(cardOnPlay.isWild() == false) {
            if (player.getSpecificCard(choice).getColour().equals(cardOnPlay.getColour()) || 
                player.getSpecificCard(choice).getValue().equals(cardOnPlay.getValue()) ||
                player.getSpecificCard(choice).isWild() == true){
                return true;
            }
            return false;
        }
        return false;
    }
    
    /**
     * Displays the cards in a player's hands with some texts,
     * to make the game more interactive.
     * 
     * @param player the player whose cards are displayed.
     * @see Player#printCardsInHand()
     */
    public void display(Player player) {
        
        System.out.print(player + ", it's your turn.");
        System.out.println(" Your cards are : \n");
        player.printCardsInHand();
    }
    
    /**
     * Pauses the game when the current player wants to 
     * and creates empty space in the console, 
     * so that the cards of this player can be hidden.
     * Prompts the next player to start their turn.
     */
    private void pauseSpace() {
        
        for (int i = 0; i < 30; i++) {
            System.out.println();     
        }
        
        while(sc.nextLine() == " ") {
            System.out.println("Press Space to create a pause: ");
        }
        
        do {
            System.out.println("Press Enter to start next turn: ");
        }while(sc.nextLine() == "");
    }
    
    /**
     * Runs the game process if the card on play is a wild card.
     * Basically, draws card for the player given the value of the wild card.
     * 
     * @param player the player who needs to draw cards
     *
     * @see Player#addCardToHand(Card aCard)
     * @see Card#getWildValue()
     * @see Deck#drawCard()
     */ 
    public void wildOnPlay(Player player) {
        
        if(cardOnPlay.isWild() == true) {
            String wildValue = (cardOnPlay.getWildValue() == Wild.WILDTWO) ? "two" : "four";
            int numOfCards = (cardOnPlay.getWildValue() == Wild.WILDTWO) ? 2 : 4;
            System.out.println("A wild card was played, you need to draw " + wildValue +" cards.");
            for (int i = 0; i < numOfCards ; i++) {
                Card draw = cards.drawCard();
                player.addCardToHand(draw);
                System.out.print("\nYou have got: "+draw);
            }
            System.out.println("\nThe colour set by the previous player is: " + setColour);
        }
    }
    
    /**
     * Takes care of the wild card playing process.
     * When a player plays a wild card they have to choose a colour
     * which the next player will have to play.
     * 
     * @param playedCard the card which is played and checked for its wildness
     * @see Card#isWild()
     */ 
    public void wildPlayed(Card playedCard) {
        if (playedCard.isWild() == true) {
            System.out.println("\nSet colour for the next player as you are playing your wild card: ");
            System.out.println("1. Blue\n2. Green\n3. Yellow\n4. Red");
            while(!sc.hasNextInt()) {
                System.out.println("Your choice must be an integer value. Please enter again: ");
                sc.next();
            }
            int choice = sc.nextInt();
            System.out.println("choice" + choice);
            while(choice > 4 && choice < 0) {
                System.out.println("You don't have " + (choice) + " options. Please enter a valid option: ");
                while(!sc.hasNextInt()) { 
                    System.out.println("Your choice must be an integer value. Please enter again: ");
                    sc.next();
                }
                choice = sc.nextInt();
            }
            switch (choice) {
                case 1 : setColour=Colour.BLUE; 
                break;
                case 2 : setColour=Colour.GREEN;
                break;
                case 3 : setColour=Colour.YELLOW; 
                break;
                case 4 : setColour=Colour.RED;
                break;
            }
        }
    }
    
    /**
     * Plays each round of a Uno game. Displays the cards
     * of the player whose round is being played,
     * Checks if they have valid card to play and then prompts them
     * to choose a card from their hand. 
     * If it matches the card on play,
     * then removes it from their hand 
     * and places it on the played deck.
     * If the main deck becomes empty, restores it with cards from
     * the played cards deck and shuffles the cards.
     * Creates pause and 
     * prepares for the next player's round to be played.
     * 
     * @param player the player whose round is being played.
     * @see #display(Player player)
     * @see #drawIfNoMatch(Player player)
     * @see #promptUser(Player player)
     * @see #match(Player player, int choice)
     * @see Player#playCard(int index)
     * @see Player#checkUno()
     * @see Deck#checkEmpty()
     * @see Deck#restoreDeck(ArrayList<Card> playedCards)
     * @see Deck#shuffle()
     * @see #pauseSpace()
     * @see #wildOnPlay(Player player, Wild wild, int numOfCards)
     * @see #wildPlayed(Card playedCard)
     */
    public void round(Player player) {
        
        Card playedCard; // variable to store a card that has been played
        System.out.println("The card on play is: " + cardOnPlay +".\n");
        
        wildOnPlay(player);
        display(player);
        drawIfNoMatch(player);
        
        promptUser(player);
        while(!match(player, chosenIndex)) {
            System.out.println("The card you choose has to be the same colour or number!! Try again: ");
            chosenIndex = sc.nextInt()-1;
        }
        playedCard = player.playCard(chosenIndex);
        wildPlayed(playedCard);
        player.checkUno();
        cardOnPlay = playedCard;
        playedCards.add(cardOnPlay);
        
        while(cards.checkEmpty()){
            cards.restoreDeck(playedCards);
            cards.shuffle();
        }
        pauseSpace();
    }
    
    /**
     * Checks if the other player has won the game
     * if not, then plays the round for this player.
     * if this player has no card in hands,
     * announces this player as the winner.
     * 
     * @param mainPlayer the player whose round will be played.
     * @param otherPlayer the player whose victory or defeat will determine
     *     if mainPlayer can play his round.
     * @see Player#checkWin()
     * @see #round(Player)
     */
    public void endGame(Player mainPlayer, Player otherPlayer) {
        if(otherPlayer.checkWin() == false) {
            round(mainPlayer);
        }
        if(mainPlayer.checkWin() == true) {
            System.out.println(mainPlayer + " wins !!");
        }
    }
    
    /**
     * Creates loop of Uno rounds for two players and 
     * stops the game when any of the player has won.
     *
     * @see Player#checkWin()
     * @see #endGame(Player mainPlayer, Player otherPlayer) 
     */
    public void unoForTwo() {
        int loop = 1;
        while(loop == 1) { 
            if(playerOne.checkWin() == false && playerTwo.checkWin() == false ) {
                endGame(playerOne, playerTwo);
            }
            
            if(playerOne.checkWin() == false && playerTwo.checkWin() == false ) {
                endGame(playerTwo, playerOne);
            }        
        }
    }
}