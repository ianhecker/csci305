package csci305.javalab; 

public class StupidBot extends Player {

	StupidBot(){
		super("StupidBot");
	}
	
	@Override
	public Element play() {
		
		//Always returns Rock
		//Hence, StupidBot..
		return Main.moves.get("Rock");
	}
	
	@Override
	public void setLastPlay(Element lastPlay) {}
}
