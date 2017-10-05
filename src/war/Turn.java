package war;

import java.util.ArrayList;

public class Turn {
	
	private String winner;
	private PointSystem pointSystem;
	private ArrayList<Player> players;
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
		boolean sufficientCards = checkForSufficientCards(players);
		Card winningCard;
		Player winningPlayer;
		ArrayList<Card> upCards;
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound() 
				&& turnNumber < MAX_TURN_NUMBER && sufficientCards) {
			winningCard = null;
			winningPlayer = null;			
			upCards = new ArrayList<Card>();
			checkForWar = false;
			for (Player player : players) {
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				System.out.println(
						player.getName() + " plays " + upCard.getRank() + " of " + upCard.getSuit() + " as up card");
				if (winningCard == null) {
					winningCard = upCard;
					winningPlayer = player;
				} else if (upCard.getRank().getValue() > winningCard.getRank().getValue()) {
					winningCard = upCard;
					winningPlayer = player;
				} else if (upCard.getRank().getValue() == winningCard.getRank().getValue())
					checkForWar = true;
				sufficientCards = checkForSufficientCards(players);
			}
			
			if (checkForWar && sufficientCards) {
				initiateWar(players, upCards);
				checkForWar = false;
			}
			else if (Menu.getVariation() == 1) {
				for (Card card : upCards)
					winningPlayer.getHand().addCard(card);
				System.out.println(winningPlayer.getName() + " wins the round");
				displayScores(players);
			} else {
				pointSystem.adjustScore(winningPlayer, upCards.size());
				System.out.println(winningPlayer.getName() + " wins the round");
				displayScores(players);
			}
			
			pointSystem.checkForWinner();
			turnNumber++;
		}
		
		 if (pointSystem.getTieFound())
				winner = "Tie!";
		 else if (pointSystem.getWinnerFound())
			winner = pointSystem.getWinner().getName() + " wins!";
		 else if (turnNumber >= MAX_TURN_NUMBER)
			winner = "Maximum turn reached: Tie!";
		
		return winner;
	}
	
	private void initiateWar(ArrayList<Player> players, ArrayList<Card> upCards) {
		boolean checkForWar = true;
		boolean sufficientCards = checkForSufficientCards(players);
		Card winningCard = null;
		Player winningPlayer = null;
		ArrayList<Card> downCards = new ArrayList<Card>();
		while (checkForWar && sufficientCards) {
			winningCard = null;
			winningPlayer = null;
			System.out.println("War!");
			for (Player player : players) {
				Card downCard = player.getHand().drawCard();
				downCards.add(downCard);
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				sufficientCards = checkForSufficientCards(players);
				System.out.println(
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
			if (!upCards.isEmpty()) {
				for (Card card : upCards)
					winningPlayer.getHand().addCard(card);
			}
			if (!downCards.isEmpty()) {
				for (Card card : downCards)
					winningPlayer.getHand().addCard(card);
			}
		} else {
			if (!upCards.isEmpty())
				pointSystem.adjustScore(winningPlayer, upCards.size());
			if (!downCards.isEmpty())
				pointSystem.adjustScore(winningPlayer, downCards.size());
		}
		
		System.out.println(winningPlayer.getName() + " wins the round");
		displayScores(players);
	}
	
	private void displayScores(ArrayList<Player> players) {
		System.out.print("Score is ");
		if (Menu.getVariation() == 1) {
			for (Player player : players)
				System.out.print(player.getName() + " " + player.getHand().getDeckSize() + " ");
		} else {
			for (Player player : players)
				System.out.print(player.getName() + " " + player.getScore() + " ");
		}
		System.out.println("\n");
	}
	
	private boolean checkForSufficientCards(ArrayList<Player> players) {
		for (Player player : players) {
			if (player.getHand().getDeckSize() < 2)
				return false;
		}
		return true;
	}
}
