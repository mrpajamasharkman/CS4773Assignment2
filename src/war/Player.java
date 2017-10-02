package war;

public class Player extends Deck {
	
	private String name;
	private int score;

	public Player(String name, int deckSize) {
		super(deckSize);
		this.name = name;
		this.score = 0;
	}
	
	public void adjustScore(int adjustment) { this.score += adjustment; }
	
	public String getName() { return this.name; }
	
	public int getScore() { return this.score; }
	
	public void setName(String name) { this.name = name; }
	
	public void setScore(int score) { this.score = score; }
}
