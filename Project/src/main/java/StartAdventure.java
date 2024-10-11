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
        startGame();
    }

    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        startTime = System.currentTimeMillis();

        System.out.println("\nWelcome to Acrius, the Dark Realm by Terrence, Chibuikem, and Grant.");
        System.out.println(
                "You find yourself in a dimly lit cavern with distant echoes. Before you lies a narrow path deeper into the unknown.");
        System.out.println("Do you want to proceed or go back? (proceed/exit)");

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
                    System.out.println("Oh well, you decide not to venture forward. The game ends here.");
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
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    command = scanner.nextLine();
                    break;
                case "left":
                    Acrius.leftPath(scanner);
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

    // Right path scenario
    public static void rightPath(Scanner scanner) {
        System.out.println(
                "\nThe right path takes you through a narrow tunnel. It widens into a large chamber filled with old mining equipment.");
        System.out.println("You see a broken elevator that descends further underground and a ladder leading upward.");
        System.out.println("Do you take the elevator or the ladder? (elevator/ladder)");

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
        System.out.println("\nYou walk straight ahead and come across a sign that says 'Welcome to Diddy's realm'.");
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
        System.out.println("Do you examine your surroundings or run? (examine/run)");

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);

            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "examine":
                    handleExamine(scanner); // Moved this logic into a helper method
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
        // Running away logic can be expanded here (chance of escape, etc.)
    }

}