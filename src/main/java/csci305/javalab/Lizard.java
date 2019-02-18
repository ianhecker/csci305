package csci305.javalab;

public class Lizard extends Element{
	
	Lizard(){
		super("Lizard");
	}
	
	@Override
	public Outcome compareTo(Element opponent)
	{
		//Houses all responses to moves against Lizard
		String hand = opponent.getName();
		
		switch(hand){
		
		case "Rock":
			return new Outcome("Rock smashes Lizard", "Lose");
			
		case "Paper":
			return new Outcome("Lizard eats Paper", "Win");
			
		case "Scissors":
			return new Outcome("Scissors decapitates Lizard", "Lose");
			
		case "Lizard":
			return new Outcome("Lizard befriends Lizard", "Tie");
			
		case "Spock":
			return new Outcome("Lizard poisons Spock", "Win");
			
		default:
			return new Outcome("ERROR", "TIE");
		}
	}
}