import java.util.List;

// TextEngine made by Terrence

public class TextEngine {

    // main method to print text.
    public static void pt(String text, int delay, boolean newline) {
        for (char c : text.toCharArray()) {
            System.out.print(c);  // Print the current character without moving to the next line
            try {
                Thread.sleep(delay); // Pause for 250 milliseconds (0.25 seconds)
            } catch (InterruptedException e) {
                // Handle the interruption during sleep (if any)
                Thread.currentThread().interrupt();
                System.out.println("Interrupted: " + e.getMessage());
            }
        }
        if (newline) {
            System.out.println(); // Move to the next line if newline is true
        }
    }

    /*
        By default newline is true unless specified otherwise
        Default delay is set to 25 ms
        This has several overloaded methods to handle different cases
    */

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
