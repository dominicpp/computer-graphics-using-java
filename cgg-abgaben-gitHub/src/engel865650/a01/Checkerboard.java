package engel865650.a01;

import cgtools.Vec3;
import engel865650.Image;

public class Checkerboard {

	Vec3 background;
	Vec3 square;
	Vec3 pattern;

	private int squareWidth = 0;
	private int squareHeight = 0;

	private int centerX = 0;
	private int centerY = 0;

	private int patternWidth = 0;
	private int patternHeight = 0;

	Image image = null;

	public Checkerboard(Vec3 background, Vec3 square, Vec3 pattern) {
		this.background = background;
		this.square = square;
		this.pattern = pattern;

		// Image height halved
		squareHeight = Main.image.getHeight() / 2;
		// Image splited into thirds
		squareWidth = Main.image.getWidth() / 3;

		// set square in center
		centerX = Main.image.getWidth() / 2;
		centerY = Main.image.getHeight() / 2;

		// 6 little squares in height
		// Mein Schachmuster ist ein Drittel so breit, wie das Image
		patternHeight = Main.image.getHeight() / 3;
		// 10 little squares in width
		// Mein Schachmuster ist ein Fünftel so breit, wie das Image
		patternWidth = Main.image.getWidth() / 5;
	}

	Vec3 pixelColor(double x, double y) {
		if ((x >= centerX - squareWidth / 2 && x <= centerX + squareWidth / 2)
				&& (y >= centerY - squareHeight / 2 && y <= centerY + squareHeight / 2)) {
			return square;
		}

		/*
		 * First try! 102 pixels (first width) is gray (background) -> rest is black
		 * (pattern)
		 */
//		if ((x >= patternWidth / 2)) {
//			return pattern;
//		}

		/*
		 * Second try! First try! 102 pixels (first height) is gray (background) -> rest
		 * is black (pattern)
		 */
//		if ((y >= patternWidth / 2)) {
//			return pattern;
//		}

		/*
		 * Third try! First width and first height are gray (background) completely ->
		 * rest is black (pattern)
		 */
//		if ((y >= patternWidth / 2) && (x >= patternWidth / 2)) {
//			return pattern;
//		}

		/*
		 * Fifth try! It is working! Got my first square with a pattern like I wanted it
		 * to be. :D -> but it only applies for one square (upper left corner), not for
		 * the rest.
		 */
//		if ((x < patternWidth / 2 && y < patternHeight / 2) || (x >= patternWidth / 2 && y >= patternHeight / 2)) {
//			return pattern;
//		}

		/*
		 * Sixth try! With Modulo! It is working! I know have black multiple stripes!
		 */
//		if ((x % patternWidth < patternWidth / 2)) {
//			return pattern;
//		}

		/*
		 * Seventh try! Working! I know have a reasonable pattern.
		 */
//		if ((x % patternWidth < patternWidth / 2 && y % patternHeight < patternHeight / 2)) {
//			return pattern;
//		}

		/*
		 * Eighth try! Perfetto!! Got my pattern.
		 */
		/*
		 * ACHTUNG! Wenn man anstelle x und y Zahlen einsetzt, gibt es keine
		 * Unterscheidung mehr und alles wird true!!
		 */
		if ((x % patternWidth < patternWidth / 2 && y % patternHeight < patternHeight / 2)
				|| (x % patternWidth >= patternWidth / 2 && y % patternHeight >= patternHeight / 2)) {
			return pattern;
		}

		return background;
	}

}
