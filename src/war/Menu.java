package war;

import java.util.ArrayList;

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
	
	public static void enableDebug() { debug = true; }
	
	public static boolean getDebug() { return debug; }
}
