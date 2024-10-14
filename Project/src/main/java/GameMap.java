import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private String currentWorld;
    private String currentRoom;
    private Map<String, Map<String, String[]>> worldMaps;

    public GameMap() {
        worldMaps = new HashMap<>();
    }

    public void addRoom(String world, String room, String north, String south, String east, String west) {
        if (!worldMaps.containsKey(world)) {
            worldMaps.put(world, new HashMap<>());
        }
        worldMaps.get(world).put(room, new String[]{north, south, east, west});
    }

    public void setLocation(String world, String room) {
        if (worldMaps.containsKey(world) && worldMaps.get(world).containsKey(room)) {
            currentWorld = world;
            currentRoom = room;
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
        String[] connections = worldMaps.get(currentWorld).get(currentRoom);
        switch (direction.toLowerCase()) {
            case "north": return connections[0];
            case "south": return connections[1];
            case "east": return connections[2];
            case "west": return connections[3];
            default: return null;
        }
    }

    public void displayMap() {
        System.out.println("Current location: " + currentWorld + " - " + currentRoom);
        System.out.println("Connected rooms:");
        String[] directions = {"North", "South", "East", "West"};
        String[] connections = worldMaps.get(currentWorld).get(currentRoom);
        for (int i = 0; i < 4; i++) {
            if (connections[i] != null) {
                System.out.println(directions[i] + ": " + connections[i]);
            }
        }
    }
}