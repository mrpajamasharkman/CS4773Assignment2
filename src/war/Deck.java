package war;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private static final int MAX_DECK_SIZE = 52;
	protected ArrayList<Card> deck;
	
	//	Creates a standard 52 card deck; not Bicycle Playing Cards certified
	public Deck() {
		this.deck = new ArrayList<Card>();
		for (int i = 0; i < 13; i++) {
			Rank rank = Rank.values()[i];
			for (int j = 0; j < 4; j++) {
				Card card = new Card(rank, Suit.values()[j]);
				this.deck.add(card);
			}
		}
		
		Collections.shuffle(deck);
	}
	
	public Deck (int deckSize) {
		if (deckSize <= MAX_DECK_SIZE)
			deck = new ArrayList<Card>(deckSize);
		else
			System.err.print("Number of cards greater than maximum deck size");
		
		//	MORE DECKBUILDING TO HAPPEN
	}
	
	public void addCard(Card card) {
		if (deck.size() < MAX_DECK_SIZE)
			deck.add(card);
		else
			System.err.print("Cannot add card; already at maximum deck size");
	}
	
	public Card drawCard() {
		return deck.remove(0);
	}
	
	public int getDeckSize() { return deck.size(); }
}
