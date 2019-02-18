package csci305.javalab;

public class Paper extends Element{
	
	Paper(){
		super("Paper");
	}
	
	@Override
	public Outcome compareTo(Element opponent)
	{
		
		//Houses all responses to moves against Paper
		String hand = opponent.getName();
		
		switch(hand){
		
		case "Rock":
			return new Outcome("Paper covers Rock", "Win");
			
		case "Paper":
			return new Outcome("Paper ties Paper", "Tie");
			
		case "Scissors":
			return new Outcome("Scissors cuts Paper", "Lose");
			
		case "Lizard":
			return new Outcome("Lizard eats Paper", "Lose");
			
		case "Spock":
			return new Outcome("Paper disproves Spock", "Win");
			
		default:
			return new Outcome("ERROR", "TIE");
		}
	}
}