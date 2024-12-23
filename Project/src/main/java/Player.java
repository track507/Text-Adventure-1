import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
// Player made by Terrence
// Modified by Chibuikem

public class Player {
    private List<String> inventory;
    private Map<String, String> locations = new HashMap<>();
    private int health = 100;
    private int hunger = 100;
    private int gameMinutesElapsed = 0; // Tracks total in-game minutes since start
    private final int startingHour = 8; // Start at 8:00 AM
    private ScheduledExecutorService scheduler;

    // Add a Skills field to the Player class
    private Skills skills;

    // booleans for certain rooms
    public boolean hasSearchedCave = false;
    public boolean hasSearchedHiddenTemple = false;
    public boolean hasSearchedStairCaseIntoDarkness = false;

    // Constructor initializes inventory and skills
    public Player() {
        inventory = new ArrayList<>();
        skills = new Skills(); // Initialize skills here
        startTrackingTime();
    }

    // Accessor method for skills
    public Skills getSkills() {
        return skills;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // examines an item to the player's inventory.
    public void addItem(String item) {
        if(item == null) return;
        inventory.add(item);
        TextEngine.pt(Handler.applyStyle("You have picked up: ", "i", "darkgrey") + Handler.applyStyle(item, "b", "yellow"));
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public String getHealthStatus() {
        if (isDead()) {
            return "You have died. Game over!";
        } else {
            return "Your health is now: " + health;
        }
    }

    // Get the current hunger level.
    public int getHunger() {
        return hunger;
    }

    // Reduce hunger by a specific amount (simulating hunger over time).
    public void reduceHunger(int amount) {
        hunger = Math.max(0, hunger - amount);
        TextEngine.pt(Handler.applyStyle("Your hunger has decreased by " + amount + ". Your hunger is now: " + hunger + ".", "i", "darkgrey")); 
        if (isStarving()) {
            takeDamage(10); // Player takes damage when starving
            TextEngine.pt(Handler.applyStyle("You are starving and took 10 damage.", "b", "red"));
        }
    }

    // Method to start tracking in-game time
    private void startTrackingTime() {
        scheduler = Executors.newScheduledThreadPool(1); // Single-threaded executor for scheduling
        scheduler.scheduleAtFixedRate(() -> {
            gameMinutesElapsed += 5; // Increment in-game time by 5 minutes every 10 seconds
        }, 0, 10, TimeUnit.SECONDS); // Runs every 10 seconds
    }

    // Method to stop tracking time
    private void stopTrackingTime() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    // Method to get the current in-game time
    public void getCurrentTime() {
        int totalMinutes = gameMinutesElapsed;
        int hours = (startingHour + totalMinutes / 60) % 24; // Adjust for 24-hour format
        int minutes = totalMinutes % 60;
        TextEngine.pt(Handler.applyStyle("You check your watch.\n", "i", "darkgrey") + "Current time: " + Handler.applyStyle(String.format("%02d:%02d", hours, minutes), "b", "magenta"), 10);
    }

    // Method to get the total elapsed time in the game
    public void getElapsedTime() {
        int hours = gameMinutesElapsed / 60;
        int minutes = gameMinutesElapsed % 60;
        TextEngine.pt("Elapsed in-game time: " + Handler.applyStyle(String.format("%02dh:%02dm", hours, minutes), "b", "magenta"));
    }

    public void getHelp() {
        String[] text = {
            "use <item> - Use an item in your inventory.",
            "   example: \"use food\", \"use medkit\", \"use healing potion\"",
            "time - Check the current in-game time.",
            "inventory - View your inventory.",
            "map - View the game map (connected rooms).",
            "help - Display this help message.",
        };
        TextEngine.pt(Handler.applyStyle("\nAvailable Commands:\n", "b", "yellow") + text, 5);
    }
    
    // Increase hunger when eating food (but not beyond the max of 100).
    public void eatFood() {
        hunger = Math.min(hunger + 20, 100);
        TextEngine.pt(Handler.applyStyle("You ate some food. Your hunger is now: " + hunger, "i", "darkgrey"));
    }

    // Check if the player is starving (hunger at 0).
    private boolean isStarving() {
        return hunger == 0;
    }

    // Get the hunger status message.
    public String getHungerStatus() {
        if (isStarving()) {
            return "You are starving! Eat something before it's too late.";
        } else if (hunger < 30) {
            return "You are getting hungry.";
        } else {
            return "You are well-fed.";
        }
    }

    private int extractValueFromItem(String item, String type) {
        // Match a pattern like "healing potion +70"
        String pattern = "(?i)" + type + " \\+(\\d+)";
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(pattern).matcher(item);
    
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)); // Extract the numeric value
        }
    
