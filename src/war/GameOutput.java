package war;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all the output
 * 
 * @author Lucas Tiedeman
 * @author Sean Woerner
 * @author Christian Andrada
 */

public class GameOutput {
	
	private StringBuffer stringBuffer;
	
	/**
	 * Handles all of the output of a game of War
	 */
	public GameOutput () {
		 stringBuffer = new StringBuffer();
	}
	
	/**
	 * Prints the opening menu of the game and accepts player input for the variation to be played
	 */
	public void startGame() {
		Scanner playerInput = new Scanner(System.in);
		int variation;
		System.out.println("\n                                WAR\n");
		System.out.println("Possible variations:");
		System.out.println("1. 2-Player: Placing won cards on the bottom of the player's hand.");
		System.out.println("2. 2-Player: Placing won cards in a seperate points pile.");
		System.out.println("3. 3-Player: Placing won cards in a seperate points pile.\n");
		do {
			System.out.print("Select a variation (1-3): ");
			while (!playerInput.hasNextInt()) {
				System.out.print("Select a variation (1-3): ");
				playerInput.next();
			}
			variation = playerInput.nextInt();
			playerInput.nextLine();
		} while (variation > 3 || variation < 1);
		Menu.setVariation(variation);
		playerInput.close();
	}
	
	/**
	 * Displays the scores of all of the players
	 * 
	 * @param players	The ArrayList of players to be displayed
	 */
	public void displayScores(ArrayList<Player> players) {
		setStringBuffer(getStringBuffer().append(String.format("Score is ")));
		if (Menu.getVariation() == 1) {
			for (Player player : players)
				getStringBuffer().append(String.format(player.getName() + " = " + player.getHand().getDeckSize() + " "));
		} else {
			for (Player player : players)
				getStringBuffer().append(String.format(player.getName() + " = " + player.getScore() + " "));
		}
		getStringBuffer().append(String.format("\n\n"));
	}
	
	/**
	 * Adds events to the stringBuffer to be printed later
	 * 
	 * @param event	The String to be printed
	 */
	public void appendEvent(String event) { getStringBuffer().append(String.format(event + "\n")); }
	
	/**
	 * Prints the contents of stringBuffer
	 */
	public void printStringBuffer() { System.out.print(getStringBuffer().toString()); }
	
	public StringBuffer getStringBuffer() { return stringBuffer; }
	
	public void setStringBuffer(StringBuffer stringBuffer) { this.stringBuffer = stringBuffer; }
}