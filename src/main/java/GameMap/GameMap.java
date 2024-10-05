package GameMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Room {
    private String name;
    private String description;
    private Map<String, Room> exits; // Exits to other rooms

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
    }

    public void addExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Room> getExits() {
        return exits;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}

public class GameMap {
    private Map<String, Room> rooms;
    private Stack<String> roomHistory;
    private String currentRoom;

    public GameMap() {
        rooms = new HashMap<>();
        roomHistory = new Stack<>();
        initializeRooms();
        currentRoom = "Start"; // Starting room
    }

    public void initializeRooms() {
        
    }
}
