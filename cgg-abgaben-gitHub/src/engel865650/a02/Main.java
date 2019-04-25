
package engel865650.a02;

import java.io.IOException;
import engel865650.Image;

public class Main {

	private static final int width = 1200;
	private static final int height = 800;
	public static Image image = null;
	public static Image sampling = null;

	public static void main(String[] args) {
		image = new Image(width, height);
		sampling = new Image(width, height);

		ColoredDiamond diamonds = new ColoredDiamond(500);
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				image.setPixel(x, y, diamonds.pixelColor(x, y));
				sampling.setPixel(x, y, diamonds.stratified_Sampling(x, y));
			}
		}
		write(image, "doc/a02-discs.png");
		write(sampling, "doc/a02-supersampling.png");
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