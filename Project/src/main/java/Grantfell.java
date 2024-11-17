import java.util.Scanner;

public class Grantfell {

    public static Player player = StartAdventure.player;
    public static GameMap gameMap = StartAdventure.gameMap;

    public static void rightPath(Scanner scanner) {
        String[] text = {
                "\nYou chose to go right. You immediately come across a portal, shimmering with an electric blue hue.",
                "Through the portal, you catch a glimpse of strange, towering structures in the distance, half-lit by artificial lights that flicker erratically.",
                "Do you step through the portal and enter this unknown city, or turn back? (explore/back)"
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
                case "back":
                    StartAdventure.startAdventure(scanner);
                    break;
                case "inventory":
                    System.out.println(player.showInventory());
                    command = scanner.nextLine();
                    break;
                case "explore":
                    exploreCity(scanner);
                    String[] explore = { "You cautiously step deeper into the abandoned city..." };
                    TextEngine.pt(Handler.applyStyle(explore, "i"));
                    command = scanner.nextLine();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    command = scanner.nextLine();
            }
        }
    }

    public static void exploreCity(Scanner scanner) {
        String[] text = {
                "\nYou step through the portal and find yourself in a sprawling, abandoned city, its once-grand buildings towering over you.",
                "The air is filled with a faint hum, and flickering holograms project faded advertisements in an alien language.",
                "Streets stretch out in every direction, littered with strange metallic debris. You notice movement in the shadows—figures darting between the towering ruins.",
                "Do you examine your surroundings closely or keep moving through the city? (examine/move)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i", "yellow"));

        String command = scanner.nextLine();
        while (true) {
            String[] parts = Handler.parseCommand(command.toLowerCase());

            // Detect keywords and adjust the command
            if (command.contains("use")) {
                parts[0] = "use";
            } else if (command.contains("examine")) {
                parts[0] = "examine";
            } else if (command.contains("fight")) {
                parts[0] = "fight";
            } else if (command.contains("move")) {
                parts[0] = "move";
            }

            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "examine":
                    String[] examine = {
                            "\nYou carefully study your surroundings, aware of the eerie silence that fills the air.",
                            "Among the crumbling structures, you spot a control panel with flickering lights, half-buried in the rubble.",
                            "You approach it, the display faintly glowing. It seems to power something nearby—a hidden compartment perhaps?"
                    };
                    TextEngine.pt(Handler.applyStyle(examine, "i", "cyan"));

                    String[] examine1 = {
                            "You reach towards the panel, and with a low hum, a hidden compartment slides open, revealing a high-tech weapon.",
                            "It’s a sleek blade, the edge glowing faintly with an energy field. You grip it, feeling a surge of strength.",
                            "From the shadows, more movement—machines that resemble hybrid creatures, with a menacing, predatory gleam in their eyes.",
                            "Do you stand your ground and prepare to fight, or try to evade them? (fight/evade)"
                    };
                    TextEngine.pt(Handler.applyStyle(examine1, "i", "blue"));
                    break;
                case "fight":
                    startFight();
                    break;
                case "move":
                    attemptToMove();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }

            command = scanner.nextLine().toLowerCase();
        }
    }

    public static void startFight() {
        Scanner scanner = new Scanner(System.in);
        boolean fightContinues = true;
        while (fightContinues) {
            System.out.println("\nChoose your move (swing/slash/thrust/block/evade):");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "swing":
                    String[] swing = {
                            "\nYou swing the energy blade in a wide arc, slicing through the air.",
                            "The weapon hums as it connects with a creature, sparks flying as metal meets energy."
                    };
                    TextEngine.pt(Handler.applyStyle(swing, "i", "red"));
                    break;
                case "slash":
                    String[] slash = {
                            "\nYou perform a calculated slash, aiming for the creature’s power core.",
                            "The creature sparks and sputters, collapsing in a heap of metal and circuitry."
                    };
                    TextEngine.pt(Handler.applyStyle(slash, "i", "red"));
                    break;
                case "thrust":
                    String[] thrust = {
                            "\nYou thrust forward, driving the blade into the creature’s chest.",
                            "The creature staggers, then crumbles, its eyes dimming as its power fades."
                    };
                    TextEngine.pt(Handler.applyStyle(thrust, "i", "green"));
                    break;
                case "block":
                    String[] block = {
                            "\nYou raise the blade defensively as another creature lunges at you.",
                            "Its claws scrape against the energy field of your blade, giving you a moment to counterattack."
                    };
                    TextEngine.pt(Handler.applyStyle(block, "i", "green"));
                    break;
                case "evade":
                    attemptToMove();
                    fightContinues = false;
                    break;
                default:
                    System.out.println("Invalid action. Try again.");
            }
        }
    }

    private static void attemptToMove() {
        String[] move = {
                "\nYou make a quick decision and dart down an alleyway, hoping to lose the pursuing creatures.",
                "The alley twists and turns, leading you to an open square filled with more remnants of this advanced society.",
                "You spot a massive statue in the center, an engraved plaque glowing faintly with words you can’t read.",
                "Suddenly, the statue shifts and opens, revealing a hidden passageway beneath it.",
                "Do you enter the passage, or try to find another way out of the city? (enter/stay)"
        };
        TextEngine.pt(Handler.applyStyle(move, "i"));
    }
}
