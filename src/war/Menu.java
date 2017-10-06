package war;

import java.util.ArrayList;

/**
 * The class which contains the main method for running War
 * 
 * Allows a user to input a variation of War to play:
 * <ol>
 * 	<li> Two players play cards until one deck is empty with the winner claiming cards
 * 		 after a war is run, or until 4000 rounds have passed.</i>
 * 	<li> Two players play cards until their decks are empty, keeping score with the
 * 		 cards that are won in each war</i>
 * 	<li> Three players play with the same rules as variation 2.</i>
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
	private static Deck deck = new Deck(52);
	private static boolean debug = false;
	private static Turn turn;
	private static GameOutput gameOutput = new GameOutput();
	
	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		String winner = "";
		
		gameOutput.startGame();
		
		players.add(new Player("Player1", deck, getVariation()));
		players.add(new Player("Player2", deck, getVariation()));
		if (getVariation() == 3)
			players.add(new Player("Player3", deck, getVariation()));
		
		turn = new Turn(players);
		winner = turn.runTurn();
		gameOutput.printEvent(winner);
	}

	public static int getVariation() { return variation; }
	
	public static void setVariation(int gameType) { variation = gameType; }
	
	/**
	 * Enables a debug mode
	 */
	public static void enableDebug() { debug = true; }
	
	/**
	 * @return	True if debug is enabled, false if debug is disabled
	 */
	public static boolean getDebug() { return debug; }
}