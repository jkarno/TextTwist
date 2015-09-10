import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

// The instructions page simply is a panel that displays images that explain
// How the game works and how to play. This is accessed by the question mark
// on the main menu. You can click through the instructions and then return
// to the main menu.
@SuppressWarnings("serial")
public class instructionsPage extends JPanel {

	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 400;
	private int INTERVAL = 35;
	private Point mousePos = new Point(0, 0);
	private MouseHandler mouseHandler;
	private int pageCount = 1;

	public instructionsPage() {
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);

		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		reset();
	}

	void tick() {
		repaint();
	}

	public void reset() {
		mousePos = new Point(0, 0);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x = mousePos.x;
		int y = mousePos.y;

		if (pageCount == 1) {
			g.drawImage(readImagesToMap.images.get("instructionsGameImp"), 0,
					0, 600, 400, null);
		} else if (pageCount == 2) {
			g.drawImage(readImagesToMap.images.get("instructionsHowToPlay"), 0,
					0, 600, 400, null);
		} else if (pageCount == 3) {
			g.drawImage(
					readImagesToMap.images.get("instructions_BonusFeatures"),
					0, 0, 600, 400, null);
		} else if (pageCount == 4) {
			g.drawImage(
					readImagesToMap.images.get("instructions_BonusFeatures2"),
					0, 0, 600, 400, null);
		}

		if (x >= 5 && x <= 148 && y >= -2 && y <= 18) {
			g.drawImage(readImagesToMap.images.get("BUTTON_menuSelected"), 5,
					-5, 150, 30, null);
		} else {
			g.drawImage(readImagesToMap.images.get("BUTTON_menuRegular"), 5,
					-5, 150, 30, null);
		}

		if (pageCount < 4) {
			if (x >= 490 && x <= 590 && y >= 1 && y <= 21) {
				g.drawImage(readImagesToMap.images.get("Button_NextPageSel"),
						490, 1, 100, 20, null);
			} else {
				g.drawImage(readImagesToMap.images.get("Button_NextPage"), 490,
						1, 100, 20, null);
			}
		}

	}
	
	// Only the mouse move and click events are used.
	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		// Mouse move updates the mouse position so that the repaint knows
		// Whether to draw the selected state of buttons
		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			mousePos = arg0.getPoint();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int x = arg0.getX();
			int y = arg0.getY();
			if (x >= 5 && x <= 148 && y >= -2 && y <= 18) {
				Game.cl.show(Game.fullGameLayout, "1"); // main menu
				Game.menuClass.reset();
				pageCount = 1;
			} else if (x >= 490 && x <= 590 && y >= 1 && y <= 21) {
				if (pageCount < 4) {
					pageCount++;
				}
			}

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
