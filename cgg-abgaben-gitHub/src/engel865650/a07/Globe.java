package engel865650.a07;

import cgtools.Vec3;
import engel865650.a07.Hit;
import engel865650.a03.Ray;

public class Globe implements Shape {

	private Vec3 position = null;
	private double radius = 0;

	public Material material;

	public Globe(Vec3 p, double r, Material m) {
		this.position = p;
		this.radius = r;
		this.material = m;
	}

	@Override
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

			if (r.contains(t)) {
				Vec3 point = r.pointAt(t);
				Vec3 normVec = Vec3.divide(Vec3.subtract(point, position), radius);
				return new Hit(t, point, normVec, material);
			}
		}

		if (discriminant > 0) {
			// pq
			double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
			double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

			if (t1 < t2) {
				t = t1;
			} else {
				t = t2;
			}
			if (r.contains(t)) {
				Vec3 point = r.pointAt(t);
				Vec3 normV = Vec3.divide(Vec3.subtract(point, position), radius);
				return new Hit(t, point, normV, material);
			}
		}
		return null;
	}

}
