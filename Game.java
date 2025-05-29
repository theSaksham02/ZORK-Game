package org.uob.a1;
import java.util.Scanner;
/*Game requirement -> 10 rooms, 2 puzzles, 4 items, 1 (or more additional feature)
My Game -> 15 rooms, 4 Puzzles, 6 items, 2 Additional Features 
All the elements of the game are explained below. 
*/



//The Main class controls the operations and interaction of the Game with the use
public class Game {
    private static Room currentRoom;  // shows player's current location
    private static Map map;           // Here is the map that consists of all the rooms in game map
    private static Inventory inventory; // Inventory to store all the items user collects while playing  
    private static Score score;       // This gives the score of the player
    private static boolean gameRun = true;  // A flag, when set to true means the game is underway, false when game ends
    private static Scanner sc = new Scanner(System.in);  // Scanner for player input

    // Main metho consist of the game start and manages the main game loop
    public static void main(String[] args) {
        startGame();  // Starts the game 

        System.out.println("Welcome to the Grand Mansion: Your Task is to Escape the Map. The Game is Inspired with Jumanji Movie");
        System.out.println("The rules are simple, find Exit key and hence the Exit room to win the game with maximum points. While in the jouney solve various puzzles and earn score points");
        System.out.println(currentRoom.getDescription());

        // Main Game loop runs untill the gameRun is false, i.e. game over
        while (gameRun) {
            System.out.print(">> ");
            String input = sc.nextLine().toLowerCase();  // Reads player input
            command(input);  // Processes player commands
        }
        
        sc.close();  // Closes the scanner when the game ends
    }
    
    // Initializes the game map, rooms, inventory, and starting score
    private static void startGame() {
        map = new Map(5, 4);
        inventory = new Inventory();
        score = new Score(100); //This is to know the game me start with 100 score and negates points everytime user visits a room, but adds score whenever the user solves a puzzle.
        score.visitRoom();  // Scores how many rooms we have visited 

        // Room creation and placement on the map
        // Each room has a unique description and symbol
        Room entrance = new Room("Entrance", "Welcome to the mansion entrance. MOVE around and you will find hints and keys that will help you find your way out", 'E', new Position(2, 0));
        Room livingroom = new Room("Living Room", "A cozy living room with furniture like TABLE, chair, etc", 'L', new Position(1, 0));
        Room kitchen = new Room("Kitchen", "It smells good; it's the kitchen where you are.", 'K', new Position(1, 2));
        Room bedroom = new Room("Grand Bedroom", "The Grand Bedroom; look around for hints, you might find something of use", 'B', new Position(2, 1));
        Room bathroom = new Room("Bathroom", "You're in the bathroom with a sink. You can check the sink for hints or keys.", 'R', new Position(3, 1));
        Room hallway = new Room("Hallway", "A long hallway connecting rooms", 'H', new Position(1, 1));
        Room library = new Room("Library", "Room filled with books; search the bookshelves, you might find something", 'I', new Position(0, 1)); 
        Room office = new Room("Office", "Personal Office. Look around carefully.", 'O', new Position(3, 0));
        Room garden = new Room("Garden", "A beautiful garden area.", 'G', new Position(3, 2));
        Room exit = new Room("Exit", "You are at the exit", 'X', new Position(2, 3));
        Room dining = new Room("Dining Room", "You are in the dining room; explore places like drawers and cabinets", 'D', new Position(2, 2));
        Room closet = new Room("Closet", "You're in the closet. The FLOORing is marble; worth a SEARCH.", 'C', new Position(4, 1));
        Room backyard = new Room("Backyard", "It's cold. You're outside but still within the mansion boundary. Move around.", 'Y', new Position(3, 3));
        Room treasure1 = new Room("Treasure Room 1", "You've found Treasure Room 1! It's locked; you need a key to enter.", 'T', new Position(0, 3));
        Room treasure2 = new Room("Treasure Room 2", "You've found Treasure Room 2! It's locked; you need a key to enter.", 'T', new Position(4, 3));

        // Place rooms on the map
        map.placeRoom(entrance.getPosition(), entrance.getSymbol(), entrance.getName(), entrance.getDescription());
        map.placeRoom(kitchen.getPosition(), kitchen.getSymbol(), kitchen.getName(), kitchen.getDescription());
        map.placeRoom(livingroom.getPosition(), livingroom.getSymbol(), livingroom.getName(), livingroom.getDescription());
        map.placeRoom(bedroom.getPosition(), bedroom.getSymbol(), bedroom.getName(), bedroom.getDescription());
        map.placeRoom(bathroom.getPosition(), bathroom.getSymbol(), bathroom.getName(), bathroom.getDescription());
        map.placeRoom(hallway.getPosition(), hallway.getSymbol(), hallway.getName(), hallway.getDescription());
        map.placeRoom(library.getPosition(), library.getSymbol(), library.getName(), library.getDescription());
        map.placeRoom(office.getPosition(), office.getSymbol(), office.getName(), office.getDescription());
        map.placeRoom(garden.getPosition(), garden.getSymbol(), garden.getName(), garden.getDescription());
        map.placeRoom(exit.getPosition(), exit.getSymbol(), exit.getName(), exit.getDescription());
        map.placeRoom(dining.getPosition(), dining.getSymbol(), dining.getName(), dining.getDescription());
        map.placeRoom(closet.getPosition(), closet.getSymbol(), closet.getName(), closet.getDescription());
        map.placeRoom(treasure1.getPosition(), treasure1.getSymbol(), treasure1.getName(), treasure1.getDescription());
        map.placeRoom(treasure2.getPosition(), treasure2.getSymbol(), treasure2.getName(), treasure2.getDescription());
        map.placeRoom(backyard.getPosition(), backyard.getSymbol(), backyard.getName(), backyard.getDescription());

        currentRoom = entrance;  // Set starting room as entrance
    }

