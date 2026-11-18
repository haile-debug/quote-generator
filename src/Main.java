import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get the quotes from the QuoteCategories class.
        String[] quotes = QuoteCategories.getStudyQuotes();

        Random random = new Random();

        // -1 means no quote has been shown yet.
        int previousIndex = -1;

        // Automatically close the scanner when finished.
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                previousIndex = printRandomQuote(
                        quotes, random, previousIndex
                );

                System.out.println(
                        "Press Enter for another quote, or type q to quit."
                );

                // Stop if no more input is available.
                if (!scanner.hasNextLine()) {
                    break;
                }

                // Remove extra spaces from the user's input.
                String answer = scanner.nextLine().trim();

                // Accept both lowercase q and uppercase Q to quit.
                if (answer.equalsIgnoreCase("q")) {
                    break;
                }
            }
        }

        System.out.println("Thanks for using the quote generator!");
    }

    public static int printRandomQuote(
            String[] quotes, Random random, int previousIndex) {

        int index;

        // Avoid showing the same quote twice in a row.
        // Only retry when multiple quotes exist to avoid an infinite loop.
        do {
            index = random.nextInt(quotes.length);
        } while (quotes.length > 1 && index == previousIndex);

        System.out.println("\nYour quote: " + quotes[index]);

        // Return the index so the next call can avoid repeating it.
        return index;
    }
}