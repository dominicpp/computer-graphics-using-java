package engel865650.a03;

import cgtools.Vec3;

public class CObscura {

	private double angle, camera_width, camera_height = 0;
	private Ray ray = null;

	public CObscura(double a, double cw, double ch) {
		this.angle = a;
		this.camera_width = cw;
		this.camera_height = ch;
	}

	public Vec3 direction(double x, double y) {
		double xc = x - camera_width / 2.0;
		double yc = camera_height / 2.0 - y;
		double zc = -(camera_height / 2.0) / Math.tan(angle / 2.0);
		// camera testing
//		System.out.println(Vec3.normalize(new Vec3(x_ray, y_ray, z_ray)));
		return Vec3.normalize(new Vec3(xc, yc, zc));

	}

	public Ray generate(double x, double y) {
		ray = new Ray(new Vec3(0, 0, 0), direction(x, y), 0, Double.POSITIVE_INFINITY);
		return ray;
	}

	public double getAngle() {
		return angle;
	}
}
