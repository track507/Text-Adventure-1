import java.util.Scanner;

public class Diddy {
    public static Player player = StartAdventure.player;
    public static GameMap gameMap = StartAdventure.gameMap;
    // note:
    // green text: you were successful in that action
    // red text: you were unsuccessful in that action
    // yellow text: you are cautios/ afraid
    // blue text: you are calm/relaxed(Usually after an encounter or a battle)
    // magenta text: a new scenario happinging
    // cyan text: you found a weapon

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
                    exploreForest(scanner);
                    String[] explore = { "You venture deeper into the forest ..." };
                    TextEngine.pt(Handler.applyStyle(explore, "i"));
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
        String[] text = {
                "\nYou venture deeper into the forest, the trees closing in around you.",
                "As you move, the air becomes thick with mist, and the ground beneath your feet feels strangely soft.",
                "Suddenly, a low growl echoes through the trees, and shadowy figures move between the branches.",
                "Do you examine your surrounding or run? (examine/run)"
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
            } else if (command.contains("run")) {
                parts[0] = "run";
            }

            switch (parts[0]) {
                case "use":
                    player.useItem(parts[1]);
                    break;
                case "examine":
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
                            "The blade is ancient, its surface etched with runes and symbols, and you feel a surge of power as you hold it.",
                            "You turn to face the unknown, your heart pounding in your chest, and you know that you are not alone."
                    };
                    TextEngine.pt(Handler.applyStyle(examine1, "i", "blue"));

                    String[] examine2 = {
                            "Out of the darkness, a pack of feral creatures emerges, their red glowing eyes fixed on you.",
                            "Do you fight or run? (fight/run)"
                    };
                    TextEngine.pt(Handler.applyStyle(examine2, "i", "yellow"));
                    break;
                case "fight":
                    String[] fight = {
                            "\nYou drop into a fighting stance, gripping your newly found sword, its weight balanced in your hands, ready to face whatever comes next:"
                    };
                    TextEngine.pt(Handler.applyStyle(fight, "i", "yellow"));
                    startFight();
                    break;
                case "run":
                    attemptToRun();
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }

            // Allow the player to input new commands
            command = scanner.nextLine().toLowerCase();
        }
    }

    // Method to handle the fight scenario
    public static void startFight() {
        Scanner scanner = new Scanner(System.in);
        boolean fightContinues = true;
        while (fightContinues) {
            // System.out.println("What would you like to do?
            // (swing/lunge/slash/thrust/block/run)");
            String action = scanner.nextLine().toLowerCase();

            // Check for keywords in the input and map to the correct sword action
            if (action.contains("swing")) {
                action = "swing";
            } else if (action.contains("slash")) {
                action = "slash";
            } else if (action.contains("thrust")) {
                action = "thrust";
            } else if (action.contains("overhead")) {
                action = "overhead";
            }

            switch (action) {
                case "swing":
                    String[] swing = {
                            "\nYou swing your sword in a wide arc, hoping to hit the enemy.",
                            "The sword whooshes through the air, connecting with a glancing blow.",
                            "The enemy staggers back but doesn't seem to have taken damage from that attack."
                    };
                    TextEngine.pt(Handler.applyStyle(swing, "i", "red"));
                    break;
                case "slash":
                    String[] slash = {
                            "\nYou go for a diagonal slash, aiming for the enemy's torso.",
                            "But you were unable to strike a fatal blow due to your inexperience with a sword."
                    };
                    TextEngine.pt(Handler.applyStyle(slash, "i", "red"));
                    break;
                case "thrust":
                    String[] thrust = {
                            "\nYou aim the tip of your sword and thrust forward.",
                            "The enemy stumbles as the blade strikes its mark, dealing significant damage."
                    };
                    TextEngine.pt(Handler.applyStyle(thrust, "i", "green"));
                    break;
                case "overhead":
                    String[] overhead = {
                            "\nYou lift the sword above your head and bring it down with all your strength.",
                            "The powerful downward strike hits hard, but the enemy barely flinches, due to your lack of strength."
                    };
                    TextEngine.pt(Handler.applyStyle(overhead, "i", "red"));
                    break;
                case "parry":
                    String[] parry = {
                            "\nYou ready your sword, prepared to deflect the next strike.",
                            "The enemy's attack glances off your blade, giving you a moment to counter."
                    };
                    TextEngine.pt(Handler.applyStyle(parry, "i", "green"));
                    break;
                case "fall":
                    String[] fall = {
                            "\nYou fall into the ground right close to the gravestone, blood oozing out your head, you realize you are hurt.",
                            "The enemy swings it's arm ready to give you a hard blow that will probably send you unconcious."
                    };
                    TextEngine.pt(Handler.applyStyle(fall, "i", "red"));
                    break;
                case "block":
                    String[] block = {
                            "\nYou roll to the side and missed the blow.You raise your sword to block the incoming attack and tackled the enemy.",
                            "You successfully block the attack, but feel the force push you back slightly."
                    };
                    TextEngine.pt(Handler.applyStyle(block, "i", "green"));
                    break;
                case "run":
                    String[] run = {
                            "\nYou attempt to flee from the fight, turning your back on the enemy."
                    };
                    TextEngine.pt(Handler.applyStyle(run, "i", "yellow"));
                    fightContinues = false;
                    break;
                default:
                    System.out.println("Invalid action. Try again.");
            }
        }

    }
    // Add enemy action and result calculation here
    // For example:
    // enemy.takeDamage(player.getAttackDamage());
    // player.takeDamage(enemy.getAttackDamage());
    // Check if enemy is defeated
    // If so, add logic for victory
    // If not, continue the fight

    // Check if fight should continue (e.g., based on player/enemy health)
    // If not, set fightContinues to false

    // Method to handle running away
    private static void attemptToRun() {
        // Add logic for attempting to run away
        String[] run = {
                "\nYou turn and sprint back the way you came, heart pounding in your chest, the creatures' ragged breaths and snarls growing louder behind you.",
                "You make it to the clearing, but the creatures are relentless, their glowing eyes burning into your back as they close the distance.",
                "Suddenly, from the shadows, a cloaked figure on a towering black horse emerges, silent and menacing. Without warning, a whip cracks through the air, wrapping around your leg.",
                "You're yanked off your feet, dragged mercilessly back into the dark forest.",
                "\nThe growls of the feral creatures become deafening, and the mist thickens, swallowing everything around you.",
                "As you're pulled into a cave, the shadows seem to come alive. Just as the creatures are about to strike, shadowy figures spring from their hiding places near the cave entrance. They ambush the beasts, their swift and silent movements almost unnatural, leaving you uncertain of what horrors await deeper within."
        };
        TextEngine.pt(Handler.applyStyle(run, "i"));
        // traumaticExperience();

        // Running away logic can be expanded here (chance of escape, etc.)
    }

}