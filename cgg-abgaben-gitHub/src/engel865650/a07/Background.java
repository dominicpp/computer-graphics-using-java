package engel865650.a07;

import cgtools.Vec3;
import engel865650.a07.Hit;
import engel865650.a03.Ray;

public class Background implements Shape {

	public Material material;

	public Background(Material m) {
		this.material = m;
	}

	@Override
	public Hit intersect(Ray r) {
		double ray_t = Double.POSITIVE_INFINITY;
		Vec3 point = r.pointAt(ray_t);
		return new Hit(ray_t, point, Vec3.zero, material);
	}

}
