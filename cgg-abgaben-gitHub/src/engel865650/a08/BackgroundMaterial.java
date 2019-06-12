package engel865650.a08;

import cgtools.Vec3;
import engel865650.a08.Hit;
import engel865650.a03.Ray;

public class BackgroundMaterial implements Material {

	@Override
	public Vec3 emission(Ray r, Hit h) {
		return Vec3.reflection;
	}

	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return Vec3.zero;
	}

	@Override
	public Ray scatteredRay(Ray r, Hit h) {
		return null;
	}

}
