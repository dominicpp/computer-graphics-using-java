package engel865650.a10;

import cgtools.Vec3;

public class Constant implements Sampler {

	private Vec3 color = null;

	public Constant(Vec3 color) {
		this.color = color;
	}

	@Override
	public Vec3 color(double u, double v) {
		return color;
	}

}
