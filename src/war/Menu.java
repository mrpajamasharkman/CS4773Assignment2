package war;

import java.util.ArrayList;

/**
 * The class which contains the main method for running War
 * 
 * Allows a user to input a variation of War to play:
 * <ol>
 * 	<li> Two players play cards until one deck is empty with the winner claiming cards
 * 		 after a war is run, or until 4000 rounds have passed.</li>
 * 	<li> Two players play cards until their decks are empty, keeping score with the
 * 		 cards that are won in each war</li>
 * 	<li> Three players play with the same rules as variation 2.</li>
 * </ol>
 * 
 * The game is made with a mainDeck to ensure that unique cards are used and put into each hand - once the cards are
 * dealt from the main hand, mainDeck is no longer used
 * 
 * @author Lucas Tiedeman
 * @author Sean Woerner
 * @author Christian Andrada
 */
public class Menu {

	private static int variation;
	private static int handSize;
	private static Deck deck = new Deck(52);
	private static Turn turn;
	private static GameOutput gameOutput = new GameOutput();
	
	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		
		gameOutput.startGame();
		
		setHandSize(getVariation());
		
		players.add(new Player("Player1", deck, getHandSize()));
		players.add(new Player("Player2", deck, getHandSize()));
		if (getVariation() == 3)
			players.add(new Player("Player3", deck, getHandSize()));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		gameOutput.printStringBuffer();
	}
	
	private static int getHandSize() { return handSize; }
	
	/**
	 * Sets the size of the players' hands based off of the variation of War being played
	 * 
	 * @param variation	the variation of War being played
	 */
	private static void setHandSize(int variation) {
		if (variation == 3)
			handSize = 17;
		else
			handSize = 26;
	} 

	public static int getVariation() { return variation; }
	
	public static void setVariation(int gameType) { variation = gameType; }
}