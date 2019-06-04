package engel865650.a07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cgtools.Mat4;
import cgtools.Random;
import cgtools.Vec3;
import engel865650.Image;
import engel865650.a07.CObscura;
import engel865650.a07.Hit;
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

		// a06_mirrors_glass_1();
		a06_mirrors_glass_2();
	}

	public static void a06_mirrors_glass_1() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight(), new Vec3(-46.0, 215, 0),
				Mat4.translate(-5, 16, -8));
		//
		Plane plane = new Plane(new Vec3(0.0, -1.0, 0.0), new Vec3(0.0, 1.8, 0.0),
				new PolishedMetalMaterial(new Vec3(0.5, 0.2, 0.30), 0.01));
		//
		Background background = new Background(new BackgroundMaterial());
		//
		Shape globe1 = new Globe(new Vec3(0, 10.3, -6.0), 0.5, new PolishedMetalMaterial(Vec3.reflection, 0.5));
		Shape cylinder1 = new Cylinder(new Vec3(0, -1.0, -6.0), 0.6, 11, new DiffuseMaterial(Vec3.green));
		//
		Shape globe3 = new Globe(new Vec3(5, 10.3, 6.0), 0.5, new PolishedMetalMaterial(Vec3.reflection, 0.5));
		Shape cylinder3 = new Cylinder(new Vec3(5, -1.0, 6.0), 0.6, 11,
				new PolishedMetalMaterial(Vec3.reflection, 0.1));
		//
		Shape globe4 = new Globe(new Vec3(-5, 10.1, 3.0), 0.5, new DiffuseMaterial(new Vec3(1, 0, 0)));
		Shape cylinder4 = new Cylinder(new Vec3(-5, -1.0, 3.0), 0.6, 11, new DiffuseMaterial(Vec3.blue));
		//
		Shape globe5 = new Globe(new Vec3(10, 10.1, -3.0), 0.5,
				new DiffuseMaterial(new Vec3(Random.random() + 0.2, Random.random() + 0.5, Random.random() + 0.8)));
		Shape cylinder5 = new Cylinder(new Vec3(10, -1.0, -3.0), 0.6, 11, new DiffuseMaterial(new Vec3(1, 1, 0)));
		//
		List<Shape> shapes = new ArrayList<Shape>();
		//
		int globes = 9;
		for (double i = -4; i < globes; i++) {
			for (double j = -4; j < globes; j++) {
				Shape littleGlobes = new Globe(new Vec3(i, Random.random() * 9, j - 2.9), 0.5 * Random.random(),
						new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random())));
				shapes.add(littleGlobes);
			}
		}
		//
		Shape glass = new Globe(new Vec3(2, 3, -1), 2.0, new GlassMaterial(1.5));
		//
		shapes.add(plane);
		//
		shapes.add(background);
		//
		shapes.add(cylinder1);
		shapes.add(cylinder3);
		shapes.add(cylinder4);
		shapes.add(cylinder5);
		shapes.add(globe1);
		shapes.add(globe3);
		shapes.add(globe4);
		shapes.add(globe5);
		shapes.add(glass);
		//
		Group group = new Group(shapes);
		raytrace1(obscura, group, 10);
	}

	public static Image raytrace1(CObscura obscura, Group group, double sampler) {
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
						shade = calculateRadiance(group, currentRay, 3.8);
						background = Vec3.divide(shade, sampler * sampler);
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a07-1.png");
		return image;
	}

	public static void a06_mirrors_glass_2() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight(), new Vec3(25.0, -20, 0),
				Mat4.translate(-2.5, -0.8, 11));
		//
		Plane plane = new Plane(new Vec3(0.0, -1.0, 0.0), new Vec3(0.0, 1.8, 0.0),
				new PolishedMetalMaterial(new Vec3(0.5, 0.2, 0.30), 0.01));
		//
		Background background = new Background(new BackgroundMaterial());
		//
		Shape globe1 = new Globe(new Vec3(0, 10, -6.0), 1.1, new PolishedMetalMaterial(Vec3.reflection, 0.5));
		Shape cylinder1 = new Cylinder(new Vec3(0, -1.0, -6.0), 0.6, 11, new DiffuseMaterial(Vec3.green));
		//
		Shape globe3 = new Globe(new Vec3(5, 10, 6.0), 1.1, new PolishedMetalMaterial(Vec3.reflection, 0.5));
		Shape cylinder3 = new Cylinder(new Vec3(5, -1.0, 6.0), 0.6, 11,
				new PolishedMetalMaterial(Vec3.reflection, 0.1));
		//
		Shape globe4 = new Globe(new Vec3(-5, 10, 3.0), 1.1, new PolishedMetalMaterial(Vec3.reflection, 0.5));
		Shape cylinder4 = new Cylinder(new Vec3(-5, -1.0, 3.0), 0.6, 11, new DiffuseMaterial(Vec3.blue));
		//
		Shape globe5 = new Globe(new Vec3(10, 10, -3.0), 1.1, new PolishedMetalMaterial(Vec3.reflection, 0.5));
		Shape cylinder5 = new Cylinder(new Vec3(10, -1.0, -3.0), 0.6, 11, new DiffuseMaterial(new Vec3(1, 1, 0)));
		//
		List<Shape> shapes = new ArrayList<Shape>();
		//
		int globes = 9;
		for (double i = -4; i < globes; i++) {
			for (double j = -4; j < globes; j++) {
				Shape littleGlobes = new Globe(new Vec3(i, Random.random() * 9, j - 2.9), 0.5 * Random.random(),
						new DiffuseMaterial(new Vec3(Random.random(), Random.random(), Random.random())));
				shapes.add(littleGlobes);
			}
		}
		//
		Shape glass = new Globe(new Vec3(2, 3, -1), 2.0, new GlassMaterial(1.5));
		//
		shapes.add(plane);
		//
		shapes.add(background);
		//
		shapes.add(cylinder1);
		shapes.add(cylinder3);
		shapes.add(cylinder4);
		shapes.add(cylinder5);
		shapes.add(globe1);
		shapes.add(globe3);
		shapes.add(globe4);
		shapes.add(globe5);
		shapes.add(glass);
		//
		Group group = new Group(shapes);
		raytrace2(obscura, group, 10);
	}

	public static Image raytrace2(CObscura obscura, Group group, double sampler) {
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
						shade = calculateRadiance(group, currentRay, 3.8);
						background = Vec3.divide(shade, sampler * sampler);
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a07-2.png");
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