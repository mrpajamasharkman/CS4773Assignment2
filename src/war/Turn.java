package war;

import java.util.ArrayList;

public class Turn {
	
	private PointSystem pointSystem;
	private ArrayList<Player> players;
	private GameOutput gameOutput;
	private Winner winningPlayer;
	private boolean checkForWar;
	private int turnNumber;
	private int maxTurnNumber;
	
	public Turn(ArrayList<Player> players, GameOutput gameOutput) {
		turnNumber = 1;
		this.players = players;
		pointSystem = new PointSystem(players);
		winningPlayer = new Winner();
		this.gameOutput = gameOutput;
		setMaxTurnNumber(4000);
		setCheckForWar(false);
	}
	
	public String runTurn(){
		ArrayList<Card> upCards;
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound()
				&& turnNumber < getMaxTurnNumber() && pointSystem.checkForSufficientCards(1)) {
			winningPlayer = new Winner();			
			upCards = new ArrayList<Card>();
			setCheckForWar(false);
			for (Player player : players) {
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				determineCheckForWar(player, upCard);
			}
			determineRoundWinner(upCards);
			
			pointSystem.checkForWinner();
			turnNumber++;
		}
		
		return determineGameWinner();
	}
	
	public void determineCheckForWar(Player player, Card upCard) {
		gameOutput.appendEvent(
				player.getName() + " plays " + upCard.getRank() + " of " + upCard.getSuit() + " as up card");
		if (winningPlayer.getWinningCard() == null) {
			winningPlayer.setWinningCard(upCard);
			winningPlayer.setWinner(player);
		} else if (upCard.getRank().getValue() > winningPlayer.getWinningCard().getRank().getValue()) {
			winningPlayer.setWinningCard(upCard);
			winningPlayer.setWinner(player);
			setCheckForWar(false);
		} else if (upCard.getRank().getValue() == winningPlayer.getWinningCard().getRank().getValue())
			setCheckForWar(true);
	}

	public void determineRoundWinner(ArrayList<Card> upCards) {
		if (getCheckForWar() && pointSystem.checkForSufficientCards(1)) {
			initiateWar(players, upCards);
			setCheckForWar(false);
		} else if (getCheckForWar() && !pointSystem.checkForSufficientCards(1))
			gameOutput.appendEvent("Cards discarded due to war with insufficient cards.");
		else if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				winningPlayer.getWinner().getHand().addCard(card);
			for (Player player : players)
				player.setScore(player.getHand().getDeckSize());
			gameOutput.appendEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
		} else {
			pointSystem.adjustScore(winningPlayer.getWinner(), upCards.size());
			gameOutput.appendEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
		}
		gameOutput.displayScores(players);
	}

	public void initiateWar(ArrayList<Player> players, ArrayList<Card> upCards) {
		ArrayList<Card> downCards = new ArrayList<Card>();
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound() && getCheckForWar() && pointSystem.checkForSufficientCards(1)) {
			winningPlayer = new Winner();
			setCheckForWar(false);
			gameOutput.appendEvent("War!");
			for (Player player : players) {
				// If player only has one card remaining, allow him to skip down card in order to continue
				if (player.getHand().getDeckSize() > 1) {
					Card downCard = player.getHand().drawCard();
					downCards.add(downCard);
				}
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				determineCheckForWar(player, upCard);
			}
		}
		determineWarWinner(upCards, downCards);
	}

	public void determineWarWinner(ArrayList<Card> upCards, ArrayList<Card> downCards) {
		if (getCheckForWar() && !pointSystem.checkForSufficientCards(1))
			gameOutput.appendEvent("Cards discarded due to war with insufficient cards.");
		else if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				winningPlayer.getWinner().getHand().addCard(card);
			for (Card card : downCards)
				winningPlayer.getWinner().getHand().addCard(card);
			gameOutput.appendEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
		} else {
			pointSystem.adjustScore(winningPlayer.getWinner(), upCards.size());
			pointSystem.adjustScore(winningPlayer.getWinner(), downCards.size());
			gameOutput.appendEvent(winningPlayer.getWinner().getName() + " wins the round!\n*************");
		}
	}

	public String determineGameWinner() {
		String winner = "";
		if (turnNumber >= getMaxTurnNumber()) {
			gameOutput.appendEvent("Maximum turns reached:");
			pointSystem.setCurrentWinner();
		}
		if (pointSystem.getTieFound())
			winner = "Tie!";
		else if (pointSystem.getWinnerFound())
			winner = ">  "+ pointSystem.getWinner().getName() + " wins!";
		return winner;
	}
	
	public int getMaxTurnNumber() { return maxTurnNumber; }
	
	public void setMaxTurnNumber(int maxTurnNumber) { this.maxTurnNumber = maxTurnNumber; }
	
	public boolean getCheckForWar() { return checkForWar; }
	
	public void setCheckForWar(boolean checkForWar) { this.checkForWar = checkForWar; }
}
