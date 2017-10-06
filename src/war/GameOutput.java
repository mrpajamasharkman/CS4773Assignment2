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
	
	public GameOutput () {}
	
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
		System.out.print("Score is ");
		if (Menu.getVariation() == 1) {
			for (Player player : players)
				System.out.print(player.getName() + " = " + player.getHand().getDeckSize() + " ");
		} else {
			for (Player player : players)
				System.out.print(player.getName() + " = " + player.getScore() + " ");
		}
		System.out.println("\n");
	}
	
	/**
	 * Honestly kind of a cop out way to ensure that printing only happens here
	 * 
	 * @param event	The String to be printed
	 */
	public void printEvent(String event) { System.out.println(event); }
}