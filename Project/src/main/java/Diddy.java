import java.util.Scanner;

public class Diddy {
    public static Player player = StartAdventure.player;
    public static GameMap gameMap = StartAdventure.gameMap;

    // Straight path scenario
    public static void straightPath(Scanner scanner) {
        String[] text = {
                "\nYou chose to go straight ahead. You come across a sign that says 'Welcome to Diddy's realm'.",
                "You find yourself in a dense underground forest. It's dark, but the trees seem alive.",
                "Do you explore deeper into the forest or turn back? (explore/back)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i"));

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command);
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "back":
                    StartAdventure.startAdventure(scanner);
                    return;
                case "inventory":
                    System.out.println(player.showInventory());
                    break;
                case "explore":
                    exploreForest(scanner);
                    return; // Exit the current method and go deeper into the forest
                default:
                    System.out.println("Invalid command. Please try again.");
            }
            command = scanner.nextLine();
        }
    }

    // Explore Forest scenario
    public static void exploreForest(Scanner scanner) {
        String[] text = {
                "\nYou venture deeper into the forest, the trees closing in around you.",
                "The air becomes thick with mist, and the ground beneath your feet feels strangely soft.",
                "Suddenly, a low growl echoes through the trees, and shadowy figures move between the branches.",
                "Do you examine your surroundings or run? (examine/run)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i", "yellow"));

        String command = scanner.nextLine();
        boolean examinedGravestone = false; // Track if the gravestone has been examined

        while (true) {
            String[] parts = Handler.parseCommand(command.toLowerCase());
            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "examine":
                    if (!examinedGravestone) {
                        examineGravestone();
                        examinedGravestone = true;
                    } else {
                        alreadyExaminedGravestone();
                    }
                    break;
                case "fight":
                    startFight(scanner);
                    return; // Once the fight starts, exit the exploreForest method
                case "run":
                    attemptToRun();
                    return; // If the player runs, exit the exploration method
                default:
                    System.out.println("Invalid command. Please try again.");
            }
            command = scanner.nextLine().toLowerCase();
        }
    }

    private static void examineGravestone() {
        String[] examine = {
                "\nYou examine your surroundings, your senses heightened by the unknown.",
                "You notice a gravestone in the distance, its surface worn and moss-covered.",
                "You approach the gravestone, its surface smooth and cool against your fingertips."
        };
        TextEngine.pt(Handler.applyStyle(examine, "i", "cyan"));

        String[] examine1 = {
                "You run your hand over the inscription, the words etched into the stone, and a chill runs down your spine.",
                "You feel a presence, a presence that is not of this world, and you know that you are not alone.",
                "You reach into the gravestone, your hand closing around a hilt, and you pull out a sword.",
                "The blade is ancient, its surface etched with runes and symbols, and you feel a surge of power as you hold it."
        };
        TextEngine.pt(Handler.applyStyle(examine1, "i"));

        player.addItem("rusty ancient sword");

        String[] creatures = {
                "Out of the darkness, a pack of feral creatures emerges, their red glowing eyes fixed on you.",
                "Do you fight or run? (fight/run)"
        };
        TextEngine.pt(Handler.applyStyle(creatures, "i", "yellow"));
    }

    private static void alreadyExaminedGravestone() {
        String[] alreadyExamined = {
                "\nYou've already examined the gravestone and taken the sword.",
                "Do you want to fight the creatures or try to run? (fight/run)"
        };
        TextEngine.pt(Handler.applyStyle(alreadyExamined, "i", "yellow"));
    }

    public static void startFight(Scanner scanner) {
        boolean fightContinues = true;
        int attackCount = 0;
        while (fightContinues) {
            String action = scanner.nextLine().toLowerCase();
            String actionResult = handleCombatAction(action);
            TextEngine.pt(Handler.applyStyle(new String[] { actionResult }, "i"));

            attackCount++;
            if (attackCount == 3) {
                triggerCombatSequence(action);
            } else {
                System.out.println("You lack the strength to perform this action properly.");
            }

            if (action.equals("run")) {
                fightContinues = false;
            }
        }
    }

    private static String handleCombatAction(String action) {
        switch (action) {
            case "slash":
            case "swing":
                return "\nYou grip the hilt of your sword tightly and go for a diagonal slash, aiming for the creature's torso.";
            case "thrust":
                return "\nYou thrust your sword forward, trying to pierce the creature's defenses.";
            case "overhead":
                return "\nYou swing your sword overhead, aiming for a crushing blow.";
            case "block":
                return "\nYou raise your sword to block the incoming strike, bracing yourself.";
            case "parry":
                return "\nYou deftly parry the creature's strike, creating an opening for a counterattack.";
            case "run":
                return "\nYou turn to flee, hoping to escape the fight.";
            default:
                return "Invalid action. Try again.";
        }
    }

    private static void triggerCombatSequence(String action) {
        switch (action) {
            case "slash":
                slashSequence();
                break;
            case "thrust":
                thrustSequence();
                break;
            case "overhead":
                overheadSequence();
                break;
        }
    }

    private static void slashSequence() {
        String[] sequence = {
                "\nBlood drips from the creature's side, but it doesn't retreat. It swipes at you with its claws, and you block with your sword.",
                "The creature lunges at you again, forcing you to backpedal, its claws narrowly missing you."
        };
        TextEngine.pt(Handler.applyStyle(sequence, "i", "red"));
    }

    private static void thrustSequence() {
        String[] sequence = {
                "\nYou lunge forward with your sword, but the creature evades your thrust, retaliating with a vicious swipe."
        };
        TextEngine.pt(Handler.applyStyle(sequence, "i", "red"));
    }

    private static void overheadSequence() {
        String[] sequence = {
                "\nYou bring your sword down with all your might, but the creature dodges, countering with a quick swipe."
        };
        TextEngine.pt(Handler.applyStyle(sequence, "i", "red"));
    }

    private static void attemptToRun() {
        String[] run = {
                "\nYou attempt to flee from the fight, but the creatures are too fast, and you're dragged back into the darkness."
        };
        TextEngine.pt(Handler.applyStyle(run, "i"));
    }
}
