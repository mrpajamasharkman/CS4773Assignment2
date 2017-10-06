package war;

public class Winner extends Player {

	private Card winningCard;

	public Winner() {
		setWinner(null, null);
		setWinningCard(null);
	}
	
	public Card getWinningCard() { return winningCard; }
	
	public void setWinningCard(Card winningCard) { this.winningCard = winningCard; }
	
	public void setWinner(String name, Deck mainDeck) {
		setName(name);
		setHand(mainDeck);
	}
	
	public void reset() { 
		setWinner(null, null);
		setWinningCard(null);
	}
}