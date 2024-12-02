
// package Player would fix some issues
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Player made by Terrence
// Modified by Chibuikem

public class Player {
    public static GameMap gameMap = StartAdventure.gameMap;
    private List<String> inventory;
    private Map<String, String> locations = new HashMap<>();
    private int health = 100;
    private int hunger = 100;

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
        if (item == null)
            return;
        inventory.add(item);
        System.out.println("\033[1;33mYou have picked up: \033[0m\033[3;90m" + item + "\033[0m");
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
        System.out.println("Your hunger has decreased to: " + hunger);
        if (isStarving()) {
            takeDamage(10); // Player takes damage when starving
            System.out.println("You are starving and took 10 damage.");
        }
    }

    // Increase hunger when eating food (but not beyond the max of 100).
    public void eatFood() {
        hunger = Math.min(hunger + 20, 100);
        System.out.println("You ate some food. Your hunger is now: " + hunger);
    }

    // Check if the player is starving (hunger at 0).
    public boolean isStarving() {
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

    public void parseItem(String item) {
        switch (item) {
            case "medkit":
                health = Math.min(health + 50, 100);
                System.out.println("\033[3;90mYou have used a medkit. Your health is now: " + health + "\033[0m");
                break;
            case "ancient key":
                break;
            case "food":
                hunger = Math.min(hunger + 20, 100);
                System.out.println("\033[3;90mYou have eaten some food. Your hunger is now: " + hunger + "\033[0m");
                break;
            default:
                System.out.println("\033[3;90mThat item does not exist. Please try again.\033[0m");
                break;
        }
    }

    // Uses an item from the player's inventory.
    public boolean useItem(String item) {
        if (item == "map" && hasItem("map")) {
            gameMap.displayMap();
            return true;
        } else if (hasItem(item)) {
            // Create a method that parses the item and uses it.
            parseItem(item);
            inventory.remove(item);
            return true;
        } else if (!hasItem(item)) {
            System.out.println("Item not found in inventory.");
            return false;
        } else {
            System.out.println("Error Occurred.");
            return false;
        }
    }

    // Checks if the player has an item in their inventory.
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    // Removes all items from the inventory.
    public void clearInventory() {
        inventory.clear();
        System.out.println("Your inventory has been cleared.");
    }

    // Returns the list of items in the player's inventory.
    public List<String> getInventory() {
        return inventory;
    }

    // Gets the current size of the player's inventory.
    public int getInventorySize() {
        return inventory.size();
    }

    // Returns a string representation of the player's inventory.
    public String showInventory() {
        if (inventory.isEmpty()) {
            return "\033[3;90mYour inventory is empty.\033[0m";
        }

        // Join the inventory items with ", " and avoid extra space at the end
        String formattedInventory = String.join(", ", inventory);

        return "\nYour inventory: [" + formattedInventory + "]\n\033[3;90mTo use an item, type '<item>'.\033[0m\n";
    }

    // public void showMap() {

    // }

    // Returns a string representation of the Player object, including the
    // inventory.
    @Override
    public String toString() {
        return "Player Inventory: " + inventory.toString();
    }

    public void showSkills() {
        System.out.println(skills.showSkills()); // Show the player's skills
    }
}
