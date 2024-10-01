import java.io.Console;
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
        Console console = System.console();
        startTime = System.currentTimeMillis();

        System.out.println("\nWelcome to Acrius, the Dark Realm by Terrence, Grant, and Chibuikem.");
        System.out.println("You find yourself in a dimly lit cavern with distant echoes. Before you lies a narrow path deeper into the unknown.");
        System.out.println("Do you want to proceed or go back? (proceed/back)");

        String command = console.readLine();
        while(!command.equals("proceed") || !command.equals("back")) {
            if(command.equals("proceed")) {
                startAdventure(console);
            }
            else if (command.equals("back")) {
                System.out.println("You decide not to venture forward. The game ends here.");
            }
            else if(command.equals("inventory")) {
                player.showInventory();
            }
        }
    }

    // Start the adventure
    public static void startAdventure(Console console) {
        roomEntryTime = System.currentTimeMillis();
        System.out.println("\nYou cautiously step forward. After a few minutes, you see an intersection.");
        System.out.println("Do you take the left path, the right path, or continue straight? (left/right/straight)");

        String command = console.readLine();
        while(!command.equals("left") || !command.equals("right") || !command.equals("straight")) {
            if(command.equals("left")) {
                leftPath(console);
            }
            else if (command.equals("right")) {
                rightPath(console);
            }
            else if(command.equals("straight")) {
                straightPath(console);
            }
            else if(command.equals("inventory")) {
                player.showInventory();
            }
        }
    }

    // Left path scenario
    public static void leftPath(Console console) {
        System.out.println("\nYou take the left path and find yourself in a vast underground riverbank.");
        System.out.println("A boat is tied to the shore. Do you take the boat or explore the cave further on foot? (boat/foot/back)");

        String command = console.readLine();
        while(!command.equals("boat") || !command.equals("foot")) {
            if(command.equals("boat")) {
                boatRide(console);
            } else if (command.equals("foot")) {
                //exploreCave(console);
            } else if(command.equals("inventory")) {
                player.showInventory();
            } else if (command.equals("back")) {
                startAdventure(console);
            }
        }
    }

    public static void boatRide(Console console) {
        System.out.println("\nYou untie the boat and paddle down the eerie river. You soon encounter a waterfall.");
        System.out.println("Do you abandon the boat and swim ashore or ride over the waterfall? (swim/ride/back)");

        String command = console.readLine();
        while(!command.equals("swim") || !command.equals("ride")) {
            if(command.equals("swim")) {
                hiddenTemple(console);
            } else if (command.equals("ride")) {
                System.out.println("You ride over the waterfall but your boat crashes on the rocks below. You sustain injuries but survive.");
                //darkCavern(console);
            } else if(command.equals("back")) {
                leftPath(console);
            } else if(command.equals("inventory")) {
                player.showInventory();
            }
        }
    }

    public static void hiddenTemple(Console console) {
        System.out.println("\nYou swim ashore and find a small cave entrance. You venture inside, discovering ancient carvings.");
        System.out.println("You find yourself in an ancient temple. Strange symbols cover the walls.");
        System.out.println("Do you want to decipher the symbols or search the temple for items? (decipher/search/back)");

        String command = console.readLine();
        while(!command.equals("decipher") || !command.equals("search")) {
            if(command.equals("decipher")) {
                hiddenPassageway(console);
            } else if(command.equals("search")) {
                System.out.println("You search the temple and find a powerful artifact, but the ceiling starts to collapse. You barely escape!");
                //collapsedTemple(console);
            } else if (command.equals("back")) {
                boatRide(console);
            } else if(command.equals("inventory")) {
                player.showInventory();
            }
        }
    }

    public static void hiddenPassageway(Console console) {
        System.out.println("\nYou decipher the symbols and unlock a hidden passageway.");
        System.out.println("The air is cool, and the walls seem to close in around you.");
        System.out.println("To your left, a narrow staircase spirals downward into the darkness, while to your right, a heavy door creaks open, revealing a tunnel bathed in faint, flickering light.");
        System.out.println("Do you descend the staircase or venture into the tunnel? (staircase/tunnel/back)");

        String command = console.readLine();
        while(!command.equals("staircase") || !command.equals("tunnel")) {
            if(command.equals("staircase")) {
                stairCaseIntoDarkness(console);
            } else if(command.equals("tunnel")) {
                //faintTunnel(console);
            } else if(command.equals("back")) {
                hiddenTemple(console);
            } else if(command.equals("inventory")) {
                player.showInventory();
            }
        }
    }
    
    public static void stairCaseIntoDarkness(Console console) {
        System.out.println("\nYou descend the spiraling staircase, the air growing colder with each step.");
        System.out.println("The faint light above disappears, and you find yourself in total darkness.");
        System.out.println("You feel the stone walls, searching for some kind of clue when your hand brushes against a rusty lever.");
        System.out.println("Do you pull the lever or keep searching in the dark? (pull/search/back)");
    
        String command = console.readLine();
        while (!command.equals("pull") || !command.equals("search")) {
            if (command.equals("pull")) {
                ironDoor(console);
            } else if (command.equals("search")) {
                if(!player.hasItem("Ancient Key")) {
                    System.out.println("\nYou feel along the walls, finding something wrapped in cloth behind a loose stone.");
                    System.out.println("Inside, you find an 'Ancient Key.' You pocket it for later use.");
                    player.addItem("Ancient Key");
                    System.out.println("You also find a 'Torch' which you light, illuminating the staircase. (pull/back)");
                } else {
                    System.out.println("You've already found the \"Ancient Key\". You can't find anything else.");
                    System.out.println("You should find a place to use the key. (pull/back)");
                }
                command = console.readLine();
            } else if (command.equals("back")) {
                hiddenPassageway(console);
            } else if (command.equals("inventory")) {
                player.showInventory();
            } 
        }
    }
    
    public static void ironDoor(Console console) {
        System.out.println("\nThe lever creaks loudly as you pull it down. Suddenly, torches along the walls ignite, lighting up the room.");
        System.out.println("Ahead, you see an iron door. It's locked tight, with strange engravings of keys on the handle.");
        System.out.println("It seems you need a special key to open this door. (use key?/back)");
        String command = console.readLine();

        while(!command.equals("yes")) {
            if (player.hasItem("Ancient Key")) {
                System.out.println("Try the ancient key?");
                command = console.readLine();
                if (command.equals("yes")) {
                    System.out.println("The door opens and you find yourself in a dark room.");
                    //darkRoom(console);
                }
            } else {
                System.out.println("You don't have the key. You can't open the door.");
                stairCaseIntoDarkness(console);
            }
        }
    }

    // Right path scenario
    public static void rightPath(Console console) {
        System.out.println("The right path takes you through a narrow tunnel. It widens into a large chamber filled with old mining equipment.");
        System.out.println("You see a broken elevator that descends further underground and a ladder leading upward.");
        System.out.println("Do you take the elevator or the ladder? (elevator/ladder)");

        String command = console.readLine();
        while(!command.equals("elevator") || !command.equals("ladder")) {
            if(command.equals("elevator")) {
                //brokenElevator(console);
            }
            else if (command.equals("ladder")) {
                //upperChamber(console);
            }
            else if(command.equals("inventory")) {
                player.showInventory();
            } 
        }
    }

    // Straight path scenario
    public static void straightPath(Console console) {
        System.out.println("You walk straight ahead and find yourself in a dense underground forest. It's dark, but the trees seem alive.");
        System.out.println("Do you explore deeper into the forest or turn back? (explore/back)");

        String command = console.readLine();
    }
}