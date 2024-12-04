import java.util.List;

// TextEngine made by Terrence

public class TextEngine {

    // Flag to control whether delays are skipped (default is false)
    private static boolean skipDelay = false;

    // Method to set the skipDelay flag
    public static void setSkipDelay(boolean skip) {
        skipDelay = skip;
    }

    // Main method to print text
    public static void pt(String text, int delay, boolean newline) {
        for (char c : text.toCharArray()) {
            System.out.print(c);  // Print the current character without moving to the next line
            if (!skipDelay) {     // Apply delay only if skipDelay is false
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Interrupted: " + e.getMessage());
                }
            }
        }
        if (newline) {
            System.out.println(); // Move to the next line if newline is true
        }
    }

    // Overloaded methods (same as before)
    public static void pt(String text, int delay) {
        pt(text, delay, true);
    }

    public static void pt(String text, boolean newline) {
        pt(text, 25, newline);
    }

    public static void pt(String text) {
        pt(text, 25, true);
    }

    public static void pt(String[] text) {
        for (String line : text) {
            pt(line);
        }
    }

    public static void pt(List<String> text) {
        for (String line : text) {
            pt(line);
        }
    }
}
