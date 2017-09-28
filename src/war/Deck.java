package war;

import java.util.ArrayList;

public class Deck {
	
	public static final int MAX_DECK_SIZE = 52;
	
	private ArrayList<Card> deck;
	
	public Deck (int deckSize) {
		if (deckSize <= MAX_DECK_SIZE)
			deck = new ArrayList<Card>(deckSize);
		else
			System.err.print("Number of cards greater than maximum deck size");
	}
	
	public void addCard(int rank, char suit) {
		if (deck.size() < MAX_DECK_SIZE)
			deck.add(new Card(rank, suit));
		else
			System.err.print("Cannot add card; already at maximum deck size");
	}
}
