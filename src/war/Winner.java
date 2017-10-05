package war;

public class Winner extends Player {

	private Card winningCard;

	public Winner() {}

	public Winner(String name, Deck mainDeck, int variation, Card winningCard) {
		super(name, mainDeck, variation);
		setWinningCard(winningCard);
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
