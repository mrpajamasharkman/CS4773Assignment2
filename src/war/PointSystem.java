package war;

import java.util.ArrayList;

public class PointSystem {

	private Player winner;
	private boolean winnerFound;
	private boolean tieFound;
	private ArrayList<Player> players;
	
	/**
	 * @param players	The players in the game
	 */
	public PointSystem(ArrayList<Player> players) {
		setWinnerFound(false);
		setTieFound(false);
		this.players = players;
	}
	
	/**
	 * Checks for a winner in the overall game
	 */
	public void checkForWinner() {
		if (!checkForSufficientCards(1)) {
			if (Menu.getVariation() == 1)
				setFullHandWinner();
			else
				setCurrentWinner();
		}
	}
	
	/**
	 * Finds a winner in variation 1 of War
	 */
	private void setFullHandWinner() {
		for (Player player : players)
			checkHandSize(player);
	}
	
	/**
	 * Checks for a deckSize that is not zero to find the winner in variation 1
	 * @param player	Current player being checked
	 */
	private void checkHandSize(Player player) {
		if (player.getHand().getDeckSize() != 0) {
			setWinner(player);
			setWinnerFound(true);
		}
	}
	
	/**
	 * Sets the winner for variations 2 or 3
	 */
	public void setCurrentWinner() {
		int highestScore = -1;
		for (Player player : players)
			highestScore = getHighestScore(player, highestScore);
		for (Player player : players) {
			if (player.getScore() == highestScore && player != getWinner())
				setTieFound(true);
		}
		if (!getTieFound())
			setWinnerFound(true);
	}
	
	/**
	 * Searches for the player with the highest score
	 * 
	 * @param player		Player currently being checked
	 * @param highestScore	The current highest score found
	 * @return				The highest score found among all the players
	 */
	private int getHighestScore(Player player, int highestScore) {
		if (player.getScore() > highestScore) {
			highestScore = player.getScore();
			setWinner(player);
		}
		
		return highestScore;
	}
	
	/**
	 * 
	 * @param sufficientCards
	 * @return
	 */
	public boolean checkForSufficientCards(int sufficientCards) {
		for (Player player : players) {
			if (player.getHand().getDeckSize() < sufficientCards)
				return false;
		}
		return true;
	}
	
	public Player getWinner() { return winner; }
	
	private void setWinner(Player winner) { this.winner = winner; }
	
	public boolean getWinnerFound() { return winnerFound; }
	
	private void setWinnerFound(boolean winnerFound) { this.winnerFound = winnerFound; }
	
	public boolean getTieFound() { return tieFound; }
	
	private void setTieFound(boolean tieFound) { this.tieFound = tieFound; }
	
	/**
	 * Adds points to the player's current score
	 * 
	 * @param player	The player to add points to
	 * @param points	The number of points to be added to a player
	 */
	public void adjustScore(Player player, int points) {
		player.setScore(player.getScore() + points);
	}
}
