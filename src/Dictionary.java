import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionary {

	private HashSet<String> d;
	private int[][] letterCounts;

	public Dictionary(String filename) throws IOException {

		BufferedReader r = null;
		HashSet<String> d = new HashSet<String>();

		try {

			r = new BufferedReader(new FileReader(filename));
			String temp = "";

			while ((temp = r.readLine()) != null) {
				temp = temp.trim().toLowerCase();
				if (!temp.equals("")) {
					if (temp.length() <= 7 && temp.length() > 2
							&& !temp.contains("-") && !temp.contains("'")) {
						d.add(temp); // only add words up to 7 letters and
										// without '-'
					}
				}
			}

		} catch (IOException e) {
			throw new IOException();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		} finally {
			if (r != null) {
				r.close();
			}
			this.d = d;
		}

		letterCounts = new int[this.getNumWords()][26]; // first index is word,
														// second is amount of
														// each letter
		String[] words = d.toArray(new String[this.getNumWords()]); // makes an
																	// array of
																	// all words

		for (int i = 0; i < this.getNumWords(); i++) {
			char[] word = words[i].toCharArray();
			for (int j = 0; j < word.length; j++) {
				if ((int) word[j] >= 97 && (int) word[j] <= 122) {
					letterCounts[i][(int) word[j] - 97]++;
				}
			}
		}
	}

	// returns the dictionary size
	public int getNumWords() {
		return d.size();
	}

	// checks if the word is in the dictionary
	public boolean isWord(String word) {
		if (word == null || word.trim().equals("")) { // null or empty string is
														// not in the dictionary
			return false;
		} else {
			word = word.trim().toLowerCase();
			return d.contains(word);
		}
	}

	// returns array of words with length requested, empty if no words exist
	public String[] getArrayWordsWithLength(int l) {

		HashSet<String> wordsWithLength = new HashSet<String>();
		String[] words = d.toArray(new String[0]);
		for (int i = 0; i < d.size(); i++) {
			if (words[i].length() == l) {
				wordsWithLength.add(words[i]);
			}
		}
		return wordsWithLength.toArray(new String[0]);

	}

	// returns an array that counts how many of each character is in a word
	// (for example, dog would have a 0 for every position except the 4th,
	// the 15th, and 7th indices
	public int[][] getLetterLengths() {
		return this.letterCounts;
	}

	// returns the word at an index in the dictionary
	public String wordAtIndex(int index) {
		String[] words = d.toArray(new String[0]);
		return words[index];
	}
}
