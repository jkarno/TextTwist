import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The game over screen. If the score was 0, it simple asks if you want to try
 * again. Otherwise, it will ask you to enter your initials and you can then
 * either click submit or enter to save your score to the leaderboard.
 **/
@SuppressWarnings("serial")
public class GameOver extends JPanel {

	public static final int COURT_WIDTH = 600;
	public static final int COURT_HEIGHT = 400;
	private int INTERVAL = 35;
	private int score;
	String initials = "";
	private Point mousePos = new Point(0, 0);
	private MouseHandler mouseHandler;

	// Creates a new panel that has a key listener for typing initials
	public GameOver() {
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);

		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		setFocusable(true);

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (!initials.isEmpty()) {
						initials = initials.substring(0, initials.length() - 1);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (initials.length() > 0) {
						Game.cl.show(Game.fullGameLayout, "3");
						Game.leaderBoardClass.reset();
						Game.leaderBoardClass.submitScore(initials, score);
					}
				} else if ((e.getKeyCode() > 64 && e.getKeyCode() < 91)
						|| (e.getKeyCode() > 96 && e.getKeyCode() < 123)) {
					char entered = e.getKeyChar();
					if (initials.length() < 3) {
						initials = initials.concat(String.valueOf(entered));
					}
				}
			}

			public void keyReleased(KeyEvent e) {

			}
		});

		reset();
	}

	void tick() {
		repaint();
	}

	// Resets intials and makes the key listener focusable again
	public void reset() {
		mousePos = new Point(0, 0);
		setFocusable(true);
		requestFocusInWindow();
		initials = "";
	}

	// Paints the background image, the initials, and the submit button
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x = mousePos.x;
		int y = mousePos.y;

		if (score != 0) {
			Game.GameOver.setBackground(Color.decode("#274ab2"));
			g.drawImage(readImagesToMap.images.get("GameOverScreen"), 0, 0,
					600, 400, null);

			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString(String.valueOf(score), 370, 345);

			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.drawString(initials.toUpperCase(), 270, 222);

			if (x >= 243 && x <= 358 && y >= 249 && y <= 270) {
				g.drawImage(readImagesToMap.images.get("GameOverSubmitSel"),
						243, 249, 115, 30, null);
			} else {
				g.drawImage(readImagesToMap.images.get("GameOverSubmitReg"),
						243, 249, 115, 30, null);
			}
		} else {
			Game.GameOver.setBackground(Color.decode("#513e92"));
			g.drawImage(readImagesToMap.images.get("GameOverScore0"), 0, 0,
					600, 400, null);

			if (x >= 200 && x <= 400 && y >= 295 && y <= 345) {
				g.drawImage(readImagesToMap.images.get("tryAgainSel"), 200,
						295, 200, 50, null);
			} else {
				g.drawImage(readImagesToMap.images.get("tryAgain"), 200, 295,
						200, 50, null);
			}
		}

	}

	public void setScore(int score) {
		this.score = score;
	}

	// Events for mouse clicking, mouse moving, and mouse releasing
	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

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

			if (score == 0) {
				if (x >= 200 && x <= 400 && y >= 295 && y <= 345) {
					Game.cl.show(Game.fullGameLayout, "2");
					Game.court.reset();
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
			int x = arg0.getX();
			int y = arg0.getY();

			if (score != 0) {
				if (x >= 243 && x <= 358 && y >= 249 && y <= 270) {
					if (initials.length() > 0) {
						Game.cl.show(Game.fullGameLayout, "3");
						Game.leaderBoardClass.reset();
					}
				}
			} else {
				if (x >= 200 && x <= 400 && y >= 295 && y <= 345) {
					Game.cl.show(Game.fullGameLayout, "2");
					Game.court.reset();
				}
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
