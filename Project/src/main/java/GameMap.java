import java.util.*;
import javax.swing.text.Position;

// Map made by Terrence

public class GameMap {
    private static Player player = StartAdventure.player;
    private String currentWorld;
    private String currentRoom;
    private Map<String, Map<String, Room>> worldMaps;
    private Map<String, char[][]> mapDisplays;
    private Map<String, Position> playerPositions;

    public GameMap() {
        worldMaps = new HashMap<>();
        mapDisplays = new HashMap<>();
        playerPositions = new HashMap<>();
        initializeRooms();
    }

    public void initializeRooms() {
        // World, Room, North, South, East, West in this order.
        // When you first add a room, you must also add each of the rooms it connects too.
        // You can see the connecting rooms values to null so that you can change them later.
        // e.g.
        /*
         * Add start world with start room with connected rooms.
         * this.addRoom("Start", "Start", "Straight Path", null, "Right Path", "Left Path");
         ! Set each rooms values to null except for the room they lead back to.
         * this.addRoom("Start", "Left Path", null, null, "Start", null);
         * this.addRoom("Start", "Right Path", null, null, null, "Start");
         * this.addRoom("Diddy", "Straight Path", null, "Start", null, null);
         ! Then change them later on when you make the methods to move between rooms.
         */
        this.addRoom("Start", "Start", "Straight Path", null, "Right Path", "Left Path");
        this.addRoom("Start", "Left Path", null, null, "Start", null);
        this.addRoom("Start", "Right Path", null, null, null, "Start");
        this.setLocation("Start", "Start");
        // Left path leads to boat ride and explore cave, east goes back to start
        this.addRoom("Acrius", "Left Path", "Boat Ride", null, "Start", "Explore Cave");
        this.addRoom("Acrius", "Boat Ride", "Faint Tunnel", "Left Path", null, "Hidden Temple");
        this.addRoom("Acrius", "Faint Tunnel", null, "Boat Ride", "Mysterious Cavern", null);
        this.addRoom("Acrius", "Mysterious Cavern", null, "Crystal Hollow", null, "Faint Tunnel");
        this.addRoom("Acrius", "Crystal Hollow", "Mysterious Cavern", null, null, null);
        this.addRoom("Acrius", "Explore Cave", null, null, "Left Path", null);
        this.addRoom("Acrius", "Hidden Temple", null, null, "Boat Ride", "Hidden Passageway");
        this.addRoom("Acrius", "Hidden Passageway", "Dark Cavern", null, "Hidden Temple", "Staircase Into Darkness");
        this.addRoom("Acrius", "Staircase Into Darkness", null, null , "Hidden Passageway", "Iron Door");
        this.addRoom("Acrius", "Iron Door", null, null, "Staircase Into Darkness", "Dark Room");
        this.addRoom("Acrius", "Dark Room", null, null, "Iron Door", null);
        this.addRoom("Acrius", "Dark Cavern", "Northern Depths", "Hidden Passageway", null, "Glowing Passage");
        this.addRoom("Acrius", "Glowing Passage", null, "Unlocked Passageway", "Dark Cavern", null);
        this.addRoom("Acrius", "Northern Depths", null, "Dark Cavern", "Unknown Pathway", "Whispering Crevice");
        this.addRoom("Acrius", "Whispering Crevice", null, null, "Northern Depths", null);
        this.addRoom("Acrius", "Unknown Pathway", null, null, null, "Northern Depths");
        this.addRoom("Acrius", "Unlocked Passageway", "Glowing Passage", "Sloping Darkness", "Flowing Water Room", "Rumbling Chamber");
        this.addRoom("Acrius", "Flowing Water Room", "Crystal Pool", null, "Mystic Cavern", "Unlocked Passageway");
        this.addRoom("Acrius", "Crystal Pool", null, "Flowing Water Room", null, null);
        this.addRoom("Acrius", "Mystic Cavern", null, null, null, "Flowing Water Room");
        this.addRoom("Acrius", "Sloping Darkness", "Unlocked Passageway", null, "Luminous Chamber", "Shadowed Alcove");
        this.addRoom("Acrius", "Shadowed Alcove", null, null, "Sloping Darkness", null);
        this.addRoom("Acrius", "Luminous Chamber", null, null, null, "Sloping Darkness");
        this.addRoom("Acrius", "Rumbling Chamber", "Echoing Hall", "Collapsed Tunnel", "Unlocked Passageway", null);
        this.addRoom("Acrius", "Echoing Hall", null, "Rumbling Chamber", null, null);
        this.addRoom("Acrius", "Collapsed Tunnel", "Rumbling Chamber", null, null, null);
  
        // Chibuikems world
        this.addRoom("Diddy", "Straight Path", null, "Start", null, null);
    }

