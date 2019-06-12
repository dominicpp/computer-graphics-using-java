package engel865650.a08;

import cgtools.Random;
import cgtools.Vec3;
import engel865650.a03.Ray;

public class GlassMaterial implements Material {

	private Vec3 color;
	private double n1, n2 = 0;

	public GlassMaterial(double n2) {
		color = Vec3.reflection;
		n1 = 1.0;
		this.n2 = n2;
	}

	@Override
	public Vec3 emission(Ray r, Hit h) {
		return Vec3.zero;
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return color;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		Vec3 scattered = null;
		Vec3 n = h.getNormalVector();
		Vec3 d = r.getDirection();
		if (Vec3.dotProduct(n, d) > 0) {
			n = Vec3.multiply(n, -1);
			n1 = n2;
		}
		if (refract(d, n, n1, n2) != null) {
			if (Random.random() > schlick(d, n, n1, n2)) {
				scattered = refract(d, n, n1, n2);
			} else {
				scattered = reflect(d, n);
			}
		} else {
			scattered = reflect(d, n);
		}
		return new Ray(h.getPositionHit(), scattered, 0.00001, Double.POSITIVE_INFINITY);
	}

	public Vec3 refract(Vec3 d, Vec3 n, double n1, double n2) {
		double r = n1 / n2;
		double c = -1.0 * Vec3.dotProduct(n, d);
		double dt = ((1 - Math.pow(r, 2) * (1 - Math.pow(c, 2))));
		if (dt > 0) {
			Vec3 result = Vec3.add(Vec3.multiply(r, d), Vec3.multiply(r * c - Math.sqrt(dt), n));
			return result;
		}
		return null;

	}

	public double schlick(Vec3 d, Vec3 n, double n1, double n2) {
		double specularReflectionFactor = Math.pow((n1 - n2) / (n1 + n2), 2);
		return specularReflectionFactor + (1 - specularReflectionFactor) * Math.pow((1 + Vec3.dotProduct(n, d)), 5);
	}

	public Vec3 reflect(Vec3 d, Vec3 n) {
		return Vec3.subtract(d, (Vec3.multiply(2, Vec3.multiply(Vec3.dotProduct(n, d), n))));
	}

}
