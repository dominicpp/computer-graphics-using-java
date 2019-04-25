package engel865650.a01;

import java.io.IOException;

import cgtools.Vec3;
import engel865650.Image;

public class Main {

	private static final int width = 1024;
	private static final int height = 600;
	public static Image image = null;

	public static void main(String[] args) {

		image = new Image(width, height);

		class ConstantColor {
			Vec3 color;

			ConstantColor(Vec3 color) {
				this.color = color;
			}

			Vec3 pixelColor(double x, double y) {
				return color;
			}
		}

		ConstantColor allGray = new ConstantColor(Vec3.gray);
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				image.setPixel(x, y, allGray.pixelColor(x, y));
			}
		}
		write(image, "doc/a01.png");

		// CLASS COLOREDSQUARE
		ColoredSquare centerSquare = new ColoredSquare(Vec3.gray, Vec3.red);
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				image.setPixel(x, y, centerSquare.pixelColor(x, y));
			}
		}
		write(image, "doc/a01-square.png");

		// CLASS Checkerboard
		Checkerboard pattern = new Checkerboard(Vec3.gray, Vec3.red, Vec3.black);
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				image.setPixel(x, y, pattern.pixelColor(x, y));
			}
		}
		write(image, "doc/a01-checkered-background.png");
	}

	static void write(Image image, String filename) {
		try {
			image.write(filename);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
	}

}
