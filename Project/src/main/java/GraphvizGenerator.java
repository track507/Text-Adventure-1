import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
// Made by Terrence

public class GraphvizGenerator {
    private final Map<String, Map<String, Room>> worldMaps;

    public GraphvizGenerator() {
        worldMaps = new HashMap<>();
        initializeRooms();
    }

    public void generateGraphviz(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("digraph G {\n");
            writer.write("    rankdir=LR;\n");
            writer.write("    node [shape=rectangle];\n");

            for (String world : worldMaps.keySet()) {
                writer.write("    subgraph cluster_" + world.replaceAll("\\s", "_") + " {\n");
                writer.write("        label=\"" + world + "\";\n");

                Map<String, Room> rooms = worldMaps.get(world);
                for (Map.Entry<String, Room> roomEntry : rooms.entrySet()) {
                    String roomName = roomEntry.getKey();
                    Room room = roomEntry.getValue();

                    // Write edges for connections
                    writeEdge(writer, roomName, room.north, "North");
                    writeEdge(writer, roomName, room.south, "South");
                    writeEdge(writer, roomName, room.east, "East");
                    writeEdge(writer, roomName, room.west, "West");
                }
                writer.write("    }\n");
            }
            writer.write("}\n");
        }
    }

    private void writeEdge(FileWriter writer, String from, String to, String direction) throws IOException {
        if (to != null) {
            writer.write("        \"" + from + "\" -> \"" + to + "\" [label=\"" + direction + "\"];\n");
        }
    }

    private void initializeRooms() {
        // World, Room, North, South, East, West
        this.addRoom("Start", "Start", "Straight Path", null, "Right Path", "Left Path");
        this.addRoom("Start", "Left Path", null, null, "Start", null);
        this.addRoom("Start", "Right Path", null, null, null, "Start");
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
        this.addRoom("Acrius", "Dark Room", "Frozen Abyss", null, "Iron Door", null);
        this.addRoom("Acrius", "Frozen Abyss", "Icy Chamber", "Dark Room", "Frozen Shrine", null);
        this.addRoom("Acrius", "Frozen Shrine", null, null, null, "Frozen Abyss");
        this.addRoom("Acrius", "Icy Chamber", null, "Frozen Abyss", null, null);
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

    private void addRoom(String world, String roomName, String north, String south, String east, String west) {
        worldMaps.computeIfAbsent(world, k -> new HashMap<>())
                 .put(roomName, new Room(roomName, north, south, east, west));
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
    }

    public static void main(String[] args) {
        GraphvizGenerator generator = new GraphvizGenerator();
        try {
            generator.generateGraphviz("game_map.dot");
            System.out.println("Graphviz file generated: game_map.dot");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
