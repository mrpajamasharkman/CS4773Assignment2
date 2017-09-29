package war;

import java.util.Scanner;

public class Menu {
	
	private static int variation;

	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);
		String player1Name;
		String player2Name;
		String player3Name = "";
		
		System.out.println("                                WAR");
		System.out.println("==================================================================");
		System.out.println("Supported variations:");
		System.out.println("1. 2-Player: Placing won cards on the bottom of the player's hand.");
		System.out.println("2. 2-Player: Placing won cards in a seperate points pile.");
		System.out.println("3. 3-Player: Placing won cards in a seperate points pile.");
		
		do {
			System.out.print("\nSelect a variation (1-3): ");
			variation = playerInput.nextInt();
			playerInput.nextLine();
		} while (variation > 3 || variation < 1);
		
		System.out.print("Player 1 Name: ");
		player1Name = playerInput.nextLine();
		System.out.print("Player 2 Name: ");
		player2Name = playerInput.nextLine();
		
		if (variation == 3) {
			System.out.print("Player 2 Name: ");
			player3Name = playerInput.nextLine();
		}
		
		playerInput.close();
	}
	
	public int getVariation() { return variation; }

}
