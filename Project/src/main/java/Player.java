
// package Player would fix some issues
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private List<String> inventory;
    private Map<String, String> locations = new HashMap<>();
    private int health = 100;
    private int hunger = 100;

    // booleans for certain rooms
    public boolean hasSearchedCave = false;
    public boolean hasSearchedHiddenTemple = false;
    public boolean hasSearchedStairCaseIntoDarkness = false;

    public Player() {
        inventory = new ArrayList<>();
    }

    // examines an item to the player's inventory.
    public void addItem(String item) {
        inventory.add(item);
        System.out.println("\033[1;33mYou have picked up: \033[0m\033[3;90m" + item + "\033[0m");
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
        if (hasItem(item)) {
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
}
