import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue("Item should be removed successfully.", player.useItem("Healing Potion"));
        assertFalse("Inventory should not contain 'Healing Potion'.", player.getInventory().contains("Healing Potion"));
        assertEquals("Inventory should be empty.", 0, player.getInventorySize());
    }

    @Test
    public void testRemoveNonExistentItem() {
        // attempt to remove an item that doesn't exist in the inventory
        player.addItem("Shield");
        assertFalse("Item should not have been removed.", player.useItem("Sword")); // Sword was never added
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
        assertEquals(1, player.getInventorySize());
        assertTrue(player.getInventory().contains("Healing Potion"));
        String expectedInventory = "\nYour inventory: [Healing Potion]\n\033[3;90mTo use an item, type '<item>'.\033[0m\n";
        assertEquals(expectedInventory, player.showInventory());
    }

    @Test
    public void testAddDuplicateItem() {
        // add a duplicate item to the inventory, it's size should be 2
        player.addItem("Mysterious Stone");
        player.addItem("Mysterious Stone");
        assertEquals(2, player.getInventorySize());
    }

    @Test
    public void testRemoveFromEmptyInventory() {
        // attempt to remove an item from an empty inventory
        assertFalse(player.useItem("Shield"));
        assertEquals( 0, player.getInventorySize());
    }

    @Test
    public void testAddSpecialCharacterItem() {
        // add a special character item to the inventory
        player.addItem("Special@Item!");
        assertTrue(player.getInventory().contains("Special@Item!"));
    }

    @Test
    public void testAddEmptyStringItem() {
        // add an empty string item to the inventory
        player.addItem("");
        assertTrue(player.getInventory().contains(""));
    }

    @Test
    public void testMultipleAddRemove() {
        // add multiple items to the inventory and remove one of them
        player.addItem("Item1");
        player.addItem("Item2");
        assertEquals(2, player.getInventorySize());
        player.useItem("Item1");
        assertEquals(1, player.getInventorySize());
    }
}
