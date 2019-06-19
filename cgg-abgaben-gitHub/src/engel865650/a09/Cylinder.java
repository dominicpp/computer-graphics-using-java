package engel865650.a09;

import cgtools.Vec3;
import engel865650.a03.Ray;

public class Cylinder implements Shape {

	private Vec3 position = null;
	private double radius, height = 0;
	private Material material;

	public Cylinder(Vec3 p, double r, double h, Material m) {
		this.position = p;
		this.radius = r;
		this.height = h;
		this.material = m;
	}

	@Override
	public Hit intersect(Ray r) {

		// Source1: http://woo4.me/wootracer/cylinder-intersection/
		// Source2: https://www.math.uni-trier.de//~schulz/prosem0708/Raytracing.pdf
		// Source3: https://de.wikipedia.org/wiki/Zylinder_(Geometrie)

		Vec3 new_position = Vec3.subtract(r.getOrigin(), position);

		double a = Math.pow(r.getDirection().x, 2) + Math.pow(r.getDirection().z, 2);
		double b = 2 * (new_position.x * r.getDirection().x + new_position.z * r.getDirection().z);
		double c = Math.pow(new_position.x, 2) + Math.pow(new_position.z, 2) - Math.pow(radius, 2);

		double discriminant = Math.sqrt(Math.pow(b, 2) - (4 * a * c));

		if (discriminant < 0) {
			return null;
		}

		double t1 = (-b + discriminant) / (2 * a);
		double t2 = (-b - discriminant) / (2 * a);
		double t0 = t1;

		if (t1 > t2) {
			t1 = t2;
		}

		t2 = t0;
		double y1 = new_position.y + t1 * r.getDirection().y;
		double y2 = new_position.y + t2 * r.getDirection().y;

		if (y1 < -height) {
			if (y2 < -height)
				return null;
			else {
				double t = t1 + (t2 - t1) * (y1 + height) / (y1 - y2);
				if (!(r.contains(t))) {
					return null;
				} else {
					Vec3 hitVec = r.pointAt(t);
					Vec3 hitNormVec = Vec3.divide(Vec3.subtract(hitVec, position), radius);
					return new Hit(t, hitVec, hitNormVec, material);
				}

			}
		}
		if (y1 >= -height && y1 <= height) {
			if (!(r.contains(t1))) {
				return null;
			} else {
				Vec3 hitVec = r.pointAt(t1);
				Vec3 hitNormVec = Vec3.divide(Vec3.subtract(hitVec, position), radius);
				return new Hit(t1, hitVec, hitNormVec, material);
			}
		}
		if (y1 > height) {
			if (y2 > height) {
				return null;
			} else {
				double t = t1 + (t2 - t1) * (y1 - height) / (y1 - y2);
				if (!(r.contains(t))) {
					return null;
				} else {
					Vec3 hitVec = r.pointAt(t);
					Vec3 hitNormVec = Vec3.divide(Vec3.subtract(hitVec, position), radius);
					return new Hit(t, hitVec, hitNormVec, material);
				}
			}
		}
		return null;

	}
}
