
package engel865650.a02;

import cgtools.Random;
import cgtools.Vec3;

public class Diamond {

	private double x, y = 0;
	private double size_e, size_f = 0;
	Vec3 color;

	public Diamond() {

	}

	public Diamond(double x, double y, double size_e, double size_f, Vec3 color) {
		this.x = x;
		this.y = y;
		this.size_e = size_e;
		this.size_f = size_f;
		this.color = color;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getSize_E() {
		return size_e;
	}

	public double getSize_F() {
		return size_f;
	}

	public Vec3 getColor() {
		return color;
	}

	// for every diamond, random attributes
	public Diamond getRandomAttributes() {
		x = Random.random() * Main.image.getWidth();
		y = Random.random() * Main.image.getHeight();
		size_e = Random.random() * Main.image.getWidth() / 7 + 10;
		size_f = Random.random() * Main.image.getHeight() / 8 + 15;
		return new Diamond(x, y, size_e, size_f, new Vec3(Random.random(), Random.random(), Random.random()));
	}

}