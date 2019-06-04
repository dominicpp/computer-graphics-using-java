package engel865650.a07;

import cgtools.Random;
import cgtools.Vec3;
import engel865650.a03.Ray;

public class PolishedMetalMaterial implements Material {

	private Vec3 color = null;
	private double scatter_factor = 0;

	public PolishedMetalMaterial() {
		color = Vec3.reflection;
	}

	public PolishedMetalMaterial(Vec3 c, double sf) {
		this.color = c;
		this.scatter_factor = sf;
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
		Vec3 rr = Vec3.subtract(r.getDirection(),
				Vec3.multiply(2 * Vec3.dotProduct(h.getNormalVector(), r.getDirection()), h.getNormalVector()));
		if (scatter_factor != 0) {
			Vec3 random = new Vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);
			rr = Vec3.add(rr, Vec3.multiply(scatter_factor, random));
		}
		return new Ray(h.getPositionHit(), rr, 0.00001, Double.POSITIVE_INFINITY);
	}
}
