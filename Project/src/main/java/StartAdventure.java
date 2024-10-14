import java.util.Scanner;

/**
 * Adventure
 * Be more welcoming
 *
 * Terrence, Grant, Chibuikem
 * CS 374
 */

public class StartAdventure {
    // The player
    public static Player player = new Player();
    public static GameMap gameMap;
    // To track the time spent in the game
    private static long startTime;
    private static long roomEntryTime;

    // Timer method to check how much total time has passed
    private static long trackTime() {
        return (startTime) / 1000;
    }

    // Method to check how much time has passed in the current room
    private static long checkRoomTime() {
        return (System.currentTimeMillis() - roomEntryTime) / 1000;
    }

    public static void main(String[] args) {
        gameMap = new GameMap();
        gameMap.addRoom("Start", "Start", "Straight Path", null, "Right Path", "Left Path");
        gameMap.setLocation("Start", "Start");
        startGame();
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);

        String[] text = {
                "Welcome to Acrius, the Dark Realm by Terrence, Chibuikem, and Grant.",
                "You find yourself in a dimly lit cavern with distant echoes. Before you lies a narrow path deeper into the unknown.",
                "Do you want to proceed or go back? (proceed/exit)"
        };
        TextEngine.pt(Handler.applyStyle(text[0], "b", "magenta"));
        for (int i = 1; i < text.length; i++) {
            TextEngine.pt(Handler.applyStyle(text[i], "i"));
        }

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "proceed":
                    startAdventure(scanner);
                    break;
                case "exit":
                    System.out.println("You decide to take the easy way out. Your story ends here.");
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
        String[] text = {
                "\nYou cautiously step forward. After a few minutes, you see an intersection.",
                "Do you take the west path, the east path, or continue north? (west, east, or north)"
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
                    Acrius.leftPath(scanner);
                    break;
                case "east":
                    gameMap.moveTo("east");
                    rightPath(scanner);
                    break;
                case "north":
                    gameMap.addRoom("Diddy", "Straight Path", null, "Start", null, null);
                    gameMap.moveTo("north");
                    Diddy.straightPath(scanner);
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
        String[] text = {
                "\nThe right path takes you through a narrow tunnel. It widens into a large chamber filled with old mining equipment.",
                "You see a broken elevator that descends further underground and a ladder leading upward.",
                "Do you take the elevator or the ladder? (elevator/ladder)"
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
            String[] parts = Handler.parseCommand(command);
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
        System.out.println(
                "As you move, the air becomes thick with mist, and the ground beneath your feet feels strangely soft.");
        System.out.println(
                "Suddenly, a low growl echoes through the trees, and shadowy figures move between the branches.");
        System.out.println("Do you examine your surrounding or run? (examine/run)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "examine":

                    System.out.println("You draw the weapon from the gravestone and prepare to face the unkown.");
                    System.out.println(
                            "Out of the darkness, a pack of feral creatures emerges, their red glowing eyes fixed on you.");
                    System.out.println("Do you fight or run? (fight/run)");// still trying to figure this part out.
                    break;
                case "fight":
                    System.out.println(
                            "You look around trying to find something to fight with, do you continue to look, or search your inventory (inventory/search)");
                    break; // we should add so if the sword is here then you can kill the,
                case "search":
                    System.out.println(
                            "You look frantically around but the only thing you can find to arm yourself with is a stick.");
                    System.out.println("Do you pick it up or run (pickup/run)");
                    break;
                case "pickup":
                    System.out.println(
                            "Your reach down and try to pick up the stick, but as you fumble with it dark creatures maul you from behind.");
                    System.out.println("You have died"); // end thing here
                    break;
                case "run":
                    attemptToRun();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
            command = scanner.nextLine(); // Allow player to input new commands
        }
    }

    // Helper method for handling the "examine" option
    private static void handleExamine(Scanner scanner) {
        System.out.println("You draw the weapon from the gravestone and prepare to face the unknown.");
        System.out.println(
                "Out of the darkness, a pack of feral creatures emerges, their red glowing eyes fixed on you.");
        System.out.println("Do you fight or run? (fight/run)");

        String action = scanner.nextLine();
        switch (action) {
            case "fight":
                startFight(); // Call to a fight logic handler
                break;
            case "run":
                attemptToRun();
                break;
            default:
                System.out.println("Invalid choice. Do you fight or run?");
                handleExamine(scanner); // Recurse until a valid choice is made
        }
    }

    // Method to handle the fight scenario
    private static void startFight() {
        System.out.println("You stand your ground, ready to fight!");
        // Fight logic can be implemented here, such as health, attack, etc.
    }

    // Method to handle running away
    private static void attemptToRun() {
        System.out.println("You turn and sprint back the way you came, the creatures close behind you.");
        System.out.println("You make it back to the clearing, but the creatures are still close behind you.");
        System.out.println(
                "Suddenly, a figure cloaked in all black, riding a dark horse, appears from the shadows. With a swift motion, he lashes a whip around your leg and yanks you back towards the forest as the feral creatures close in, their growls echoing in the mist.");

        // Running away logic can be expanded here (chance of escape, etc.)
    }

}