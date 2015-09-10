import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */

public class Game implements Runnable {

	public static CardLayout cl = new CardLayout();
	public static JPanel fullGameLayout = new JPanel(cl);

	// initialize images
	public readImagesToMap images = new readImagesToMap();
	public static TextTwist court;
	public static menuMain menuClass = new menuMain();
	public static GameOver gameOverClass = new GameOver();
	public static instructionsPage instructions = new instructionsPage();
	public static LeaderBoard leaderBoardClass = new LeaderBoard();
	public static JPanel gameOver = gameOverClass;
	public static JPanel GameOver = new JPanel();

	public void run() {

		// initialize game font
		// This font can be used by enabling this and setting font elsewhere
		
//		GraphicsEnvironment ge = GraphicsEnvironment
//				.getLocalGraphicsEnvironment();
//		try {
//			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(
//					"Krungthep.ttf")));
//		} catch (FontFormatException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		// Main Frame
		final JFrame frame = new JFrame("TextTwist");
		frame.setLocation(400, 200);
		frame.setResizable(false);

		// The panels to switch between for different game states
		JPanel GameCont = new JPanel(new BorderLayout());
		JPanel MainMenu = new JPanel();
		JPanel LeaderBoard = new JPanel();
		JPanel InstructionsPage = new JPanel();

		// Main Menu
		JPanel menu = menuClass;
		MainMenu.add(menu, BorderLayout.CENTER);
		MainMenu.setBackground(Color.decode("#3366ff"));

		// Text Twist
		court = new TextTwist();
		court.setBackground(Color.decode("#3366ff"));
		GameCont.add(court, BorderLayout.CENTER);

		// Game Over
		gameOverClass.setSize(600, 400);
		GameOver.add(gameOver, BorderLayout.SOUTH);
		GameOver.setBackground(Color.decode("#274ab2"));

		// LeaderBoard
		JPanel leaderBoard = leaderBoardClass;
		LeaderBoard.add(leaderBoard, BorderLayout.CENTER);
		LeaderBoard.setBackground(Color.decode("#274ab2"));

		// Instructions Page
		InstructionsPage.add(instructions, BorderLayout.CENTER);
		InstructionsPage.setBackground(Color.decode("#284db9"));

		// Add panels to main panel, assign them card numbers
		fullGameLayout.add(MainMenu, "1");
		fullGameLayout.add(GameCont, "2");
		fullGameLayout.add(LeaderBoard, "3");
		fullGameLayout.add(GameOver, "4");
		fullGameLayout.add(InstructionsPage, "5");
		frame.add(fullGameLayout);

		cl.show(fullGameLayout, "1");

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it NOTE: Do NOT delete! You MUST include this
	 * in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
