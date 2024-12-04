import java.util.Scanner;

public class Grantfell {
    public static Player player = StartAdventure.player;
    public static GameMap gameMap = StartAdventure.gameMap;

    public static void centralSpire(Scanner scanner) {
        gameMap.setLocation("Grantfell", "Central Spire");
        String text = "\nYou arrive at the Central Spire, the heart of Grantfell's forgotten grandeur.\n" +
                "Options: [explore/inventory/map/use/time/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "explore":
                    exploreCentralSpire(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                    StartAdventure.startAdventure(scanner);
                    return;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void exploreCentralSpire(Scanner scanner) {
        String text = "\nThe Central Spire towers above, a monument to lost technology.\n" +
                "Options: [east] Glass Gardens, [west] Obsidian Nexus, [north] Skybridge Ruins, [south] Industrial Sector, [inventory/map/use/time/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "east":
                    gameMap.moveTo("east");
                    glassGardens(scanner);
                    return;
                case "west":
                    gameMap.moveTo("west");
                    obsidianNexus(scanner);
                    return;
                case "north":
                    gameMap.moveTo("north");
                    skybridgeRuins(scanner);
                    return;
                case "south":
                    gameMap.moveTo("south");
                    industrialSector(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                    centralSpire(scanner);
                    return;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid direction. Please try again.", "i", "red"));
            }
        }
    }

    public static void glassGardens(Scanner scanner) {
        String text = "\nThe Glass Gardens shimmer with vibrant colors as sunlight streams through.\n" +
                "Options: [west] Central Spire, [east] Overgrown Atrium, [inventory/map/use/time/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "back":
                case "west":
                    gameMap.moveTo("west");
                    exploreCentralSpire(scanner);
                    return;
                case "east":
                    gameMap.moveTo("east");
                    overgrownAtrium(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void obsidianNexus(Scanner scanner) {
        String text = "\nThe Obsidian Nexus is dark, with glowing black crystals illuminating faint energy.\n" +
                "Options: [east] Central Spire, [south] Sundial Plaza, [inventory/map/use/time/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "back":
                case "east":
                    gameMap.moveTo("east");
                    exploreCentralSpire(scanner);
                    return;
                case "south":
                    gameMap.moveTo("south");
                    sundialPlaza(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void overgrownAtrium(Scanner scanner) {
        String text = "\nThe Overgrown Atrium is filled with vines and ancient remnants of technology.\n" +
                "Options: [west] Glass Gardens, [inventory/map/use/time/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "back":
                case "west":
                    gameMap.moveTo("west");
                    glassGardens(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void skybridgeRuins(Scanner scanner) {
        String text = "\nThe Skybridge Ruins stretch across the skyline, offering a breathtaking view.\n" +
                "Options: [south] Central Spire, [inventory/map/use/time/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "back":
                case "south":
                    gameMap.moveTo("south");
                    exploreCentralSpire(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void industrialSector(Scanner scanner) {
        String text = "\nThe Industrial Sector hums with the remnants of machinery long forgotten.\n" +
                "Options: [north] Central Spire, [inventory/map/use/time/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "back":
                case "north":
                    gameMap.moveTo("north");
                    exploreCentralSpire(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void sundialPlaza(Scanner scanner) {
        String text = "\nThe Sundial Plaza glows faintly, a peaceful center of the forgotten metropolis.\n" +
                "Options: [north] Obsidian Nexus, [east] Crystal Fountain, [west] Market Square, [inventory/map/use/time/help/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "north":
                    gameMap.moveTo("north");
                    obsidianNexus(scanner);
                    return;
                case "east":
                    gameMap.moveTo("east");
                    crystalFountain(scanner);
                    return;
                case "west":
                    gameMap.moveTo("west");
                    marketSquare(scanner);
                    return;
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                    centralSpire(scanner);
                    return;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void crystalFountain(Scanner scanner) {
        String text = "\nThe Crystal Fountain sparkles with refracted light, casting rainbows across the plaza.\n" +
                "Options: [west] Sundial Plaza, [inventory/map/use/time/help/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                case "west":
                    sundialPlaza(scanner);
                    gameMap.moveTo("west");
                    return;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

    public static void marketSquare(Scanner scanner) {
        String text = "\nThe Market Square is a bustling hub of trade, even in the city's forgotten state.\n" +
                "Options: [east] Sundial Plaza, [inventory/map/use/time/help/back]";
        TextEngine.pt(Handler.applyStyle(text, "i"));

        while (true) {
            String command = scanner.nextLine().toLowerCase();
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "inventory":
                    player.showInventory(true);
                    break;
                case "map":
                    gameMap.displayMap();
                    break;
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "help":
                    player.getHelp();
                    break;
                case "time":
                    player.getCurrentTime();
                    break;
                case "back":
                case "east":
                    sundialPlaza(scanner);
                    gameMap.moveTo("east");
                    return;
                default:
                    TextEngine.pt(Handler.applyStyle("Invalid command. Please try again.", "i", "red"));
            }
        }
    }

}
