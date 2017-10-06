package war;

/**
 * A class to represent a single Card
 * 
 * @author Lucas Tiedeman
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
	
	/**
	 * @return	The card's numeric rank
	 */
	public Rank getRank() { return rank; }
	
	/** 
	 * @return	The card's suit
	 */
	public Suit getSuit() { return suit; }
	
	/**
	 * @param rank	The rank to be assigned to the Card object
	 */
	public void setRank(Rank rank) { this.rank = rank; }
	
	/**
	 * @param suit	The suit to be assigned to the Card object
	 */
	public void setSuit(Suit suit) { this.suit = suit; }
}
