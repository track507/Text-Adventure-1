import java.security.SecureRandom;
import java.util.*;

// Blackjack made by Terrence

public class BlackJack {
    private List<String> deck = new ArrayList<>();
    private List<Integer> playerDeck = new ArrayList<>();
    private List<Integer> dealerDeck = new ArrayList<>();
    private String faceDownCard; // Dealer's face-down card
    private int faceDownValue;
    private Random random;

    public BlackJack() {
        this.random = new SecureRandom();
        buildDeck();
        shuffleDeck();
        //testShuffles();
    }

    // Build the deck of cards
    private void buildDeck() {
        deck.clear();
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
    }

    // Shuffle the deck
    private void shuffleDeck() {
        Collections.shuffle(deck, random);
    }

    private void printShuffledDeck() {
        System.out.println("Shuffled deck:");
        for (String card : deck) {
            System.out.println(card);
        }
        System.out.println(); // For better readability
    }

    public void testShuffles() {
        for (int i = 0; i < 3; i++) { // Perform 3 shuffles
            System.out.println("Shuffle " + (i + 1) + ":");
            shuffleDeck();
            printShuffledDeck();
        }
    }    

    private int getCardValue(String card) {
        String rank = card.split(" ")[0]; // Get the rank part (e.g., "10" or "J")
        switch (rank) {
            case "J":
            case "Q":
            case "K":
                return 10;
            case "A":
                return 11; // Aces default to 11; adjust later if necessary
            default:
                return Integer.parseInt(rank); // Numeric cards
        }
    }
    

    // Play the game
    public int play(Scanner scanner) {
        // Initial deal
        dealCardToPlayer(playerDeck);
        dealCardToDealer(dealerDeck);
        dealCardToPlayer(playerDeck);
        dealFaceDownCard(dealerDeck);

        TextEngine.pt(Handler.applyStyle("Your cards: " + playerDeck + " (Total: " + getTotal(playerDeck) + ")", "i", "darkgrey"));
        TextEngine.pt(Handler.applyStyle("Dealer's visible card: " + dealerDeck, "i", "darkgrey"));

        // Player's turn
        while (true) {
            TextEngine.pt(Handler.applyStyle("Your turn. Hit or stand? (h/s)", "i"));
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("h")) {
                dealCardToPlayer(playerDeck);
                TextEngine.pt(Handler.applyStyle("Your cards: " + playerDeck + " (Total: " + getTotal(playerDeck) + ")", "i", "darkgrey"));
                if (getTotal(playerDeck) > 21) {
                    TextEngine.pt(Handler.applyStyle("You busted", "b", "red"));
                    return -1; // Player loses
                }
            } else if (choice.equals("s")) {
                break;
            } else {
                TextEngine.pt(Handler.applyStyle("Invalid choice. Please enter 'h' or 's'.", "i", "red"));
            }
        }

        // Dealer's turn
        dealerDeck.add(faceDownValue); // Reveal face-down card
        TextEngine.pt(Handler.applyStyle("Dealer's cards: " + dealerDeck + " (Total: " + getTotal(dealerDeck) + ")", "i", "darkgrey"));
        while (getTotal(dealerDeck) < 17) {
            dealCardToDealer(dealerDeck);
            TextEngine.pt(Handler.applyStyle("Dealer hits. Dealer's cards: " + dealerDeck + " (Total: " + getTotal(dealerDeck) + ")", "i", "darkgrey"));
        }

        return hasWon(); // Determine the result
    }

    // Determine the winner
    private int hasWon() {
        int playerTotal = getTotal(playerDeck);
        int dealerTotal = getTotal(dealerDeck);

        if (playerTotal > 21) {
            TextEngine.pt(Handler.applyStyle("You lost! Your total exceeded 21.", "b", "red"));
            return -1;
        } else if (dealerTotal > 21) {
            TextEngine.pt(Handler.applyStyle("You won! Dealer's total exceeded 21.", "b", "green"));
            return 1;
        } else if (playerTotal == dealerTotal) {
            System.out.println("It's a tie...");
            return 0;
        } else if (playerTotal > dealerTotal) {
            TextEngine.pt(Handler.applyStyle("You won! Your total is higher.", "b", "green"));
            return 1;
        } else {
            TextEngine.pt(Handler.applyStyle("You lost! Dealer's total is higher.", "b", "red"));
            return -1;
        }
    }

    // Calculate the total value of a deck
    private int getTotal(List<Integer> cardDeck) {
        int total = 0;
        int aceCount = 0;

        for (int card : cardDeck) {
            total += card;
            if (card == 11) {
                aceCount++;
            }
        }

        // Adjust for Aces
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }

    // Deal a card
    private void dealCardToPlayer(List<Integer> cardDeck) {
        if (deck.isEmpty()) {
            TextEngine.pt(Handler.applyStyle("The deck is empty! No more cards can be dealt.", "i", "darkgrey"));
            return;
        }

        String card = deck.remove(0); // Remove the top card
        int value = getCardValue(card);
        cardDeck.add(value);

        TextEngine.pt(Handler.applyStyle("You are dealt: ", "i", "darkgrey") + Handler.applyStyle(card, "b", "yellow"));
    }

    private void dealCardToDealer(List<Integer> cardDeck) {
        if (deck.isEmpty()) {
            TextEngine.pt(Handler.applyStyle("The deck is empty! No more cards can be dealt.", "i", "darkgrey"));
            return;
        }

        String card = deck.remove(0); // Remove the top card
        int value = getCardValue(card);
        cardDeck.add(value);

        TextEngine.pt(Handler.applyStyle("Acrius is dealt: ", "i", "darkgrey") + Handler.applyStyle(card, "b", "red"));
    }    

    // Deal a face-down card to the dealer
    private void dealFaceDownCard(List<Integer> cardDeck) {
        if (deck.isEmpty()) {
            System.out.println("The deck is empty! No more cards can be dealt.");
            return;
        }
        faceDownCard = deck.remove(0); // Remove the top card
        faceDownValue = getCardValue(faceDownCard); // Calculate value
        TextEngine.pt(Handler.applyStyle("Dealer deals a face-down card.", "i", "darkgrey"));
    }

    public static void TakeItem(Player player) {
        List<String> inventory = player.getInventory();
    
        // Check if inventory is not empty
        if (inventory.isEmpty()) {
            TextEngine.pt(Handler.applyStyle("The player's inventory is empty. No item to take.", "i", "darkgrey"));
            return;
        }
    
        // Generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(inventory.size());
    
        // Remove the item at the random index
        String removedItem = inventory.remove(randomIndex);
        TextEngine.pt(Handler.applyStyle("Acrius has taken: ", "i", "darkgrey") + Handler.applyStyle(removedItem, "b", "yellow"));
    }
    
    public static void GiveItem(Player player) {
        List<String> inventory = player.getInventory();
    
        String[] potentialItems = {"Health Potion", "Sword", "Shield", "Gold Coin", "Map"};
        Random random = new Random();
    
        // Pick a random item from the potential items
        String newItem = potentialItems[random.nextInt(potentialItems.length)];
    
        // Add the item to the player's inventory
        inventory.add(newItem);
        TextEngine.pt(Handler.applyStyle("Acrius has given you: ", "i", "darkgrey") + Handler.applyStyle(newItem, "b", "yellow"));
    }

    public void resetGame() {
        playerDeck.clear();  // Clear player cards
        dealerDeck.clear();  // Clear dealer cards
        deck.clear();
        buildDeck();         // Rebuild the deck
        shuffleDeck();       // Reshuffle the deck
    }
}
