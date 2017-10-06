package war;

import java.util.ArrayList;

public class Turn {
	
	private PointSystem pointSystem;
	private ArrayList<Player> players;
	private GameOutput gameOutput = new GameOutput();
	private Winner winningPlayer;
	private int turnNumber;
	private static final int MAX_TURN_NUMBER = 4000;
	
	public Turn(ArrayList<Player> players) {
		turnNumber = 1;
		this.players = players;
		pointSystem = new PointSystem(players);
		winningPlayer = new Winner();
	}
	
	public String runTurn(){
		boolean checkForWar;
		ArrayList<Card> upCards;
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound()
				&& turnNumber < MAX_TURN_NUMBER && pointSystem.checkForSufficientCards(1)) {
			winningPlayer = new Winner();			
			upCards = new ArrayList<Card>();
			checkForWar = false;
			for (Player player : players) {
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				checkForWar = determineCheckForWar(player, upCard);
			}
			
			determineRoundWinner(checkForWar, upCards);
			
			pointSystem.checkForWinner();
			turnNumber++;
		}
		
		return determineGameWinner();
	}
	
	private void determineRoundWinner(boolean checkForWar, ArrayList<Card> upCards) {
		if (checkForWar && pointSystem.checkForSufficientCards(1)) {
			initiateWar(players, upCards);
			gameOutput.displayScores(players);
			checkForWar = false;
		} else if (checkForWar && !pointSystem.checkForSufficientCards(1)) {
			gameOutput.printEvent("Cards discarded due to war with insufficient cards.");
		} else if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				winningPlayer.getWinner().getHand().addCard(card);
			for (Player player : players)
				player.setScore(player.getHand().getDeckSize());
			gameOutput.printEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
			gameOutput.displayScores(players);
		} else {
			pointSystem.adjustScore(winningPlayer.getWinner(), upCards.size());
			gameOutput.printEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
			gameOutput.displayScores(players);
		}
	}
	
	private void initiateWar(ArrayList<Player> players, ArrayList<Card> upCards) {
		boolean checkForWar = true;
		ArrayList<Card> downCards = new ArrayList<Card>();
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound() && checkForWar && pointSystem.checkForSufficientCards(1)) {
			winningPlayer = new Winner();
			checkForWar = false;
			gameOutput.printEvent("War!");
			for (Player player : players) {
				// If player only has one card remaining, allow him to skip down card in order to continue
				if (player.getHand().getDeckSize() > 1) {
					Card downCard = player.getHand().drawCard();
					downCards.add(downCard);
				}
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				checkForWar = determineCheckForWar(player, upCard);
			}
		}
		determineWarWinner(upCards, downCards);
	}
	
	private boolean determineCheckForWar(Player player, Card upCard) {
		gameOutput.printEvent(
				player.getName() + " plays " + upCard.getRank() + " of " + upCard.getSuit() + " as up card");
		if (winningPlayer.getWinningCard() == null) {
			winningPlayer.setWinningCard(upCard);
			winningPlayer.setWinner(player);
		} else if (upCard.getRank().getValue() > winningPlayer.getWinningCard().getRank().getValue()) {
			winningPlayer.setWinningCard(upCard);
			winningPlayer.setWinner(player);
			return false;
		} else if (upCard.getRank().getValue() == winningPlayer.getWinningCard().getRank().getValue())
			return true;
		return false;
	}
	
	private void determineWarWinner(ArrayList<Card> upCards, ArrayList<Card> downCards) {
		if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				winningPlayer.getWinner().getHand().addCard(card);
			for (Card card : downCards)
				winningPlayer.getWinner().getHand().addCard(card);
			gameOutput.printEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
		} else {
			pointSystem.adjustScore(winningPlayer.getWinner(), upCards.size());
			pointSystem.adjustScore(winningPlayer.getWinner(), downCards.size());
			gameOutput.printEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
		}
	}
	
	private String determineGameWinner() {
		String winner = "";
		if (turnNumber >= MAX_TURN_NUMBER) {
			gameOutput.printEvent("Maximum turns reached:");
			pointSystem.setCurrentWinner();
		}
		if (pointSystem.getTieFound())
			winner = "Tie!";
		else if (pointSystem.getWinnerFound())
			winner = ">  "+ pointSystem.getWinner().getName() + " wins!";
		return winner;
	}
}
