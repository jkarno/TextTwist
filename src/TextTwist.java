import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import java.util.Collections;
import java.util.HashSet;

/**
 * GameCourt
 * 
 * This class holds the primary game logic of how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class TextTwist extends JPanel {

	// The state of the game logic
	public boolean playing = false; // Whether the game is running
	private int score; // Current Score
	private int wordsLeft; // Current words left
	private int roundNum = 1; // Current round
	public static boolean allowNextRound = false;
	private MouseHandler mouseHandler; // Follows the internal class
	private Point mousePos = new Point(0, 0); // Updates with mouse position

	// Game Objects
	// keeps track of words played by the game so far
	private HashSet<String> wordsPlayed = new HashSet<String>();
	private letterPieceHolder letterPieceHolder; // creates letters
	private inputTiles inputTiles; // container for letter input
	private Dictionary d; // dictionary with words
	private HashSet<String> enteredWords; // keeps track of entered words
	private timeCounter countDown; // timer that counts down time

	// Counter tools
	private int timeLeft; // timeleft, based on the timer
	// These add time if button is clicked, changing image to clicked image
	private int shuffleClickCount = 0;
	private int hintClickCount = 0;
	// Add time to displaying alert messages
	private int notInDictCount = 0;
	private int alreadyGuessedCount = 0;
	// Keeps track of hints left
	private int hintsLeft = 3;

	// The word list has all words that can be played
	private String[] wordsList;
	// Using word list, maps the words to what should display
	private possibleWords wordsMap;

	// If round is over, words will reveal
	private boolean reveal = false;
	private int revealTime = 100;

	// Tools for keeping track of bonuses
	private int scoreMult = 1;
	private int multCount = 0;
	private boolean allWordsBonus = false;
	private int allWordsBonusCount = 0;
	private boolean addedTime = false;

	// Court size
	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 400;

	// Update interval for timer in milliseconds
	public static final int INTERVAL = 35;

	public TextTwist() {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		enteredWords = new HashSet<String>();
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);

		try {
			d = new Dictionary("textTwistWithSev.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		setFocusable(true);

		// Listens for key clicks
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					char delete = inputTiles.removeEnd();
					if (delete != '-') {
						letterPieceHolder.addLetter(delete);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!reveal) {
						String word = inputTiles.returnAsWord();
						if (d.isWord(word) && !enteredWords.contains(word)) {
							// found a word
							score += word.length() * 100 * scoreMult;
							enteredWords.add(word);
							wordsLeft--;
							wordsMap.guessedWord(word);
							if (word.length() == 7) {
								// seven letter word is found
								allowNextRound = true;
							}
							if (wordsLeft == 0) { // finished round early
													// (score should add bonus)
								allWordsBonus = true;
								allWordsBonusCount = 100;
								nextRound();
							}
							notInDictCount = 0;
							alreadyGuessedCount = 0;
						} else if (word.isEmpty()) {
						} else if (enteredWords.contains(word)) {
							// already entered
							alreadyGuessedCount = 35;
							notInDictCount = 0;
						} else { // not a word
							notInDictCount = 35;
							alreadyGuessedCount = 0;
						}
					}
					for (int i = 0; i < inputTiles.toString().length(); i++) {
						char end = inputTiles.removeEnd();
						if (end != '-') {
							letterPieceHolder.addLetter(end);
						}
					}
				} else {
					if (!reveal) {
						char entered = e.getKeyChar();
						if (letterPieceHolder.contains(entered)) {
							char remove = letterPieceHolder
									.removeLetter(entered);
							inputTiles.addLetter(remove);
						}
					}
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		});

		this.score = 0;
		this.countDown = new timeCounter(120);
	}

	/**
	 * (Re-)set the state of the game to its initial state.
	 */
	public void reset() {
		letterPieceHolder = new letterPieceHolder(COURT_WIDTH, COURT_HEIGHT, d);
		while (letterPieceHolder.getWordCount() > 45
				|| letterPieceHolder.getWordCount() < 8
				|| !Collections.disjoint(wordsPlayed, letterPieceHolder
						.getPossiblewords().returnAllSevenLetterWords())) {
			letterPieceHolder = new letterPieceHolder(COURT_WIDTH,
					COURT_HEIGHT, d);
		}
		wordsPlayed.addAll(letterPieceHolder.getPossiblewords()
				.returnAllSevenLetterWords());
		inputTiles = new inputTiles(COURT_WIDTH, COURT_HEIGHT, d);

		this.wordsLeft = letterPieceHolder.getWordCount();
		this.wordsList = letterPieceHolder.getWordsList();
		this.wordsMap = letterPieceHolder.getPossiblewords();
		playing = true;
		score = 0;
		allowNextRound = false;
		countDown.restart();
		roundNum = 1;
		revealTime = 100;
		reveal = false;
		hintsLeft = 3;
		notInDictCount = 0;
		alreadyGuessedCount = 0;
		scoreMult = 1;
		multCount = 0;
		addedTime = false;
		enteredWords = new HashSet<String>();

		mousePos = new Point(0, 0);

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	// Moves to next round in game
	public void nextRound() {
		letterPieceHolder = new letterPieceHolder(COURT_WIDTH, COURT_HEIGHT, d);
		while (letterPieceHolder.getWordCount() > 45 
				|| letterPieceHolder.getWordCount() < 8
				|| !Collections.disjoint(wordsPlayed, letterPieceHolder
						.getPossiblewords().returnAllSevenLetterWords())) {
			letterPieceHolder = new letterPieceHolder(COURT_WIDTH,
					COURT_HEIGHT, d);
		}
		inputTiles = new inputTiles(COURT_WIDTH, COURT_HEIGHT, d);

		this.wordsLeft = letterPieceHolder.getWordCount();
		this.wordsList = letterPieceHolder.getWordsList();
		this.wordsMap = letterPieceHolder.getPossiblewords();

		playing = true;
		allowNextRound = false;
		countDown.restart();
		roundNum++;
		revealTime = 100;
		reveal = false;
		notInDictCount = 0;
		alreadyGuessedCount = 0;
		scoreMult = 1;
		multCount = 0;
		addedTime = false;
		enteredWords = new HashSet<String>();

		if (allWordsBonus) {
			countDown.addTime(30);
			addedTime = true;
		}

		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {

		if (playing) {

			timeLeft = countDown.getTime();
			// update the display
			repaint();

			if (countDown.done()) {
				reveal = true;
				if (revealTime == 0) {
					if (allowNextRound) {
						nextRound();
						countDown.restart();
					} else {
						playing = false;
						Game.cl.show(Game.fullGameLayout, "4");
						Game.gameOverClass.setScore(score);
						Game.gameOverClass.reset();
					}
				}
				revealTime--;
			}

			// each of these reduces the counter for certain actions
			if (shuffleClickCount > 0) {
				shuffleClickCount--;
			}
			if (hintClickCount > 0) {
				hintClickCount--;
			}
			if (notInDictCount > 0) {
				notInDictCount--;
			}
			if (alreadyGuessedCount > 0) {
				alreadyGuessedCount--;
			}
			if (multCount > 0) {
				multCount--;
				if (multCount == 0) {
					scoreMult = 1;
				}
			}
			if (allWordsBonusCount > 0) {
				allWordsBonusCount--;
				if (allWordsBonusCount == 0) {
					allWordsBonus = false;
				}
			}

			if (!addedTime) {
				if (countDown.getTime() == 110) {
					if (enteredWords.size() >= 5) {
						scoreMult = 2;
						multCount = 500;
					}
				}
			} else {
				if (countDown.getTime() == 140) {
					if (enteredWords.size() >= 5) {
						scoreMult = 2;
						multCount = 500;
					}
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(readImagesToMap.images.get("GUI_BG"), 0, 0, 600, 400, null);
		g.drawImage(readImagesToMap.images.get("GUI_tileHolder"), 250, 180,
				280, 40, null);
		g.drawRect(250, 180, 280, 40);
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		g.drawString(String.valueOf(score), 75, 388);
		g.drawString(String.valueOf(roundNum), 450, 388);
		g.drawString(String.valueOf(timeLeft), 540, 388);
		letterPieceHolder.draw(g);
		inputTiles.draw(g);

		int x = mousePos.x;
		int y = mousePos.y;

		if (x >= 5 && x <= 148 && y >= 5 && y <= 25) {
			g.drawImage(readImagesToMap.images.get("BUTTON_menuSelected"), 5,
					2, 150, 30, null);
		} else {
			g.drawImage(readImagesToMap.images.get("BUTTON_menuRegular"), 5, 2,
					150, 30, null);
		}
		if (allowNextRound) {
			if (x >= 453 && x <= 600 && y >= 5 && y <= 25) {
				g.drawImage(
						readImagesToMap.images.get("BUTTON_nextRoundSelected"),
						450, 2, 150, 30, null);
			} else {
				g.drawImage(
						readImagesToMap.images.get("BUTTON_nextRoundRegular"),
						450, 2, 150, 30, null);
			}
		}
		if (shuffleClickCount > 0) {
			g.drawImage(readImagesToMap.images.get("BUTTON_ShuffleClick"), 250,
					317, 100, 30, null);
		} else if (x >= 250 && x <= 350 && y >= 317 && y <= 347) {
			g.drawImage(readImagesToMap.images.get("BUTTON_ShuffleHover"), 250,
					317, 100, 30, null);
		} else {
			g.drawImage(readImagesToMap.images.get("BUTTON_ShuffleRegular"),
					250, 317, 100, 30, null);
		}

		if (hintClickCount > 0) {
			if (hintsLeft == 3) {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsClick3"),
						400, 317, 100, 30, null);
			} else if (hintsLeft == 2) {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsClick2"),
						400, 317, 100, 30, null);
			} else if (hintsLeft == 1) {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsClick1"),
						400, 317, 100, 30, null);
			} else {
				hintClickCount = 0;
			}
		} else if (x >= 400 && x <= 500 && y >= 317 && y <= 347) {
			if (hintsLeft == 3) {
				g.drawImage(
						readImagesToMap.images.get("BUTTON_HintsSelected3"),
						400, 317, 100, 30, null);
			} else if (hintsLeft == 2) {
				g.drawImage(
						readImagesToMap.images.get("BUTTON_HintsSelected2"),
						400, 317, 100, 30, null);
			} else if (hintsLeft == 1) {
				g.drawImage(
						readImagesToMap.images.get("BUTTON_HintsSelected1"),
						400, 317, 100, 30, null);
			} else {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsUsed"),
						400, 317, 100, 30, null);
			}
		} else {
			if (hintsLeft == 3) {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsRegular3"),
						400, 317, 100, 30, null);
			} else if (hintsLeft == 2) {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsRegular2"),
						400, 317, 100, 30, null);
			} else if (hintsLeft == 1) {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsRegular1"),
						400, 317, 100, 30, null);
			} else {
				g.drawImage(readImagesToMap.images.get("BUTTON_HintsUsed"),
						400, 317, 100, 30, null);
			}
		}

		if (reveal == false) {
			for (int i = 0; i < wordsList.length; i++) {
				if (i < 15) {
					g.drawString(wordsMap.getWord(wordsList[i]), 15,
							60 + i * 20);
				} else if (i < 30) {
					g.drawString(wordsMap.getWord(wordsList[i]), 105, -240 + i
							* 20);
				} else if (i < 35) {
					g.drawString(wordsMap.getWord(wordsList[i]), 250, -540 + i
							* 20);
				} else if (i < 40) {
					g.drawString(wordsMap.getWord(wordsList[i]), 340, -640 + i
							* 20);
				} else if (i < 45) {
					g.drawString(wordsMap.getWord(wordsList[i]), 430, -740 + i
							* 20);
				}
			}
		} else {
			for (int i = 0; i < wordsList.length; i++) {
				String revealWord = wordsMap.getWord(wordsList[i]);
				if (revealWord.contains("?")) {
					g.setColor(Color.decode("#a52452"));
				} else {
					g.setColor(Color.BLACK);
				}
				if (i < 15) {
					g.drawString(wordsList[i], 15, 60 + i * 20);
				} else if (i < 30) {
					g.drawString(wordsList[i], 105, -240 + i * 20);
				} else if (i < 35) {
					g.drawString(wordsList[i], 250, -540 + i * 20);
				} else if (i < 40) {
					g.drawString(wordsList[i], 340, -640 + i * 20);
				} else if (i < 45) {
					g.drawString(wordsList[i], 430, -740 + i * 20);
				}
			}
		}

		if (notInDictCount > 0) {
			g.drawImage(readImagesToMap.images.get("ERROR_NotInDict"), 290, 50,
					200, 100, null);
		} else if (alreadyGuessedCount > 0) {
			g.drawImage(readImagesToMap.images.get("ERROR_AlreadyEntered"),
					290, 50, 200, 100, null);
		} else if (multCount > 0) {
			g.drawImage(readImagesToMap.images.get("BONUS_DoublePoints"), 290,
					50, 200, 100, null);
		} else if (allWordsBonus) {
			g.drawImage(readImagesToMap.images.get("BONUS_AllWords"), 290, 50,
					200, 100, null);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {

		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX();
			int y = arg0.getY();
			if (x >= 5 && x <= 148 && y >= 5 && y <= 25) {
				Game.cl.show(Game.fullGameLayout, "1"); // main menu
				countDown.stopTimer();
				Game.menuClass.reset();
			} else if (x >= 453 && x <= 600 && y >= 5 && y <= 25) {
				if (allowNextRound) { // next round
					Game.court.nextRound();
				}
			} else if (x >= 250 && x <= 350 && y >= 317 && y <= 347) {
				shuffleClickCount = 1;
				letterPieceHolder.shuffle(); // clicked shuffle
			} else if (x >= 400 && x <= 500 && y >= 317 && y <= 347) {
				if (hintsLeft > 0) {
					hintClickCount = 1;
					wordsMap.usedHint(); // clicked hint
					if (!allowNextRound) {
						hintsLeft--;
					}
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			mousePos = arg0.getPoint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int x = arg0.getX();
			int y = arg0.getY();
			if (x >= 250 && x <= 350 && y >= 317 && y <= 347) {
				shuffleClickCount = 1000;
			} else if (x >= 400 && x <= 500 && y >= 317 && y <= 347) {
				if (hintsLeft > 0) {
					hintClickCount = 1000;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int x = arg0.getX();
			int y = arg0.getY();
			shuffleClickCount = 0;
			hintClickCount = 0;
			if (x >= 250 && x <= 350 && y >= 317 && y <= 347) {
				letterPieceHolder.shuffle();
			}
		}
	}

}
