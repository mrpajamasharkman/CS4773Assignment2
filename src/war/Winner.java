package war;

/**
 * Contains a Player object and a Card object
 * 
 * @author Lucas Tiedeman
 * @author Sean Woerner
 * @author Christian Andrada
 */
public class Winner{

	private Player winningPlayer;
	private Card winningCard;

	public Winner() {
		setWinner(null);
		setWinningCard(null);
	}
	
	public Card getWinningCard() { return winningCard; }
	
	public void setWinningCard(Card card) { winningCard = card; }
	
	public Player getWinner() { return winningPlayer;}
	
	public void setWinner(Player player) { winningPlayer = player; }
	
	/**
	 * Sets all members of the member to null
	 */
	public void reset() { 
		setWinner(null);
		setWinningCard(null);
	}
}