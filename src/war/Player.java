package war;

/**
 * Extends Deck, and adds four members (String name, int handSize, Deck hand, int Score) and aside from getters and setters
 * adds one new method (buildHand).
 * 
 * @author Lucas Tiedeman
 * @author Sean Woerner
 * @author Christian Andrada
 */

public class Player extends Deck {
	
	private String name;
	private Deck hand;
	private int score;
	
	public Player() {}

	/**
	 * @param name		The player's name
	 * @param mainDeck	The game's main deck to pull cards from
	 * @param handSize	The number of cards in the player's hand
	 */
	public Player(String name, Deck mainDeck, int handSize) {
		super(0);
		this.name = name;
		hand = new Deck(handSize);
		buildHand(mainDeck, handSize);
		score = 0;
	}
	
	/**
	 * Builds the hand
	 * 
	 * @param mainDeck	The deck to pull cards from
	 * @param handSize	The hand to add cards to
	 */
	public void buildHand(Deck mainDeck, int handSize) {
		for (int i = 0; i < handSize; i++)
			hand.addCard(mainDeck.drawCard());
	}
	
	public String getName() { return name; }
	
	public int getScore() { return score; }
	
	public Deck getHand() { return hand; }
	
	public void setName(String name) { this.name = name; }
	
	public void setScore(int score) { this.score = score; }
	
	public void setHand(Deck hand) { this.hand = hand; }
}