package engel865650.a08;

import cgtools.Random;
import cgtools.Vec3;
import engel865650.a03.Ray;

public class DiffuseMaterial implements Material {

	private Vec3 color = null;

	public DiffuseMaterial(Vec3 c) {
		this.color = c;
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
		double xRnd = Random.random() * 2 - 1;
		double yRnd = Random.random() * 2 - 1;
		double zRnd = Random.random() * 2 - 1;
		while (!(Math.pow(xRnd, 2) + Math.pow(yRnd, 2) + Math.pow(zRnd, 2) <= 1)) {
			xRnd = Random.random() * 2 - 1;
			yRnd = Random.random() * 2 - 1;
			zRnd = Random.random() * 2 - 1;
		}
		Vec3 NormDir = Vec3.normalize(Vec3.add(h.getNormalVector(), new Vec3(xRnd, yRnd, zRnd)));
		return new Ray(h.getPositionHit(), NormDir, 0.000001, Double.POSITIVE_INFINITY);
	}

}
