package engel865650.a04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cgtools.Vec3;
import engel865650.Image;
import engel865650.a03.CObscura;
import engel865650.a03.Hit;
import engel865650.a03.Ray;

public class Main {

	private static final int width = 1600;
	private static final int height = 900;
	public static Image image = null;
	public static Globe globe = null;
	private static CObscura obscura = null;
	private static Vec3 color, background, shade = null;

	public static void main(String[] args) {
		a04_spheres();
		a04_scene();
	}

	public static void a04_spheres() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight());
		Plane plane = new Plane(new Vec3(0.0, -0.5, 0.0), new Vec3(0, 1, 0), Vec3.gray);
		Background background = new Background(Vec3.black);
		Shape globe1 = new Globe(new Vec3(-1.0, -0.25, -2.5), 0.7, Vec3.red);
		Shape globe2 = new Globe(new Vec3(0.0, -0.25, -2.5), 0.5, Vec3.green);
		Shape globe3 = new Globe(new Vec3(1.0, -0.25, -2.5), 0.7, Vec3.blue);
		Shape globe4 = new Globe(new Vec3(0.0, 0.8, -2.5), 0.3, Vec3.white);
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(plane);
		shapes.add(background);
		shapes.add(globe1);
		shapes.add(globe2);
		shapes.add(globe3);
		shapes.add(globe4);
		Group group = new Group(shapes);
		raytraceSpheres(obscura, group, 10);
	}

	public static void a04_scene() {
		image = new Image(width, height);
		obscura = new CObscura(Math.PI / 3, image.getWidth(), image.getHeight());
		Plane plane = new Plane(new Vec3(0.0, -1.0, 0.0), new Vec3(-0.2, 1.8, 0.0), Vec3.gray);
		Plane plane2 = new Plane(new Vec3(0.0, -1.0, 0.0), new Vec3(0.2, 1.8, 0.0), Vec3.white);
		Background background = new Background(Vec3.black);

		// Globe (x, y, z) -> z determines the distance to the camera
		// Ball in middle, and feet
		Shape globe2 = new Globe(new Vec3(0.0, 0.0, -2.5), 0.4, Vec3.green);
		Shape globe9 = new Globe(new Vec3(0.0, 0.0, -2.0), 0.1, new Vec3(0.05, 0.05, 0.05));
		Shape globe1 = new Globe(new Vec3(-0.4, -0.3, -2.5), 0.3, Vec3.red);
		Shape globe3 = new Globe(new Vec3(0.4, -0.3, -2.5), 0.3, Vec3.blue);
		Shape globe5 = new Globe(new Vec3(0.8, -0.6, -2.5), 0.3, new Vec3(1, 1, 0));
		Shape globe6 = new Globe(new Vec3(1.2, -0.9, -2.5), 0.3, new Vec3(1, 0, 1));
		Shape globe7 = new Globe(new Vec3(-0.8, -0.6, -2.5), 0.3, new Vec3(0, 1, 1));
		Shape globe8 = new Globe(new Vec3(-1.2, -0.9, -2.5), 0.3, new Vec3(1.9, 0.75, 0.2));
		// End

		// Both arms
		Shape globe4 = new Globe(new Vec3(-0.4, 0.3, -2.5), 0.3, Vec3.white);
		Shape globe10 = new Globe(new Vec3(0.4, 0.3, -2.5), 0.3, Vec3.white);
		Shape globe11 = new Globe(new Vec3(0.7, 0.5, -2.0), 0.25, Vec3.white);
		Shape globe12 = new Globe(new Vec3(1.2, 0.9, -2.5), 0.25, Vec3.white);
		Shape globe13 = new Globe(new Vec3(1.2, 1.2, -2.5), 0.25, Vec3.white);
		Shape globe14 = new Globe(new Vec3(-0.7, 0.5, -2.0), 0.25, Vec3.white);
		Shape globe15 = new Globe(new Vec3(-1.2, 0.9, -2.5), 0.25, Vec3.white);
		Shape globe16 = new Globe(new Vec3(-1.2, 1.2, -2.5), 0.25, Vec3.white);
		// End

		// All balls in the middle. Balls go backwards.
		Shape globe17 = new Globe(new Vec3(0.0, 0.6, -2.5), 0.15, Vec3.white);
		Shape globe18 = new Globe(new Vec3(0.0, 0.9, -2.9), 0.15, Vec3.white);
		Shape globe19 = new Globe(new Vec3(0.0, 1.2, -3.3), 0.15, Vec3.white);
		Shape globe20 = new Globe(new Vec3(0.0, 1.5, -3.7), 0.15, Vec3.white);
		Shape globe21 = new Globe(new Vec3(0.0, 1.8, -4.1), 0.15, Vec3.red);
		Shape globe22 = new Globe(new Vec3(0.0, 2.1, -4.5), 0.15, Vec3.green);
		Shape globe23 = new Globe(new Vec3(0.0, 2.4, -4.9), 0.15, Vec3.blue);
		Shape globe24 = new Globe(new Vec3(0.0, 2.7, -5.3), 0.15, Vec3.white);
		Shape globe25 = new Globe(new Vec3(0.0, 3.0, -5.7), 0.15, Vec3.white);
		Shape globe26 = new Globe(new Vec3(0.0, 3.3, -6.1), 0.15, Vec3.white);
		Shape globe27 = new Globe(new Vec3(0.0, 3.6, -6.5), 0.15, Vec3.white);
		// End

		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(plane);
		shapes.add(plane2);
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
		shapes.add(globe11);
		shapes.add(globe12);
		shapes.add(globe13);
		shapes.add(globe14);
		shapes.add(globe15);
		shapes.add(globe16);
		shapes.add(globe17);
		shapes.add(globe18);
		shapes.add(globe19);
		shapes.add(globe20);
		shapes.add(globe21);
		shapes.add(globe22);
		shapes.add(globe23);
		shapes.add(globe24);
		shapes.add(globe25);
		shapes.add(globe26);
		shapes.add(globe27);
		Group group = new Group(shapes);
		raytraceScene(obscura, group, 10);
	}

	public static Image raytraceSpheres(CObscura obscura, Group group, int sampler) {
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
						Hit hit = group.intersect(currentRay);
						shade = Globe.lightSurface(hit.getPositionHit(), hit.getNormalVector(), hit.getColor());
						background = Vec3.divide(shade, sampler * sampler);
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a04-3-spheres.png");
		return image;
	}

	public static Image raytraceScene(CObscura obscura, Group group, int sampler) {
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
						Hit hit = group.intersect(currentRay);
						shade = Globe.lightSurface(hit.getPositionHit(), hit.getNormalVector(), hit.getColor());
						background = Vec3.divide(shade, sampler * sampler);
						color = Vec3.add(color, background);
					}
				}
				image.setPixel(x, y, color);
			}
		}
		write(image, "doc/a04-scene.png");
		return image;
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