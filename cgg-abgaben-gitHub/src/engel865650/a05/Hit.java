package engel865650.a05;

import cgtools.Vec3;
import engel865650.a05.Material;

public class Hit {

	// am besten die Attribute mit public final deklarieren
	// Dadurch sind keine Getter und Setter notwendig!!!

	private double t = 0;
	private Vec3 x, n = null;
	public Material material;

	public Hit(double ray_parameter, Vec3 position_hit, Vec3 normal_vector, Material material) {
		this.t = ray_parameter;
		this.x = position_hit;
		this.n = normal_vector;
		this.material = material;
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

	public Material getMaterial() {
		return material;
	}

}
