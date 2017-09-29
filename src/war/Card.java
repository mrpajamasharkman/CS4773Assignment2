package war;

public class Card {

	private String rank;
	private static final String[] RANKS = {
			"ACE", "TWO", "THREE", "FOUR", "FIVE",
			"SIX", "SEVEN", "EIGHT", "NINE", "JACK",
			"QUEEN", "KING"};
	private String suit;
	
	public Card(int index, String suit) {
		rank = RANKS[index - 1];
		this.suit = suit;
	}
	
	public String getRank() { return rank; }
	
	public int getRankIndex() {
		int index;
		for (index = 0; index < 13; index++) {
			if (rank == RANKS[index])
				break;
		}
		
		return index;
	}
	
	public String getSuit() { return suit; }
	
	public void setRank(String rank) { this.rank = rank; }
	
	public void setSuit(String suit) { this.suit = suit; }
}
