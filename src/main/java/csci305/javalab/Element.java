package csci305.javalab;

public abstract class Element
{
	//rock, paper, scissors, lizard, or spock
    private String name;    
    
    Element(String name){
    	this.name = name;}
    
    public String getName(){
    	return name;}
    
    //To be overriden by subclasses
    public abstract Outcome compareTo(Element other);  
}

