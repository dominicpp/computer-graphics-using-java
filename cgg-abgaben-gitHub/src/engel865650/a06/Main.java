package engel865650.a06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cgtools.Random;
import cgtools.Vec3;
import engel865650.Image;
import engel865650.a03.CObscura;
import engel865650.a06.Hit;
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
		a06_mirrors_glass_1();
		a06_mirrors_glass_2();
	}

	public static void a06_mirrors_glass_1() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight());
		//
		Plane plane = new Plane(new Vec3(0.0, -1.0, 0.0), new Vec3(0.0, 1.8, 0.0),
				new PolishedMetalMaterial(Vec3.gray, 0.05));
		//
		Background background = new Background(new BackgroundMaterial());
		//
		Shape glassGlobeCenter = new Globe(new Vec3(0.0, -0.4, -3.3), 0.5, new PolishedMetalMaterial());
		//
		Shape glass1 = new Globe(new Vec3(0.7, -0.4, -1.5), 0.2, new GlassMaterial(1.0));
		Shape glass2 = new Globe(new Vec3(0.0, 0.30, -1.4), 0.2, new GlassMaterial(1.0));
		Shape glass3 = new Globe(new Vec3(-0.7, -0.4, -1.5), 0.2, new GlassMaterial(1.0));
		//
		Shape blueGlobe = new Globe(new Vec3(1.3, -0.6, -3.3), 0.5, new DiffuseMaterial(Vec3.blue));
		Shape greenGlobe = new Globe(new Vec3(-1.3, -0.6, -3.3), 0.5, new DiffuseMaterial(Vec3.green));
		Shape redGlobe = new Globe(new Vec3(0.0, 0.5, -1.5), 0.25, new DiffuseMaterial(Vec3.red));
		//
		Shape yellowGlobe = new Globe(new Vec3(0.0, 0.0, 0.4), 1.1, new DiffuseMaterial(new Vec3(1, 1, 0)));
		Shape orangeGlobe = new Globe(new Vec3(0.0, 5.0, 8.5), 3.0, new DiffuseMaterial(new Vec3(1.9, 0.75, 0.2)));
		//
		Shape colorReflectedGreen = new Globe(new Vec3(-1.65, -0.8, -2.0), 0.2,
				new PolishedMetalMaterial(new Vec3(0, 2, 0.5), 0.0));
		Shape colorReflectedPink = new Globe(new Vec3(1.65, -0.8, -2.0), 0.2,
				new PolishedMetalMaterial(new Vec3(2, 0, 1), 0.0));
		//
		Shape grayGlobeLeft = new Globe(new Vec3(-1.3, 0.7, -2.3), 0.3, new PolishedMetalMaterial(Vec3.gray, 0.0));
		Shape grayGlobeRight = new Globe(new Vec3(1.3, 0.7, -2.3), 0.3, new PolishedMetalMaterial(Vec3.gray, 0.0));
		//
		Shape polishedGlobeLeft = new Globe(new Vec3(-0.8, 0.2, -2.5), 0.2, new PolishedMetalMaterial(Vec3.gray, 2.5));
		Shape polishedGlobeRight = new Globe(new Vec3(0.8, 0.2, -2.5), 0.2,
				new PolishedMetalMaterial(Vec3.reflection, 1.5));
		//
		Shape glassGlobeLeft = new Globe(new Vec3(-4.1, -0.6, -5.2), 0.7, new GlassMaterial(2.3));
		Shape glassGlobeRight = new Globe(new Vec3(4.1, -0.6, -5.2), 0.7, new GlassMaterial(1.8));
		//
		Shape glassGlobeCenterLeft = new Globe(new Vec3(-0.3, -0.8, -2.0), 0.25, new GlassMaterial(4.0));
		Shape glassGlobeCenterRight = new Globe(new Vec3(0.3, -0.8, -2.0), 0.25, new GlassMaterial(4.0));
		//
		List<Shape> shapes = new ArrayList<Shape>();
		//
		shapes.add(plane);
		//
		shapes.add(background);
		//
		shapes.add(glassGlobeCenter);
		//
		shapes.add(glass1);
		shapes.add(glass2);
		shapes.add(glass3);
		//
		shapes.add(blueGlobe);
		shapes.add(greenGlobe);
		shapes.add(redGlobe);
		//
		shapes.add(yellowGlobe);
		shapes.add(orangeGlobe);
		//
		shapes.add(colorReflectedGreen);
		shapes.add(colorReflectedPink);
		//
		shapes.add(grayGlobeLeft);
		shapes.add(grayGlobeRight);
		//
		shapes.add(polishedGlobeLeft);
		shapes.add(polishedGlobeRight);
		//
		shapes.add(glassGlobeLeft);
		shapes.add(glassGlobeRight);
		//
		shapes.add(glassGlobeCenterLeft);
		shapes.add(glassGlobeCenterRight);
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
						shade = calculateRadiance(group, currentRay, 3.5);
						background = Vec3.divide(shade, sampler * sampler);
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a06-mirrors-glass-1.png");
		return image;
	}

	public static void a06_mirrors_glass_2() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight());
		//
		Plane plane = new Plane(new Vec3(0.0, -1.0, 0.0), new Vec3(0.0, 1.8, 0.0),
				new PolishedMetalMaterial(Vec3.gray, 0.0));
		//
		Background background = new Background(new BackgroundMaterial());
		//
		Shape blueGlobe = new Globe(new Vec3(1.8, -0.3, -3.3), 0.5, new DiffuseMaterial(Vec3.blue));
		Shape greenGlobe = new Globe(new Vec3(-1.9, -0.8, -3.3), 0.5, new DiffuseMaterial(Vec3.green));
		//
		Shape glassGlobe = new Globe(new Vec3(-0.9, -0.1, -2.3), 0.4, new GlassMaterial(2.0));
		Shape glass = new Globe(new Vec3(0.3, 0.0, -2.5), 0.35, new GlassMaterial(1.0));
		//
		Shape yellowGlobe = new Globe(new Vec3(0.0, 0.0, 0.4), 1.1, new DiffuseMaterial(new Vec3(1, 1, 0)));
		//
		Shape glassGlobeCenter = new Globe(new Vec3(1.4, 0.7, -3.5), 0.5, new PolishedMetalMaterial(Vec3.gray, 0.0));
		Shape glassGlobeCenter2 = new Globe(new Vec3(-1.4, 0.7, -5.0), 0.8,
				new PolishedMetalMaterial(new Vec3(Random.random(), Random.random(), Random.random()), 0.0));
		//
		Shape polishedGlobe = new Globe(new Vec3(0.5, -0.8, -2.5), 0.2, new PolishedMetalMaterial(Vec3.white, 1.5));
		//
		ArrayList<Shape> shapes = new ArrayList<>();
		//
		shapes.add(plane);
		//
		shapes.add(background);
		//
		shapes.add(blueGlobe);
		shapes.add(greenGlobe);
		shapes.add(yellowGlobe);
		//
		shapes.add(glassGlobe);
		shapes.add(glass);
		shapes.add(glassGlobeCenter);
		shapes.add(glassGlobeCenter2);
		//
		shapes.add(polishedGlobe);
		//
		Group group = new Group(shapes);
		raytrace2(obscura, group, 1);
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
		write(image, "doc/a06-mirrors-glass-2.png");
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