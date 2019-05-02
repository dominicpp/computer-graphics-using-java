package engel865650.a04;

import cgtools.Vec3;
import engel865650.a03.Hit;
import engel865650.a03.Ray;

public class Globe implements Shape {

	private Vec3 position, ballColor = null;
	private double radius = 0;

	public Globe(Vec3 p, double r, Vec3 c) {
		this.position = p;
		this.radius = r;
		this.ballColor = c;
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

			if (t > r.getTmin() && t < r.getTmax()) {
				Vec3 point = r.pointAt(t);
				Vec3 normVec = Vec3.divide(Vec3.subtract(point, position), radius);
				return new Hit(t, point, normVec, ballColor);
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
			if (t > r.getTmin() && t < r.getTmax()) {
				Vec3 point = r.pointAt(t);
				Vec3 normV = Vec3.divide(Vec3.subtract(point, position), radius);
				return new Hit(t, point, normV, ballColor);
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

}
