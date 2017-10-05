package war;

import java.util.ArrayList;

public class Turn {
	
	private String winner;
	private PointSystem pointSystem;
	private ArrayList<Player> players;
	private int turnNumber;
	private static final int MAX_TURN_NUMBER = 4000;
	private static GameOutput gameOutput = new GameOutput();
	private static Winner roundWinner = new Winner();
	
	public Turn(ArrayList<Player> players) {
		turnNumber = 1;
		winner = "";
		this.players = players;
		pointSystem = new PointSystem(players);
		roundWinner = new Winner();
	}
	
	public String runTurn(){
		boolean checkForWar;
		ArrayList<Card> upCards;
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound()
				&& turnNumber < MAX_TURN_NUMBER && pointSystem.checkForSufficientCards(1)) {
			upCards = new ArrayList<Card>();
			roundWinner.reset();
			checkForWar = true;
			for (Player player : players) {
				upCards = playCard(player, upCards);
				checkForWar = determineInitiateWar(player, roundWinner, upCards);
			}
			checkForWar = determineRoundWinner(checkForWar, upCards);
			pointSystem.checkForWinner();
			turnNumber++;
		}
		determineGameWinner();
		return winner;
	}
	
	private boolean determineInitiateWar(Player player, Winner roundWinner, ArrayList<Card> upCards) {
		gameOutput.printEvent(
				player.getName() + " plays " + upCards.get(upCards.size()-1).getRank() + " of " 
						+ upCards.get(upCards.size()-1).getSuit() + " as up card");
		if (roundWinner.getWinningCard() == null) {
			roundWinner.setWinner(player.getName(), player.getHand());
			roundWinner.setWinningCard(upCards.get(upCards.size()-1));
		} else if (upCards.get(upCards.size()-1).getRank().getValue() > roundWinner.getWinningCard().getRank().getValue()) {
			roundWinner.setWinner(player.getName(), player.getHand());
			roundWinner.setWinningCard(upCards.get(upCards.size()-1));
			return false;
		} else if (upCards.get(upCards.size()-1).getRank().getValue() < roundWinner.getWinningCard().getRank().getValue())
			return false;
		
		return true;
	}
	
	private boolean determineRoundWinner(boolean checkForWar, ArrayList<Card> upCards) {
		if (checkForWar && pointSystem.checkForSufficientCards(2)) {
			initiateWar(players, upCards);
			return false;
		} else if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				roundWinner.getHand().addCard(card);
			gameOutput.printEvent(roundWinner.getName() + " wins the round");
		} else {
			if (checkForWar && !pointSystem.checkForSufficientCards(2))
				gameOutput.printEvent("Cards are discarded due to war on the final round.");
			else {
				pointSystem.adjustScore(roundWinner, upCards.size());
				gameOutput.printEvent(roundWinner.getName() + " wins the round");
			}
		}
		gameOutput.displayScores(players);
		return true;
	}
	
	private void initiateWar(ArrayList<Player> players, ArrayList<Card> upCards) {
		boolean checkForWar = true;
		ArrayList<Card> downCards = new ArrayList<Card>();
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound()
				&& checkForWar && pointSystem.checkForSufficientCards(2)) {
			gameOutput.printEvent("War!");
			roundWinner.reset();
			checkForWar = true;
			for (Player player : players) {
				downCards = playCard(player, downCards);
				upCards = playCard(player, upCards);
				checkForWar = determineInitiateWar(player, roundWinner, upCards);
			}
		}
		
		determineWarWinner(checkForWar, roundWinner, upCards, downCards);
	}
	
	private ArrayList<Card> playCard(Player player, ArrayList<Card> cards) {
		Card card = player.getHand().drawCard();
		cards.add(card);
		
		return cards;
	}
	
	private void determineWarWinner(boolean checkForWar, Winner winningPlayer, ArrayList<Card> upCards, ArrayList<Card> downCards) {
		if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				winningPlayer.getHand().addCard(card);
			for (Card card : downCards)
				winningPlayer.getHand().addCard(card);
			gameOutput.printEvent(winningPlayer.getName() + " wins the round");
			gameOutput.displayScores(players);
		} else {
			if (checkForWar && !pointSystem.checkForSufficientCards(2))
				gameOutput.printEvent("Cards are discarded due to war on the final round.");
			else {
				pointSystem.adjustScore(winningPlayer, upCards.size());
				pointSystem.adjustScore(winningPlayer, downCards.size());
				gameOutput.printEvent(winningPlayer.getName() + " wins the round");
				gameOutput.displayScores(players);
			}
		}
	}
	
	private void determineGameWinner() {
		if (turnNumber >= MAX_TURN_NUMBER)
			pointSystem.setCurrentWinner();
		if (pointSystem.getTieFound())
			winner = "Tie!";
		else if (pointSystem.getWinnerFound())
			winner = pointSystem.getWinner().getName() + " wins!";
	}
}
