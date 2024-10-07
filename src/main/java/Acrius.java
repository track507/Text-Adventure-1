import java.util.Scanner;

public class Acrius {
    public static Player player = StartAdventure.player;

    // Left path scenario
    public static void leftPath(Scanner scanner) {
        System.out.println("\nYou take the left path and find yourself in a vast underground riverbank.");
        System.out.println("A boat is tied to the shore. Do you take the boat or explore the cave further on foot? (boat/foot/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "boat":
                    boatRide(scanner);
                    break;
                case "foot":
                    exploreCave(scanner);
                    break;
                case "back":
                    StartAdventure.startAdventure(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void exploreCave(Scanner scanner) {
        System.out.println("\nYou chose to explore the cave further on foot. You make your way deeper into the cave.");
        System.out.println("You find three skeletons with their bags and backpacks on the ground.");
        System.out.println("Do you want to search the skeletons or go back? (search/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "search":
                    if(!player.hasSearchedCave) {
                        System.out.println("You find an iron sword, a buckler, and a health potion. You pocket them for later use.");
                        player.addItem("iron sword");
                        player.addItem("buckler");
                        player.addItem("health potion");
                        player.hasSearchedCave = true;
                    }
                    else { 
                        System.out.println("You've already searched this room. You can't find anything else.");
                    }
                    command = scanner.nextLine();
                    break;
                case "back":
                    leftPath(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void boatRide(Scanner scanner) {
        System.out.println("\nYou untie the boat and paddle down the eerie river. You soon encounter a waterfall.");
        System.out.println("Do you abandon the boat and swim ashore or ride over the waterfall? (swim/ride/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "swim":
                    hiddenTemple(scanner);
                    break;
                case "ride":
                    // darkCavern(scanner);
                    break;
                case "back":
                    leftPath(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void hiddenTemple(Scanner scanner) {
        System.out.println("\nYou swim ashore and find a small cave entrance. You venture inside, discovering ancient carvings.");
        System.out.println("You find yourself in an ancient temple. Strange symbols cover the walls.");
        System.out.println("Do you want to decipher the symbols or search the temple for items? (decipher/search/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "decipher":
                    hiddenPassageway(scanner);
                    break;
                case "search":
                    if(!player.hasSearchedHiddenTemple) {
                        System.out.println("You search the temple and find a map. You can now track your progress. But the cave starts to collapse. You barely escape!");
                        player.addItem("map");
                    } else {
                        System.out.println("You've already searched this area. You can't find anything else.");
                    }
                    break;
                case "back":
                    if(player.hasSearchedHiddenTemple) { 
                        System.out.println("n\033[3;90mYou can't go back. The debris is blocking your path...\033[0m");
                        break; //can no longer go back
                    }
                    boatRide(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void hiddenPassageway(Scanner scanner) {
        System.out.println("\nYou decipher the symbols and unlock a hidden passageway.");
        System.out.println("The air is cool, and the walls seem to close in around you.");
        System.out.println("To your left, a narrow staircase spirals downward into the darkness, while to your right, a heavy door creaks open, revealing a tunnel bathed in faint, flickering light.");
        System.out.println("Do you descend the staircase or venture into the tunnel? (staircase/tunnel/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "staircase":
                    stairCaseIntoDarkness(scanner);
                    break;
                case "tunnel":
                    // faintTunnel(scanner);
                    break;
                case "back":
                    hiddenTemple(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void stairCaseIntoDarkness(Scanner scanner) {
        System.out.println("\nYou descend the spiraling staircase, the air growing colder with each step.");
        System.out.println("The faint light above disappears, and you find yourself in total darkness.");
        System.out.println("You feel the stone walls, searching for some kind of clue when your hand brushes against a rusty lever.");
        System.out.println("Do you pull the lever or keep searching in the dark? (pull/search/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "pull":
                    ironDoor(scanner);
                    break;
                case "search":
                    if (!player.hasSearchedStairCaseIntoDarkness) {
                        System.out.println("You search the dark room and find a small key. You pocket it for later use.");
                        System.out.println("You also find a 'Torch' which you light, illuminating the staircase. (pull/back)");
                        player.addItem("Ancient Key");
                        player.hasSearchedStairCaseIntoDarkness = true;
                    }
                    else {
                        System.out.println("You've already searched this room. You can't find anything else. (pull/back)");
                    }
                    command = scanner.nextLine();
                    break;
                case "back":
                    hiddenPassageway(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void ironDoor(Scanner scanner) {
        System.out.println("\nThe lever creaks loudly as you pull it down. Suddenly, torches along the walls ignite, lighting up the room.");
        System.out.println("Ahead, you see an iron door. It's locked tight, with strange engravings of keys on the handle.");
        System.out.println("It seems you need a special key to open this door.");
        String command;
        while (true) {
            if (player.hasItem("Ancient Key")) {
                System.out.println("\n\033[3;90mTry the ancient key? (yes/no/back)\033[0m");
                command = scanner.nextLine();
                String[] parts = Handler.parseCommand(command);
                switch (parts[0]) {
                    case "back":
                        stairCaseIntoDarkness(scanner);
                        break;
                    case "yes":
                        darkRoom(scanner);
                        break;
                    case "no":
                        System.out.println("You decide not to use the key.");
                        ironDoor(scanner);
                        break;
                    case "use":
                        player.useItem(parts[1]);
                        command = scanner.nextLine();
                        break;
                    case "inventory":
                        System.out.println(player.showInventory());
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
                    default:
                        System.out.println("Invalid command. Please try again.");
                }
            }
        }
    }

    public static void darkRoom(Scanner scanner) {
        System.out.println("\nThe door opens and you find yourself in a dark room.");
        System.out.println("The torch dimly lights the room. You find a medkit and some food.");
        System.out.println("Where do you want to go now? (back)");
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
                    ironDoor(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }
}