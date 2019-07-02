package engel865650.a10;

import cgtools.Vec3;
import engel865650.a10.Hit;
import engel865650.a03.Ray;

public class Plane implements Shape {

	private Vec3 normVec, anchorP = null;
	public Material material;

	public Plane(Vec3 ap, Vec3 nv, Material m) {
		this.anchorP = ap;
		this.normVec = nv;
		this.material = m;
	}

	@Override
	public Hit intersect(Ray r) {
		double t = (Vec3.dotProduct(anchorP, normVec) - Vec3.dotProduct(normVec, r.getOrigin()))
				/ Vec3.dotProduct(r.getDirection(), normVec);
		if (t > r.getTmin() && t < r.getTmax()) {
			Vec3 point = r.pointAt(t);
			Vec3 texture = new Vec3(point.x / 1 + 0.5, point.z / 1 + 0.5, 0);
			return new Hit(t, point, normVec, texture, material);
		}
		return null;
	}
}
