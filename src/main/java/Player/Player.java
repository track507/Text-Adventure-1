package Player;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<String> inventory;

    public Player() {
        inventory = new ArrayList<>();
    }

    //Adds an item to the player's inventory.
    public void addItem(String item) {
        inventory.add(item);
        System.out.println("You have picked up: " + item);
    }

    //Removes an item from the player's inventory.
    public boolean removeItem(String item) {
        if (inventory.remove(item)) {
            System.out.println("You have removed: " + item);
            return true;
        } else {
            System.out.println("Item not found in inventory.");
            return false;
        }
    }

    //Checks if the player has an item in their inventory.
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    //Removes all items from the inventory.
    public void clearInventory() {
        inventory.clear();
        System.out.println("Your inventory has been cleared.");
    }

    //Returns the list of items in the player's inventory.
    public List<String> getInventory() {
        return inventory;
    }

    //Gets the current size of the player's inventory.
    public int getInventorySize() {
        return inventory.size();
    }

    //Returns a string representation of the player's inventory.
    public String showInventory() {
        return "Your inventory: " + inventory.toString();
    }

    //Returns a string representation of the Player object, including the inventory.
    @Override
    public String toString() {
        return "Player Inventory: " + inventory.toString();
    }
}