    public void addRoom(String world, String roomName, String north, String south, String east, String west) {
        worldMaps.computeIfAbsent(world, k -> new HashMap<>())
                .put(roomName, new Room(roomName, north, south, east, west));

        initializeMapDisplay(world);
    }

    private void initializeMapDisplay(String world) {
        if (!mapDisplays.containsKey(world)) {
            int size = getWorldSize(world);
            char[][] display = new char[size][size];
            for (char[] row : display) {
                Arrays.fill(row, ' ');
            }
            mapDisplays.put(world, display);

            int center = size / 2;
            playerPositions.put(world, new Position(center, center));
        }
    }

    private int getWorldSize(String world) {
        switch (world) {
            case "Acrius":
                return 11;
            case "Diddy":
            case "Lanky":
                return 9;
            default:
                return 7;
        }
    }

    public void setLocation(String world, String room) {
        if (worldMaps.containsKey(world) && worldMaps.get(world).containsKey(room)) {
            currentWorld = world;
            currentRoom = room;
            // System.out.println("You are now in " + world + " - " + room);
        } else {
            throw new IllegalArgumentException("Invalid world or room");
        }
    }

    public String getCurrentWorld() {
        return currentWorld;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public Room getRoom(String world, String roomName) {
        if (worldMaps.containsKey(world) && worldMaps.get(world).containsKey(roomName)) {
            return worldMaps.get(world).get(roomName);
        }
        throw new IllegalArgumentException("Room not found in the specified world");
    }  

    public String getConnectedRoom(String direction) {
        Room room = worldMaps.get(currentWorld).get(currentRoom);
        // System.out.println("Current world: " + currentWorld);
        // System.out.println("Direction: " + direction);
        // System.out.println("Current room: " + currentRoom);
        // System.out.println(room);
        // System.out.println(room.getConnection(direction));
        // System.out.println();
        return room.getConnection(direction);
    }

    public String displayMapAsString() {
        if (!player.hasItem("map")) {
            return "You don't have a map.";
        }

        StringBuilder output = new StringBuilder();
        output.append("Current location: ").append(currentWorld).append(" - ").append(currentRoom).append("\n");

        char[][] mapDisplay = mapDisplays.get(currentWorld);
        int size = mapDisplay.length;
        Position playerPos = playerPositions.get(currentWorld);

        // Reset the map
        for (char[] row : mapDisplay) {
            Arrays.fill(row, ' ');
        }

        // Set the current room and connections
        mapDisplay[playerPos.y][playerPos.x] = 'P';
        Room currentRoomObj = worldMaps.get(currentWorld).get(currentRoom);
        if (currentRoomObj.north != null)
            mapDisplay[playerPos.y - 1][playerPos.x] = '*';
        if (currentRoomObj.south != null)
            mapDisplay[playerPos.y + 1][playerPos.x] = '*';
        if (currentRoomObj.east != null)
            mapDisplay[playerPos.y][playerPos.x + 1] = '*';
        if (currentRoomObj.west != null)
            mapDisplay[playerPos.y][playerPos.x - 1] = '*';

        // Build the map output
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                output.append("[").append(mapDisplay[i][j]).append("]");
            }
            output.append("\n");
        }

        // Print connected rooms
        output.append("Connected rooms:\n");
        currentRoomObj.printConnections(); // Assuming this still prints to console

