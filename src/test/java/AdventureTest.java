import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import Player.Player;

public class AdventureTest {
    private Player player;

    @Before
    public void initialize() {
        player = new Player();
    }

    @Test
    public void testAddItem() {
        // Test that the player can add an item to their inventory
        player.addItem("Mysterious Stone");
        assertEquals("Inventory should contain 1 item.", 1, player.getInventorySize());
        assertTrue("Inventory should contain 'Mysterious Stone'.", player.getInventory().contains("Mysterious Stone"));
    }

    @Test
    public void testRemoveItem() {
        // Test that the player can remove an item from their inventory
        player.addItem("Healing Potion");
        assertTrue("Item should be removed successfully.", player.removeItem("Healing Potion"));
        assertFalse("Inventory should not contain 'Healing Potion'.", player.getInventory().contains("Healing Potion"));
        assertEquals("Inventory should be empty.", 0, player.getInventorySize());
    }

    @Test
    public void testRemoveNonExistentItem() {
        // attempt to remove an item that doesn't exist in the inventory
        player.addItem("Shield");
        assertFalse("Item should not have been removed.", player.removeItem("Sword")); // Sword was never added
        assertEquals("Inventory should still contain 1 item.", 1, player.getInventorySize());
    }

    @Test
    public void testClearInventory() {
        // add an item to the inventory and clear it
        player.addItem("Mysterious Stone");
        assertEquals("Inventory should contain 1 item.", 1, player.getInventorySize());
        assertTrue("Inventory should contain 'Mysterious Stone'.", player.getInventory().contains("Mysterious Stone"));
        player.clearInventory();
        assertEquals("Inventory should be empty after clearing.", 0, player.getInventorySize());
    }

    @Test
    public void testShowInventory() {
        // add an item to the inventory and check its string representation
        player.addItem("Healing Potion");
        assertEquals("Inventory should contain 1 item.", 1, player.getInventorySize());
        assertTrue("Inventory should contain 'Healing Potion'.", player.getInventory().contains("Healing Potion"));
        String expectedInventory = "Your inventory: [Healing Potion]";
        assertEquals(expectedInventory, player.showInventory());
    }

    @Test
    public void testAddDuplicateItem() {
        // add a duplicate item to the inventory, it's size should be 2
        player.addItem("Mysterious Stone");
        player.addItem("Mysterious Stone");
        assertEquals("Inventory should contain 2 items.", 2, player.getInventorySize());
    }

    @Test
    public void testRemoveFromEmptyInventory() {
        // attempt to remove an item from an empty inventory
        assertFalse("Should return false when removing from empty inventory.", player.removeItem("Shield"));
        assertEquals("Inventory should still be empty.", 0, player.getInventorySize());
    }

    @Test
    public void testAddSpecialCharacterItem() {
        // add a special character item to the inventory
        player.addItem("Special@Item!");
        assertTrue("Inventory should contain 'Special@Item!'.", player.getInventory().contains("Special@Item!"));
    }

    @Test
    public void testAddEmptyStringItem() {
        // add an empty string item to the inventory
        player.addItem("");
        assertTrue("Inventory should contain an empty string item.", player.getInventory().contains(""));
    }

    @Test
    public void testMultipleAddRemove() {
        // add multiple items to the inventory and remove one of them
        player.addItem("Item1");
        player.addItem("Item2");
        assertEquals("Inventory should contain 2 items.", 2, player.getInventorySize());
        player.removeItem("Item1");
        assertEquals("Inventory should contain 1 item after removal.", 1, player.getInventorySize());
    }
}
