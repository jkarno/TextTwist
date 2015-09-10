import java.util.ArrayList;
import java.util.Collections;

/**
 * A scrambleWord is an object that randomly finds a seven letter word from the
 * dictionary, takes its letters, and finds how many words are possible to be
 * formed from it. It also has a map of these words to strings that begin as
 * question marks and, once found by the player, are replaced with the word
 * itself, using the possibleWords class.
 **/
public class scrambleWord {

	private char[] letters;
	private Dictionary d;
	public possibleWords wordsLeft;
	public String[] wordsList;

	public scrambleWord(Dictionary d, int size) {
		String[] wordsWithLength = d.getArrayWordsWithLength(size);
		int x = (int) Math.floor((Math.random() * wordsWithLength.length));
		this.wordsLeft = new possibleWords(wordsWithLength[x]);
		letters = wordsWithLength[x].toCharArray();
		this.d = d;
	}

	// A scramble word can be shuffled to change the letters around
	public void shuffle() {

		int length = letters.length;
		ArrayList<Character> shuffled = new ArrayList<Character>(length);

		for (int i = 0; i < length; i++) {
			shuffled.add(letters[i]);
		}
		Collections.shuffle(shuffled);

		char[] shuffledChar = new char[shuffled.size()];
		for (int i = 0; i < length; i++) {
			shuffledChar[i] = shuffled.get(i);
		}

		letters = shuffledChar;
	}

	// You can check if a scrambleWord contains a certain letter
	public boolean containsLetter(char l) {
		for (int i = 0; i < letters.length; i++) {
			if (l == letters[i]) {
				return true;
			}
		}
		return false;
	}

	// You can remove a letter from a scramble word
	public char removeLetterAt(int index) {
		try {
			if (index >= letters.length) {
				throw new ArrayIndexOutOfBoundsException();
			} else {
				char[] newLetters = new char[letters.length - 1];
				for (int i = 0; i < index; i++) {
					newLetters[i] = letters[i];
				}
				for (int i = index + 1; i < letters.length; i++) {
					newLetters[i - 1] = letters[i];
				}
				char get = letters[index];
				letters = newLetters;
				return get;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	// You can get a letter from a scramble word
	public char getLetterAt(int index) {
		try {
			if (index >= letters.length) {
				throw new ArrayIndexOutOfBoundsException();
			} else {
				return letters[index];
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	// Add a letter to the end of the array
	public void addLetter(char l) {
		char[] newLetters = new char[letters.length + 1];
		for (int i = 0; i < letters.length; i++) {
			newLetters[i] = letters[i];
		}
		newLetters[newLetters.length - 1] = l;
		letters = newLetters;
	}

	// Find how many letters are left in the array
	public int length() {
		return letters.length;
	}

	// Calculates what words can be formed. This needs to be run in order to
	// initialize a proper wordsList and wordsLeft state
	public int wordsLeft() {
		int count = 0;
		int[][] wordCount = d.getLetterLengths();
		int[] letterLength = new int[26];
		for (int i = 0; i < letters.length; i++) {
			letterLength[letters[i] - 97]++;
		}

		for (int i = 0; i < wordCount.length; i++) {
			int temp = 0;
			for (int x = 0; x < 26; x++) {
				if (letterLength[x] >= wordCount[i][x]) {
					temp++;
				}
			}
			if (temp == 26) {
				count++;
				wordsLeft.addWord(d.wordAtIndex(i));
			}
		}

		// uncomment this line to print all possible words for a round
//		wordsLeft.printWords();
		this.wordsList = wordsLeft.compileOrder();

		return count;
	}
}
