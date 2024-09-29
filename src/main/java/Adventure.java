import java.io.Console;
import java.util.*;
import Player.Player;
/**
 * Adventure
 * Be more welcoming
 *
 * Terrence
 * CS 374
 */

public class Adventure {
    // The player
    private static Player player = new Player();
    // To track the time spent in the game
    private static long startTime; 

    // Timer method to check how much time has passed
    private static void trackTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        System.out.println("Time elapsed: " + (elapsedTime / 1000) + " seconds.");
    }
    
    // The main method
    public static void main(String[] args) {
        Console console = System.console();

        System.out.println("Welcome to Acrius, the Dark Realm by Terrence, Grant, and Chibuikem.");
        System.out.println("You find yourself in a dimly lit cavern with distant echoes. Before you lies a narrow path deeper into the unknown.");
        System.out.println("Do you want to proceed or go back? (proceed/back)");

        String command = console.readLine();
        if (command.equals("proceed")) {
            startTime = System.currentTimeMillis(); // Start timer
            startAdventure(console);
        } else {
            System.out.println("You decide not to venture forward. The game ends here.");
        }

        if (command.equals("inventory")) {
            player.showInventory();
        }
    }

    // Start the adventure
    public static void startAdventure(Console console) {
        System.out.println("You cautiously step forward. After a few minutes, you see an intersection.");
        System.out.println("Do you take the left path, the right path, or continue straight? (left/right/straight)");

        String command = console.readLine();
        trackTime();

        switch (command) {
            case "left":
                leftPath(console);
                break;
            case "right":
                rightPath(console);
                break;
            case "straight":
                straightPath(console);
                break;
            default:
                System.out.println("You hesitate and stand still, unsure of which direction to take. The silence grows heavier.");
                break;
        }
    }

    // Left path scenario
    public static void leftPath(Console console) {
        System.out.println("You take the left path and find yourself in a vast underground riverbank.");
        System.out.println("A boat is tied to the shore. Do you take the boat or explore the cave further on foot? (boat/foot)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("boat")) {
            boatRide(console);
        } else if (command.equals("foot")) {
            exploreCave(console);
        } else {
            System.out.println("Indecision grips you as the river flows beside you. Time slips away...");
        }
    }

    // Boat ride scenario
    public static void boatRide(Console console) {
        System.out.println("You untie the boat and paddle down the eerie river. You soon encounter a waterfall.");
        System.out.println("Do you abandon the boat and swim ashore or ride over the waterfall? (swim/ride)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("swim")) {
            System.out.println("You swim ashore and find a small cave entrance. You venture inside, discovering ancient carvings.");
            hiddenTemple(console);
        } else if (command.equals("ride")) {
            System.out.println("You ride over the waterfall but your boat crashes on the rocks below. You sustain injuries but survive.");
            darkCavern(console);
        } else {
            System.out.println("You hesitate and the current pulls you toward the waterfall anyway. It's too late to turn back.");
        }
    }

    // Explore cave scenario
    public static void exploreCave(Console console) {
        System.out.println("You walk deeper into the cave on foot. The walls are damp, and the air grows colder.");
        System.out.println("Suddenly, you see glowing eyes in the dark. Do you run or confront the creature? (run/confront)");
    
        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("run")) {
            System.out.println("You run back toward the riverbank and escape the creature.");
        } else if (command.equals("confront")) {
            System.out.println("You step forward bravely. The creature turns out to be a harmless, blind mole. Relieved, you continue.");
            player.addItem("Mysterious Stone");
            hiddenTemple(console);
        } else {
            System.out.println("The creature approaches, but it vanishes into the darkness. You may never know what it was.");
        }
    }
    

    // Right path scenario
    public static void rightPath(Console console) {
        System.out.println("The right path takes you through a narrow tunnel. It widens into a large chamber filled with old mining equipment.");
        System.out.println("You see a broken elevator that descends further underground and a ladder leading upward.");
        System.out.println("Do you take the elevator or the ladder? (elevator/ladder)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("elevator")) {
            brokenElevator(console);
        } else if (command.equals("ladder")) {
            upperChamber(console);
        } else {
            System.out.println("You stand there, weighing your options as dust falls from the ceiling.");
        }
    }

    // Broken elevator
    public static void brokenElevator(Console console) {
        System.out.println("You try the elevator, but it breaks halfway down. You fall into a dark, forgotten section of the cave.");
        trackTime(); // Check the time spent
        darkCavern(console);
    }

    // Upper chamber
    public static void upperChamber(Console console) {
        System.out.println("You climb the ladder into a large, sunlit chamber. Ancient ruins dot the landscape.");
        System.out.println("There's a giant stone door ahead. Do you want to try opening it? (yes/no)");
    
        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("yes")) {
            System.out.println("You push open the stone door and find a treasure trove of gold and jewels.");
            player.addItem("Treasure Chest");
            System.out.println("Congratulations, you've found one of the hidden treasures of Acrius!");
        } else {
            System.out.println("You decide not to open the door, leaving the mysteries behind. You exit the chamber into the daylight.");
        }
    }

    // Straight path scenario
    public static void straightPath(Console console) {
        System.out.println("You walk straight ahead and find yourself in a dense underground forest. It's dark, but the trees seem alive.");
        System.out.println("Do you explore deeper into the forest or turn back? (explore/back)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("explore")) {
            System.out.println("As you venture deeper, you discover a glowing tree at the center of the forest. Its light is mesmerizing.");
            System.out.println("Do you touch the tree or leave it alone? (touch/leave)");
            
            command = console.readLine();
            trackTime(); // Check the time spent
            if (command.equals("touch")) {
                System.out.println("You touch the tree and suddenly feel yourself transported to another realm, filled with strange creatures.");
                alternateRealm(console);
            } else if (command.equals("leave")) {
                System.out.println("You leave the tree and continue through the forest. After some time, you find an exit and leave the cave.");
            } else {
                System.out.println("You hesitate, unsure of the power the tree holds.");
            }
        } else if (command.equals("back")) {
            System.out.println("You turn back toward the cavern entrance, but the path is now blocked. You're forced to keep moving forward.");
            alternateRealm(console);
        }
    }

    // Hidden Temple
    public static void hiddenTemple(Console console) {
        System.out.println("You find yourself in an ancient temple. Strange symbols cover the walls.");
        System.out.println("Do you want to decipher the symbols or search the temple for items? (decipher/search)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("decipher")) {
            System.out.println("You decipher the symbols and unlock a hidden passageway.");
            System.out.println("You win! You've uncovered a great secret of Acrius.");
        } else if (command.equals("search")) {
            System.out.println("You search the temple and find a powerful artifact, but the ceiling starts to collapse. You barely escape!");
        } else {
            System.out.println("The temple remains silent as you make no move. Dust falls from the ceiling.");
        }
    }

    // Dark Cavern
    public static void darkCavern(Console console) {
        System.out.println("You land in a dark cavern filled with strange sounds. You see faint lights in the distance.");
        System.out.println("Do you investigate the lights or wait? (investigate/wait)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("investigate")) {
            System.out.println("You move toward the lights and discover a group of mysterious figures chanting around a fire.");
            System.out.println("Do you approach them or hide? (approach/hide)");

            command = console.readLine();
            if (command.equals("approach")) {
                System.out.println("The figures turn out to be friendly. They offer you food and warmth.");
            } else if (command.equals("hide")) {
                System.out.println("You hide behind a rock, but one of them notices you. They seem curious, not hostile.");
            } else {
                System.out.println("The figures' chants grow louder, but you stay hidden.");
            }
        } else if (command.equals("wait")) {
            System.out.println("You wait, listening to the strange sounds until they slowly fade. You're alone again.");
        }
    }

    // Alternate Realm
    public static void alternateRealm(Console console) {
        System.out.println("You find yourself in a strange new world filled with floating islands and bizarre creatures.");
        System.out.println("Do you explore this new realm or try to find a way back? (explore/return)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("explore")) {
            System.out.println("You explore the realm and find that time works differently here. Days feel like minutes.");
        } else if (command.equals("return")) {
            System.out.println("You find a portal and return to the cave, but something feels different. You may never know how much time has passed.");
        }
    }

    // Abyss scenario
    public static void abyss(Console console) {
        System.out.println("You stumble upon an abyss, its bottom hidden in darkness.");
        System.out.println("Do you throw a rock to gauge its depth or try to jump across? (throw/jump)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("throw")) {
            System.out.println("The rock falls silently, and you decide to turn back.");
        } else if (command.equals("jump")) {
            System.out.println("You make the jump but barely. A dark figure watches from afar...");
        } else {
            System.out.println("You stand still, feeling the void's cold embrace.");
        }
    }

    // Enchanted Garden scenario
    public static void enchantedGarden(Console console) {
        System.out.println("You find yourself in a strange garden where the plants glow faintly.");
        System.out.println("Do you touch the flowers or continue walking? (touch/walk)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("touch")) {
            System.out.println("The flowers glow brighter, and you feel at peace. You gain +1 health.");
        } else if (command.equals("walk")) {
            System.out.println("You leave the garden, the glow fading behind you.");
        } else {
            System.out.println("The flowers sway in the silence, as if waiting.");
        }
    }

    // Crystal Cave scenario
    public static void crystalCave(Console console) {
        System.out.println("You enter a cave filled with crystals. Their light reflects off every surface.");
        System.out.println("Do you mine the crystals or admire their beauty? (mine/admire)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("mine")) {
            System.out.println("You collect some crystals, their magic granting you +1 to your next roll.");
        } else if (command.equals("admire")) {
            System.out.println("The crystals soothe your mind, and you feel rested.");
        } else {
            System.out.println("The crystals seem to hum softly as you stand still.");
        }
    }

    // Hall of Mirrors scenario
    public static void hallOfMirrors(Console console) {
        System.out.println("You walk into a room full of mirrors, reflecting you from every angle.");
        System.out.println("Do you smash the mirrors or try to find the exit? (smash/exit)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("smash")) {
            System.out.println("You smash the mirrors, but they only reform instantly. You feel uneasy...");
        } else if (command.equals("exit")) {
            System.out.println("You find the exit and leave the mirrors behind, but something still feels off.");
        } else {
            System.out.println("The mirrors reflect you endlessly as you stand there.");
        }
    }

    // Library scenario
    public static void library(Console console) {
        System.out.println("You enter an ancient library, filled with dusty tomes and scrolls.");
        System.out.println("Do you read a book or take a scroll? (book/scroll)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("book")) {
            System.out.println("The book reveals ancient secrets about Acrius, improving your knowledge.");
        } else if (command.equals("scroll")) {
            System.out.println("The scroll disintegrates in your hand, but not before you glimpse its forbidden knowledge.");
        } else {
            System.out.println("The library remains silent as you make no move.");
        }
    }

    // Dungeon scenario
    public static void dungeon(Console console) {
        System.out.println("You descend into a dark dungeon. The air smells of damp stone and fear.");
        System.out.println("Do you explore the cells or search for a way out? (explore/search)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("explore")) {
            System.out.println("You find a skeleton clutching a key. You take it.");
            player.addItem("Key");
        } else if (command.equals("search")) {
            System.out.println("You find a hidden door, but it's locked.");
            if (player.hasItem("Key")) {
                System.out.println("You use the key to unlock the door and escape.");
            } else {
                System.out.println("You need a key to open this door.");
            }
        } else {
            System.out.println("The dungeon's darkness closes in as you stand still.");
        }
    }

    // Alchemist Lab scenario
    public static void alchemistLab(Console console) {
        System.out.println("You enter a lab filled with bubbling potions and strange apparatus.");
        System.out.println("Do you drink a potion or examine the apparatus? (drink/examine)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("drink")) {
            System.out.println("The potion grants you a random effect.");
        } else if (command.equals("examine")) {
            System.out.println("You find a strange, glowing crystal and take it.");
            player.addItem("Crystal");
        } else {
            System.out.println("The lab hums with strange energy as you hesitate.");
        }
    }

    // Chapel scenario
    public static void chapel(Console console) {
        System.out.println("You find an old chapel, its walls crumbling.");
        System.out.println("Do you kneel and pray or continue exploring? (kneel/explore)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("kneel")) {
            System.out.println("You feel a divine presence, granting you +1 to your next battle.");
        } else if (command.equals("explore")) {
            System.out.println("You find an ancient relic and take it.");
            player.addItem("Relic");
        } else {
            System.out.println("The chapel remains silent as you make no move.");
        }
    }

    // Spider Nest scenario
    public static void spiderNest(Console console) {
        System.out.println("You stumble into a spider's nest. The webs are thick, and the spiders are watching.");
        System.out.println("Do you burn the webs or try to sneak past? (burn/sneak)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("burn")) {
            System.out.println("The fire spreads quickly, and you barely escape.");
        } else if (command.equals("sneak")) {
            System.out.println("You sneak past the spiders, but one follows you closely.");
        } else {
            System.out.println("The spiders begin to move as you stand still.");
        }
    }

    // Lava Lake scenario
    public static void lavaLake(Console console) {
        System.out.println("You find yourself at the edge of a lake of lava. The heat is intense.");
        System.out.println("Do you attempt to cross or turn back? (cross/back)");

        String command = console.readLine();
        trackTime(); // Check the time spent
        if (command.equals("cross")) {
            System.out.println("You find a narrow bridge and cross it, though the heat scorches your skin.");
        } else if (command.equals("back")) {
            System.out.println("You turn back, leaving the lake of lava behind.");
        } else {
            System.out.println("The heat grows unbearable as you stand still.");
        }
    }
}