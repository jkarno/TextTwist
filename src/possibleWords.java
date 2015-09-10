import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class possibleWords {

	// this takes words that are possible and assigns them to a hashmap
	// if the word is guessed, it will be changed from question marks to 
	// the actual word. These words are displayed in the main game.
	private HashMap<String, String> words = new HashMap<String, String>();
	private HashMap<String, String> revealed = new HashMap<String, String>();
	private int currentHint = 0;
	private String fullWord;
	private ArrayList<Integer> hintNum;
	private ArrayList<String> threeLet = new ArrayList<String>();
	private ArrayList<String> fourLet = new ArrayList<String>();
	private ArrayList<String> fiveLet = new ArrayList<String>();
	private ArrayList<String> sixLet = new ArrayList<String>();
	private ArrayList<String> sevLet = new ArrayList<String>();

	public possibleWords(String fullWord) {
		this.fullWord = fullWord; // initialize full word for round

		threeLet = new ArrayList<String>();
		fourLet = new ArrayList<String>();
		fiveLet = new ArrayList<String>();
		sixLet = new ArrayList<String>();
		sevLet = new ArrayList<String>();

		ArrayList<Integer> hintNum = new ArrayList<Integer>(fullWord.length());

		for (int i = 0; i < fullWord.length(); i++) {
			hintNum.add(i);
		}
		Collections.shuffle(hintNum);
		this.hintNum = hintNum;
	}

	public String getWord(String word) {
		return words.get(word);
	}

	public void addWord(String word) {
		String blanks = new String("");
		for (int i = 0; i < word.length(); i++) {
			blanks = blanks.concat("?");
		}
		words.put(word, blanks);
		revealed.put(word, word);

		if (word.length() == 3) {
			threeLet.add(word);
		} else if (word.length() == 4) {
			fourLet.add(word);
		} else if (word.length() == 5) {
			fiveLet.add(word);
		} else if (word.length() == 6) {
			sixLet.add(word);
		} else if (word.length() == 7) {
			sevLet.add(word);
		}
	}

	// Returns the word to display (will either be the word, or question marks)
	public String displayWord(String word) {
		return words.get(word);
	}

	// Tells map to change question marks to the real word
	public void guessedWord(String word) {
		if (words.get(word) != null) {
			words.put(word, word);
		}
	}

	// Reveals two random letters in full word
	public void usedHint() {

		if (currentHint == 6) {
			return; // only give hint if three haven't been used
		}
		// Goes through next
		// Two values to reveal, replaces question mark with letter
		for (int i = currentHint; i < currentHint + 2; i++) {

			String newWord = words.get(fullWord);
			int num = hintNum.get(i);
			char replace = fullWord.charAt(num);

			newWord = newWord.substring(0, num) + String.valueOf(replace)
					+ newWord.substring(num + 1, fullWord.length());

			words.put(fullWord, newWord);
		}
		currentHint += 2;
	}

	// Prints all possible words (can be enabled in scrambleWord)
	public void printWords() {
		String[] printWords = new String[words.size()];
		printWords = words.keySet().toArray(printWords);
		String[] printUnknown = new String[words.size()];
		printUnknown = words.values().toArray(printUnknown);
		for (int i = 0; i < words.size(); i++) {
			System.out.println(printWords[i]);
		}
	}

	// Compiles the entire map in order to display them by word length
	public String[] compileOrder() {

		ArrayList<String> compiled = new ArrayList<String>();
		compiled.addAll(threeLet);
		compiled.addAll(fourLet);
		compiled.addAll(fiveLet);
		compiled.addAll(sixLet);
		compiled.addAll(sevLet);

		String[] compileWords = new String[words.size()];
		compileWords = compiled.toArray(compileWords);
		return compileWords;
	}

	// Returns all the seven letter words that can be played
	public ArrayList<String> returnAllSevenLetterWords() {
		return this.sevLet;
	}
}
