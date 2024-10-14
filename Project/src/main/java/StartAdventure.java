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
    public static GameMap gameMap = new GameMap();
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

        String[] text = {
                "Welcome to Acrius, the Dark Realm by Terrence, Chibuikem, and Grant.",
                "You find yourself in a dimly lit cavern with distant echoes. Before you, lies a narrow path deeper into the unknown.",
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
                "Do you take the west path, the east path, or continue north? (north, west, or east)"
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
                    Acrius.leftPath(scanner);
                    break;
                case "east":
                    rightPath(scanner);
                    break;
                case "north":
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

}