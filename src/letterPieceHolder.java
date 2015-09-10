import java.awt.Graphics;

public class letterPieceHolder extends GameObj {

	public static final int SIZE_X = 280;
	public static final int SIZE_Y = 40;
	public static int INIT_X = 250;
	public static int INIT_Y = 0;
	public static scrambleWord scrambleWord;
	private int wordCount;
	private String[] wordsList;

	/** A new letterPieceHolder will automatically get a seven letter scramble
	 * word from the dictionary and shuffle it's letters. It will then form
	 * a word count with how many words can be formed with the letters
	 * and a word list with all of the possible words. Similar to the input
	 * tiles, letters can be added or removed from the scrambleWord, only
	 * showing the letters in the rack **/
	public letterPieceHolder(int courtWidth, int courtHeight, Dictionary d) {
		super(INIT_X, INIT_Y, SIZE_X, SIZE_Y, courtWidth, courtHeight);
		scrambleWord = new scrambleWord(d, 7);
		scrambleWord.shuffle();
		wordCount = scrambleWord.wordsLeft();
		wordsList = scrambleWord.wordsList;
	}

	// Draws the letter pieces by sending in the letters from the array
	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < scrambleWord.length(); i++) {
			letterPiece lp = new letterPiece(pos_x + i * 40, 250, 600, 400,
					scrambleWord.getLetterAt(i));
			lp.draw(g);
		}
	}

	// if character doesn't exist, return '-'
	public char removeLetter(char l) {
		for (int i = 0; i < scrambleWord.length(); i++) {
			if (scrambleWord.getLetterAt(i) == l) {
				return scrambleWord.removeLetterAt(i);
			}
		}
		return '-';
	}

	// Adds a letter to the scramble word
	public void addLetter(char l) {
		scrambleWord.addLetter(l);
	}

	// Checks if a letter exists in the array
	public boolean contains(char l) {
		return scrambleWord.containsLetter(l);
	}

	// Shuffles the letters in the array
	public void shuffle() {
		scrambleWord.shuffle();
	}

	// Returns how many words can be formed
	public int getWordCount() {
		return this.wordCount;
	}

	// Returns the list of words that are formed
	public String[] getWordsList() {
		return this.wordsList;
	}

	// Returns the map of all words that are left
	public possibleWords getPossiblewords() {
		return scrambleWord.wordsLeft;
	}
}
