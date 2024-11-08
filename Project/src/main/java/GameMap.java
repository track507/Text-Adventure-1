import java.util.*;

import javax.swing.text.Position;

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
        // Left path leads to boat ride and explore cave, east goes back to start
        addRoom("Acrius", "Left Path", "Boat Ride", null, "Start", "Explore Cave");
        addRoom("Acrius", "Boat Ride", "Dark Cavern", "Left Path", null, "Hidden Temple");
        addRoom("Acrius", "Explore Cave", null, null, "Left Path", null);
        addRoom("Acrius", "Hidden Temple", null, null, "Boat Ride", "Hidden Passageway");
        addRoom("Acrius", "Hidden Passageway", "Faint Tunnel", null, "Hidden Temple", "Staircase Into Darkness");
        addRoom("Acrius", "Staircase Into Darkness", null, "Hidden Passageway", null, "Iron Door");
        addRoom("Acrius", "Iron Door", null, null, "Staircase Into Darkness", "Dark Room");
        addRoom("Acrius", "Dark Room", null, null, "Iron Door", null);
        
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
                return 9;
            case "Diddy":
            case "Lanky":
                return 7;
            default:
                return 3;
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
        if (!player.hasItem("map")) {
            System.out.println("You don't have a map.");
            return;
        }
        System.out.println("Current location: " + currentWorld + " - " + currentRoom);

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
                System.out.print("[" + mapDisplay[i][j] + "]");
            }
            System.out.println();
        }

        // Print connected rooms
        System.out.println("Connected rooms:");
        currentRoomObj.printConnections();
    }

    public boolean moveTo(String direction) {
        String nextRoom = getConnectedRoom(direction);
        if (nextRoom == null || nextRoom.isEmpty()) {
            System.out.println("There is no room in that direction.");
            return false;
        } else {
            updatePlayerPosition(direction);
            setLocation(currentWorld, nextRoom);
            // System.out.println("You move " + direction + " to " + nextRoom + ".");
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
                System.out.println("North: " + north);
            if (south != null)
                System.out.println("South: " + south);
            if (east != null)
                System.out.println("East: " + east);
            if (west != null)
                System.out.println("West: " + west);
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