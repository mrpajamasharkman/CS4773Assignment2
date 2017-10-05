package war;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	private static Scanner playerInput = new Scanner(System.in);
	private static int variation;
	private static Deck deck = new Deck(52);
	private static boolean debug = false;
	private static Turn turn;
	
	public static void main(String[] args) {
		ArrayList<Player> players = new ArrayList<Player>();
		String winner = "";
		
		System.out.println("                                WAR");
		System.out.println("==================================================================");
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
		
		players.add(new Player("Player1", deck, getVariation()));
		players.add(new Player("Player2", deck, getVariation()));
		if (getVariation() == 3)
			players.add(new Player("Player3", deck, getVariation()));
		
		playerInput.close();
		
		turn = new Turn(players);
		winner = turn.runTurn();
		System.out.println(winner);
	}

	public static int getVariation() { return variation; }
	
	public static void enableDebug() { debug = true; }
	
	public static boolean getDebug() { return debug; }
}
