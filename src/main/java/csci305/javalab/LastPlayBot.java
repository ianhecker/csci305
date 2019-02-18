package csci305.javalab;

public class LastPlayBot extends Player{

	private Element lastPlay;
	
	LastPlayBot(){
		super("LastPlayBot");
		lastPlay = Main.moves.get("Rock");
	}
	
	@Override
	public Element play() {
		return lastPlay;
	}
	
	@Override
	public void setLastPlay(Element lastPlay) {
		
		//Updates opponent's last move as this bot's next move
		//Is called during Game class instance after dueling
		this.lastPlay = lastPlay;
	}		
}
