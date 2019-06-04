package engel865650.a07;

import cgtools.Vec3;
import engel865650.a07.Hit;
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
			return new Hit(t, point, normVec, material);
		}
		return null;
	}
}
