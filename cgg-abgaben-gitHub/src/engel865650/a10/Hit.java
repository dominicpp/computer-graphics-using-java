package engel865650.a10;

import cgtools.Vec3;
import engel865650.a10.Material;

public class Hit {

	private double t = 0;
	private Vec3 x, n, texture = null;
	public Material material;

	public Hit(double ray_parameter, Vec3 position_hit, Vec3 normal_vector, Vec3 texture, Material material) {
		this.t = ray_parameter;
		this.x = position_hit;
		this.n = normal_vector;
		this.texture = texture;
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

	public Vec3 getTexture() {
		return texture;
	}

	public Material getMaterial() {
		return material;
	}

}
