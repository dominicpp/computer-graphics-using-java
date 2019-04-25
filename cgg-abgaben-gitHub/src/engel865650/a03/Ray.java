package engel865650.a03;

import cgtools.Vec3;

public class Ray {

	// am besten die Attribute mit public final deklarieren
	// Dadurch sind keine Getter und Setter notwendig!!!

	private Vec3 origin, direction = null;
	private double tmin = 0, tmax = 0;

	public Ray(Vec3 x0, Vec3 d, double tmin, double tmax) {
		this.origin = x0;
		this.direction = d;
		this.tmin = tmin;
		this.tmax = tmax;
	}

	public Vec3 getOrigin() {
		return origin;
	}

	public Vec3 getDirection() {
		return direction;
	}

	public double getTmin() {
		return tmin;
	}

	public double getTmax() {
		return tmax;
	}

	public Vec3 pointAt(double t) {
		return Vec3.add(origin, Vec3.multiply(t, direction));
	}
}
