package hangman;
import java.util.Random;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"HELLO", "UNIX", "ECLIPSE", "JAVA", "INTERFACE"};
		
		// Generate an index to pick a random word from the array
		Random rand = new Random();
		int numWords = words.length;
		int randomIndex = rand.nextInt(numWords);
		
		// Create a new Hangman game
		Hangman game = new Hangman(words[randomIndex]);
		
		// Start the game. Keep asking user to guess until the game is over
		System.out.println("Welcome to Hangman!");
		Scanner scanner = new Scanner(System.in);
		while (!game.isOver()) {
			// Print the progress so far
			System.out.print("The word now looks like this: ");
			System.out.println(game.getGuess());
			
			//Print number of guesses left to make
			int remainingGuesses = game.getRemainingGuesses();
			
			if (remainingGuesses == 1) {
				System.out.println("You have only one guess left.");
			} else {
				System.out.println("You have " + remainingGuesses + " guesses left.");
			}
			
			// Ask the user to guess a letter
			System.out.print("Your guess: ");
			char userGuess = scanner.nextLine().toUpperCase().charAt(0);
			
			// Check if this letter has been guessed before
			if (game.letterIsUsed(userGuess)) {
				System.out.println("Sorry, this letter has been used before. You may make another guess");
				continue;
			}
			
			// Check if correct guess or not, and update game parameters accordingly
			if (game.checkGuess(userGuess)) {
				System.out.println("That guess is correct.");
			} else {
				System.out.println("There are no " + userGuess + "'s in the word.");
			}	
		}
		scanner.close();
		// Display the results from the game
		if (game.wordCorrectlyGuessed) {
			System.out.println("You guessed the word: " + words[randomIndex]);
			System.out.println("You win.");
		} else {
			System.out.println("You're completely hung.");
			System.out.println("The word was: " + words[randomIndex]);
			System.out.println("You lose.");
		}		
	}

}
