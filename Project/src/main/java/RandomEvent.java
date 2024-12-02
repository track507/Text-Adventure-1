// chibuikem would try to make this class work
//idea is random event that happens randomly throughout the game
// In RandomEvent.java

import java.util.Random;
import java.util.Scanner;

public class RandomEvent {
    // Assuming a method to generate a random event based on the world and room
    public String generateRandomEvent(String currentWorld, String currentRoom) {
        // Example implementation: You could have some predefined events
        String[] events = {
                "Event 1: You find a treasure!",
                "Event 2: A monster attacks!",
                "Event 3: A storm is coming!" };

        // Select a random event (this is just a placeholder)
        Random rand = new Random();
        return events[rand.nextInt(events.length)];
    }
}
