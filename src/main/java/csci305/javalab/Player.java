package csci305.javalab;

public abstract class Player {
	
	private String name;
	
	Player(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract Element play();
	
	public abstract void setLastPlay(Element lastPlay);
}
