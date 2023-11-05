import java.util.Random;
import java.util.Scanner;

class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        int rounds = 3;

        for (int round = 1; round <= rounds; round++) {
            System.out.println("Round " + round);
            int targetNumber = random.nextInt(101); // Generate a random number between 0 and 100
            int attempts = 10;
            int score = 0;

            System.out.println("Guess the number between 0 and 100. You have " + attempts + " attempts.");

            while (attempts > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    score += attempts * 10; // Score based on remaining attempts
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }

                attempts--;
            }

            totalScore += score;
            System.out.println("Round " + round + " Score: " + score);
        }

        System.out.println("Game Over!");
        System.out.println("Total Score: " + totalScore);

        scanner.close();
    }
}
