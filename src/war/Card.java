package war;

public class Card {

	String rank;
	private static final String[] RANKS = {
			"Ace", "2", "3", "4", "5",
			"6", "7", "8", "9", "Jack",
			"Queen", "King"	};
	private char suit;
	
	public Card(int index, char suit) {
		rank = RANKS[index - 1];
		this.suit = suit;
	}
	
	public String getRank() { return rank; }
	
	public int getRankIndex() { 
		for (int i = 0; i < 13; i++) {
			if (rank.equals(RANKS[i]))
				return i;
		}
		
		return -1;
	}
	
	public char getSuit() { return suit; }
	
	public void setRank(String rank) { this.rank = rank; }
	
	public void setSuit(char suit) { this.suit = suit; }
}
