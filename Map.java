package org.uob.a1;
//The map consists of all multiple methods (apart from the default) to run the commands and arguments in game.java
public class Map {
    private char[][] map;  // 2D array for the map grid
    private int width;     // Map width (columns)
    private int height;    // Map height (rows)
    private final char EMPTY = '.';  // Default empty symbol
    private Room[] rooms;  // Array to store the rooms

    public Map(int width, int height) {
        if (width <= 0 || height <= 0) {
            System.out.println("Width and height must be positive. Setting to default values (5x4).");
            this.width = 5; 
            this.height = 4;  
        } else {
            this.width = width;
            this.height = height;
        }

        map = new char[this.width][this.height];  
        rooms = new Room[this.width * this.height];
        SetMap();  
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }



    // Method to initialize the map with empty spaces ('.')
    private void SetMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = EMPTY;  // Set each position on the map to EMPTY
            }
        }
    }

    public void placeRoom(Position pos, char symbol, String roomName, String description) {
        if (isValidPosition(pos)) {
            map[pos.x][pos.y] = symbol;  
            rooms[pos.x + pos.y * width] = new Room(roomName, description, symbol, pos); 
        } else {
            System.out.println("Error: Position out of bounds.");
        }
    }

    // Method to check if a position is within the bounds of the map
    public boolean isValidPosition(Position pos) {
        return pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height;
    }

   
   public String display() {
    StringBuilder mapDisplay = new StringBuilder();
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {  
            mapDisplay.append(map[j][i]);  
        }
        mapDisplay.append("\n");
    }
    return mapDisplay.toString();
}

    // Method to get the room at a specific position on the map
    public Room getRoomAtPosition(Position pos) {
        if (isValidPosition(pos)) {
            return rooms[pos.x + pos.y * width];  // Retrieve the room at the given position
        }
        return null;  // Return null if the position is invalid
    }

    // Method to get all rooms on the map
    public Room[] getRooms() {
        return rooms;  // Return the array of rooms
    }
}
