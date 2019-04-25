package engel865650.a03;

import java.io.IOException;

import cgtools.Vec3;
import engel865650.Image;

public class Main {

	private static final int width = 900;
	private static final int height = 600;
	private static double sampler = 10;
	protected static Image image = null;
	private static CObscura obscura = null;
	private static Ball ball = null;
	private static Vec3 color, background, shade = null;

	public static void main(String[] args) {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 2, image.getWidth(), image.getHeight());
		ball = new Ball(new Vec3(0, 0, -50), 28, new Vec3(1.5, 0.2, 0.1));

		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				color = new Vec3(0, 0, 0);
				for (int xi = 0; xi < sampler; xi++) {
					for (int yi = 0; yi < sampler; yi++) {
						double rx = Math.random();
						double ry = Math.random();
						double xs = x + (xi + rx) / sampler;
						double ys = y + (yi + ry) / sampler;
						Ray currentRay = obscura.generate(xs, ys);
						Hit hit = ball.intersect(currentRay);
						if (hit == null) {
							background = Vec3.divide(ball.pixelColor(xs, ys), sampler * sampler);
						} else {
							shade = Ball.lightSurface(hit.getPositionHit(), hit.getNormalVector(), hit.getColor());
							background = Vec3.divide(shade, sampler * sampler);
						}
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a03-one-sphere.png");

		// camera testing
//		CObscura cam = new CObscura(Math.PI / 2, 10, 10);
//		cam.direction(0, 0);

		// Intersection testing
//		Ball2 ball = new Ball2(new Vec3(0, 0, -2), 1, Vec3.black);
//		Ray r = new Ray(new Vec3(0, 0, -1), new Vec3(0, 0, -1), 0, Double.MAX_VALUE);
//		Hit hit = ball.intersect(r);
//		System.out.println("Trefferpunkt x___: " + hit.getPositionHit() + "\n" + "Normale n________: " + hit.getNormalVector());
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