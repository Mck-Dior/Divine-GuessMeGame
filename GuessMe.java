import java.util.Random;
import java.util.Scanner;


// Name: Divine Chinecherem Nnamdi
class Player {
    String name;
    int id;
    int score = 0;
    int highestScore = 0;
    int currentLevel = 1;

    Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    void updateScore(int points) {
        score += points;
        if (score > highestScore) {
            highestScore = score;
        }
    }

    void reset() {
        score = 0;
        currentLevel = 1;
    }

    void displayStatus() {
        System.out.println("\n");
        System.out.println("Player: " + name + " | ID: " + id);
        System.out.println("Current Score: " + score);
        System.out.println("Highest Score: " + highestScore);
        System.out.println("\n");
    }
}

public class GuessMe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("");
        System.out.println("      Welcome to GuessMe game");
        System.out.println("");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int id;
        while (true) {
            System.out.print("Enter your ID (numbers only): ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid ID! Please enter numbers only.");
            }
        }

        Player player = new Player(name, id);
        boolean playAgain = true;

        while (playAgain) {
            player.reset();
            System.out.println("\nHi " + player.name + ", your current score is " + player.score + ".");

            boolean gameOver = false;

            while (!gameOver && player.currentLevel <= 3) {                                                                                                                                                                   int max = 10, trials = 3;
                if (player.currentLevel == 2) {
                    max = 20;                                                                                                                                                                                                       trials = 4;
                } else if (player.currentLevel == 3) {
                    max = 100;
                    trials = 6;
                }

                System.out.println("\n" + player.name + ", you have " + trials + " attempts for Level " + player.currentLevel + ".");
                gameOver = !playLevel(scanner, player, max, trials);
                if (!gameOver) {
                    player.currentLevel++;
                    System.out.println("Current Score: " + player.score);
                }
            }

            System.out.println("\nGame Over!");
            player.displayStatus();

            // Replay prompt
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes");

            if (playAgain) {
                System.out.println("\nLet's go again, " + player.name + "!");
                System.out.println("Your current score is: " + player.score);
                System.out.println("Your highest score is: " + player.highestScore);
            }
        }

        System.out.println("\nThanks for playing GuessMe, " + player.name + "!");
    }

    public static boolean playLevel(Scanner scanner, Player player, int max, int maxTrials) {
        Random rand = new Random();
        int number = rand.nextInt(max + 1);

        System.out.println("\nLevel " + player.currentLevel + " - " + player.name + ", guess a number between 0 and " + max + ":");

        for (int i = 1; i <= maxTrials; i++) {
            int guess = -1;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Attempt " + i + ": ");
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                    if (guess < 0 || guess > max) {
                        System.out.println("Value out of range! Enter a number between 0 and " + max + ".");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a whole number.");
                }
            }

            if (guess == number) {
                System.out.println("Awesome! That's correct. You earned 10 points.");
                player.updateScore(10);
                return true;
            } else if (guess < number) {
                System.out.println("Too low.");
            } else {
                System.out.println("Too high.");
            }
        }

        System.out.println("Oops! The correct number was: " + number);
        return false;
    }
}
                                                                                                                