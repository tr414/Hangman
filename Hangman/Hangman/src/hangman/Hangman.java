package hangman;
import java.util.ArrayList;
import java.util.Arrays;

public class Hangman {
	private final char[] word;
	private char[] guess;
	private int remainingGuesses;
	private ArrayList<Character> guessedLetters;
	public boolean wordCorrectlyGuessed;
	
	// Constructor
	public Hangman(String word) {
		this.word = word.toCharArray();
		this.guess = createGuessArray(word);
		this.remainingGuesses = 8;
		this.guessedLetters = new ArrayList<Character>();
		this.wordCorrectlyGuessed = false;
	}
	
	private char[] createGuessArray(String word) {
		// This method creates an array to represent the word as a series of dashes.
		int wordLength = word.length();
		char[] guessArray = new char[wordLength];
		Arrays.fill(guessArray, '-');
		return guessArray;
	}
	
	public char[] getGuess() {
		return this.guess;
	}
	
	public int getRemainingGuesses() {
		return this.remainingGuesses;
	}
	
	public boolean isOver() {
		// Check if the word has been correctly guessed, or if the user is out of guesses to make.
		if (Arrays.equals(word, guess)) {
			this.wordCorrectlyGuessed = true;
			return true;
		}
		if (remainingGuesses == 0) {
			return true;
		}
		return false;
	}
	
	public boolean letterIsUsed(char letter) {
		// Check if the letter input by the user has already been used before
		return guessedLetters.contains(letter);
	}
	
	private void updateGuess(char letter, ArrayList<Integer> indices) {
		// Update the 'Guess' array to reflect the latest guess by the user
		for (int index : indices) {
			this.guess[index] = letter;
		}
	}
	
	public boolean checkGuess(char letter) {
		// Check if the letter guessed by the user is part of the hidden word. Return true if it is.
		guessedLetters.add(letter);
	
		boolean guessInWord = false;
		ArrayList<Integer> indexOfLetter = new ArrayList<Integer>();
		
		// Check if the letter guessed by the user is present in the word, and store it's positions
		for (int i = 0 ; i < this.word.length; i++) {
			if (letter == word[i]) {
				guessInWord = true;
				indexOfLetter.add(i);
			}
		}
		
		// IF letter is found in the word, update the guessed word so far to reflect this. Else, decrement remaining guesses by 1
		if (guessInWord) {
			updateGuess(letter, indexOfLetter);
		} else {
			this.remainingGuesses -= 1;
		}
		
		return guessInWord;	
	}
		
}
