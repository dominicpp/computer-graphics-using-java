package engel865650.a07;

import cgtools.Mat4;
import cgtools.Vec3;
import engel865650.a03.Ray;

public class CObscura {

	private double angle, camera_width, camera_height = 0;
	private Vec3 rotation = null;
	private Mat4 translation = null;

	public CObscura(double a, double cw, double ch, Vec3 r, Mat4 t) {
		this.angle = a;
		this.camera_width = cw;
		this.camera_height = ch;
		this.rotation = r;
		this.translation = t;
	}

	public Vec3 direction(double x, double y) {
		double xc = x - camera_width / 2.0;
		double yc = camera_height / 2.0 - y;
		double zc = -(camera_height / 2.0) / Math.tan(angle / 2.0);
		return Vec3.normalize(new Vec3(xc, yc, zc));
	}

	public Ray generate(double x, double y) {
		Mat4 xrotation = Mat4.rotate(new Vec3(1, 0, 0), rotation.x);
		Mat4 yrotation = Mat4.rotate(new Vec3(0, 1, 0), rotation.y);
		Mat4 zrotation = Mat4.rotate(new Vec3(0, 0, 1), rotation.z);

		Vec3 temp = direction(x, y);

		Vec3 dir = xrotation.transformDirection(temp);
		dir = yrotation.transformDirection(dir);
		dir = zrotation.transformDirection(dir);

		return new Ray(translation.transformPoint(new Vec3(0, 0, 0)), dir, 0, Double.POSITIVE_INFINITY);
	}

	public double getAngle() {
		return angle;
	}
}
