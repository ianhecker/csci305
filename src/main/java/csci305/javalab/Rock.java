package csci305.javalab;

public class Rock extends Element{
	
	Rock(){
		super("Rock");
	}
	
	@Override
	public Outcome compareTo(Element opponent)
	{
		
		//Houses all responses to moves against Rock
		String hand = opponent.getName();
		
		switch(hand){
		
		case "Rock":
			return new Outcome("Rock ties Rock", "Tie");
			
		case "Paper":
			return new Outcome("Paper covers Rock", "Lose");
			
		case "Scissors":
			return new Outcome("Rock crushes Scissors", "Win");
			
		case "Lizard":
			return new Outcome("Rock smashes Lizard", "Win");
			
		case "Spock":
			return new Outcome("Spock vaporizes Rock", "Lose");
			
		default:
			return new Outcome("ERROR", "TIE");
		}
	}
}














