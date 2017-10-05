package war;

import java.util.ArrayList;

public class Turn {
	
	private String winner;
	private PointSystem pointSystem;
	private ArrayList<Player> players;
	private GameOutput gameOutput = new GameOutput();
	private int turnNumber;
	private static final int MAX_TURN_NUMBER = 4000;
	
	public Turn(ArrayList<Player> players) {
		turnNumber = 1;
		winner = "";
		this.players = players;
		pointSystem = new PointSystem(players);
	}
	
	public String runTurn(){
		boolean checkForWar;
		Card winningCard;
		Player winningPlayer;
		ArrayList<Card> upCards;
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound()
				&& turnNumber < MAX_TURN_NUMBER && pointSystem.checkForSufficientCards(1)) {
			winningCard = null;
			winningPlayer = null;			
			upCards = new ArrayList<Card>();
			checkForWar = true;
			for (Player player : players) {
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				gameOutput.printEvent(
						player.getName() + " plays " + upCard.getRank() + " of " + upCard.getSuit() + " as up card");
				if (winningCard == null) {
					winningCard = upCard;
					winningPlayer = player;
				} else if (upCard.getRank().getValue() > winningCard.getRank().getValue()) {
					winningCard = upCard;
					winningPlayer = player;
					checkForWar = false;
				} else if (upCard.getRank().getValue() < winningCard.getRank().getValue())
					checkForWar = false;
			}
			
			if (checkForWar && pointSystem.checkForSufficientCards(2)) {
				initiateWar(players, upCards);
				checkForWar = false;
			} else if (Menu.getVariation() == 1) {
				if (checkForWar && !pointSystem.checkForSufficientCards(2))
					gameOutput.printEvent("Cards are discarded due to war on the final round.");
				else {
					for (Card card : upCards)
						winningPlayer.getHand().addCard(card);
					for (Player player : players)
						player.setScore(player.getHand().getDeckSize());
					gameOutput.printEvent(winningPlayer.getName() + " wins the round!\n*************");
					gameOutput.displayScores(players);
				}
			} else {
				System.out.println(checkForWar + " " + pointSystem.checkForSufficientCards(2));
				if (checkForWar && !pointSystem.checkForSufficientCards(2))
					gameOutput.printEvent("Cards are discarded due to war on the final round.");
				else {
					pointSystem.adjustScore(winningPlayer, upCards.size());
					gameOutput.printEvent(winningPlayer.getName() + " wins the round!\n*************");
				}
				gameOutput.displayScores(players);
			}
			
			pointSystem.checkForWinner();
			turnNumber++;
		}
		
		if (turnNumber >= MAX_TURN_NUMBER)
			pointSystem.setCurrentWinner();
		if (pointSystem.getTieFound())
			winner = "Tie!";
		else if (pointSystem.getWinnerFound())
			winner = ">  "+ pointSystem.getWinner().getName() + " wins!";
		
		return winner;
	}
	
	private void initiateWar(ArrayList<Player> players, ArrayList<Card> upCards) {
		boolean checkForWar = true;
		Card winningCard = null;
		Player winningPlayer = null;
		ArrayList<Card> downCards = new ArrayList<Card>();
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound() && checkForWar && pointSystem.checkForSufficientCards(2)) {
			winningCard = null;
			winningPlayer = null;
			checkForWar = true;
			gameOutput.printEvent("War!");
			for (Player player : players) {
				Card downCard = player.getHand().drawCard();
				downCards.add(downCard);
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				gameOutput.printEvent(
						player.getName() + " plays " + upCard.getRank() + " of " + upCard.getSuit() + " as up card");
				if (winningCard == null) {
					winningCard = upCard;
					winningPlayer = player;
				} else if (upCard.getRank().getValue() > winningCard.getRank().getValue()) {
					winningCard = upCard;
					winningPlayer = player;
					checkForWar = false;
				} else if (upCard.getRank().getValue() < winningCard.getRank().getValue())
					checkForWar = false;
			}
		}
		
		if (Menu.getVariation() == 1) {
			if (checkForWar && !pointSystem.checkForSufficientCards(2))
				gameOutput.printEvent("Cards are discarded due to war on the final round.");
			else {
				for (Card card : upCards)
					winningPlayer.getHand().addCard(card);
				for (Card card : downCards)
					winningPlayer.getHand().addCard(card);
				gameOutput.printEvent(winningPlayer.getName() + " wins the round!\n*************");
				gameOutput.displayScores(players);
			}
		} else {
			System.out.println(checkForWar + " " + pointSystem.checkForSufficientCards(2));
			if (checkForWar && !pointSystem.checkForSufficientCards(2))
				gameOutput.printEvent("Cards are discarded due to war on the final round.");
			else {
				pointSystem.adjustScore(winningPlayer, upCards.size());
				pointSystem.adjustScore(winningPlayer, downCards.size());
				gameOutput.printEvent(winningPlayer.getName() + " wins the round!\n*************");
				gameOutput.displayScores(players);
			}
		}
	}
}
