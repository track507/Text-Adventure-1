import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AdventureTest {
    private Player player;
    private GameMap gameMap;

    @Before
    public void initialize() {
        player = new Player();
        gameMap = new GameMap();
        
        gameMap.addRoom("Acrius", "Room1", "Room2", "Room3", "Room4", null);
        gameMap.addRoom("Acrius", "Room2", null, "Room1", null, null);
        gameMap.addRoom("Acrius", "Room3", "Room1", null, null, null);
        gameMap.addRoom("Acrius", "Room4", null, "Room5", null, "Room1");
        gameMap.addRoom("Acrius", "Room5", "Room4", null, null, null);
    }
    

    @Test
    public void testAddItem() {
        player.addItem("Mysterious Stone");
        assertEquals(1, player.getInventorySize());
        assertTrue(player.getInventory().contains("Mysterious Stone"));
    }

    @Test
    public void testRemoveItem() {
        player.addItem("Healing Potion");
        assertTrue(player.useItem("Healing Potion"));
        assertFalse(player.getInventory().contains("Healing Potion"));
        assertEquals(0, player.getInventorySize());
    }

    @Test
    public void testRemoveNonExistentItem() {
        player.addItem("Shield");
        assertFalse(player.useItem("Sword")); // Sword was never added
        assertEquals(1, player.getInventorySize());
    }

    @Test
    public void testClearInventory() {
        player.addItem("Mysterious Stone");
        assertEquals(1, player.getInventorySize());
        assertTrue(player.getInventory().contains("Mysterious Stone"));
        player.clearInventory();
        assertEquals(0, player.getInventorySize());
    }

    @Test
    public void testShowInventory() {
        player.addItem("Healing Potion");
        assertEquals(1, player.getInventorySize());
        assertTrue(player.getInventory().contains("Healing Potion"));
        String expectedInventory = "\nYour inventory: [Healing Potion]\n\033[3;90mTo use an item, type '<item>'.\033[0m\n";
        assertEquals(expectedInventory, player.showInventory());
    }

    @Test
    public void testAddDuplicateItem() {
        player.addItem("Mysterious Stone");
        player.addItem("Mysterious Stone");
        assertEquals(2, player.getInventorySize());
    }

    @Test
    public void testRemoveFromEmptyInventory() {
        assertFalse(player.useItem("Shield"));
        assertEquals(0, player.getInventorySize());
    }

    @Test
    public void testAddSpecialCharacterItem() {
        player.addItem("Special@Item!");
        assertTrue(player.getInventory().contains("Special@Item!"));
    }

    @Test
    public void testAddEmptyStringItem() {
        player.addItem("");
        assertTrue(player.getInventory().contains(""));
    }

    @Test
    public void testMultipleAddRemove() {
        player.addItem("Item1");
        player.addItem("Item2");
        assertEquals(2, player.getInventorySize());
        player.useItem("Item1");
        assertEquals(1, player.getInventorySize());
    }

    @Test
    public void testApplyBoldStyle() {
        String result = Handler.applyStyle("Bold Text", "b");
        assertEquals("\033[1mBold Text\033[0m", result);
    }

    @Test
    public void testApplyItalicStyle() {
        String result = Handler.applyStyle("Italic Text", "i");
        assertEquals("\033[3mItalic Text\033[0m", result);
    }

    @Test
    public void testApplyUnderlineStyle() {
        String result = Handler.applyStyle("Underline Text", "u");
        assertEquals("\033[4mUnderline Text\033[0m", result);
    }

    @Test
    public void testApplyRedColor() {
        String result = Handler.applyStyle("Red Text", "red");
        assertEquals("\033[31mRed Text\033[0m", result);
    }

    @Test
    public void testApplyBlueColor() {
        String result = Handler.applyStyle("Blue Text", "blue");
        assertEquals("\033[34mBlue Text\033[0m", result);
    }

    @Test
    public void testApplyGreenBackground() {
        String result = Handler.applyStyle("Green Background", "green_bg");
        assertEquals("\033[42mGreen Background\033[0m", result);
    }

    @Test
    public void testApplyBoldUnderline() {
        String result = Handler.applyStyle("Bold and Underline", "b,u");
        assertEquals("\033[1m\033[4mBold and Underline\033[0m", result);
    }

    @Test
    public void testApplyItalicRed() {
        String result = Handler.applyStyle("Italic and Red", "i,red");
        assertEquals("\033[3m\033[31mItalic and Red\033[0m", result);
    }

    @Test
    public void testApplyDimGreen() {
        String result = Handler.applyStyle("Dim and Green", "d,green");
        assertEquals("\033[2m\033[32mDim and Green\033[0m", result);
    }

    @Test
    public void testApplyReverseRedBackground() {
        String result = Handler.applyStyle("Reverse and Red Background", "rev,red_bg");
        assertEquals("\033[7m\033[41mReverse and Red Background\033[0m", result);
    }

    @Test
    public void testApplyHiddenYellow() {
        String result = Handler.applyStyle("Hidden and Yellow", "h,yellow");
        assertEquals("\033[8m\033[33mHidden and Yellow\033[0m", result);
    }

    @Test
    public void testApplyBoldItalicBlue() {
        String result = Handler.applyStyle("Bold, Italic, and Blue", "b,i,blue");
        assertEquals("\033[1m\033[3m\033[34mBold, Italic, and Blue\033[0m", result);
    }

    @Test
    public void testApplyRedBackgroundWhiteText() {
        String result = Handler.applyStyle("Red Background and White Text", "red_bg,white");
        assertEquals("\033[41m\033[37mRed Background and White Text\033[0m", result);
    }

    @Test
    public void testApplyDarkGreyItalic() {
        String result = Handler.applyStyle("Dark Grey and Italic", "darkgrey,i");
        assertEquals("\033[90m\033[3mDark Grey and Italic\033[0m", result);
    }

    @Test
    public void testApplyMultipleStyles() {
        String result = Handler.applyStyle("Bold, Underline, Red, and Blue Background", "b,u,red,blue_bg");
        assertEquals("\033[1m\033[4m\033[31m\033[44mBold, Underline, Red, and Blue Background\033[0m", result);
    }

    @Test
    public void testInitialHealth() {
        assertEquals(100, player.getHealth());
    }

    @Test
    public void testTakeDamage() {
        player.takeDamage(40);
        assertEquals(60, player.getHealth());
    }

    @Test
    public void testHealthCannotGoBelowZero() {
        player.takeDamage(120); // Exceeds initial health
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testIsDeadWhenHealthIsZero() {
        player.takeDamage(100);
        assertTrue(player.isDead());
    }

    @Test
    public void testGetHealthStatus() {
        player.takeDamage(60);
        assertEquals("Your health is now: 40", player.getHealthStatus());

        player.takeDamage(40); // Health reaches 0
        assertEquals("You have died. Game over!", player.getHealthStatus());
    }

    @Test
    public void testGetHunger_initialHungerIs100() {
        assertEquals(100, player.getHunger());
    }

    @Test
    public void testReduceHunger_correctlyReducesHunger() {
        player.reduceHunger(30);
        assertEquals(70, player.getHunger());
    }

    @Test
    public void testReduceHunger_hungerCannotGoBelowZero() {
        player.reduceHunger(110);  // Reducing by more than available hunger
        assertEquals(0, player.getHunger());
    }

    @Test
    public void testEatFood_correctlyIncreasesHunger() {
        player.reduceHunger(50); // Set hunger to 50
        player.eatFood();        // Increase hunger by 20
        assertEquals(70, player.getHunger());
    }

    @Test
    public void testGetHungerStatus_correctStatusMessage() {
        player.reduceHunger(80); // Set hunger to 20
        assertEquals("You are getting hungry.", player.getHungerStatus());

        player.reduceHunger(20); // Set hunger to 0
        assertEquals("You are starving! Eat something before it's too late.", player.getHungerStatus());
    }

     @Test
    public void testSetLocation_ValidWorldAndRoom() {
        gameMap.setLocation("Acrius", "Room1");
        assertEquals("Acrius", gameMap.getCurrentWorld());
        assertEquals("Room1", gameMap.getCurrentRoom());
    }

    @Test
    public void testSetLocation_InvalidWorld() {
        assertThrows(IllegalArgumentException.class, () -> {
            gameMap.setLocation("InvalidWorld", "Room1");
        });
    }

    @Test
    public void testSetLocation_InvalidRoom() {
        assertThrows(IllegalArgumentException.class, () -> {
            gameMap.setLocation("Acrius", "InvalidRoom");
        });
    }

    @Test
    public void testGetConnectedRoom_ValidDirection() {
        gameMap.setLocation("Acrius", "Room1");
        gameMap.displayMap();
        assertEquals("Room2", gameMap.getConnectedRoom("north"));
        assertEquals("Room3", gameMap.getConnectedRoom("south"));
        assertEquals("Room4", gameMap.getConnectedRoom("east"));
        assertNull(gameMap.getConnectedRoom("west"));
    }

    @Test
    public void testDisplayMap_NoMapItem() {
        // Ensure the player does not have a map item
        gameMap.setLocation("Acrius", "Room1");
        
        // Assert the output of displayMap method
        assertEquals("You don't have a map.", gameMap.displayMapAsString());
    }

    @Test
    public void testMoveTo_ValidDirection() {
        gameMap.setLocation("Acrius", "Room1");
        assertTrue(gameMap.moveTo("north"));
        assertEquals("Room2", gameMap.getCurrentRoom());
    }

    @Test
    public void testMoveTo_InvalidDirection() {
        gameMap.setLocation("Acrius", "Room1");
        assertFalse(gameMap.moveTo("west")); // No room in that direction
        assertEquals("Room1", gameMap.getCurrentRoom()); // Should still be in Room1
    }

    @Test
    public void testMoveTo_NonexistentRoom() {
        // Manually set the current room to one that has no exit
        gameMap.setLocation("Acrius", "Room3");
        assertFalse(gameMap.moveTo("south")); // No room in that direction
        assertFalse(gameMap.moveTo("east")); // No room in that direction
        assertFalse(gameMap.moveTo("west")); // No room in that direction
        assertEquals("Room3", gameMap.getCurrentRoom()); // Should still be in Room3
    }
    // @Test
    // public void testAddRoomDuplicateRoom() {
    //     gameMap.addRoom("Acrius", "Room1", null, null, null, null);
    //     gameMap.addRoom("Acrius", "Room1", "Room2", "Room3", "Room4", "Room5");
    //     gameMap.setLocation("Acrius", "Room1");
        
    //     // Accessing Room via GameMap instance
    //     Room room = gameMap.getCurrentRoom();
        
    //     // Validate that the updated connections are correctly stored
    //     assertEquals("Room2", room.getConnection("north"));
    //     assertEquals("Room3", room.getConnection("south"));
    //     assertEquals("Room4", room.getConnection("east"));
    //     assertEquals("Room5", room.getConnection("west"));
    // }

    @Test
    public void testPlayerInventoryAfterUsingItem() {
        player.addItem("Food");
        player.useItem("Food");
        assertFalse(player.getInventory().contains("Food")); // Ensure the item is removed after use
        assertEquals(0, player.getInventorySize());
    }

    @Test
    public void testAddItemWithSpecialCharacters() {
        player.addItem("Mysterious@Stone!");
        assertTrue(player.getInventory().contains("Mysterious@Stone!"));
        assertEquals(1, player.getInventorySize());
    }

    @Test
    public void testHungerDoesNotExceedMaxWhenEating() {
        player.reduceHunger(50); // Hunger is now 50
        player.eatFood();        // Hunger increases by 20 to 70
        player.eatFood();        // Hunger increases to 90
        player.eatFood();        // Hunger increases to 100 (max)
        player.eatFood();        // Hunger should remain at 100
        assertEquals(100, player.getHunger());
    }

    @Test
    public void testAddNullItemToInventory() {
        player.addItem(null);
        player.getInventory();
        player.getInventorySize();
        assertFalse(player.getInventory().contains(null)); // Should not contain null
        assertEquals(0, player.getInventorySize());        // Inventory size should be unchanged
    }

    @Test
    public void testMoveToInvalidDirectionDoesNotChangeLocation() {
        gameMap.setLocation("Acrius", "Room1");
        String initialRoom = gameMap.getCurrentRoom();
        assertFalse(gameMap.moveTo("upward")); // Invalid direction
        assertEquals(initialRoom, gameMap.getCurrentRoom()); // Location should remain the same
    }

    @Test
    public void testMoveToNonExistentRoom() {
        gameMap.addRoom("TestWorld", "Test Room", null, null, null, null);
        gameMap.setLocation("TestWorld", "Test Room");
        assertFalse(gameMap.moveTo("south")); // Assuming "south" from "Room1" does not exist
        assertEquals("Test Room", gameMap.getCurrentRoom()); // Location should remain unchanged
    }

    @Test
    public void testHealthDoesNotExceedMaxWhenUsingMedkit() {
        player.takeDamage(20); // Health is now 80
        player.addItem("medkit");
        player.useItem("medkit"); // Health increases by 50 to 100 (max)
        assertEquals(100, player.getHealth());
        player.addItem("medkit");
        player.useItem("medkit"); // Health should remain at 100
        assertEquals(100, player.getHealth());
    }

    @Test
    public void testAddNewWorldAndMoveBetweenWorlds() {
        // Add a new world with rooms
        gameMap.addRoom("NewWorld", "StartRoom", "SecondRoom", null, null, null);
        gameMap.addRoom("NewWorld", "SecondRoom", null, "StartRoom", null, null);

        // Move to the new world
        gameMap.setLocation("NewWorld", "StartRoom");
        assertEquals("NewWorld", gameMap.getCurrentWorld());
        assertEquals("StartRoom", gameMap.getCurrentRoom());

        // Move within the new world
        assertTrue(gameMap.moveTo("north"));
        assertEquals("SecondRoom", gameMap.getCurrentRoom());

        // Move back to the original world
        gameMap.setLocation("Acrius", "Room1");
        assertEquals("Acrius", gameMap.getCurrentWorld());
        assertEquals("Room1", gameMap.getCurrentRoom());
    }
}
