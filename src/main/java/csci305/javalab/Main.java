package csci305.javalab;

import java.util.HashMap;
import java.util.Map;


public class Main {
	
	//Hash tables of concrete instances to be reused over games
	public static final Map<String, Element> moves = new HashMap<String, Element>();
	public static final Map<String, Player> bots = new HashMap<String, Player>();
	
	public static void main(String args[]) {	

		//Add all Elements to list to make concrete instances
		moves.put("Rock", new Rock());
		moves.put("Paper", new Paper());
		moves.put("Scissors", new Scissors());
		moves.put("Spock", new Spock());
		moves.put("Lizard", new Lizard());
		
		//Add all bots to list to make concrete instances
		bots.put("StupidBot", new StupidBot());
		bots.put("RandomBot", new RandomBot());
		bots.put("IterativeBot", new IterativeBot());
		bots.put("LastPlayBot", new LastPlayBot());
		bots.put("HumanPlayer", new HumanPlayer());
		bots.put("MyBot", new MyBot());
				
		//Start new game
		//Game class handles all dueling logic and printouts
		Game g1 = new Game();		
	}	
}//End of Main class
