import java.util.List;

// TextEngine made by Terrence

public class TextEngine {

    public static void pt(String text, int delay) {
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
        System.out.println();  // Move to the next line after printing the entire string
    }

    // Method to print text with a delay between characters
    public static void pt(String text) {
        // default delay of 25ms.
        pt(text, 25);
    }

    public static void pt(String[] text) {
        for (String line : text) {
            pt(line); // Call the pt method that takes a single String
        }
    }

    public static void pt(List<String> text) {
        for (String line : text) {
            pt(line); // Call the pt method that takes a single String
        }
    }
}
