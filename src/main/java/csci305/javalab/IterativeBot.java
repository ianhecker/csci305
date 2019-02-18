package csci305.javalab; 

public class IterativeBot extends Player{

	private int lastMove;
	
	IterativeBot(){
		super("IterativeBot");
		lastMove = 4;
		//Sets default move to "Rock" 
		//will change 4 ->""Spock to 0 ->"Rock" via play() for first move
	}
	
	@Override
	public Element play() {
		
		//Iterates over all five moves
		//Starts with Rock, always
		if(lastMove < 4) {
			lastMove++;
		}
		else {
			lastMove = 0;
		}
		
		switch(lastMove) {
		
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
	
	@Override
	public void setLastPlay(Element lastPlay) {}
}
