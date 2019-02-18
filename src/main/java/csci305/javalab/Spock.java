package csci305.javalab;

public class Spock extends Element{
	
	Spock(){
		super("Spock");
	}

	@Override
	public Outcome compareTo(Element opponent)
	{
		//Houses all responses to moves against Spock
		String hand = opponent.getName();
		
		switch(hand){
		
		case "Rock":
			return new Outcome("Spock vaporizes Rock", "Win");
			
		case "Paper":
			return new Outcome("Paper disproves Spock", "Lose");
			
		case "Scissors":
			return new Outcome("Spock smashes Scissors", "Win");
			
		case "Lizard":
			return new Outcome("Lizard poisons Spock", "Lose");
			
		case "Spock":
			return new Outcome("Spock cannot best Spock", "Tie");
			
		default:
			return new Outcome("ERROR", "TIE");
			
		}
	}
}