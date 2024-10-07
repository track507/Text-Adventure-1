import java.util.Scanner;
import Player.Player;
/**
 * Adventure
 * Be more welcoming
 *
 * Terrence, Grant, Chibuikem
 * CS 374
 */

public class Adventure {
    // The player
    private static Player player = new Player();
    // To track the time spent in the game
    private static long startTime;
    private static long roomEntryTime;

    private static String[] parseCommand(String string) {
        // Split the string into an array of two elements
        String[] parts = string.split(" ", 2);
        String action = parts[0];
        // If there is no argument, use an empty string
        String argument = parts.length > 1 ? parts[1] : "";
        // Return an array with the action and argument
        return new String[] { action, argument };
    }

    // Timer method to check how much total time has passed
    private static long trackTime() {
        return (startTime) / 1000;
    }

    // Method to check how much time has passed in the current room
    private static long checkRoomTime() {
        return (System.currentTimeMillis() - roomEntryTime) / 1000;
    }

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        startTime = System.currentTimeMillis();

        System.out.println("\nWelcome to Acrius, the Dark Realm by Terrence, Grant, and Chibuikem.");
        System.out.println("You find yourself in a dimly lit cavern with distant echoes. Before you lies a narrow path deeper into the unknown.");
        System.out.println("Do you want to proceed or go back? (proceed/exit)");

        String command = scanner.nextLine();
        while (true) {

            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "proceed":
                    startAdventure(scanner);
                    break;
                case "exit":
                    System.out.println("You decide not to venture forward. The game ends here.");
                    System.exit(0);
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    // Start the adventure
    public static void startAdventure(Scanner scanner) {
        roomEntryTime = System.currentTimeMillis();
        System.out.println("\nYou cautiously step forward. After a few minutes, you see an intersection.");
        System.out.println("Do you take the left path, the right path, or continue straight? (left/right/straight)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "left":
                    leftPath(scanner);
                    break;
                case "right":
                    rightPath(scanner);
                    break;
                case "straight":
                    straightPath(scanner);
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

    // Left path scenario
    public static void leftPath(Scanner scanner) {
        System.out.println("\nYou take the left path and find yourself in a vast underground riverbank.");
        System.out.println("A boat is tied to the shore. Do you take the boat or explore the cave further on foot? (boat/foot/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = parseCommand(command);
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
                    startAdventure(scanner);
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

        boolean hasSearched = false;

        String command = scanner.nextLine();
        while (true) {
            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "search":
                    if(!hasSearched) {
                        System.out.println("You find an iron sword, a buckler, and a health potion. You pocket them for later use.");
                        player.addItem("iron sword");
                        player.addItem("buckler");
                        player.addItem("health potion");
                        hasSearched = true;
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
            String[] parts = parseCommand(command);
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
            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "decipher":
                    hiddenPassageway(scanner);
                    break;
                case "search":
                    System.out.println("You search the temple and find a powerful artifact, but the ceiling starts to collapse. You barely escape!");
                    // collapsedTemple(scanner);
                    break;
                case "back":
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
            String[] parts = parseCommand(command);
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
            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "pull":
                    ironDoor(scanner);
                    break;
                case "search":
                    if (!player.hasItem("Ancient Key")) {
                        System.out.println("You search the dark room and find a small key. You pocket it for later use.");
                        System.out.println("You also find a 'Torch' which you light, illuminating the staircase. (pull/back)");
                        player.addItem("Ancient Key");
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
                System.out.println("\n\033[3;90mTry the ancient key? (yes/no)\033[0m");
                command = scanner.nextLine();
                String[] parts = parseCommand(command);
                switch (parts[0]) {
                    case "yes":
                        darkRoom(scanner);
                        break;
                    case "no":
                        System.out.println("You decide not to use the key.");
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
                    default:
                        System.out.println("Invalid command. Please try again.");
                        command = scanner.nextLine();
                }
            } else if (!player.hasItem("Ancient Key")) {
                System.out.println("You don't have the key. You can't open the door. (back)");
                command = scanner.nextLine();
                String[] parts = parseCommand(command);
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
            String[] parts = parseCommand(command);
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

    // Right path scenario
    public static void rightPath(Scanner scanner) {
        System.out.println("\nThe right path takes you through a narrow tunnel. It widens into a large chamber filled with old mining equipment.");
        System.out.println("You see a broken elevator that descends further underground and a ladder leading upward.");
        System.out.println("Do you take the elevator or the ladder? (elevator/ladder)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "elevator":
                    // brokenElevator(scanner);
                    break;
                case "ladder":
                    // upperChamber(scanner);
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

    // Straight path scenario
    // Apologize chibuikem is gonna mess with this
    public static void straightPath(Scanner scanner) {
        System.out
                .println("\nYou walk straight ahead and come across a sign that says 'Welcome to Chibuikem's realm'.");
        System.out.println("You find yourself in a dense underground forest. It's dark, but the trees seem alive");
        System.out.println("Do you explore deeper into the forest or turn back? (explore/back)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "back":
                    startAdventure(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                    break;
                case "explore":
                    exploreForest(scanner);
                    System.out.println("You venture deeper into the forest ...");// chibuikem: I will add more later
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    // TODO: chibuikem write in how to get the sword from the tomb stone after
    // examining
    // TODO: chibuikem complete the scenario for when you turn to run away from the
    // creatures.
    public static void exploreForest(Scanner scanner) {
        System.out.println("You venture deeper into the forest, the trees closing in around you.");
        System.out.println("As you move, the air becomes thick with mist, and the ground beneath your feet feels strangely soft.");
        System.out.println("Suddenly, a low growl echoes through the trees, and shadowy figures move between the branches.");
        System.out.println("Do you examine your surrounding or run? (examine/run)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "examine":

                    System.out.println("You draw the weapon from the gravestone and prepare to face the unkown.");
                    System.out.println("Out of the darkness, a pack of feral creatures emerges, their glowing eyes fixed on you.");
                    System.out.println("Do you fight or run? (fight/run)");// still trying to figure this part out.
                    break;
                case "run":
                    // attemptToRun();
                    System.out.println("You turn and sprint back the way you came, the creatures close behind you.");
                    break;// will add more to this later

            }

        }

    }

}