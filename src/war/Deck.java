package war;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deck of cards - fills with a standard deck of 52 cards by default and is shuffled
 * upon creation.
 * 
 * @author Lucas Tiedeman
 * @author Sean Woerner
 * @author Christian Andrada
 */

public class Deck {
	
	private static final int MAX_DECK_SIZE = 52;
	protected ArrayList<Card> cards;
	
	public Deck() {}
	
	/**
	 * @param deckSize	The desired size of the deck. If deckSize == 52, creates a standard deck,
	 * 					and is otherwise left empty to be filled with custom cards
	 */
	public Deck(int deckSize) {
		cards = new ArrayList<Card>();
		
		if (deckSize == 52) {
			createCards(cards);
			Collections.shuffle(cards);
		}
	}
	
	/**
	 * @param cards	The ArrayList to be filled with the standard deck
	 */
	private void createCards(ArrayList<Card> cards) {
		for (int i = 0; i < 13; i++) {
			Rank rank = Rank.values()[i];
			for (int j = 0; j < 4; j++)
				this.cards.add(new Card(rank, Suit.values()[j]));
		}
	}
	
	/**
	 * Adds a single card to the deck
	 * 
	 * @param card	The card to be added
	 */
	public void addCard(Card card) {
		if (cards.size() < MAX_DECK_SIZE)
			cards.add(card);
	}
	
	/**
	 * Removes a card from the ArrayList
	 * 
	 * @return	The removed card
	 */
	public Card drawCard() { return cards.remove(0); }
	
	public int getDeckSize() { return cards.size(); }
	
	/**
	 * Outputs all of the cards of the deck in order of index
	 */
	public void displayDeck() {
		for (Card c : cards)
			System.out.println(c.getRank() + "\tof\t" + c.getSuit());
	}
}