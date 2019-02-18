package csci305.javalab;

public class Scissors extends Element{
	
	Scissors(){
		super("Scissors");
	}
	
	@Override
	public Outcome compareTo(Element opponent)
	{
		//Houses all responses to moves against Scissors
		String hand = opponent.getName();
		
		switch(hand){
		
		case "Rock":
			return new Outcome("Rock crushes Scissors", "Lose");
			
		case "Paper":
			return new Outcome("Scissors cuts Paper", "Win");
			
		case "Scissors":
			return new Outcome("Scissors ties Scissors", "Tie");
			
		case "Lizard":
			return new Outcome("Scissors decapitates Lizard", "Win");
			
		case "Spock":
			return new Outcome("Spock smashes Scissors", "Lose");
			
		default:
			return new Outcome("ERROR", "TIE");
			
		}
	}
}