        return output.toString();
    }

    public void displayMap() {
        // if (!player.hasItem("map")) {
        //     TextEngine.pt(Handler.applyStyle("You don't have a map.", "d", "red"));
        //     return;
        // }
        TextEngine.pt(Handler.applyStyle("Current location: " + currentWorld + " - " + currentRoom, "d"));

        char[][] mapDisplay = mapDisplays.get(currentWorld);
        int size = mapDisplay.length;
        Position playerPos = playerPositions.get(currentWorld);

        // Reset the map
        for (char[] row : mapDisplay) {
            Arrays.fill(row, ' ');
        }

        // Set the current room and connections
        mapDisplay[playerPos.y][playerPos.x] = 'P';
        Room currentRoomObj = worldMaps.get(currentWorld).get(currentRoom);
        if (currentRoomObj.north != null)
            mapDisplay[playerPos.y - 1][playerPos.x] = '*';
        if (currentRoomObj.south != null)
            mapDisplay[playerPos.y + 1][playerPos.x] = '*';
        if (currentRoomObj.east != null)
            mapDisplay[playerPos.y][playerPos.x + 1] = '*';
        if (currentRoomObj.west != null)
            mapDisplay[playerPos.y][playerPos.x - 1] = '*';

        // Print the map
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                TextEngine.pt(Handler.applyStyle("[" + mapDisplay[i][j] + "]", "d"), 1, false);
                //System.out.print(Handler.applyStyle("[" + mapDisplay[i][j] + "]", "d"));
            }
            System.out.println();
        }

        // Print connected rooms
        TextEngine.pt(Handler.applyStyle("Connected rooms:", "i", "darkgrey"));
        currentRoomObj.printConnections();
    }

    public boolean moveTo(String direction) {
        if (player.isDead()) {
            TextEngine.pt(Handler.applyStyle("You cannot move because you are dead.", "b", "red"));
            return false;
        }
    
        String nextRoom = getConnectedRoom(direction);
        if (nextRoom == null || nextRoom.isEmpty()) {
            TextEngine.pt(Handler.applyStyle("There is no room in that direction.", "i", "darkgrey"));
            return false;
        } else {
            updatePlayerPosition(direction);
            setLocation(currentWorld, nextRoom);
            return true;
        }
    }    

    private void updatePlayerPosition(String direction) {
        Position pos = playerPositions.get(currentWorld);
        switch (direction.toLowerCase()) {
            case "north":
                pos.y = Math.max(0, pos.y - 1);
                break;
            case "south":
                pos.y = Math.min(mapDisplays.get(currentWorld).length - 1, pos.y + 1);
                break;
            case "east":
                pos.x = Math.min(mapDisplays.get(currentWorld)[0].length - 1, pos.x + 1);
                break;
            case "west":
                pos.x = Math.max(0, pos.x - 1);
                break;
        }
    }

    private static class Room {
        String name;
        String north, south, east, west;

        Room(String name, String north, String south, String east, String west) {
            this.name = name;
            this.north = north;
            this.south = south;
            this.east = east;
            this.west = west;
        }

        String getConnection(String direction) {
            switch (direction.toLowerCase()) {
                case "north":
                    return north;
                case "south":
                    return south;
                case "east":
                    return east;
                case "west":
                    return west;
                default:
                    return null;
            }
        }

        void printConnections() {
            if (north != null)
                TextEngine.pt(Handler.applyStyle("North: ", "i", "darkgrey") + Handler.applyStyle(north, "b", "yellow"));
            if (south != null)
                TextEngine.pt(Handler.applyStyle("South: ", "i", "darkgrey") + Handler.applyStyle(south, "b", "yellow"));
            if (east != null)
                TextEngine.pt(Handler.applyStyle("East:  ", "i", "darkgrey") + Handler.applyStyle(east, "b", "yellow"));
            if (west != null)
                TextEngine.pt(Handler.applyStyle("West:  ", "i", "darkgrey") + Handler.applyStyle(west, "b", "yellow"));
        }
    }

    private static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}