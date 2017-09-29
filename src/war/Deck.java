package war;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private static final int MAX_DECK_SIZE = 52;
	protected ArrayList<Card> deck;
	
	public Deck (int deckSize) {
		if (deckSize <= MAX_DECK_SIZE)
			deck = new ArrayList<Card>(deckSize);
		else
			System.err.print("Number of cards greater than maximum deck size");
	}
	
	public void addCard(int rank, String suit) {
		if (deck.size() < MAX_DECK_SIZE)
			deck.add(new Card(rank, suit));
		else
			System.err.print("Cannot add card; already at maximum deck size");
	}
	
	public Card drawCard() {
		Random randomCard = new Random();		
		return deck.remove(randomCard.nextInt(deck.size()));
	}
	
	public int getDeckSize() { return deck.size(); }
}
