package engel865650.a01;

import cgtools.Vec3;
import engel865650.Image;

public class ColoredSquare {

	Vec3 background;
	Vec3 square;

	private int squareWidth = 0;
	private int squareHeight = 0;

	private int centerX = 0;
	private int centerY = 0;

	Image image = null;

	public ColoredSquare(Vec3 background, Vec3 square) {
		this.background = background;
		this.square = square;

		// Image height halved
		// Mein Square ist die Hälfte so hoch, wie das Image
		squareHeight = Main.image.getHeight() / 2;
		// Image splited into thirds
		// Mein Square ist ein Drittel so breit, wie das Image
		squareWidth = Main.image.getWidth() / 3;

		// set square in center
		centerX = Main.image.getWidth() / 2;
		centerY = Main.image.getHeight() / 2;
	}

	Vec3 pixelColor(double x, double y) {
		if ((x >= centerX - squareWidth / 2 && x <= centerX + squareWidth / 2)
				&& (y >= centerY - squareHeight / 2 && y <= centerY + squareHeight / 2)) {
			return square;
		}
		return background;

	}

}
