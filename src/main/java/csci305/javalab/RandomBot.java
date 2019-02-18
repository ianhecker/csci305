package csci305.javalab;

public class RandomBot extends Player{

	RandomBot(){
		super("RandomBot");
	}
	
	@Override
	public Element play() {
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
	
	@Override
	public void setLastPlay(Element lastPlay) {
		
	}
}
