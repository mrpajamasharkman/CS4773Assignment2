package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import war.Card;
import war.Deck;
import war.GameOutput;
import war.Menu;
import war.Player;
import war.Rank;
import war.Suit;
import war.Turn;

public class TestWar {
	
	private static Deck deck;
	private static Turn turn;
	private static GameOutput gameOutput;
	private static String expectedOutput;
	
	public String getExpected(String fileName)  throws Exception {
		StringBuffer fileContents = new StringBuffer();
		Scanner fileInput = new Scanner(new File(fileName));
		while(fileInput.hasNextLine())
			fileContents.append(fileInput.nextLine() + "\n");
		expectedOutput = fileContents.toString();
		fileInput.close();
		return expectedOutput;
	}

	@Test
	public void testVariation1Player1Wins() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1Player1Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1Player2Wins() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1Player2Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1WarPlayer1Wins() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1WarPlayer1Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1WarPlayer2Wins() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1WarPlayer2Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1DoubleWar() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		deck.addCard(new Card(Rank.JACK, Suit.SPADES));
		deck.addCard(new Card(Rank.NINE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 5));
		players.add(new Player("player2", deck, 5));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1DoubleWar.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1WarNoDownCard() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 2));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1WarNoDownCard.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1WarInsufficientCards() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 2));
		players.add(new Player("player2", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1WarInsufficientCards.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1DoubleWarInsufficientCards() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 5));
		players.add(new Player("player2", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1DoubleWarInsufficientCards.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1MaxTurnsReached() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 2));
		players.add(new Player("player2", deck, 2));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1MaxTurnsReached.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation1MaxTurnsReachedTie() throws Exception {
		Menu.setVariation(1);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 2));
		players.add(new Player("player2", deck, 2));
		
		turn = new Turn(players, gameOutput);
		turn.setMaxTurnNumber(3999);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation1MaxTurnsReachedTie.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2Player1Wins() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2Player1Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2Player2Wins() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2Player2Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2Tie() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.TWO, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 2));
		players.add(new Player("player2", deck, 2));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2Tie.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2WarPlayer1Wins() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2WarPlayer1Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2WarPlayer2Wins() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2WarPlayer2Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2WarTie() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.NINE, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		deck.addCard(new Card(Rank.TEN, Suit.SPADES));
		deck.addCard(new Card(Rank.JACK, Suit.SPADES));
		deck.addCard(new Card(Rank.EIGHT, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 6));
		players.add(new Player("player2", deck, 6));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2WarTie.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2DoubleWar() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		deck.addCard(new Card(Rank.JACK, Suit.SPADES));
		deck.addCard(new Card(Rank.NINE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 5));
		players.add(new Player("player2", deck, 5));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2DoubleWar.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2WarNoDownCard() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 2));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2WarNoDownCard.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2WarInsufficientCards() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));

		deck.addCard(new Card(Rank.THREE, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 2));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2WarInsufficientCards.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation2DoubleWarInsufficientCards() throws Exception {
		Menu.setVariation(2);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));

		deck.addCard(new Card(Rank.THREE, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 6));
		players.add(new Player("player2", deck, 4));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation2DoubleWarInsufficientCards.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3Player3Wins() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.FIVE, Suit.HEARTS));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		players.add(new Player("player3", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3Player3Wins.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3NoWar1() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.FIVE, Suit.CLUBS));
		deck.addCard(new Card(Rank.FIVE, Suit.HEARTS));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		players.add(new Player("player3", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3NoWar1.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3NoWar2() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.FIVE, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.FIVE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		players.add(new Player("player3", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3NoWar2.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3NoWar3() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.FIVE, Suit.HEARTS));
		deck.addCard(new Card(Rank.FIVE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 1));
		players.add(new Player("player2", deck, 1));
		players.add(new Player("player3", deck, 1));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3NoWar3.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3War1() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));

		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.JACK, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		players.add(new Player("player3", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3War1.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3War2() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));

		deck.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.JACK, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		players.add(new Player("player3", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3War2.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3War3() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));

		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.JACK, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		players.add(new Player("player3", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3War3.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3War4() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));

		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.JACK, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		players.add(new Player("player3", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3War4.txt"), gameOutput.getStringBuffer().toString());
	}

	@Test
	public void testVariation3Tie() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
	
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		players.add(new Player("player3", deck, 3));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3Tie.txt"), gameOutput.getStringBuffer().toString());
	}

	@Test
	public void testVariation3WarTie() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.NINE, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.EIGHT, Suit.CLUBS));
		deck.addCard(new Card(Rank.SEVEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.SIX, Suit.CLUBS));
	
		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.JACK, Suit.HEARTS));
		deck.addCard(new Card(Rank.TEN, Suit.HEARTS));
		deck.addCard(new Card(Rank.NINE, Suit.HEARTS));
		deck.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
		deck.addCard(new Card(Rank.EIGHT, Suit.HEARTS));
		deck.addCard(new Card(Rank.SEVEN, Suit.HEARTS));
		deck.addCard(new Card(Rank.SIX, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.JACK, Suit.SPADES));
		deck.addCard(new Card(Rank.TEN, Suit.SPADES));
		deck.addCard(new Card(Rank.NINE, Suit.SPADES));
		deck.addCard(new Card(Rank.SIX, Suit.SPADES));
		deck.addCard(new Card(Rank.EIGHT, Suit.SPADES));
		deck.addCard(new Card(Rank.SEVEN, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 9));
		players.add(new Player("player2", deck, 9));
		players.add(new Player("player3", deck, 9));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3WarTie.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3DoubleWar() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.NINE, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
		deck.addCard(new Card(Rank.JACK, Suit.HEARTS));
		deck.addCard(new Card(Rank.NINE, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		deck.addCard(new Card(Rank.JACK, Suit.SPADES));
		deck.addCard(new Card(Rank.TEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 5));
		players.add(new Player("player2", deck, 5));
		players.add(new Player("player3", deck, 5));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3DoubleWar.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3WarNoDownCard() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
		
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		players.add(new Player("player3", deck, 2));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3WarNoDownCard.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3WarInsufficientCards() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.TWO, Suit.HEARTS));
		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));

		deck.addCard(new Card(Rank.THREE, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 3));
		players.add(new Player("player2", deck, 3));
		players.add(new Player("player3", deck, 2));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3WarInsufficientCards.txt"), gameOutput.getStringBuffer().toString());
	}
	
	@Test
	public void testVariation3DoubleWarInsufficientCards() throws Exception {
		Menu.setVariation(3);
		gameOutput = new GameOutput();
		
		deck = new Deck();
		deck.addCard(new Card(Rank.TWO, Suit.CLUBS));
		deck.addCard(new Card(Rank.ACE, Suit.CLUBS));
		deck.addCard(new Card(Rank.KING, Suit.CLUBS));
		deck.addCard(new Card(Rank.QUEEN, Suit.CLUBS));
		deck.addCard(new Card(Rank.JACK, Suit.CLUBS));
		deck.addCard(new Card(Rank.TEN, Suit.CLUBS));
		
		deck.addCard(new Card(Rank.TWO, Suit.HEARTS));
		deck.addCard(new Card(Rank.ACE, Suit.HEARTS));
		deck.addCard(new Card(Rank.KING, Suit.HEARTS));
		deck.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
		deck.addCard(new Card(Rank.JACK, Suit.HEARTS));
		deck.addCard(new Card(Rank.TEN, Suit.HEARTS));

		deck.addCard(new Card(Rank.THREE, Suit.SPADES));
		deck.addCard(new Card(Rank.ACE, Suit.SPADES));
		deck.addCard(new Card(Rank.KING, Suit.SPADES));
		deck.addCard(new Card(Rank.QUEEN, Suit.SPADES));
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("player1", deck, 6));
		players.add(new Player("player2", deck, 6));
		players.add(new Player("player3", deck, 4));
		
		turn = new Turn(players, gameOutput);
		gameOutput.appendEvent(turn.runTurn());
		assertEquals(getExpected("Variation3DoubleWarInsufficientCards.txt"), gameOutput.getStringBuffer().toString());
	}
}
