package engel865650.a05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cgtools.Random;
import cgtools.Vec3;
import engel865650.Image;
import engel865650.a03.CObscura;
import engel865650.a05.Hit;
import engel865650.a03.Ray;

public class Main {

	private static final int width = 1920;
	private static final int height = 1080;
	public static Image image = null;
	public static Globe globe = null;
	private static CObscura obscura = null;
	private static Vec3 color, background, shade = null;
	static Material material;

	public static void main(String[] args) {
		a05_diffuse_spheres();
	}

	public static void a05_diffuse_spheres() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight());
		Plane plane = new Plane(new Vec3(0.0, -1.0, 0.0), new Vec3(0.0, 1.8, 0.0), new DiffuseMaterial(Vec3.gray));
//		Plane plane = new Plane(new Vec3(0, -4, 0), new Vec3(0, 1, 0), new DiffuseMaterial(new Vec3(0.85, 0.85, 0.85)));
		Background background = new Background(new BackgroundMaterial());

		// Globe (x, y, z) -> z determines the distance to the camera
		// Ball in middle, and feet
		Shape globe2 = new Globe(new Vec3(0.0, 0.0, -2.5), 0.9,
				new DiffuseMaterial(new Vec3(Random.random() + 1, Random.random() + 1, Random.random())));
		Shape globe1 = new Globe(new Vec3(-1.2, -0.4, -2.5), 0.5,
				new DiffuseMaterial(new Vec3(Random.random(), Random.random() + 1.5, Random.random())));
		Shape globe3 = new Globe(new Vec3(1.0, 0.3, -1.9), 0.4,
				new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random() + 1)));
		Shape globe4 = new Globe(new Vec3(1.1, 0.3, -1.4), 0.1,
				new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random())));
		Shape globe5 = new Globe(new Vec3(1.5, -0.2, -2.1), 0.3,
				new DiffuseMaterial(new Vec3(Random.random() + 1, Random.random(), Random.random())));
		Shape globe6 = new Globe(new Vec3(-1.5, 0.0, -2.0), 0.2,
				new DiffuseMaterial(new Vec3(Random.random(), Random.random() + 2, Random.random() + 1)));
		Shape globe7 = new Globe(new Vec3(-6.0, 0.8, -6.5), 0.3,
				new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random())));
		Shape globe8 = new Globe(new Vec3(-0.4, 0.6, -1.5), 0.22,
				new DiffuseMaterial(new Vec3(Random.random() + 1, Random.random(), Random.random())));
		Shape globe9 = new Globe(new Vec3(2.4, -0.5, -5.1), 0.3,
				new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random() + 1)));
		Shape globe10 = new Globe(new Vec3(0.4, -0.8, -1.9), 0.2,
				new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random() + 1)));
		// End
		List<Shape> shapes = new ArrayList<Shape>();
//		int nSpheres = 10;
//		for (int i = 0; i < nSpheres; i++) {
//			for (int j = 0; j < nSpheres; j++) {
//				if (i == 0) {
//					Shape sphere = new Globe(new Vec3(i, -3.5, -10 - j * 1.5), 0.5,
//							new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random())));
//					shapes.add(sphere);
//					continue;
//				}
//				Shape sphere1 = new Globe(new Vec3(i * 1.3, -3.5, (-10 - i) - j * 1.5), 0.5,
//						new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random())));
//				Shape sphere2 = new Globe(new Vec3(-i * 1.3, -3.5, (-10 - i) - j * 1.5), 0.5,
//						new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random())));
//				shapes.add(sphere1);
//				shapes.add(sphere2);
//			}
//		}

		shapes.add(plane);
		shapes.add(background);
		shapes.add(globe1);
		shapes.add(globe2);
		shapes.add(globe3);
		shapes.add(globe4);
		shapes.add(globe5);
		shapes.add(globe6);
		shapes.add(globe7);
		shapes.add(globe8);
		shapes.add(globe9);
		shapes.add(globe10);
		Group group = new Group(shapes);
		raytrace(obscura, group, 10);
	}

	public static Image raytrace(CObscura obscura, Group group, double sampler) {
		for (int x = 0; x != width; x++) {
			for (int y = 0; y != height; y++) {
				color = new Vec3(0, 0, 0);
				for (int xi = 0; xi < sampler; xi++) {
					for (int yi = 0; yi < sampler; yi++) {
						double rx = Random.random();
						double ry = Random.random();
						double xs = x + (xi + rx) / sampler;
						double ys = y + (yi + ry) / sampler;
						Ray currentRay = obscura.generate(xs, ys);
						shade = calculateRadiance(group, currentRay, 4.0);
						background = Vec3.divide(shade, sampler * sampler);
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a05-diffuse-spheres.png");
		return image;
	}

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