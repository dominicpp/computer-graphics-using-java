package engel865650.a10;

import cgtools.Vec3;
import engel865650.a10.Hit;
import engel865650.a03.Ray;

public interface Material {
	Vec3 emission(Ray r, Hit h);

	Vec3 albedo(Ray r, Hit h);

	Ray scatteredRay(Ray r, Hit h);
}
