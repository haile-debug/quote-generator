import java.util.Random;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
       String[] quotes = {
               "Small steps every day lead to progress.",
               "You don't need to know everything to get started.",
               "Every mistake is a chance to learn.",
               "Practice helps you improve.",
               "Everyone starts somewhere."
       };

       Random random = new Random();
       Scanner scanner = new Scanner(System.in);

       while (true) {
           printRandomQuote(quotes, random);

           System.out.println("Press Enter for another quote, or type q and press Enter to quit.");           String answer = scanner.nextLine();

           if (answer.equalsIgnoreCase("q")) {
               break;
           }
       }
       System.out.println("Thanks for using the quote generator!");
           scanner.close();
    }
    public static void printRandomQuote(String[] quotes, Random random) {
        int index = random.nextInt(quotes.length);
        System.out.println("Your quote: " + quotes[index]);
    }
}
