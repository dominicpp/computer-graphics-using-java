package engel865650.a10;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import cgtools.Mat4;
import cgtools.Random;
import cgtools.Vec3;
import engel865650.Image;
import engel865650.a10.CObscura;
import engel865650.a10.Hit;
import engel865650.a03.Ray;

public class Main {

	private static final int width = 1280;
	private static final int height = 720;
	public static Image image = null;
	public static Globe globe = null;
	private static CObscura obscura = null;
	// private Vec3 color, background, shade = null;
	static Material material;

	public static void main(String[] args) {
		System.out.println("Please wait...\n");
		Instant start = Instant.now();
		parallelization();
		System.out.println("\n" + "Image is done!");
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).getSeconds();
		System.out.println("Rendering took " + timeElapsed + " seconds in total!");
	}

	public static void parallelization() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight(), new Vec3(-5, 180, 0),
				Mat4.translate(-5, 25, -35));
		/*
		 * all Scenes together
		 */
		List<Shape> allScenes = new ArrayList<Shape>();

		// everything together
		Plane plane = new Plane(new Vec3(0.0, 7.0, 0.0), new Vec3(0.0, -1.0, 0.0),
				new DiffuseMaterial(new CheckerBoardTexture(1)));
		Background background = new Background(new BackgroundMaterial(new Texture("doc/texture_img/usa.png")));
		Shape ball1 = new Globe(new Vec3(5, 20, -10), 6,
				new BackgroundMaterial(new Texture("doc/texture_img/newyork.png")));
		Shape ball2 = new Globe(new Vec3(-5, 28, -2), 8,
				new BackgroundMaterial(new Texture("doc/texture_img/seattle.png")));
		Shape ball3 = new Globe(new Vec3(-10, 20, 9), 9, new PolishedMetalMaterial(Vec3.gray, 0.00001));
		allScenes.add(plane);
		allScenes.add(background);
		allScenes.add(ball1);
		allScenes.add(ball2);
		allScenes.add(ball3);
		Group allGroupes = new Group(allScenes, new Transform(Mat4.rotate(0, 1, 0, 105)));
		//
//		acceleration(obscura, allGroupes);
		raytrace(obscura, allGroupes, 10);

	}

	public static Image raytrace(CObscura obscura, Group group, double sampler) {
		Image image = new Image(width, height);
		//
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				Vec3 color = new Vec3(0, 0, 0);
				for (int xi = 0; xi < sampler; xi++) {
					for (int yi = 0; yi < sampler; yi++) {
						double rx = Random.random();
						double ry = Random.random();
						double xs = x + (xi + rx) / sampler;
						double ys = y + (yi + ry) / sampler;
						Ray currentRay = obscura.generate(xs, ys);
						Vec3 shade = calculateRadiance(group, currentRay, 3.8);
						Vec3 background = Vec3.divide(shade, sampler * sampler);
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a10-2.png");
		return image;

	}

//	public static Image acceleration(CObscura obscura, Group group) {
//		int processors = Runtime.getRuntime().availableProcessors();
//		System.out.println(processors + " Processors Available!\n\nPlease wait...\n");
//		Image image = new Image(width, height);
//		//
//		int n = 10;
//		int cores = 8;
//		int sampler = n / cores;
//		Thread[] threads = new Thread[cores];
//		Image[] images = new Image[cores];
//		for (int i = 0; i != cores; i++) {
//			final int core = i;
//			threads[i] = new Thread() {
//				public void run() {
//					images[core] = raytrace(obscura, group, sampler);
//				}
//			};
//			threads[i].start();
//		}
//		for (int i = 0; i != cores; i++) {
//			try {
//				threads[i].join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		for (int x = 0; x < width; x++) {
//			for (int y = 0; y < height; y++) {
//				Vec3 color = Vec3.zero;
//				for (int i = 0; i < images.length; i++) {
//					color = Vec3.add(color, images[i].getPixel(x, y));
//				}
//				image.setPixel(x, y, Vec3.divide(color, images.length));
//			}
//		}
//		return image;
//	}

	public static Vec3 calculateRadiance(Shape scene, Ray ray, double depth) {
		if (depth == 0) {
			return Vec3.black;
		}
		Hit hit = scene.intersect(ray);
		if (hit.getMaterial().scatteredRay(ray, hit) != null) {
			return Vec3.add(hit.getMaterial().emission(ray, hit), Vec3.multiply(hit.getMaterial().albedo(ray, hit),
					calculateRadiance(scene, hit.getMaterial().scatteredRay(ray, hit), depth - 1)));
		}
		return hit.getMaterial().emission(ray, hit);
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