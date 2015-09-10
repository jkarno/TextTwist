import java.awt.*;

/**
 * A piece that displays a letter by taking a character and printing an image
 * corresponding to that letter
 */
public class letterPiece extends GameObj {
	public static final int SIZE = 40;
	public static final int INIT_X = 150;
	public static final int INIT_Y = 250;
	public static String LETTER = "";

	public letterPiece(int x_pos, int y_pos, int courtWidth, int courtHeight,
			char letter) {
		super(x_pos, y_pos, SIZE, SIZE, courtWidth, courtHeight);
		LETTER = String.valueOf(letter);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(readImagesToMap.images.get(LETTER), pos_x, pos_y, 40, 40,
				null);
	}
}
