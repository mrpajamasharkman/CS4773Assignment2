package war;

import java.util.ArrayList;

public class PointSystem {
	
	private Player winner;
	private boolean winnerFound;
	private boolean tieFound;
	private ArrayList<Player> players;
	
	public PointSystem(ArrayList<Player> players) {
		setWinnerFound(false);
		setTieFound(false);
		this.players = players;
	}
	
	public void checkForWinner() {
		for (Player player : players){
			determineGameEnd(player);
		}
	}
	
	private void determineGameEnd(Player player) {
		if (player.getDeckSize() == 0) {
			if (Menu.getVariation() == 1)
				setFullHandWinner();
			else
				setPointPileWinner();
		}
	}
	
	private void setFullHandWinner() {
		for (Player player : players)
			checkHandSize(player);
	}
	
	private void checkHandSize(Player player) {
		if (player.getDeckSize() != 0) {
			setWinner(player);
			setWinnerFound(true);
		}
	}
	
	private void setPointPileWinner() {
		int highestScore = -1;
		for (Player player : players) {
			getHighestScore(player, highestScore);
		}
		for (Player player : players) {
			if (player.getScore() == highestScore && player != getWinner())
				setTieFound(true);
		}
		if (!getTieFound())
			setWinnerFound(true);
	}
	
	private void getHighestScore(Player player, int highestScore) {
		if (player.getScore() > highestScore) {
			highestScore = player.getScore();
			setWinner(player);
		}
	}
	
	public Player getWinner() { return winner; }
	
	private void setWinner(Player winner) { this.winner = winner; }
	
	public boolean getWinnerFound() { return winnerFound; }
	
	private void setWinnerFound(boolean winnerFound) { this.winnerFound = winnerFound; }
	
	public boolean getTieFound() { return tieFound; }
	
	private void setTieFound(boolean tieFound) { this.tieFound = tieFound; }
	
	public void adjustScore(Player player, int points) {
		player.setScore(player.getScore() + points);
	}
}
