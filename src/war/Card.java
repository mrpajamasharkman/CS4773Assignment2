package war;

/**
 * A class to represent a single Card
 * 
 * @author Lucas Tiedeman
 * @author Sean Woerner
 * @author Christian Andrada
 */

public class Card {

	private Rank rank;
	private Suit suit;
	
	/**
	 * A card <i>must</i> be constructed with its values
	 * 
	 * @param rank	The card's numeric rank, which is used to compare cards
	 * 				against each other
	 * @param suit	The card's suit, which is only used for display purposes
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank() { return rank; }
	
	public Suit getSuit() { return suit; }
	
	public void setRank(Rank rank) { this.rank = rank; }
	
	public void setSuit(Suit suit) { this.suit = suit; }
}