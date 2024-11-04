import java.util.Scanner;

public class Acrius {
    public static Player player = StartAdventure.player;
    public static GameMap gameMap = StartAdventure.gameMap;

    // Left path scenario
    public static void leftPath(Scanner scanner) {
        gameMap.setLocation("Acrius", "Left Path");
        String text = "\nYou chose the west path. You see a large cave with a wide riverbank. A boat is tied to the shore.\nDo you north towards the boat or west to explore the cave further on foot? (north/west/back)";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
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
                    command = scanner.nextLine();
                case "map":
                    gameMap.displayMap();
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
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

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
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
                    command = scanner.nextLine();
                    break;
                case "back":
                    gameMap.moveTo("east");
                    leftPath(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                case "map":
                    gameMap.displayMap();
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void boatRide(Scanner scanner) {
        String[] text = {
                "\nYou untie the boat and paddle down the eerie river. You soon encounter a waterfall.",
                "Do you abandon the boat and swim west to the shore or keep going north and ride over the waterfall? (west/north/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "west":
                    gameMap.moveTo("west");
                    hiddenTemple(scanner);
                    break;
                case "north":
                    // gameMap.moveTo("north");
                    // darkCavern(scanner);
                    break;
                case "back":
                    gameMap.moveTo("south");
                    leftPath(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                case "map":
                    gameMap.displayMap();
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
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

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "decipher":
                    gameMap.moveTo("west");
                    hiddenPassageway(scanner);
                    break;
                case "search":
                    if (!player.hasSearchedHiddenTemple) {
                        System.out.println("You search the temple and find a map. You can now track your progress. But the cave starts to collapse. You barely escape!");
                        player.addItem("map");
                        player.hasSearchedHiddenTemple = true;
                    } else {
                        System.out.println("You've already searched this area. You can't find anything else.");
                    }
                    command = scanner.nextLine();
                    break;
                case "back":
                    if (player.hasSearchedHiddenTemple) {
                        TextEngine.pt(Handler.applyStyle("\nYou can't go back. The debris is blocking your path...", "i", "darkGrey"));
                        break; // can no longer go back
                    }
                    gameMap.moveTo("east");
                    boatRide(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                case "map":
                    gameMap.displayMap();
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void hiddenPassageway(Scanner scanner) {
        String[] text = {
                "\nYou decipher the symbols and unlock a hidden passageway.",
                "The air is cool, and the walls seem to close in around you.",
                "To your west, a narrow staircase spirals downward into the darkness, while to your east, a heavy door creaks open, revealing a tunnel bathed in faint, flickering light.",
                "Do you descend the staircase or venture into the tunnel? (staircase/tunnel/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "staircase":
                    gameMap.moveTo("west");
                    stairCaseIntoDarkness(scanner);
                    break;
                case "tunnel":
                    // gameMap.moveTo("north");
                    // faintTunnel(scanner);
                    break;
                case "back":
                    gameMap.moveTo("east");
                    hiddenTemple(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                case "map":
                    gameMap.displayMap();
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
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

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "pull":
                    gameMap.moveTo("west");
                    ironDoor(scanner);
                    break;
                case "search":
                    if (!player.hasSearchedStairCaseIntoDarkness) {
                        System.out.println("You search the dark room and find a small key. You pocket it for later use.");
                        System.out.println("You also find a 'Torch' which you light, illuminating the staircase. (pull/back)");
                        player.addItem("Ancient Key");
                        player.hasSearchedStairCaseIntoDarkness = true;
                    } else {
                        System.out.println("You've already searched this room. You can't find anything else. (pull/back)");
                    }
                    command = scanner.nextLine();
                    break;
                case "back":
                    gameMap.moveTo("south");
                    hiddenPassageway(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                    break;
                case "map":
                    gameMap.displayMap();
                    command = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void ironDoor(Scanner scanner) {
        gameMap.addRoom("Acrius", "Iron Door", null, null, "Staircase into Darkness", null);
        String[] text = {
                "\nThe lever creaks loudly as you pull it down. Suddenly, torches along the walls ignite, lighting up the room.",
                "Ahead, you see an iron door. It's locked tight, with strange engravings of keys on the handle.",
                "It seems you need a special key to open this door."
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        String command;
        while (true) {
            if (player.hasItem("Ancient Key")) {
                System.out.println("\n\033[3;90mTry the ancient key? (yes/no/back)\033[0m");
                command = scanner.nextLine();
                String[] parts = Handler.parseCommand(command);
                switch (parts[0]) {
                    case "back":
                        gameMap.moveTo("east");
                        stairCaseIntoDarkness(scanner);
                        break;
                    case "yes":
                        gameMap.moveTo("west");
                        darkRoom(scanner);
                        break;
                    case "no":
                        System.out.println("You decide not to use the key.");
                        ironDoor(scanner);
                        command = scanner.nextLine();
                        break;
                    case "use":
                        player.useItem(parts[1]);
                        command = scanner.nextLine();
                        break;
                    case "inventory":
                        System.out.println(player.showInventory());
                        command = scanner.nextLine();
                        break;
                    case "map":
                        gameMap.displayMap();
                        command = scanner.nextLine();
                        break;
                    default:
                        System.out.println("Invalid command. Please try again.");
                        command = scanner.nextLine();
                }
            } else if (!player.hasItem("Ancient Key")) {
                System.out.println("You don't have the key. You can't open the door. (back)");
                command = scanner.nextLine();
                String[] parts = Handler.parseCommand(command);
                switch (parts[0]) {
                    case "back":
                        gameMap.moveTo("east");
                        stairCaseIntoDarkness(scanner);
                        break;
                    case "use":
                        player.useItem(parts[1]);
                        command = scanner.nextLine();
                        break;
                    case "inventory":
                        System.out.println(player.showInventory());
                        command = scanner.nextLine();
                        break;
                    case "map":
                        gameMap.displayMap();
                        command = scanner.nextLine();
                        break;
                    default:
                        System.out.println("Invalid command. Please try again.");
                        command = scanner.nextLine();
                        break;
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
        String command = scanner.nextLine();

        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "back":
                    gameMap.moveTo("east");
                    ironDoor(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
                    break;
            }
        }
    }
}