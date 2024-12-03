import java.util.Scanner;
// Acrius made by Terrence

public class Acrius {
    public static Player player = StartAdventure.player;
    public static GameMap gameMap = StartAdventure.gameMap;

    // Left path scenario
    public static void leftPath(Scanner scanner) {
        gameMap.setLocation("Acrius", "Left Path");
        String text = "\nYou chose the west path. You see a large cave with a wide riverbank. A boat is tied to the shore.\nDo you north towards the boat or west to explore the cave further on foot? (north/west/back)";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "north":
                    gameMap.moveTo("north");
                    boatRide(scanner);
                    break;
                case "west":
                    gameMap.moveTo("west");
                    exploreCave(scanner);
                    break;
                case "back":
                case "east":
                    gameMap.moveTo("east");
                    StartAdventure.startAdventure(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void exploreCave(Scanner scanner) {
        String[] text = {
            "\nYou chose to explore the cave further on foot. You make your way deeper into the cave.",
            "You find three skeletons with their bags and backpacks on the ground.",
            "Do you want to search the skeletons or go back? (search/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "search":
                    if (!player.hasSearchedCave) {
                        player.addItem("iron sword");
                        player.addItem("buckler");
                        player.addItem("health potion");
                        player.hasSearchedCave = true;
                        System.out.println();
                    } else {
                        System.out.println("You've already searched this room. You can't find anything else.\n");
                    }
                    command = scanner.nextLine().toLowerCase();
                    break;
                case "back":
                    gameMap.moveTo("east");
                    leftPath(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void boatRide(Scanner scanner) {
        String[] text = {
            "\nYou untie the boat and paddle down the eerie river. You soon encounter a waterfall.",
            "Do you abandon the boat and swim west to the shore or keep going north and ride over the waterfall? (west/north/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "west":
                    gameMap.moveTo("west");
                    hiddenTemple(scanner);
                    break;
                case "north":
                    gameMap.moveTo("north");
                    faintTunnel(scanner);
                    break;
                case "back":
                    gameMap.moveTo("south");
                    leftPath(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void faintTunnel(Scanner scanner) {
        String[] text = {
            "\nYou enter the Faint Tunnel, a narrow and dimly lit passage.",
            "The air here is damp and carries the faint scent of saltwater.",
            "The walls are lined with moss, and you hear the distant sound of dripping water echoing through the tunnel.",
            "To the south, the tunnel opens into the Boat Ride area.",
            "To the east, you see a faint glow leading to the Mysterious Cavern.",
            "Where do you want to go? (south/east/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                case "south":
                    gameMap.moveTo("south");
                    boatRide(scanner);
                    return;
                case "east":
                    gameMap.moveTo("east");
                    mysteriousCavern(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }


    public static void mysteriousCavern(Scanner scanner) {
        String[] text = {
            "\nYou step into the Mysterious Cavern. The air is thick with the scent of damp moss, and faintly glowing crystals illuminate the walls.",
            "Scattered on the ground, you see signs of past adventurers—broken tools, discarded backpacks, and old campfires.",
            "Do you want to search the cavern, head south to the Crystal Hollow, or return to the Faint Tunnel? (search/south/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "search":
                    if (!player.hasSearchedCave) {
                        TextEngine.pt(Handler.applyStyle("You search the cavern and find some useful items!", "i", "darkgrey"));
                        player.addItem("Small Torch");
                        player.addItem("Silver Coins");
                        player.hasSearchedCave = true;
                    } else {
                        TextEngine.pt(Handler.applyStyle("You've already searched this area. You can't find anything else.", "i", "darkgrey"));
                    }
                    break;
                case "south":
                    gameMap.moveTo("south");
                    crystalHollow(scanner);
                    return;
                case "west":
                case "back":
                    gameMap.moveTo("west");
                    faintTunnel(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void crystalHollow(Scanner scanner) {
        String[] text = {
            "\nYou descend into the Crystal Hollow, a breathtaking chamber filled with glittering crystals of various hues.",
            "The air is cool and damp, and the sound of dripping water echoes throughout.",
            "You notice something shimmering among the crystals. Do you want to search the area or return to the Mysterious Cavern? (search/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "search":
                    if (!player.hasSearchedCave) {
                        TextEngine.pt(Handler.applyStyle("You carefully search the hollow and find some rare items!", "i", "darkgrey"));
                        player.addItem("Crystal Shard");
                        player.addItem("Gold Coins");
                        player.hasSearchedCave = true;
                    } else {
                        TextEngine.pt(Handler.applyStyle("You've already searched this area. There's nothing else to find.", "i", "darkgrey"));
                    }
                    break;
                case "north":
                case "back":
                    gameMap.moveTo("north");
                    mysteriousCavern(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }    

    public static void hiddenTemple(Scanner scanner) {
        String[] text = {
            "\nYou swim ashore and find a small cave entrance. You venture inside, discovering ancient carvings.",
            "You find yourself in an ancient temple. Strange symbols cover the walls.",
            "Do you want to decipher the symbols or search the temple for items? (decipher/search/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "decipher":
                    gameMap.moveTo("west");
                    hiddenPassageway(scanner);
                    return;
                case "search":
                    if (!player.hasSearchedHiddenTemple) {
                        TextEngine.pt(Handler.applyStyle("You search the temple and find a map. You can now track your progress.", "i", "darkGrey") + Handler.applyStyle(" But the cave starts to collapse. You barely escape!", "i", "red"), 10);
                        player.addItem("map");
                        player.hasSearchedHiddenTemple = true;
                    } else {
                        TextEngine.pt(Handler.applyStyle("You've already searched this area. You can't find anything else.", "i", "darkGrey"));
                    }
                    break;
                case "east":
                case "back":
                    if (player.hasSearchedHiddenTemple) {
                        TextEngine.pt(Handler.applyStyle("You can't go back. The debris is blocking your path...", "i", "darkGrey"));
                        break; // Can no longer go back
                    }
                    gameMap.moveTo("east");
                    boatRide(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void hiddenPassageway(Scanner scanner) {
        String[] text = {
            "\nYou decipher the symbols and unlock a hidden passageway.",
            "The air is cool, and the walls seem to close in around you.",
            "To your west, a narrow staircase spirals downward into the darkness, while to your north, a heavy door creaks open, revealing a dark cavern in faint, flickering light.",
            "Do you descend the staircase or venture into the tunnel? (west/north/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "west":
                    gameMap.moveTo("west");
                    stairCaseIntoDarkness(scanner);
                    return;
                case "north":
                    gameMap.moveTo("north");
                    darkCavern(scanner);
                    return;
                case "back":
                    gameMap.moveTo("east");
                    hiddenTemple(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void darkCavern(Scanner scanner) {
        String[] text = {
            "\nYou move north into the dark cavern. The walls are slick and shiny, reflecting faint glimmers of light.",
            "The air feels damp and cold, and a low rumbling sound echoes from deeper within.",
            "You see faint pathways leading west and north, while the way back south remains open.",
            "To the west, you notice a faint glow emanating from a narrow passage.",
            "To the north, the rumbling grows louder, as if something immense lies deeper in the cavern.",
            "Where do you want to go? (west/north/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "west":
                    gameMap.moveTo("west");
                    glowingPassage(scanner);
                    return;
                case "north":
                    gameMap.moveTo("north");
                    northernDepths(scanner);
                    return;
                case "south":
                case "back":
                    gameMap.moveTo("south");
                    hiddenPassageway(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void northernDepths(Scanner scanner) {
        String[] text = {
            "\nYou venture deeper into the northern depths. The air grows colder, and faint vibrations pulse beneath your feet.",
            "Ahead stands a towering monolith, its glowing runes pulsing in rhythm with the cavern's hum.",
            "To the east, a narrow pathway leads deeper into the unknown. To the west, faint whispers emanate from a shadowy crevice.",
            "What do you do? (approach/east/west/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "approach":
                    unknownFigure(scanner);
                    return; 
                case "east":
                    gameMap.moveTo("east");
                    unknownPathway(scanner);
                    return; 
                case "west":
                    gameMap.moveTo("west");
                    whisperingCrevice(scanner);
                    return; 
                case "back":
                    gameMap.moveTo("south");
                    darkCavern(scanner);
                    return; 
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void unknownFigure(Scanner scanner) {
        String[] text = {
            "\nYou approach the towering monolith, its runes pulsing in rhythm with the cavern's hum.",
            "A mysterious figure appears from behind it, its gaze fixed on you."
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        text = new String[] {
            "Hello fellow traveler... It's been quite some time since someone came by.",
            "I'm Acrius, the guardian of this place. I'm here to keep the place safe and secure.",
            "But recently, I've gotten quite bored. Maybe you would like to play a game with me?",
            "If you win, I'll give you some items that will help you on your adventure.",
            "If you lose, I get to take something from you. Deal? (yes/no)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i", "b", "red"));
        long seed = System.nanoTime(); // Start timer to accumulate time spent in this room to get a random number
    
        while (true) {
            long elapsedTime = System.nanoTime() - seed;
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "yes":
                    BlackJack game = new BlackJack();
                    int result = game.play(scanner);
    
                    // Handle game result
                    if (result == 1) {
                        game.GiveItem(player);
                    } else if (result == -1) {
                        game.TakeItem(player);
                    }
    
                    // Ask if the player wants to play again
                    TextEngine.pt(Handler.applyStyle("Do you want to play again? (yes/no)", "i", "b", "red"));
                    command = scanner.nextLine().toLowerCase();
                    if (command.equals("no")) {
                        TextEngine.pt(Handler.applyStyle("You decided not to play anymore and went back to the Northern Depths.", "i", "darkgrey"));
                        northernDepths(scanner);
                        return;
                    }
                    break;
                case "no":
                    TextEngine.pt(Handler.applyStyle("You declined and went back to the Northern Depths.", "i", "darkgrey"));
                    northernDepths(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }    

    public static void whisperingCrevice(Scanner scanner) {
        String[] text = {
            "\nYou step cautiously into the shadowy crevice, the whispers growing louder with each step.",
            "The sound seems to emanate from faintly glowing stones scattered on the ground.",
            "The air here is thick and humid, making it hard to breathe.",
            "What do you do? (search/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "search":
                    TextEngine.pt(Handler.applyStyle("You search the area carefully, avoiding the sharp rocks.", "i", "darkgrey"));
                    TextEngine.pt(Handler.applyStyle("You find a mysterious glowing stone! It radiates warmth despite the cold air.", "i", "darkgrey"));
                    player.addItem("Glowing Stone");
                    break;
                case "back":
                    gameMap.moveTo("east");
                    northernDepths(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void unknownPathway(Scanner scanner) {
        String[] text = {
            "\nYou walk down the narrow pathway. The walls seem to close in around you, and the sound of dripping water echoes eerily.",
            "Strange markings cover the walls, telling a story you cannot decipher.",
            "As you progress, you see a pile of bones and debris ahead.",
            "What do you do? (search/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "search":
                    TextEngine.pt(Handler.applyStyle("You sift through the pile of bones and debris.", "i", "darkgrey"));
                    TextEngine.pt(Handler.applyStyle("Among the remains, you find a rusted dagger. It might still be useful in the right hands.", "i", "darkgrey"));
                    player.addItem("Rusted Dagger");
                    break;
                case "back":
                    gameMap.moveTo("west");
                    northernDepths(scanner);
                    return; 
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void glowingPassage(Scanner scanner) {
        String[] text = {
            "\nYou arrive at a glowing passage, its walls shimmering faintly with an ethereal light.",
            "At the center of the passage, you see a small circular indentation in the wall, glowing faintly.",
            "It looks like something could fit into it to unlock the way forward."
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            if (player.hasItem("Glowing Stone")) {
                TextEngine.pt(Handler.applyStyle("Place the Glowing Stone into the indentation? (yes/no/back)", "i", "darkgrey"));
                String command = scanner.nextLine().toLowerCase();
                String[] parts = Handler.parseCommand(command);
                switch (parts[0]) {
                    case "use":
                    case "yes":
                        TextEngine.pt(Handler.applyStyle("You place the Glowing Stone into the indentation.", "i", "darkgrey"));
                        TextEngine.pt(Handler.applyStyle("The walls tremble, and a doorway slowly reveals itself, granting you access to the passage beyond.", "i", "darkgrey"));
                        player.useItem("Glowing Stone");
                        gameMap.moveTo("south");
                        unlockedPassage(scanner);
                        return;
                    case "no":
                        TextEngine.pt(Handler.applyStyle("You decide not to use the Glowing Stone.", "i", "darkgrey"));
                        break;
                    case "back":
                        gameMap.moveTo("east");
                        northernDepths(scanner);
                        return;
                    case "inventory":
                        System.out.println(player.showInventory());
                        break;
                    case "map":
                        gameMap.displayMap();
                        break;
                    default:
                        TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
                        break;
                }
            } else {
                TextEngine.pt(Handler.applyStyle("You don't have the Glowing Stone. Without it, the passage remains sealed. (back)", "i", "darkgrey"));
                String command = scanner.nextLine().toLowerCase();
                String[] parts = Handler.parseCommand(command);
                switch (parts[0]) {
                    case "use":
                        player.useItem(parts[1]);
                        break;
                    case "back":
                        gameMap.moveTo("east");
                        northernDepths(scanner);
                        return;
                    case "inventory":
                        System.out.println(player.showInventory());
                        break;
                    case "map":
                        gameMap.displayMap();
                        break;
                    default:
                        TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
                        break;
                }
            }
        }
    }

    public static void unlockedPassage(Scanner scanner) {
        String[] text = {
            "\nYou step through the newly unlocked passage. The air feels warmer here, and the glow from the walls begins to fade.",
            "The room opens into three different paths:",
            "To the east, you hear the faint sound of running water.",
            "To the west, a deep rumbling echoes through the walls.",
            "To the south, the path slopes downward into darkness.",
            "Which direction do you choose? (east/west/south/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "east":
                    gameMap.moveTo("east");
                    flowingWaterRoom(scanner); 
                    return;
                case "west":
                    gameMap.moveTo("west");
                    rumblingChamber(scanner); 
                    return;
                case "south":
                    gameMap.moveTo("south");
                    slopingDarkness(scanner); 
                    return;
                case "back":
                case "north":
                    gameMap.moveTo("north");
                    unlockedPassage(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void flowingWaterRoom(Scanner scanner) {
        String[] text = {
            "\nYou step into the Flowing Water Room. The sound of rushing water echoes around you, and you see a small stream cutting through the center of the room.",
            "The air feels cool and damp. You notice a narrow tunnel to the north, and to the west, the way leads back to the Unlocked Passageway.",
            "Where do you want to go? (north/east/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "north":
                    gameMap.moveTo("north");
                    crystalPool(scanner);
                    return;
                case "east":
                    gameMap.moveTo("east");
                    mysticCavern(scanner);
                    return;
                case "back":
                    gameMap.moveTo("west");
                    unlockedPassage(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void crystalPool(Scanner scanner) {
        String[] text = {
            "\nYou arrive at the Crystal Pool. The water shimmers with an ethereal glow, and the room is eerily quiet.",
            "The air is cool, and you feel a strange sense of calm.",
            "The only exit is to the south, leading back to the Flowing Water Room.",
            "What do you do? (back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                case "south":
                    gameMap.moveTo("south");
                    flowingWaterRoom(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }    

    public static void mysticCavern(Scanner scanner) {
        String[] text = {
            "\nYou enter the Mystic Cavern. Strange symbols are carved into the walls, and faint whispers seem to echo around you.",
            "The atmosphere feels heavy, as if the room itself is alive.",
            "The only exit is to the west, leading back to the Flowing Water Room.",
            "What do you do? (bakc)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "west":
                    gameMap.moveTo("west");
                    flowingWaterRoom(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }   
    
    public static void rumblingChamber(Scanner scanner) {
        String[] text = {
            "\nYou enter the Rumbling Chamber. The ground beneath your feet vibrates with a low, constant rumble, as if the earth itself is alive.",
            "The chamber is vast and dimly lit, with jagged walls casting eerie shadows.",
            "To the north, a narrow path leads to the Echoing Hall. To the south, rubble marks the entrance to the Collapsed Tunnel.",
            "To the east, you can return to the Unlocked Passageway.",
            "Where do you want to go? (north/south/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "north":
                    gameMap.moveTo("north");
                    echoingHall(scanner); 
                    return;
                case "south":
                    gameMap.moveTo("south");
                    collapsedTunnel(scanner); 
                    return;
                case "back":
                case "east":
                    gameMap.moveTo("east");
                    unlockedPassage(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }
    
    public static void echoingHall(Scanner scanner) {
        String[] text = {
            "\nYou enter the Echoing Hall. The sound of your footsteps reverberates endlessly, creating an eerie atmosphere.",
            "Dust covers the floor, and you spot a chest partially buried under debris. A faint glimmer catches your eye.",
            "You can search the room or head back south to the Rumbling Chamber.",
            "What do you do? (search/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        boolean hasSearched = false;
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "search":
                    if (!hasSearched) {
                        TextEngine.pt(Handler.applyStyle("You search the room and find a sturdy shield and a small pouch of gold coins.", "i", "darkgrey"));
                        player.addItem("Sturdy Shield");
                        player.addItem("Gold Coins");
                        hasSearched = true;
                    } else {
                        TextEngine.pt(Handler.applyStyle("You've already searched this room. There's nothing else to find.", "i", "darkgrey"));
                    }
                    break;
                case "back":
                case "south":
                    gameMap.moveTo("south");
                    rumblingChamber(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void collapsedTunnel(Scanner scanner) {
        String[] text = {
            "\nYou step cautiously into the Collapsed Tunnel. The air is thick with dust, and the ground is uneven.",
            "Amidst the rubble, you spot a satchel and a gleaming bottle partially buried under rocks.",
            "You can search the room or head back north to the Rumbling Chamber.",
            "What do you do? (search/north/clear)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        boolean hasSearched = false;
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "search":
                    if (!hasSearched) {
                        TextEngine.pt(Handler.applyStyle("You search the rubble and find a health potion and a satchel of dried food.", "i", "darkgrey"));
                        player.addItem("Health Potion");
                        player.addItem("Dried Food");
                        hasSearched = true;
                    } else {
                        TextEngine.pt(Handler.applyStyle("You've already searched this room. There's nothing else to find.", "i", "darkgrey"));
                    }
                    break;
                case "clear":
                    gameMap.setLocation("Acrius", "Hidden Temple");
                    hiddenTemple(scanner);
                    return;
                case "north":
                    gameMap.moveTo("north");
                    rumblingChamber(scanner); 
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void slopingDarkness(Scanner scanner) {
        String[] text = {
            "\nYou find yourself in the Sloping Darkness. The pathway slopes downward, and the air grows colder with every step.",
            "The faint sound of dripping water echoes through the chamber, and the walls are rough to the touch.",
            "To the north lies the Unlocked Passageway. To the east, you see a faint glow emanating from a narrow opening.",
            "To the west, shadows deepen into an ominous alcove.",
            "Where do you want to go? (east/west/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                case "north":
                    gameMap.moveTo("north");
                    unlockedPassage(scanner);
                    return;
                case "east":
                    gameMap.moveTo("east");
                    luminousChamber(scanner);
                    return;
                case "west":
                    gameMap.moveTo("west");
                    shadowedAlcove(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }    

    public static void luminousChamber(Scanner scanner) {
        String[] text = {
            "\nYou step into the Luminous Chamber. The walls glow faintly with a bioluminescent moss, casting an otherworldly light.",
            "The air here is warm and slightly humid, making the chamber feel oddly alive.",
            "The only exit is to the west, leading back to the Sloping Darkness.",
            "What do you do? (back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                case "west":
                    gameMap.moveTo("west");
                    slopingDarkness(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }    

    public static void shadowedAlcove(Scanner scanner) {
        String[] text = {
            "\nYou step into the Shadowed Alcove. The darkness here feels almost tangible, broken only by faint streaks of light from cracks in the walls.",
            "The air is cool and silent, amplifying the sense of isolation.",
            "The only exit is to the east, leading back to the Sloping Darkness.",
            "What do you do? (back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));
    
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                case "east":
                    gameMap.moveTo("east");
                    slopingDarkness(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }    

    public static void stairCaseIntoDarkness(Scanner scanner) {
        String[] text = {
            "\nYou descend the spiraling staircase, the air growing colder with each step.",
            "The faint light above disappears, and you find yourself in total darkness.",
            "You feel the stone walls, searching for some kind of clue when your hand brushes against a rusty lever.",
            "Do you pull the lever to your north or keep searching in the dark? (pull/search/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "pull":
                    gameMap.moveTo("west");
                    ironDoor(scanner); 
                    return;
                case "search":
                    if (!player.hasSearchedStairCaseIntoDarkness) {
                        TextEngine.pt(Handler.applyStyle("You search the dark room and find a small key. You pocket it for later use.", "i", "darkgrey"));
                        TextEngine.pt(Handler.applyStyle("You also find a 'Torch' which you light, illuminating the staircase.", "i", "darkgrey"));
                        player.addItem("Ancient Key");
                        player.addItem("Torch");
                        player.hasSearchedStairCaseIntoDarkness = true;
                    } else {
                        TextEngine.pt(Handler.applyStyle("You've already searched this room. You can't find anything else. (pull/back)", "i", "darkgrey"));
                    }
                    break;
                case "back":
                    gameMap.moveTo("east");
                    hiddenPassageway(scanner); 
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void ironDoor(Scanner scanner) {
        String[] text = {
            "\nThe lever creaks loudly as you pull it down. Suddenly, torches along the walls ignite, lighting up the room.",
            "Ahead, you see an iron door. It's locked tight, with strange engravings of keys on the handle.",
            "It seems you need a special key to open this door."
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            if (player.hasItem("Ancient Key")) {
                TextEngine.pt(Handler.applyStyle("Try the ancient key? (yes/no/back)", "i", "darkgrey"));
                String command = scanner.nextLine().toLowerCase();
                String[] parts = Handler.parseCommand(command);
                switch (parts[0]) {
                    case "yes":
                        gameMap.moveTo("west");
                        darkRoom(scanner);
                        return;
                    case "no":
                        TextEngine.pt(Handler.applyStyle("You decide not to use the key.", "i", "darkgrey"));
                        break;
                    case "back":
                        gameMap.moveTo("east");
                        stairCaseIntoDarkness(scanner);
                        return;
                    case "inventory":
                        System.out.println(player.showInventory());
                        break;
                    case "map":
                        gameMap.displayMap();
                        break;
                    default:
                        TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
                }
            } else {
                TextEngine.pt(Handler.applyStyle("You don't have the key. You can't open the door. (back)", "i", "darkgrey"));
                String command = scanner.nextLine().toLowerCase();
                String[] parts = Handler.parseCommand(command);
                switch (parts[0]) {
                    case "back":
                        gameMap.moveTo("east");
                        stairCaseIntoDarkness(scanner);
                        return;
                    case "inventory":
                        System.out.println(player.showInventory());
                        break;
                    case "map":
                        gameMap.displayMap();
                        break;
                    default:
                        TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
                }
            }
        }
    }


    public static void darkRoom(Scanner scanner) {
        String[] text = {
            "\nYou open the door and find yourself in a dark room.",
            "The torch dimly lights the room. You find a medkit and some food.",
            "Where do you want to go now? (back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        player.addItem("medkit");
        player.addItem("food");

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                    gameMap.moveTo("east");
                    ironDoor(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

}