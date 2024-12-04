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
                    exitGame();
                case "inventory":
                    player.showInventory();
                    command = scanner.nextLine();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    private static void exitGame() {
        String[] exit = {
                "\nAs you gaze upon the sprawling landscape, anticipation pulses in the air. But you hesitate, your resolve wavering. You decide to turn back, and with a single click, the vivid world around you blurs and dims. Your journey ends before it even begins, leaving only ghostly remnants of what might have been.",
                "\nWe shall meet again in your next life."
        };
        // exitwhisper();
        TextEngine.pt(Handler.applyStyle(exit, "i"));

        System.exit(0);
    };

    /// private static void exitwhisper() {
    // string[] exitwhisper = {
    // "\n whisper:We shall meet again in your next life."
    // };
    // TextEngine.pt(Handler.applyStyle(exitwhisper, "i", "d"));
    // };

    // Start the adventure
    public static void startAdventure(Scanner scanner) {

        String[] text = {
                "\nYou cautiously step forward. After a few minutes, you see an intersection.",
                "Do you take the west path, the east path, or continue north? (west/east/north)"
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
                    gameMap.setLocation("Diddy", "Straight Path");
                    Diddy.straightPath(scanner);
                    break;
                case "inventory":
                    player.showInventory();
                    command = scanner.nextLine();
                    break;
                case "exit":
                    exitGame();
                    break;
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
                    player.showInventory();
                    command = scanner.nextLine();
                    break;
                case "exit":
                    exitGame();
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

}