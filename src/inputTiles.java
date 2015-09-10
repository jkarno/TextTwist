import java.awt.Graphics;

public class inputTiles extends GameObj {

	public static final int SIZE_X = 280;
	public static final int SIZE_Y = 40;
	public static int INIT_X = 250;
	public static int INIT_Y = 0;
	public static char[] letters;

	// Initializes the game object and creates a new empty array of letters
	// As letters are added to the array, they will show up as having been
	// input as tiles.
	public inputTiles(int courtWidth, int courtHeight, Dictionary d) {
		super(INIT_X, INIT_Y, SIZE_X, SIZE_Y, courtWidth, courtHeight);
		letters = new char[0];
	}

	// Draws letter pieces of each individual letter in the array
	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < letters.length; i++) {
			letterPiece lp = new letterPiece(pos_x + i * 40, 180, 600, 400,
					letters[i]);
			lp.draw(g);
		}
	}

	// if character doesn't exist, return '-'
	public void removeLetter(char l) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == l) {
				removeLetterAt(i);
			}
		}
	}

	// If empty, returns '-'
	public char removeEnd() {
		if (letters.length == 0) {
			return '-';
		}
		char end = letters[letters.length - 1];
		char[] newLetters = new char[letters.length - 1];
		for (int i = 0; i < newLetters.length; i++) {
			newLetters[i] = letters[i];
		}
		letters = newLetters;
		return end;
	}

	// Throws ArrayIndexOutOfBoundException if the index is not in the array.
	// Since this is a private method, this will not occur as it is only called
	// From the remove letter method which only calls it if it finds the letter
	// At the index
	private void removeLetterAt(int index) {
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
				letters = newLetters;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	// Add a letter to the array
	public void addLetter(char l) {
		char[] newLetters = new char[letters.length + 1];
		for (int i = 0; i < letters.length; i++) {
			newLetters[i] = letters[i];
		}
		newLetters[newLetters.length - 1] = l;
		letters = newLetters;
	}

	// Checks if the input tiles array has the letter
	public boolean contains(char l) {
		for (int i = 0; i < letters.length; i++) {
			if (l == letters[i]) {
				return true;
			}
		}
		return false;
	}

	// Returns the array as a word (used to check if the input tiles forms
	// a word
	public String returnAsWord() {
		return String.valueOf(letters);
	}
}
