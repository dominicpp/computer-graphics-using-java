package engel865650.a04;

import cgtools.Vec3;
import engel865650.a03.Hit;
import engel865650.a03.Ray;

public class Background implements Shape {

	private Vec3 backgroundColor = null;

	public Background(Vec3 bc) {
		this.backgroundColor = bc;
	}

	public Vec3 GetBackgroundColor() {
		return backgroundColor;
	}

	@Override
	public Hit intersect(Ray r) {
		double ray_t = Double.POSITIVE_INFINITY;
		Vec3 point = r.pointAt(ray_t);
		return new Hit(ray_t, point, new Vec3(0, 0, 0), backgroundColor);
	}

}
