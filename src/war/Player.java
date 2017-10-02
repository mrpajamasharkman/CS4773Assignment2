package war;

public class Player extends Deck {
	
	private String name;
	private int deckSize;
	private Deck deck;
	private int score;

	public Player(String name, Deck mainDeck, int variation) {
		super(0);
		this.name = name;
		if (variation == 3)
			this.deckSize = 17;
		else
			this.deckSize = 26;
		deck = new Deck(deckSize);
		buildHand(mainDeck, deckSize);
		this.score = 0;
	}
	
	public void buildHand(Deck mainDeck, int deckSize) {
		for (int i = 0; i < deckSize; i++)
			deck.addCard(mainDeck.drawCard());
	}
	
	public void adjustScore(int adjustment) { this.score += adjustment; }
	
	public String getName() { return this.name; }
	
	public int getScore() { return this.score; }
	
	public void setName(String name) { this.name = name; }
	
	public void setScore(int score) { this.score = score; }
}
