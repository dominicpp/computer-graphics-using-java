package engel865650.a03;

import cgtools.Vec3;

public class Hit {

	// am besten die Attribute mit public final deklarieren
	// Dadurch sind keine Getter und Setter notwendig!!!

	private double t = 0;
	private Vec3 x, n, c = null;

	public Hit(double ray_parameter, Vec3 position_hit, Vec3 normal_vector, Vec3 color) {
		this.t = ray_parameter;
		this.x = position_hit;
		this.n = normal_vector;
		this.c = color;
	}

	public double getRayParameter() {
		return t;
	}

	public Vec3 getPositionHit() {
		return x;
	}

	public Vec3 getNormalVector() {
		return n;
	}

	public Vec3 getColor() {
		return c;
	}

}
