package war;

public class Winner extends Player {

	private Card winningCard;
	
	public Winner(String name, Deck mainDeck, int variation, Card winningCard) {
		super(name, mainDeck, variation);
		setWinningCard(winningCard);
	}
	
	public Winner() {}

	public Card getWinningCard() { return winningCard; }
	
	public void setWinningCard(Card winningCard) { this.winningCard = winningCard; }
}
