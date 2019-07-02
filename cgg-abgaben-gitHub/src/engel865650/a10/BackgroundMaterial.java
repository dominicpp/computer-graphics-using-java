package engel865650.a10;

import cgtools.Vec3;
import engel865650.a10.Hit;
import engel865650.a03.Ray;

public class BackgroundMaterial implements Material {

	private Sampler texture = null;

	public BackgroundMaterial(Sampler texture) {
		this.texture = texture;
	}

	@Override
	public Vec3 emission(Ray r, Hit h) {
		return texture.color(h.getTexture().x, h.getTexture().y);
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
