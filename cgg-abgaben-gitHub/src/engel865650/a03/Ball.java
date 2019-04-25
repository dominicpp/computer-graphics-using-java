package engel865650.a03;

import cgtools.Random;
import cgtools.Vec3;

public class Ball {

	private Vec3 position, ballColor = null;
	private double radius, colorGradientX, colorGradientY = 0;

	private int sampling = 10;

	public Ball(Vec3 p, double r, Vec3 bc) {
		this.position = p;
		this.radius = r;
		this.ballColor = bc;
	}

	public Hit intersect(Ray r) {
		Vec3 new_position = Vec3.subtract(r.getOrigin(), position);

		double a = Vec3.dotProduct(r.getDirection(), r.getDirection());
		double b = 2 * Vec3.dotProduct(new_position, r.getDirection());
		double c = Vec3.dotProduct(new_position, new_position) - Math.pow(radius, 2);
		double discriminant = Math.pow(b, 2) - 4 * a * c;
		double t = 0;

		if (discriminant < 0) {
			return null;
		}

		if (discriminant == 0) {
			// pq
			double t1 = (-(b + Math.sqrt(discriminant))) / (2 * a);
			double t2 = (-(b - Math.sqrt(discriminant))) / (2 * a);

			if (t1 < t2) {
				t = t1;
			}
			t = t2;

			if (t > r.getTmin() && t < r.getTmax()) {
				Vec3 point = r.pointAt(t1);
				Vec3 normVec = Vec3.divide(Vec3.subtract(point, position), radius);
				return new Hit(t, point, normVec, ballColor);
			}
		}

		if (discriminant > 0) {
			// pq
			double t1 = (-(b + Math.sqrt(discriminant))) / (2 * a);
			double t2 = (-(b - Math.sqrt(discriminant))) / (2 * a);

			if (t1 < t2) {
				t = t1;
			}
			t = t2;

			if (t > r.getTmin() && t < r.getTmax()) {
				Vec3 point = r.pointAt(t1);
				Vec3 normVec = Vec3.divide(Vec3.subtract(point, position), radius);
				return new Hit(t, point, normVec, ballColor);
			}
		}
		return null;
	}

	public static Vec3 lightSurface(Vec3 position, Vec3 normal, Vec3 color) {
		Vec3 lightDir = Vec3.normalize(new Vec3(1, 1, 0.5));
		Vec3 ambient = Vec3.multiply(0.1, color);
		Vec3 diffuse = Vec3.multiply(0.9 * Double.max(0, Vec3.dotProduct(lightDir, normal)), color);
		return Vec3.add(ambient, diffuse);
	}

	public Vec3 pixelColor(double x, double y) {
		// background color
		colorGradientX = ((double) x / 8) / Main.image.getWidth();
		colorGradientY = ((double) y / 5) / Main.image.getHeight();
		return new Vec3(2.3 * colorGradientX, 7.3 * colorGradientX, 4.3 * colorGradientY);
	}

	public Vec3 stratified_Sampling(double x, double y) {
		Vec3 isResult = pixelColor(x, y);
		for (int xi = 0; xi < sampling; xi++) {
			for (int yi = 0; yi < sampling; yi++) {
				double rx = Random.random();
				double ry = Random.random();
				double xs = x + (xi + rx) / sampling;
				double ys = y + (yi + ry) / sampling;
				isResult = Vec3.add(isResult, pixelColor(xs, ys));
			}
		}
		return Vec3.divide(isResult, sampling * sampling);
	}

}