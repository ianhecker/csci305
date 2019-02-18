package csci305.javalab;

public class MyBot extends Player{

	Element lastPlay;
	
	MyBot(){
		super("MyBot");
		lastPlay = randomStart();
	}
	
	@Override
	public Element play() {
		return chooseMove();
	}
	
	@Override
	public void setLastPlay(Element lastPlay) {
		
		//Is called during Game class instance after dueling
		this.lastPlay = lastPlay;
	}
	
	private Element chooseMove() {
		
		//This bot will return either of the two superior moves to 
		//the opponent's last move. ex:
		//opponent -> Rock = next turn of MyBot -> Paper or Spock
		
		int random = (int) (Math.random() * 2);
		//Returns a double: 0 >= x < 1
		//Multiples by 2, to get doubles between 0 and 1.99
		//Truncates decimals with casting for ints of either 0 and 1
		
		String opponent = lastPlay.getName();
			
		//Chooses either superior move to opponent's last move for MyBot's current move 
		switch(opponent) {
		
			case "Rock":
				if(random == 0) {return Main.moves.get("Paper");}
				else 			{return Main.moves.get("Spock");}
			case "Paper":
				if(random == 0) {return Main.moves.get("Scissors");}
				else 			{return Main.moves.get("Lizard");}
			case "Scissors":
				if(random == 0) {return Main.moves.get("Rock");}
				else 			{return Main.moves.get("Spock");}
			case "Lizard":
				if(random == 0) {return Main.moves.get("Rock");}
				else 			{return Main.moves.get("Scissors");}
			case "Spock":
				if(random == 0) {return Main.moves.get("Paper");}
				else 			{return Main.moves.get("Lizard");}
			default:
				return Main.moves.get("Rock");
		}		
	}
	
	private Element randomStart() {
		int random = (int) (Math.random() * 5);
		//Returns a double: 0 >= x < 1
		//Multiples by 5, to get doubles between 0 and 4.99
		//Truncates decimals with casting for ints between 0 and 4
		
		switch(random) {
		
			case 0:
				return Main.moves.get("Rock");
			case 1:
				return Main.moves.get("Paper");
			case 2:
				return Main.moves.get("Scissors");
			case 3:
				return Main.moves.get("Lizard");
			case 4:
				return Main.moves.get("Spock");
		}
		return Main.moves.get("Rock");
	}
}
