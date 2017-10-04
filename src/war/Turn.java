package war;

import java.util.ArrayList;

public class Turn {
	
	private String winner;
	private PointSystem pointSystem;
	private ArrayList<Player> players;
	
	public Turn(ArrayList<Player> players) {
		winner = "";
		this.players = players;
		pointSystem = new PointSystem(players);
	}
	
	public String runTurn(){
		boolean checkForWar;
		Card winningCard;
		Player winningPlayer;
		ArrayList<Card> upCards;
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound()) {
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
			}
			
			if (checkForWar) {
				initiateWar(players, upCards);
				checkForWar = false;
			}
			else if (Menu.getVariation() == 1) {
				for (Card card : upCards)
					winningPlayer.getHand().addCard(card);
			} else
				pointSystem.adjustScore(winningPlayer, upCards.size());
			
			System.out.println(winningPlayer.getName() + " wins the round");
			displayScores(players);
			
			pointSystem.checkForWinner();
		}
		
		if (pointSystem.getWinnerFound())
			winner = pointSystem.getWinner().getName() + " wins!";
		else if (pointSystem.getTieFound())
			winner = "Tie!";
		
		return winner;
	}
	
	private void initiateWar(ArrayList<Player> players, ArrayList<Card> upCards) {
		boolean checkForWar = true;
		Card winningCard = null;
		Player winningPlayer = null;
		ArrayList<Card> downCards = new ArrayList<Card>();
		while (checkForWar) {
			winningCard = null;
			winningPlayer = null;
			System.out.println("War!");
			for (Player player : players) {
				Card downCard = player.getHand().drawCard();
				downCards.add(downCard);
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
					checkForWar = false;
				} else if (upCard.getRank().getValue() < winningCard.getRank().getValue())
					checkForWar = false;
				System.out.println(checkForWar);
			}
		}
		
		if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				winningPlayer.getHand().addCard(card);
			for (Card card : downCards)
				winningPlayer.getHand().addCard(card);
		} else
			pointSystem.adjustScore(winningPlayer, upCards.size() + downCards.size());
		
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
		System.out.println("");
	}
}
