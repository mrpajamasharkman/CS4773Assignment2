package war;

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
	
	public void reset() { 
		setWinner(null);
		setWinningCard(null);
	}
}