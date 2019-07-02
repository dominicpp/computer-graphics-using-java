package engel865650.a10;

import cgtools.Random;
import cgtools.Vec3;

public class CheckerBoardTexture implements Sampler {

	private int n = 0;

	public CheckerBoardTexture(int n) {
		this.n = n;
	}

	// Source:
	// https://tramberend.beuth-hochschule.de/lehre/19-ss/bmi-cgg/lectures/10-texture/10-texture-handout.html
	@Override
	public Vec3 color(double u, double v) {
		double ui = (int) ((u % 7) * n);
		double vi = (int) ((v % 7) * n);
		if ((ui + vi) % 2 == 0)
			return Vec3.vec3(0, 0, Random.random() * 1 + 2);
		else
			return Vec3.vec3(Random.random() * 1 + 2, 0, 0);
	}

}
