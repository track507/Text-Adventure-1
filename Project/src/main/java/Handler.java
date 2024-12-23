import java.util.ArrayList;
import java.util.List;

// Handler made by Terrence

public class Handler {

    // ANSI beginning escape sequence
    private static final String s = "\033";

    // ANSI escape sequences for text formatting
    private static final String r = "\033[0m";   // Reset
    private static final String b = "\033[1m";   // Bold
    private static final String d = "\033[2m";   // Dim
    private static final String i = "\033[3m";   // Italic
    private static final String u = "\033[4m";   // Underline
    private static final String bl = "\033[5m";  // Blink
    private static final String rev = "\033[7m"; // Reverse
    private static final String h = "\033[8m";   // Hidden

    // ANSI escape sequences for text colors
    private static final String blk = "\033[30m"; // Black
    private static final String red = "\033[31m"; // Red
    private static final String green = "\033[32m"; // Green
    private static final String yellow = "\033[33m"; // Yellow
    private static final String blue = "\033[34m"; // Blue
    private static final String magenta = "\033[35m"; // Magenta
    private static final String cyan = "\033[36m"; // Cyan
    private static final String white = "\033[37m"; // White
    private static final String darkGrey = "\033[90m"; // Dark Grey (Bright Black)

    // ANSI escape sequences for background colors
    private static final String blk_bg = "\033[40m"; // Black Background
    private static final String red_bg = "\033[41m"; // Red Background
    private static final String green_bg = "\033[42m"; // Green Background
    private static final String yellow_bg = "\033[43m"; // Yellow Background
    private static final String blue_bg = "\033[44m"; // Blue Background
    private static final String magenta_bg = "\033[45m"; // Magenta Background
    private static final String cyan_bg = "\033[46m"; // Cyan Background
    private static final String white_bg = "\033[47m"; // White Background

    public static String[] parseCommand(String string) {
        // Split the string into an array of two elements
        String[] parts = string.split(" ", 2);
        String action = parts[0];
        // If there is no argument, use an empty string
        String argument = parts.length > 1 ? parts[1] : "";
        // Return an array with the action and argument
        return new String[] { action, argument };
    }

    public static String applyStyle(String string, String... styles) {
        StringBuilder styledString = new StringBuilder();

        // Apply styles based on the input arguments
        for (String style : styles) {
            switch (style.toLowerCase()) {
                case "r": // Reset
                    styledString.append(r);
                    break;
                case "b": // Bold
                    styledString.append(b);
                    break;
                case "d": // Dim
                    styledString.append(d);
                    break;
                case "i": // Italic
                    styledString.append(i);
                    break;
                case "u": // Underline
                    styledString.append(u);
                    break;
                case "bl": // Blink
                    styledString.append(bl);
                    break;
                case "rev": // Reverse
                    styledString.append(rev);
                    break;
                case "h": // Hidden
                    styledString.append(h);
                    break;
                case "blk": // Text Color - Black
                    styledString.append(blk);
                    break;
                case "red": // Text Color - Red
                    styledString.append(red);
                    break;
                case "green": // Text Color - Green
                    styledString.append(green);
                    break;
                case "yellow": // Text Color - Yellow
                    styledString.append(yellow);
                    break;
                case "blue": // Text Color - Blue
                    styledString.append(blue);
                    break;
                case "magenta": // Text Color - Magenta
                    styledString.append(magenta);
                    break;
                case "cyan": // Text Color - Cyan
                    styledString.append(cyan);
                    break;
                case "white": // Text Color - White
                    styledString.append(white);
                    break;
                case "darkgrey": // Dark Grey (Bright Black)
                    styledString.append(darkGrey);
                    break;
                case "blk_bg": // Background Color - Black
                    styledString.append(blk_bg);
                    break;
                case "red_bg": // Background Color - Red
                    styledString.append(red_bg);
                    break;
                case "green_bg": // Background Color - Green
                    styledString.append(green_bg);
                    break;
                case "yellow_bg": // Background Color - Yellow
                    styledString.append(yellow_bg);
                    break;
                case "blue_bg": // Background Color - Blue
                    styledString.append(blue_bg);
                    break;
                case "magenta_bg": // Background Color - Magenta
                    styledString.append(magenta_bg);
                    break;
                case "cyan_bg": // Background Color - Cyan
                    styledString.append(cyan_bg);
                    break;
                case "white_bg": // Background Color - White
                    styledString.append(white_bg);
                    break;
                default:
                    break; // Ignore unrecognized styles
            }
        }

        // Append the actual string and reset at the end
        styledString.append(string).append(r); // Reset after the styled string

        return styledString.toString();
    }

    // Overloaded method to handle comma-separated styles
    public static String applyStyle(String string, String styles) {
        // Split the styles string by commas
        String[] styleArray = styles.split(",");
        for (int i = 0; i < styleArray.length; i++) {
            styleArray[i] = styleArray[i].trim(); // Ensure no whitespace.
        }
        // Delegate to the original applyStyle method
        return applyStyle(string, styleArray);
    }
    
    public static List<String> applyStyle(String[] strings, String... styles) {
        List<String> styledStrings = new ArrayList<>();
    
        for (String str : strings) {
            // Apply styles to each string and add to the list
            styledStrings.add(Handler.applyStyle(str, styles));
        }
        return styledStrings;
    }
}
