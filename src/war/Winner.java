package war;

/**
 * An extension of the Player class, which has another parameter (winningCard),
 * and four new methods (
 * 
 * @author Lucas Tiedeman
 * @author Sean Woerner
 */

public class Winner {

	private Player winningPlayer;
	private Card winningCard;

	public Winner() {
		setWinner(null);
		setWinningCard(null);
	}
	
	public Player getWinner() { return winningPlayer; }
	
	public Card getWinningCard() { return winningCard; }
	
	public void setWinningCard(Card winningCard) { this.winningCard = winningCard; }
	
	public void setWinner(Player player) { winningPlayer = player; }
	
	public void reset() { 
		setWinner(null);
		setWinningCard(null);
	}
}