package war;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private static final int MAX_DECK_SIZE = 52;
	protected ArrayList<Card> cards;
	
	public Deck(int deckSize) {
		cards = new ArrayList<Card>();
		
		if (deckSize == 52) {
			CreateCards(cards);
			Collections.shuffle(cards);
		}
	}
	
	private void CreateCards(ArrayList<Card> cards) {
		for (int i = 0; i < 13; i++) {
			Rank rank = Rank.values()[i];
			for (int j = 0; j < 4; j++)
				this.cards.add(new Card(rank, Suit.values()[j]));
		}
	}
	
	public void addCard(Card card) {
		if (cards.size() < MAX_DECK_SIZE)
			cards.add(card);
		else
			System.err.print("Cannot add card; already at maximum deck size");
	}
	
	public Card drawCard() { return cards.remove(0); }
	
	public int getDeckSize() { return cards.size(); }
	
	public void displayDeck() {
		for (Card c : cards)
			System.out.println(c.getRank() + "\tof\t" + c.getSuit());
	}
}
