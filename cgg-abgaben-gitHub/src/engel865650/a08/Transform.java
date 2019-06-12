package engel865650.a08;

import cgtools.Mat4;
import engel865650.a03.Ray;

public class Transform {
	public Mat4 toWorld = null;
	public Mat4 fromWorld = null;
	public Mat4 toWorldN = null;

	public Transform(Mat4 transform) {
		this.toWorld = transform;
		this.fromWorld = transform.invertFull();
		this.toWorldN = transform.transpose();
	}

	public Ray transformRay(Ray r) {
		return new Ray(fromWorld.transformPoint(r.getOrigin()), fromWorld.transformDirection(r.getDirection()),
				r.getTmin(), r.getTmax());
	}

	public Hit transformHit(Hit h) {
		return new Hit(h.getRayParameter(), toWorld.transformPoint(h.getPositionHit()),
				toWorldN.transformDirection(h.getNormalVector()), h.getMaterial());
	}

}
