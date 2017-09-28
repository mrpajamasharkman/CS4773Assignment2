package war;

public class Card {

	private int rank;
	private char suit;
	
	public Card(int rank, char suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() { return rank; }
	
	public char getSuit() { return suit; }
	
	public void setRank(int rank) { this.rank = rank; }
	
	public void setSuit(char suit) { this.suit = suit; }
}
