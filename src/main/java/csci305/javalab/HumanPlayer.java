package csci305.javalab;

import java.util.Scanner;

public class HumanPlayer extends Player {

	HumanPlayer() {
		super("HumanPlayer");
	}
	
	@Override
	public Element play() {		
		
		//Based on user input, returns move Element:
		//Rock, Paper, Scissors, Lizard, Spock
		int choice = 0;
		
		System.out.printf("\n(1) : Rock"+ 
						  "\n(2) : Paper"+
						  "\n(3) : Scissors"+
						  "\n(4) : Lizard"+
						  "\n(5) : Spock"+
						  "\nEnter your move: ");
		
		//Ask for user input (with exceptions handled)
		choice = getInput();
		
		switch(choice) {
		
			case 1:
				return Main.moves.get("Rock");
			case 2:
				return Main.moves.get("Paper");
			case 3:
				return Main.moves.get("Scissors");
			case 4:
				return Main.moves.get("Lizard");
			case 5:
				return Main.moves.get("Spock");	
			default:
				choice = getInput();
		}
		return Main.moves.get("Rock");
	}
		
	@Override
	public void setLastPlay(Element lastPlay) {}
	
	
	private int getInput() {		
		
		//Gets input from user
		//Handles possible errors from mistake inputs by user
		int choice;
		Scanner s = new Scanner(System.in);
		
		try {
			choice = s.nextInt();
			
			if(choice <= 5 && choice >= 1) {
				return choice;
			}
			else {
				System.out.printf("\nInvalid Move. Please try again.\nEnter your move: ");				
				getInput();
			}
		}
		catch(Exception e) {
			System.out.printf("\nInvalid Move. Please try again.\nEnter your move: ");
			getInput();
		}
		return 0;
	}	
}
