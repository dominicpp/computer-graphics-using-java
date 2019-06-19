
package engel865650;

import cgtools.ImageWriter;
import cgtools.Vec3;

import java.io.IOException;

public class Image {

	private int width, height = 0;
	private double[] pixels = null;
	private double gamma = 2.2;

	public Image() {

	}

	public Image(int width, int height) {
		this.width = width;
		this.height = height;
		// width * height * all 3 components (RGB) of a pixel
		pixels = new double[width * height * 3];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPixel(int x, int y, Vec3 color) {
		/*
		 * ..to address every component of a Pixel ..and with better gamma value
		 */
		pixels[3 * (x + (y * width)) + 0] = Math.pow(color.x, 1 / gamma); // x is R(ed)
		pixels[3 * (x + (y * width)) + 1] = Math.pow(color.y, 1 / gamma); // y is G(reen)
		pixels[3 * (x + (y * width)) + 2] = Math.pow(color.z, 1 / gamma); // z is B(lue)
	}

	public Vec3 getPixel(int x, int y) {
		return new Vec3(pixels[3 * (x + (y * width)) + 0], pixels[3 * (x + (y * width)) + 1],
				pixels[3 * (x + (y * width)) + 2]);
	}

	public void write(String filename) throws IOException {
		// call image
		ImageWriter.write(filename, pixels, width, height);
	}

}
