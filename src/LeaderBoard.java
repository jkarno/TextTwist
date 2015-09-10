import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JPanel;
import javax.swing.Timer;

// The leaderboard displays the current leaders in the game. When it is created,
// it forms a new array of empty names and scores of 0. It then creates a file
// called leaderBoard.txt, which it writes all new scores to. Each time a score
// is entered, it is written to the file. Then, when the game is quit and played
// the leaderboard will read the scores again and update the leaderboard
// immediately
@SuppressWarnings("serial")
public class LeaderBoard extends JPanel {

	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 400;
	private int INTERVAL = 35;
	private int[] topEight = { 0, 0, 0, 0, 0, 0, 0, 0 };
	private String[] topEightName = { "", "", "", "", "", "", "", "" };
	private MouseHandler mouseHandler;
	private Point mousePos = new Point(0, 0);
	private FileReader file;

	// Reader and writer for file
	private FileWriter writer;
	private BufferedReader reader;

	public LeaderBoard() {
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		// Immediately checks if the file exists and reads it in to the array
		// if it does. If not, it creates the file so it can be written to.
		readLeaderBoard();
	}

	void tick() {
		repaint();
	}

	public void reset() {
		mousePos = new Point(0, 0);
	}

	// Submits a new score to the array with the player's initials
	// This method goes through each score and once it finds one that it is
	// greater than, it replaces it and updates the topEight and topEightNames.
	// If it is not in the top eight, the arrays will stay the same. 
	//
	// These scores are saved to the file each time they are submitted.
	public void submitScore(String name, int score) {
		String[] newNames = new String[10];
		int[] newScores = new int[10];
		int count = 0;

		for (int i = 0; i < 8; i++) {
			if (score <= topEight[i]) {
				newNames[i] = topEightName[i];
				newScores[i] = topEight[i];
				count = i;
			} else if (score > topEight[i]) {
				newScores[i] = score;
				newNames[i] = name.toUpperCase();
				count = i;
				break;
			}
		}
		if (count < 7) {
			for (int i = count; i < 7; i++) {
				newNames[i + 1] = topEightName[i];
				newScores[i + 1] = topEight[i];
			}
		}

		this.topEight = newScores;
		this.topEightName = newNames;

		saveLeaderBoard();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(readImagesToMap.images.get("LeaderBoardBG"), 0, 0, 600,
				400, null);

		int x = mousePos.x;
		int y = mousePos.y;

		if (x >= 5 && x <= 125 && y >= 25 && y <= 55) {
			g.drawImage(readImagesToMap.images.get("BUTTON_menuSelected"), 5,
					25, 120, 30, null);
		} else {
			g.drawImage(readImagesToMap.images.get("BUTTON_menuRegular"), 5,
					25, 120, 30, null);
		}

		g.setFont(new Font("Arial", Font.PLAIN, 24));

		for (int i = 0; i < 8; i++) {
			if (topEight[i] != 0) {
				g.drawString(topEightName[i], 183, 150 + i * 30);
				g.drawString(String.valueOf(topEight[i]), 372, 150 + i * 30);
			}
		}
	}

	// Listens to mouse movement and clicks for buttons
	private class MouseHandler implements MouseListener, MouseMotionListener {

		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX();
			int y = arg0.getY();
			if (x >= 5 && x <= 125 && y >= 25 && y <= 55) {
				Game.cl.show(Game.fullGameLayout, "1"); // main menu
				Game.menuClass.reset();
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
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
	
	// Saves the leaderboard to the file
	private void saveLeaderBoard() {
		try {
			writer = new FileWriter("leaderBoard.txt");
			PrintWriter pw = new PrintWriter(writer);

			for (int i = 0; i < 8; i++) {
				pw.println(topEightName[i]);
			}
			for (int i = 0; i < 8; i++) {
				pw.println(topEight[i]);
			}

			pw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Reads the leaderboard from the file to the arrays. If the file doesn't
	// exist, it creates a new file
	private void readLeaderBoard() {
		createFile();
		reader = new BufferedReader(file);

		for (int i = 0; i < 8; i++) {
			try {
				topEightName[i] = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 8; i++) {
			try {
				topEight[i] = Integer.parseInt(reader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Creates the file for reading, if it doesn't exist it creates it
	private void createFile() {
		try {
			file = new FileReader("leaderBoard.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			File newFile = new File("leaderBoard.txt");
			try {
				newFile.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				file = new FileReader(newFile);
				saveLeaderBoard();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
