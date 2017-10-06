package war;

/**
 * An Enum for card ranks
 * 
 * Card ranks begin with a value of 2 and end with 14 in ascending order from
 * Two to Ace
 * 
 * @author Lucas Tiedeman
 */
public enum Rank {
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
		NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
	
	private int value;
	
	private Rank (int value) { this.value = value; }
	
	/**
	 * @return	The numeric value of the rank
	 */
	public int getValue() { return this.value; } 
}
