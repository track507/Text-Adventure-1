import java.util.Scanner;

public class Grantfell {
    public static Player player = new Player();
    public static GameMap gameMap = new GameMap();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Grantfell, the Forgotten Metropolis.");
        startAdventure(scanner);
    }


    public static void startAdventure(Scanner scanner) {
        gameMap.setLocation("Grantfell", "Central Spire"); // Initial location
        System.out.println("You arrive at the Central Spire, the heart of Grantfell’s forgotten grandeur.");
    
        while (true) {
            // Get the current location from the GameMap
            String currentLocation = gameMap.getCurrentLocation(); // This line calls the getCurrentLocation method
            System.out.println("You are currently at: " + currentLocation); // Display current location
    
            System.out.println("Options: [explore] [map] [inventory] [quit]");
            String command = scanner.nextLine().toLowerCase();
    
            switch (command) {
                case "explore":
                    exploreLocation(scanner);
                    break;
    
                case "map":
                    gameMap.displayMap();
                    break;
    
                case "inventory":
                    player.showInventory();
                    break;
    
                case "quit":
                    System.out.println("Thank you for exploring Grantfell.");
                    return;
    
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }
    

    private static void exploreLocation(Scanner scanner) {
        String currentLocation = gameMap.getCurrentLocation();
        switch (currentLocation) {
            case "Central Spire":
                centralSpire(scanner);
                break;

            case "Obsidian Nexus":
                obsidianNexus(scanner);
                break;

            case "Aqua Tunnels":
                aquaTunnels(scanner);
                break;

            case "Cryo Vaults":
                cryoVaults(scanner);
                break;

            case "Skybridge Ruins":
                skybridgeRuins(scanner);
                break;

            default:
                System.out.println("There’s nothing here yet.");
        }
    }

    // Central Spire
    public static void centralSpire(Scanner scanner) {
        System.out.println("The Central Spire towers above, a monument to lost technology.");
        System.out.println("Options: [north] Subterranean Transit Network, [east] Glass Gardens, [west] Memory Vaults, [south] Industrial Sector, [underground] Obsidian Nexus, [up] Skybridge Ruins, [back]");
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "north":
                    gameMap.moveTo("Subterranean Transit Network");
                    subterraneanTransitNetwork(scanner);
                    return;

                case "east":
                    gameMap.moveTo("Glass Gardens");
                    glassGardens(scanner);
                    return;

                case "west":
                    gameMap.moveTo("Memory Vaults");
                    memoryVaults(scanner);
                    return;

                case "south":
                    gameMap.moveTo("Industrial Sector");
                    industrialSector(scanner);
                    return;

                case "underground":
                    gameMap.moveTo("Obsidian Nexus");
                    obsidianNexus(scanner);
                    return;

                case "up":
                    gameMap.moveTo("Skybridge Ruins");
                    skybridgeRuins(scanner);
                    return;

                case "back":
                    return;

                default:
                    System.out.println("Invalid direction. Try again.");
            }
        }
    }

    // Obsidian Nexus
    public static void obsidianNexus(Scanner scanner) {
        System.out.println("You descend into the Obsidian Nexus, where glowing black crystals pulse faintly with energy.");
        System.out.println("Options: [up] Central Spire, [east] Sundial Plaza, [back]");
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "up":
                    gameMap.moveTo("Central Spire");
                    centralSpire(scanner);
                    return;

                case "east":
                    System.out.println("Sundial Plaza is not yet accessible.");
                    break;

                case "back":
                    return;

                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    // Aqua Tunnels
    public static void aquaTunnels(Scanner scanner) {
        System.out.println("The Aqua Tunnels are submerged and illuminated by bioluminescent fish.");
        System.out.println("Options: [south] Subterranean Transit Network, [back]");
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "south":
                    gameMap.moveTo("Subterranean Transit Network");
                    subterraneanTransitNetwork(scanner);
                    return;

                case "back":
                    return;

                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    // Cryo Vaults
    public static void cryoVaults(Scanner scanner) {
        System.out.println("You enter the Cryo Vaults, a frozen storage facility filled with bioengineering experiments.");
        System.out.println("Options: [north] Industrial Sector, [back]");
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "north":
                    gameMap.moveTo("Industrial Sector");
                    industrialSector(scanner);
                    return;

                case "back":
                    return;

                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    // Skybridge Ruins
    public static void skybridgeRuins(Scanner scanner) {
        System.out.println("You climb to the Skybridge Ruins, where collapsed walkways stretch over the city.");
        System.out.println("Options: [down] Central Spire, [back]");
        while (true) {
            String command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "down":
                    gameMap.moveTo("Central Spire");
                    centralSpire(scanner);
                    return;

                case "back":
                    return;

                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    

    // Placeholder methods for existing rooms
    public static void subterraneanTransitNetwork(Scanner scanner) { /* Placeholder */ }
    public static void glassGardens(Scanner scanner) { /* Placeholder */ }
    public static void memoryVaults(Scanner scanner) { /* Placeholder */ }
    public static void industrialSector(Scanner scanner) { /* Placeholder */ }
}
