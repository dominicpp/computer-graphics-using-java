package engel865650.a10;

import cgtools.Vec3;
import engel865650.a10.Hit;
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
		Vec3 normal = Vec3.normalize(r.getDirection());
		double n = Math.acos(normal.y);
		double azimut = Math.PI + Math.atan2(normal.x, normal.z);
		double u = azimut / (2 * Math.PI);
		double v = n / Math.PI;
		Vec3 uv = new Vec3(u, v, 0);
		return new Hit(ray_t, point, Vec3.zero, uv, material);
	}

}