    // Finds and returns the room at a given position
    private static Room findRoomAtPosition(Position pos) {
        for (Room room : map.getRooms()) {
            if (room != null && room.getPosition().x == pos.x && room.getPosition().y == pos.y) {
                return room;
            }
        }
        return null;
    }

    // Reads and process the command given by the user and provides the optimal output
    private static void command(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];

        switch (command) {
            case "move":
                if (parts.length == 1) {
                    System.out.println("Move where to?\n North\n South\n East\n West\n ");
                    return;
                }
                movePlayer(parts[1]); 
                break;
            case "look":
                if (parts.length == 1) {
                    System.out.println(currentRoom.getDescription());
                } else {
                    lookAt(parts[1]);
                }
                break;
            case "inventory":
                System.out.println("You have: " + inventory.displayInventory());
                break;
            case "score":
                System.out.println("Your current score: " + score.getScore());
                break;
            case "map":
                System.out.println(map.display());
                break;
            case "help":
                displayHelp();
                break;
            case "quit":
                gameRun = false;
                System.out.println("Game over");
                break;
            case "search":
                if (parts.length < 2) {
                    System.out.println("Search what (write the object you want to search)?");
                    return;
                }
                searchObject(parts[1]);
                break;
            default:
                System.out.println("Incorrect command. Use 'help' to know what commands to use.");
                break;
        }
    }

    // The method here helps the player to move around the map and informs them if the rooms are locked or end of the map.
    private static void movePlayer(String direction) {
        Position currentPos = currentRoom.getPosition();
        Position newPos = new Position(currentPos.x, currentPos.y);

        switch (direction) {
            case "north":
                newPos.y--;
                break;
            case "south":
                newPos.y++;
                break;
            case "east":
                newPos.x++;
                break;
            case "west":
                newPos.x--;
                break;
            default:
                System.out.println("Incorrect direction! Please use one of the following: north, south, east, west.");
                return;
        }

        // This is important to know that the Map contains boundaries in all direction, else the player will play in void
        if (newPos.x < 0 || newPos.y < 0 || newPos.x >= map.getWidth() || newPos.y >= map.getHeight()) {
            System.out.println("You can't move in that direction. There is a wall or boundary.");
            return;
        }

        // This checks if the new position has a room, and if the player can enter
        Room newRoom = findRoomAtPosition(newPos);
        if (newRoom != null) {
            // This is speicified if the user is at Exit, and has a key will win the game, else will be given a different prunt statment.
            if (newRoom.getName().equals("Exit")) {
                if (inventory.hasItem("Exit Key") >= 0) {
                    System.out.println("Congratulations! You've unlocked the exit and successfully escaped the mansion!");
                    System.out.println("Final Score: " + score.getScore());
                    gameRun = false;  // There are only 2 ways to end the game, either type 'quit' else finish the game.
                } else {
                    System.out.println("The exit is locked. You need the Exit Key to escape.");
                }
                return;
            }

            // Check if other rooms are locked
            if (isRoomLocked(newRoom)) {  
                System.out.println("The " + newRoom.getName() + " is locked. You need a key to enter.");
                return;
            }

            // Move to the new room if unlocked
            currentRoom = newRoom;
            score.visitRoom();
            System.out.println(currentRoom.getDescription());
        } else {
            System.out.println("No room in this direction. You hit a wall or boundary.");
        }
    }

    // This method helps us to use 'look' commands and survey around some rooms with objects. Remember not all rooms have itmes and objects, some does as mentioned below
    private static void lookAt(String target) {
        String targetLower = target.toLowerCase();  
        String roomName = currentRoom.getName(); 
        
        switch (roomName) {
            case "Living Room":
                if (targetLower.equals("table")) {
                    System.out.println("The center table holds a few scattered items. Something here might be important.");
                    return;
                }
                break;
            case "Garden":
                if (targetLower.equals("bench")) {
                    System.out.println("Bench seems odd, LOOK around it.");
                    return;
                }
                break;
            case "Dining Room":
                if (targetLower.equals("drawers")) {
                    System.out.println("The drawers have some cutlery inside of it. There might be something inside.");
                    return;
                }
                break;
            case "Bathroom":
                if (targetLower.equals("sink")) {
                    System.out.println("The sink looks wet, but something seems to be hidden near it.");
                    return;
                }
                break;
            case "Office":
                if (targetLower.equals("desk")) {
                    System.out.println("The desk is messy. LOOK carefully—there could be a useful hint.");
                    return;
                }
                break;
            case "Closet":
                if (targetLower.equals("floor")) {
                    System.out.println("The floor has litter, yet it might have something.");
                    return;
                }
                break;
            case "Library":
                System.out.println("The room is locked.");
                break;
            case "Treasure Room 1":
            case "Treasure Room 2":
            case "Exit":
                System.out.println("This room is locked. You need a key to enter.");
                return;
            default:
                System.out.println("Nothing special about this place.");
                return;
        }
        System.out.println("Nothing special about this place.");
    } 

    //Additional Feature - >As asked we can have a Additional Feature in our game, here it is. Searching an object for item is something that is user engaging. So now Enter room --> look object --> search object --> finds items --? else return statement
    private static void searchObject(String item) {
        String itemLower = item.toLowerCase(); //to avoid upper cases if typed by the user
        String roomName = currentRoom.getName(); 

        switch (roomName) {
            case "Living Room":
                if (itemLower.equals("table")) {
                    if (inventory.hasItem("Library Key") == -1) {
                        System.out.println("You found the Library Key on the table!");
                        inventory.addItem("Library Key");
                    } else {
                        System.out.println("You've already picked up the Library Key.");
                    }
                    return;
                }
                break;
            case "Library":
                if (itemLower.equals("bookshelf")) {
                    if (inventory.hasItem("Exit Key") == -1) {
                        System.out.println("You found the Exit Key hidden in the bookshelf!");
                        inventory.addItem("Exit Key");
                    } else {
                        System.out.println("You've already picked up the Exit Key.");
                    }
                    return;
                }
                break;
            case "Closet":
                if (itemLower.equals("floor")) {
                    if (inventory.hasItem("Hint Piece 1") == -1) {
                        System.out.println("You found a hint piece on the floor.");
                        System.out.println("Hint --> Spoiler: The Treasure rooms are at the bottom of the map.");
                        inventory.addItem("Hint Piece 1");
                    } else {
                        System.out.println("You've already picked up this hint.");
                    }
                    return;
                }
                break;
            case "Office":
                if (itemLower.equals("desk")) {
                    if (inventory.hasItem("Hint Piece 2") == -1) {
                        System.out.println("You found another hint piece on the desk!");
                        System.out.println("Hint --> Spoiler: The key to the Treasure Room 1 is in the Dining Room. Find the next");
                        inventory.addItem("Hint Piece 2");
                    } else {
                        System.out.println("You've already picked up this hint.");
                    }
                    return;
                }
                break;
            case "Dining Room":
                if (itemLower.equals("drawers")) {
                    if (inventory.hasItem("Treasure Key 1") == -1) {
                        System.out.println("You found the Treasure Key 1 in the drawers.");
                        inventory.addItem("Treasure Key 1");
                    } else {
                        System.out.println("You've already picked up the Treasure Key 1.");
                    }
                    return;
                }
                break;
            case "Garden":
                if (itemLower.equals("bench")) {
                    if (inventory.hasItem("Treasure Key 2") == -1) {
                        System.out.println("You found the Treasure Key 2 under the bench.");
                        inventory.addItem("Treasure Key 2");
                    } else {
                        System.out.println("You've already picked up the Treasure Key 2.");
                    }
                    return;
                }
                break;
            default:
                System.out.println("Nothing interesting here.");
                return;
        }
        System.out.println("Nothing interesting here.");
    } 

    // With 4 keys in the game (Treasure 1 and 2, Exit and Library key, we have roomlocked method, with ensures we can only enter the room with we have the specific key in the inventory to enter the room.
    private static boolean isRoomLocked(Room room) {
        String roomName = room.getName();
        if (roomName.equals("Library") && inventory.hasItem("Library Key") == -1) {
            return true;
        }
        if (roomName.equals("Exit") && inventory.hasItem("Exit Key") == -1) {
            return true;
        }
        if (roomName.equals("Treasure Room 1") && inventory.hasItem("Treasure Key 1") == -1) {
            return true;
        }
        if (roomName.equals("Treasure Room 2") && inventory.hasItem("Treasure Key 2") == -1) {
            return true;
        }
        return false;
    }

    // Help command prints out all 
    private static void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("move (direction) - Move to a new room based on the direction (north/south/east/west).");
        System.out.println("look - Displays a description of the room.");
        System.out.println("look <object> - See specific details about an object (e.g., table, sink, floor, desk).");
        System.out.println("search <object> - Search an object for hidden items (e.g., search table, sink, floor, desk).");
        System.out.println("inventory - Shows all items you have collected.");
        System.out.println("score - Displays your current score.");
        System.out.println("map - Shows the current explored game map.");
        System.out.println("help - Displays this help message.");
        System.out.println("quit - Exits the game.");
    }
}
