import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        // Store favourite quotes during the current session.
        ArrayList<String> favorites = new ArrayList<>();

        // -1 means no quote has been shown yet.
        int previousIndex = -1;

        // Automatically close the scanner when finished.
        try (Scanner scanner = new Scanner(System.in)) {
            String[] quotes = chooseCategory(scanner);

            while (true) {
                previousIndex = printRandomQuote(
                        quotes, random, previousIndex
                );

                System.out.println(
                        "Press Enter for another quote, f to save a favourite, "
                                + "v to view favourites, c to change category, or q to quit."
                );

                // Stop if no more input is available.
                if (!scanner.hasNextLine()) {
                    break;
                }

                String answer = scanner.nextLine().trim();

                if (answer.equalsIgnoreCase("f")) {
                    // Save the quote currently displayed.
                    String currentQuote = quotes[previousIndex];

// Only save the quote if it is not already a favourite.
                    if (!favorites.contains(currentQuote)) {
                        favorites.add(currentQuote);
                        System.out.println("Quote saved to favourites!");
                    } else {
                        System.out.println("This quote is already in your favourites.");
                    }


                } else if (answer.equalsIgnoreCase("v")) {
                    // Display saved favourite quotes.
                    if (favorites.isEmpty()) {
                        System.out.println("No favourites saved yet.");
                    } else {
                        System.out.println("Your favourite quotes:");

                        for (String favorite : favorites) {
                            System.out.println("- " + favorite);
                        }
                    }

                } else if (answer.equalsIgnoreCase("c")) {
                    // Choose a category and reset the previous quote index.
                    quotes = chooseCategory(scanner);
                    previousIndex = -1;

                } else if (answer.equalsIgnoreCase("q")) {
                    break;
                }
            }
        }

        System.out.println("Thanks for using the quote generator!");
    }

    public static String[] chooseCategory(Scanner scanner) {
        System.out.println("Choose a category:");
        System.out.println("1. Motivation");
        System.out.println("2. Studying");

        String category = scanner.nextLine().trim();

        // Keep asking until the user enters a valid category.
        while (!category.equals("1") && !category.equals("2")) {
            System.out.println("Invalid choice. Please enter 1 or 2.");
            category = scanner.nextLine().trim();
        }

        if (category.equals("1")) {
            return QuoteCategories.getMotivationQuotes();
        } else {
            return QuoteCategories.getStudyQuotes();
        }
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

        // Return the index so the program remembers the displayed quote.
        return index;
    }
}