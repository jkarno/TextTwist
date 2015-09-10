import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// The main menu offers the choice to view the leaderboard, play the game,
// and look at the instructions
@SuppressWarnings("serial")
public class menuMain extends JPanel {

	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 400;
	private BufferedImage menuMain;
	private BufferedImage menuMainPlaySelected;
	private BufferedImage menuMainLeaderBoardSelected;
	private String drawMode = "None";
	private MouseHandler mouseHandler;
	private Point mousePos = new Point(0, 0);
	private int INTERVAL = 35;

	public menuMain() {

		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		try {
			menuMain = ImageIO.read(new File("menuMain.png"));
			menuMainPlaySelected = ImageIO.read(new File(
					"menuMain_playSelected.png"));
			menuMainLeaderBoardSelected = ImageIO.read(new File(
					"menuMain_leaderBoardSelected.png"));
		} catch (IOException e) {
		}

		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
	}

	public void updateDraw(String mode) {
		this.drawMode = mode;
	}

	public void reset() {
		mousePos = new Point(0, 0);
	}

	void tick() {
		int x = (int) mousePos.getX();
		int y = (int) mousePos.getY();
		if (x >= 120 && x <= 480 && y >= 144 && y <= 206) {
			updateDraw("Play");
		} else if (x >= 120 && x <= 480 && y >= 244 && y <= 306) {
			updateDraw("LeaderBoard");
		} else {
			updateDraw("None");
		}
		// update the display
		repaint();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (drawMode.equals("Play")) {
			g.drawImage(menuMainPlaySelected, 0, 0, 600, 400, null);
		} else if (drawMode.equals("LeaderBoard")) {
			g.drawImage(menuMainLeaderBoardSelected, 0, 0, 600, 400, null);
		} else {
			g.drawImage(menuMain, 0, 0, 600, 400, null);
		}

		int x = mousePos.x;
		int y = mousePos.y;

		if (x >= 550 && x <= 590 && y >= 3 && y <= 43) {
			g.drawImage(readImagesToMap.images.get("Button_InsSel"), 550, 3,
					40, 40, null);
		} else {
			g.drawImage(readImagesToMap.images.get("Button_InsReg"), 550, 3,
					40, 40, null);
		}
	}

	// Only uses mouseclicked and mousemoved
	private class MouseHandler implements MouseListener, MouseMotionListener {

		public void mouseClicked(MouseEvent arg0) {
			int x = arg0.getX();
			int y = arg0.getY();
			if (x >= 120 && x <= 480 && y >= 144 && y <= 206) {
				Game.cl.show(Game.fullGameLayout, "2");
				Game.court.reset();
			} else if (x >= 120 && x <= 480 && y >= 244 && y <= 306) {
				Game.cl.show(Game.fullGameLayout, "3");
				Game.leaderBoardClass.reset();
			} else if (x >= 550 && x <= 590 && y >= 3 && y <= 43) {
				Game.cl.show(Game.fullGameLayout, "5");
				Game.instructions.reset();
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			mousePos = new Point(arg0.getPoint());
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

}
