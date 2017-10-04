package war;

public class Player extends Deck {
	
	private String name;
	private int handSize;
	private Deck hand;
	private int score;

	public Player(String name, Deck mainDeck, int variation) {
		super(0);
		this.name = name;
		if (variation == 3)
			handSize = 17;
		else
			handSize = 26;
		hand = new Deck(handSize);
		buildHand(mainDeck, handSize);
		this.score = 0;
		
//		Menu.enableDebug();
		if (Menu.getDebug()) {
			hand.displayDeck();
		}
	}
	
	public void buildHand(Deck mainDeck, int handSize) {
		for (int i = 0; i < handSize; i++)
			hand.addCard(mainDeck.drawCard());
	}
	
	public String getName() { return name; }
	
	public int getScore() { return score; }
	
	public Deck getHand() { return hand; }
	
	public void setName(String name) { this.name = name; }
	
	public void setScore(int score) { this.score = score; }
}