        // Default value if no numeric value is found
        return type.equalsIgnoreCase("healing potion") ? 20 : 10;
    }

    public boolean parseItem(String item) {
        // Check for specific key items first
        if (item.equalsIgnoreCase("ancient key") || item.equalsIgnoreCase("glowing stone")) {
            return true; // Keys are not consumed after use
        }
        // Match patterns for healing items
        else if (item.matches("(?i).*healing potion.*|.*health potion.*")) { // Case-insensitive match
            int healingAmount = item.matches("(?i).*(\\+\\d+).*") 
                ? extractValueFromItem(item, "healing potion") // Extract value if present
                : 20; // Default to +20 if no value is specified
            health = Math.min(health + healingAmount, 100);
            TextEngine.pt(Handler.applyStyle("You used: " + item + ". Your health is now: " + health, "i", "darkgrey"));
            return true;
        }
        // Match patterns for medkits
        else if (item.matches("(?i).*medkit.*")) {
            health = Math.min(health + 50, 100);
            TextEngine.pt(Handler.applyStyle("You used: " + item + ". Your health is now: " + health, "i", "darkgrey"));
            return true;
        }
        // Match patterns for hunger items
        else if (item.matches("(?i).*food.*")) {
            eatFood();
            return true;
        }
        // Default case for unknown items
        else {
            TextEngine.pt(Handler.applyStyle("That item does not exist or cannot be used. Please try again.", "i", "darkgrey"));
        }
        return false;
    }

    // Uses an item from the player's inventory.
    public boolean useItem(String item) {
        if (item == null || item.isEmpty()) {
            TextEngine.pt(Handler.applyStyle("You must specify an item to use. Please try again.", "i", "darkgrey"));
            return false;
        }

        // Use equalsIgnoreCase to handle case sensitivity
        for (String invItem : inventory) {
            if (invItem.equalsIgnoreCase(item) && parseItem(invItem)) {
                inventory.remove(invItem); // Remove the item from the inventory
                return true;
            }
        }
        return false;
    }

    // Checks if the player has an item in their inventory.
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    // Removes all items from the inventory.
    public void clearInventory() {
        inventory.clear();
        TextEngine.pt(Handler.applyStyle("Your inventory has been cleared.", "b", "yellow"));
    }

    // Returns the list of items in the player's inventory.
    public List<String> getInventory() {
        return inventory;
    }

    // Gets the current size of the player's inventory.
    public int getInventorySize() {
        return inventory.size();
    }

    // Method returning a string
    public String showInventory() {
        if (inventory.isEmpty()) {
            return "\033[3;90mYour inventory is empty.\033[0m";
        }

        // Create a map to count the occurrences of each item
        Map<String, Integer> itemCounts = new HashMap<>();
        for (String item : inventory) {
            itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
        }

        StringBuilder formattedInventory = new StringBuilder("\nYour inventory:\n");
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            int count = entry.getValue();
            String itemName = entry.getKey();
            formattedInventory.append(String.format(" - %dx %s(s)\n", count, itemName));
        }

        formattedInventory.append("\033[3;90mTo use an item, type 'use <item>'.\033[0m\n");

        return formattedInventory.toString();
    }

    // Void method that prints the inventory, accepting a parameter
    public void showInventory(boolean print) {
        String inventoryString = showInventory(); // Reuse the string-returning method
        TextEngine.pt(inventoryString);
    }

    // Returns a string representation of the Player object, including the
    // inventory.
    @Override
    public String toString() {
        return "Player Inventory: " + inventory.toString();
    }
}
