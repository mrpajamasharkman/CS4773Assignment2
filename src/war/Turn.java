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
		
//		Menu.enableDebug();
		if (Menu.getDebug()) {
			for (Player player : players) {
				System.out.println(player.getName());
				player.getHand().displayDeck();
				System.out.println(player.getScore());
			}
		}
	}
	
	public String runTurn(){
		boolean checkForWar;
		Card winningCard = null;
		Player winningPlayer = null;
		ArrayList<Card> upCards = null;
		while (!pointSystem.getWinnerFound() && !pointSystem.getTieFound()) {
			upCards = new ArrayList<Card>();
			checkForWar = false;
			for (Player player : players) {
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				System.out.println(
						player.getName() + " plays " + upCard.getRank() + " of " + upCard.getSuit() + " as up card");
				if (winningCard == null)
					winningCard = upCard;
				else if (upCard.getRank().getValue() > winningCard.getRank().getValue()) {
					winningCard = upCard;
					winningPlayer = player;
				} else if (upCard.getRank().getValue() == winningCard.getRank().getValue())
					checkForWar = true;
			}
			
			if (checkForWar)
				initiateWar(players, upCards);
			else if (Menu.getVariation() == 1) {
				for (Card card : upCards)
					winningPlayer.addCard(card);
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
		boolean checkForWar = false;
		Card winningCard = null;
		Player winningPlayer = null;
		ArrayList<Card> downCards = new ArrayList<Card>();
		while (!checkForWar) {
			System.out.println("War!");
			for (Player player : players) {
				Card downCard = player.getHand().drawCard();
				downCards.add(downCard);
				Card upCard = player.getHand().drawCard();
				upCards.add(upCard);
				System.out.println(
						player.getName() + " plays " + upCard.getRank() + " of " + upCard.getSuit() + " as up card");
				if (winningCard == null)
					winningCard = upCard;
				else if (upCard.getRank().getValue() > winningCard.getRank().getValue()) {
					winningCard = upCard;
					winningPlayer = player;
				} else if (upCard.getRank().getValue() == winningCard.getRank().getValue())
					checkForWar = true;
			}
		}
		
		if (Menu.getVariation() == 1) {
			for (Card card : upCards)
				winningPlayer.addCard(card);
			for (Card card : downCards)
				winningPlayer.addCard(card);
		} else
			pointSystem.adjustScore(winningPlayer, upCards.size() + downCards.size());
		
		System.out.println(winningPlayer.getName() + " wins the round");
		displayScores(players);
	}
	
	private void displayScores(ArrayList<Player> players) {
		System.out.print("Score is ");
		for (Player player : players)
			System.out.print(player.getName() + " " + player.getScore() + " ");
	}
}
