package war;

import java.util.Scanner;

public class Menu {

	private static Scanner playerInput = new Scanner(System.in);
	private static int variation;
	private static Deck deck = new Deck();
	
	public static void main(String[] args) {
		
		//	Setting debug settings so we don't have to change default program stuff at all
		boolean debug = false;
		
		Player player1 = null;
		Player player2 = null;
		Player player3 = null;
		
		System.out.println("                                WAR");
		System.out.println("==================================================================");
		System.out.println("Select a variation:");
		System.out.println("1. 2-Player: Placing won cards on the bottom of the player's hand.");
		System.out.println("2. 2-Player: Placing won cards in a seperate points pile.");
		System.out.println("3. 3-Player: Placing won cards in a seperate points pile.");
		
		do {
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
	}
	
	public static void setUpPlayer(Player player) {
		if (getVariation() != 3)
			player = new Player(playerInput.nextLine(), deck, getVariation());
		else
			player = new Player(playerInput.nextLine(), deck, getVariation());
	}

	public static int getVariation() { return variation; }
}
