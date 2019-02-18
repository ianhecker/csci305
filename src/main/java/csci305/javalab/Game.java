package csci305.javalab;  

import java.util.Scanner;

public class Game {

	Player p1;
	Player p2;
	
	int p1Wins;
	int p2Wins;
	
	int round;		
	
	Game() {
		p1Wins = 0;
		round  = 0;	
		//initializes game
		startGame();
	}
		
	private void startGame() {
		
		//main method of class Game
		//directs all traffic of methods
		//houses most of print statements		
		System.out.printf("\nWelcome to Rock, Paper, Scissors, Lizard, Spock,"
				+ " implemented by Ian Hecker.\n");
		System.out.printf("\nPlease choose two players:\n"
						+ "(1) Human\n"
						+ "(2) StupidBot\n"
						+ "(3) RandomBot\n"
						+ "(4) IterativeBot\n"
						+ "(5) LastPlayBot\n"
						+ "(6) MyBot\n");
		
		System.out.printf("\nSelect player 1: ");		
		//ask and return bot selection
		p1 = determinePlayer();
		
		System.out.printf("\nSelect player 2: ");
		//ask and return bot selection
		p2 = determinePlayer();
		
		System.out.printf("\n\n%s vs. %s. Go!", p1.getName(), p2.getName());
		
		//Runs for five rounds
		for(int i=0; i < 5; i++) {
			round++;
			//call method to battle 2 chosen bots
			battle();			
		}
		
		System.out.printf("\n\nThe score is %d to %d.", p1Wins, p2Wins);
		
		//Call endgame and finish game
		//Determine winner and print
		endGame();
	}
	
	private void battle() {
		
		System.out.printf("\n\nRound %d:", round);
		
		//Retrieve players' choice of rock, paper, scissors, lizard spock
		Element e1 = p1.play();
		Element e2 = p2.play();
		
		Player winner;
		
		//IMPORTANT
		//This comparison is in the point of view of player 1
		//The Outcome's outcome string is in the POV of player 1
		Outcome o = e1.compareTo(e2);
		
		System.out.printf("\n  %s chose %s", p1.getName(), e1.getName());
		System.out.printf("\n  %s chose %s\n", p2.getName(), e2.getName());				
		System.out.println("  " + o.toString());
		
		//Returns winner of battle
		//Or returns null for tie
		winner = determineWin(o);
		
		//If winner returns as null, it was a tie
		//Else, winner is either p1 or p2
		if(winner != null) {
			System.out.printf("  %s won the round", winner.getName());
		}
		else {
			System.out.printf("  Round was a tie");
		}			
		
		//Updating opponent's lastPlay variable for special bots:
		//MyBot & LastPlayBot
		//last play of opponent determines these bots' next move
		updateLastPlay(e1, e2);	
	}
	
	private Player determinePlayer() {
		int choice = 0;
		
		//Ask for input from user of bot choice
		choice = getInput();
		
		switch(choice) {
		
			case 1:
				return Main.bots.get("HumanPlayer");
			case 2:
				return Main.bots.get("StupidBot");
			case 3:
				return Main.bots.get("RandomBot");
			case 4:
				return Main.bots.get("IterativeBot");
			case 5:
				return Main.bots.get("LastPlayBot");
			case 6:
				return Main.bots.get("MyBot");
		}
		return determinePlayer();
	}
	
	private Player determineWin(Outcome o) {
		//IMPORTANT
		//The Outcome object is always in the point of view of player 1
		//Therefore, the outcome string will be from player 1 POV
		if(o.getOutcome() == "Win") {
			p1Wins++;
			return p1;
		}
		else if(o.getOutcome() == "Lose") {
			p2Wins++;
			return p2;
		}
		return null;
	}
	
	private void endGame() {
		
		//Compares tallies of wins of rounds to determine overall winner
		if(p1Wins > p2Wins) {
			System.out.printf("\n%s wins the game!", p1.getName());
		}
		else if(p2Wins > p1Wins) {
			System.out.printf("\n%s wins the game!", p2.getName());
		}
		else {//p2Wins == p1Wins
			System.out.printf("\nGame was a draw.");
		}
	}
	
	private void updateLastPlay(Element e1, Element e2) {
		
		//Updates crucial variable lastPlay in:
		//MyBot and LastPlayBot
		//Allows bots to determine their next move based on opponent's last move
		
		//Below switches determines if p1 || p2 is either mentioned bot
		switch(p1.getName()) {
			
			case "LastPlayBot":				
				Main.bots.get(p1.getName()).setLastPlay(e2);
				break;
				
			case "MyBot":
				Main.bots.get(p1.getName()).setLastPlay(e2);				
				break;
		}
		switch(p2.getName()) {
			
			case "LastPlayBot":
				Main.bots.get(p2.getName()).setLastPlay(e1);				
				break;
				
			case "MyBot":
				Main.bots.get(p2.getName()).setLastPlay(e1);				
				break;
		}				
	}
		
	private int getInput() {
		
		//Gets input from user
		//Handles possible errors from mistake inputs by user
		int choice;
		Scanner s = new Scanner(System.in);
		
		try {
			choice = s.nextInt();
			
			if(choice <= 6 && choice >= 1) {
				return choice;
			}
			else {
				System.out.printf("\nInvalid Choice. Please try again.\nEnter your choice: ");				
				getInput();
			}
		}
		catch(Exception e) {
			System.out.printf("\nInvalid Choice. Please try again.\nEnter your choice: ");
			getInput();
		}
		return 0;		
	}	
}
