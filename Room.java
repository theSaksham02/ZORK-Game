package org.uob.a1;


public class Room{
    private String name; 
    private String description; 
    private char symbol; 
    private Position postion; 

    public Room(String name, String description, char symbol, Position position){
        this.name = name; 
        this.description = description;
        this.symbol= symbol;
        this.postion= position; 
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
    
    public char getSymbol(){
        return symbol;
    }

    public Position getPosition(){
        return postion;
    }
    
}
