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

    // Explore Forest scenario
    // creatures.
    public static void exploreForest(Scanner scanner) {
        String[] text = {
                "\nYou venture deeper into the forest, the trees closing in around you.",
                "As you move, the air becomes thick with mist, and the ground beneath your feet feels strangely soft.",
                "Suddenly, a low growl echoes through the trees, and shadowy figures move between the branches.",
                "Do you examine your surroundings or run? (examine/run)"
        };
        TextEngine.pt(Handler.applyStyle(text, "i", "yellow"));

        String command = scanner.nextLine();
        boolean examinedGravestone = false; // Track if the gravestone has been examined

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
                    if (!examinedGravestone) { // Check if the gravestone has been examined
                        String[] examine = {
                                "\nYou examine your surroundings, your senses heightened by the unknown.",
                                "You notice a gravestone in the distance, its surface worn and moss-covered.",
                                "You approach the gravestone, its surface smooth and cool against your fingertips."
                        };
                        TextEngine.pt(Handler.applyStyle(examine, "i", "cyan"));

                        String[] examine1 = {
                                "\nYou run your hand over the inscription, the words etched into the stone, and a chill runs down your spine.",
                                "You feel a presence, a presence that is not of this world, and you know that you are not alone.",
                                "You reach into the gravestone, your hand closing around a hilt, and you pull out a sword.",
                                "The blade is ancient, its surface etched with runes and symbols, and you feel a surge of power as you hold it.",
                                "You turn to face the unknown, your heart pounding in your chest, and you know that you are not alone."
                        };
                        TextEngine.pt(Handler.applyStyle(examine1, "i"));

                        player.addItem("rusty ancient sword"); // Add the sword to the player's inventory
                        examinedGravestone = true; // Mark that the gravestone has been examined

                        String[] examine2 = {
                                "Out of the darkness, a pack of feral creatures emerges, their red glowing eyes fixed on you.",
                                "Do you fight or run? (fight/run)"
                        };
                        TextEngine.pt(Handler.applyStyle(examine2, "i", "yellow"));
                    } else {
                        // If already examined, provide feedback
                        String[] alreadyExamined = {
                                "\nYou've already examined the gravestone and taken the sword.",
                                "Do you want to fight the creatures or try to run? (fight/run)"
                        };
                        TextEngine.pt(Handler.applyStyle(alreadyExamined, "i", "yellow"));
                    }
                    break;
                case "fight":
                    String[] fight = {
                            "\nYou drop into a fighting stance, gripping your newly found sword, its weight balanced in your hands, ready to face whatever comes next:"
                    };
                    TextEngine.pt(Handler.applyStyle(fight, "i", "yellow"));
                    String[] fightoptins = {
                            "\nWhispers: its about time for your survival instinct to kick in, so fight with your newly found sword young one."
                    };
                    String[] skillsMessage = {
                            "\nYou have the following skills:"
                    };
                    TextEngine.pt(Handler.applyStyle(skillsMessage, "i", "yellow"));
                    // Display player skills
                    System.out.println(player.getSkills().showSkills());
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
        int attackCount = 0;
        while (fightContinues) {
            // System.out.println("What would you like to do
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
                case "slash":
                case "swing":// this mean if slash or swing is inputted then it gives the same output
                    String[] slashSequence = {
                            "\nYou grip the hilt of your sword tightly and go for a diagonal slash, aiming for the creature's torso.",
                            "The blade cuts through the air with deadly speed, but the creature dodges just in time, only grazing its thick hide.",
                            "It snarls, baring its sharp teeth, and lunges at you with terrifying speed. You barely manage to roll out of the way, the creature's claws narrowly missing your leg.",
                            "You rise to your feet, panting, as the creature circles you, its red eyes gleaming in the mist. With a hiss, it darts forward again.",
                            "You slash again, this time cutting across the beast's flank. It howls in pain but doesn't slow down, instead becoming even more enraged."
                    };
                    TextEngine.pt(Handler.applyStyle(slashSequence, "i", "red"));
                    attackCount++;
                    // slashsequence();
                    break;
                case "thrust":
                case "stab":
                    String[] thrust = {
                            "\nYou ready your sword, facing the creatures head-on. Their red eyes glow with hunger, and their claws dig into the dirt as they circle you.",
                            "With a battle cry, you lunge forward, swinging your sword in a desperate arc. The blade connects, but it barely slows the closest creature.",
                            "The feral beast snarls, swiping at you with a savage claw. You barely manage to block the attack, but the force sends you stumbling backward.",
                            "You fight valiantly, slashing and thrusting, but their numbers and speed overwhelm you."
                    };
                    TextEngine.pt(Handler.applyStyle(thrust, "i", "red"));
                    attackCount++;
                    // thrustsequence();
                    break;
                case "overhead":
                    String[] overhead = {
                            "\nYou lift the sword above your head and bring it down with all your strength.",
                            "The powerful downward strike hits hard, but the creature barely flinches, shaking off the impact with a snarl.",
                            "It counters immediately, swiping at you with its claws, and you're forced to backpedal, narrowly avoiding the razor-sharp talons.",
                            "You steady yourself, muscles straining, and ready your sword for another strike, but the creature is relentless."
                    };
                    TextEngine.pt(Handler.applyStyle(overhead, "i", "red"));
                    attackCount++;
                    // overheadsequence();
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
            if (attackCount == 3) {
                switch (action) {
                    case "slash":
                        slashsequence(); // Call the slash sequence
                        break;
                    case "thrust":
                        thrustsequence(); // Call the thrust sequence
                        break;
                    case "overhead":
                        overheadsequence(); // Call the overhead sequence
                        break;
                }
            } else {
                System.out.println("Invalid action. Try again.");
            }
            attackCount = 0; // Reset the attack count
        }

    }
    // Add enemy action and result calculation here
    // For example:
    // enemy.takeDamage(player.getAttackDamage());
    // player.takeDamage(enemy.getAttackDamage());
    // Check if enemy is defeated
    // If not, set fightContinues to false

    // prqual after you slash at enemy
    private static void slashsequence() {
        String[] prolongedFightSlash = {
                "\nBlood drips from the creature's side, but it doesn't retreat. It swipes at you with its claws, and you manage to block the attack with your sword, but the force knocks you back.",
                "You stagger, your muscles burning with exhaustion. The creature circles, preparing for another assault. You know you can't hold out much longer.",
                "It charges at you again, and this time, you lunge forward, catching it off guard. Your sword sinks deeper into its flesh, and for a moment, you think you might have the upper hand.",
                "But as you pull your blade free, a second creature leaps at you from the side. You try to react, but the force of its impact sends you crashing to the ground."
        };
        TextEngine.pt(Handler.applyStyle(prolongedFightSlash, "i", "red"));

        String[] ambushSlash = {
                "\nBefore you can recover, a sharp crack echoes through the forest. A whip coils around your leg, pulling you off your feet.",
                "You're dragged back, through the dirt and leaves, as the creatures growl in frustration at losing their prey.",
                "The mist thickens, and you feel the cold, rough ground as you're pulled deeper into the forest."
        };
        TextEngine.pt(Handler.applyStyle(ambushSlash, "i", "yellow"));
        String[] draggedToCave = {
                "\nYou're dragged into the darkness of the forest, the thorns and branches tearing at your skin. The grip of the whip tightens as you're hauled deeper into the unknown.",
                "The thick mist suffocates and obscures everything around you. Your vision blurs as the world narrows to the deafening sound of the creatures' growls echoing behind you.",
                "Just as you're pulled into the mouth of a cave, shadowy figures leap from the darkness, ambushing the creatures that had been chasing you."
        };
        TextEngine.pt(Handler.applyStyle(draggedToCave, "i", "yellow"));

        String[] caveAmbush = {
                "\nThe ambush is swift and brutal. The cloaked figures move in unison, their strikes silent yet deadly as they cut down the creatures with precision.",
                "You lay there, dazed, watching the feral beasts fall one by one. The shadows seem to dance around you as the mist thickens, swallowing the scene.",
                "Your body feels heavy, too weak to move. As darkness overtakes you, you realize you're being dragged deeper into the cave by the cloaked figure."
        };
        TextEngine.pt(Handler.applyStyle(caveAmbush, "i", "yellow"));
    };

    // prequal after you thrust/lunge at the enemy
    private static void thrustsequence() {

        String[] ambushSequence = {
                "\nSuddenly, out of the shadows, a figure cloaked in black on a towering horse appears. The creatures pause for a split second, giving you just enough time to glance over.",
                "Before you can react, a whip cracks through the air, wrapping tightly around your arm and yanking you off your feet. The ground rushes up to meet you as you're dragged mercilessly through the dirt.",
                "The feral creatures roar in fury, but you're being pulled away too quickly to notice as their glowing eyes fade into the distance."
        };
        TextEngine.pt(Handler.applyStyle(ambushSequence, "i", "yellow"));

        String[] draggedToCave = {
                "\nYou're dragged into the darkness of the forest, the thorns and branches tearing at your skin. The grip of the whip tightens as you're hauled deeper into the unknown.",
                "The mist grows thick and suffocating, obscuring everything around you. Your vision blurs as the world narrows to the deafening sound of the creatures' growls echoing behind you.",
                "Just as you're pulled into the mouth of a cave, shadowy figures leap from the darkness, ambushing the creatures that had been chasing you."
        };
        TextEngine.pt(Handler.applyStyle(draggedToCave, "i", "yellow"));

        String[] caveAmbush = {
                "\nThe ambush is swift and brutal. The cloaked figures move in unison, their strikes silent yet deadly as they cut down the creatures with precision.",
                "You lay there, dazed, watching the feral beasts fall one by one. The shadows seem to dance around you as the mist thickens, swallowing the scene.",
                "Your body feels heavy, too weak to move. As darkness overtakes you, you realize you're being dragged deeper into the cave by the cloaked figure."
        };
        TextEngine.pt(Handler.applyStyle(caveAmbush, "i", "yellow"));

        // Add logic to continue the storyline after the ambush

    }

    // uverhead strike prequal
    private static void overheadsequence() {
        String[] prolongedFightOverhead = {
                "\nThe creature's eyes lock onto yours as it charges again, faster this time. You swing the sword overhead once more, aiming for its skull.",
                "The beast leaps to the side, evading the strike, but you twist and follow through with a desperate slash.",
                "Your sword connects with its leg, drawing blood. The creature roars in pain, but doesn't slow its advance. It lunges again, forcing you into a frantic retreat.",
                "Your arms tremble under the weight of your sword. You know you can't keep this up for long, but you press on, delivering another powerful overhead swing."
        };
        TextEngine.pt(Handler.applyStyle(prolongedFightOverhead, "i", "red"));

        String[] ambushOverhead = {
                "\nSuddenly, a loud crack echoes through the trees, and before you can react, a whip wraps around your wrist, yanking your sword from your grip.",
                "You stumble as you're pulled backward, disarmed and vulnerable. The creatures growl, sensing weakness, but their advance is abruptly stopped.",
                "Shadowy figures emerge from the mist, striking the beasts with swift, brutal precision, forcing them to retreat as you're dragged deeper into the forest."
        };
        TextEngine.pt(Handler.applyStyle(ambushOverhead, "i", "yellow"));

        String[] draggedToCave = {
                "\nYou're dragged into the darkness of the forest, the thorns and branches tearing at your skin. The grip of the whip tightens as you're hauled deeper into the unknown.",
                "The mist grows thick and suffocating, obscuring everything around you. Your vision blurs as the world narrows to the deafening sound of the creatures' growls echoing behind you.",
                "Just as you're pulled into the mouth of a cave, shadowy figures leap from the darkness, ambushing the creatures that had been chasing you."
        };
        TextEngine.pt(Handler.applyStyle(draggedToCave, "i", "yellow"));

        String[] caveAmbush = {
                "\nThe ambush is swift and brutal. The cloaked figures move in unison, their strikes silent yet deadly as they cut down the creatures with precision.",
                "You lay there, dazed, watching the feral beasts fall one by one. The shadows seem to dance around you as the mist thickens, swallowing the scene.",
                "Your body feels heavy, too weak to move. As darkness overtakes you, you realize you're being dragged deeper into the cave by the cloaked figure."
        };
        TextEngine.pt(Handler.applyStyle(caveAmbush, "i", "yellow"));
    }

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