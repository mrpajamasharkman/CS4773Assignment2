package war;

import java.util.Scanner;

public class Menu {

	private static Scanner playerInput = new Scanner(System.in);
	private static int variation;
	private static Deck deck = new Deck(52);
	private static boolean debug = false;
	
	public static void main(String[] args) {		
		Player player1 = null;
		Player player2 = null;
		Player player3 = null;
		
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
		
		System.out.print("Player 1 Name: ");
		setUpPlayer(player1);
		System.out.print("Player 2 Name: ");
		setUpPlayer(player2);
		
		if (getVariation() == 3) {
			System.out.print("Player 3 Name: ");
			setUpPlayer(player3);
		}
		
		playerInput.close();
		
		//enableDebug();
		if (getDebug()) {
			System.out.println("REMAINING CARDS:");
			deck.displayDeck();
		}
		
		//BEGIN TURN CYCLE
	}
	
	public static void setUpPlayer(Player player) {
		if (getVariation() != 3)
			player = new Player(playerInput.nextLine(), deck, getVariation());
		else
			player = new Player(playerInput.nextLine(), deck, getVariation());
	}

	public static int getVariation() { return variation; }
	
	public static void enableDebug() { debug = true; }
	
	public static boolean getDebug() { return debug; }
}
