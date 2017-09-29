package war;

public class Card {

	char rank;
	private static final char[] RANKS = {
			'A', '2', '3', '4', '5',
			'6', '7', '8', '9', 'J',
			'Q', 'K'};
	private char suit;
	
	public Card(int index, char suit) {
		rank = RANKS[index - 1];
		this.suit = suit;
	}
	
	public char getRank() { return rank; }
	
	public int getRankIndex() {
		int index;
		for (index = 0; index < 13; index++) {
			if (rank == RANKS[index])
				break;
		}
		
		return index;
	}
	
	public char getSuit() { return suit; }
	
	public void setRank(char rank) { this.rank = rank; }
	
	public void setSuit(char suit) { this.suit = suit; }
}
