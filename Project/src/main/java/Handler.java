public class Handler {

    public static String[] parseCommand(String string) {
        // Split the string into an array of two elements
        String[] parts = string.split(" ", 2);
        String action = parts[0];
        // If there is no argument, use an empty string
        String argument = parts.length > 1 ? parts[1] : "";
        // Return an array with the action and argument
        return new String[] { action, argument };
    }
}