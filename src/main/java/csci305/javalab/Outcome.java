package csci305.javalab;

public class Outcome{
	
	//Text string of both opponent's choices from a battle
	private String battle;
	//Outcome string (win, lose, tie) of player compareTo() method
	//ie: "p1 move".compareTo("p2 move") -> p1 win/lose/tie
	private String outcome;
	
	Outcome(String battle, String outcome){
		this.battle = battle;
		this.outcome = outcome;
	}
	
	public String getBattle() {
		return battle;
	}
	
	public String getOutcome() {
		return outcome;
	}
		
	@Override
	public String toString(){
		
		//returns string when object of Outcome is 
		//requested to be printed out
		//System.out.println(new Outcome("abc","xyz")) --> "abc"
		return battle;
	}
}
