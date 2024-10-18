import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AdventureTest {
    private Player player;

    @Before
    public void initialize() {
        player = new Player();
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
}
