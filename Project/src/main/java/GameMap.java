import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private Room currentRoom;

    public GameMap() {
        initializeRooms();
    }

    private void initializeRooms() {
        // Create rooms
        Room cavern = new Room("You are in a dimly lit cavern.");
        Room intersection = new Room("You find yourself at an intersection.");
        Room leftPath = new Room("You are on the left path, surrounded by dense trees.");
        Room rightPath = new Room("You are on the right path, filled with old mining equipment.");
        Room straightPath = new Room("You are walking straight ahead in a dense forest.");

        // Connect rooms
        cavern.setNorth(intersection);
        intersection.setSouth(cavern);
        intersection.setEast(leftPath);
        intersection.setWest(rightPath);
        intersection.setNorth(straightPath);

        // Set the starting room
        currentRoom = cavern;
    }

    public void move(String direction) {
        Room nextRoom = null;

        switch (direction.toLowerCase()) {
            case "north":
                nextRoom = currentRoom.getNorth();
                break;
            case "south":
                nextRoom = currentRoom.getSouth();
                break;
            case "east":
                nextRoom = currentRoom.getEast();
                break;
            case "west":
                nextRoom = currentRoom.getWest();
                break;
            default:
                System.out.println("Invalid direction. Please choose north, south, east, or west.");
                return;
        }

        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You move " + direction + ": " + currentRoom.getDescription());
        } else {
            System.out.println("You can't go that way.");
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}


class Room {
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room(String description) {
        this.description = description;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public String getDescription() {
        return description;
    }
}