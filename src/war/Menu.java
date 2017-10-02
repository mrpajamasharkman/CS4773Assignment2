package war;

import java.util.Scanner;

public class Menu {

	private static Scanner playerInput = new Scanner(System.in);
	private static int variation;
	
	public static void main(String[] args) {
		
		Player player1 = null;
		Player player2 = null;
		Player player3 = new Player ("", 17);
		
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
		setUpPlayer(player1, getVariation());
		System.out.print("Player 2 Name: ");
		setUpPlayer(player2, getVariation());
		
		if (getVariation() == 3) {
			System.out.print("Player 3 Name: ");
			player3.setName(playerInput.nextLine());
		}
		
		playerInput.close();
		
		//	GAME START!!!
		Deck deck = new Deck();
	}
	
	public static void setUpPlayer(Player player, int variation) {
		if (getVariation() != 3)
			player = new Player(playerInput.nextLine(), 26);
		else
			player = new Player(playerInput.nextLine(), 17);
	}

	public static int getVariation() { return variation; }
}
