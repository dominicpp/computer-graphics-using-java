package engel865650.a09;

//
// Author: Henrik Tramberend <tramberend@beuth-hochschule.de>
//

import cgtools.Mat4;
import cgtools.Vec3;
import engel865650.a03.Ray;

import static cgtools.Vec3.*;

public class BoundingBox {
	final Vec3 min, max;
	static public boolean enabled = true;
	static public long hits = 0;
	static public long misses = 0;

	static public void reset(boolean enable) {
		enabled = enable;
		hits = 0;
		misses = 0;
	}

	public BoundingBox() {
		min = vec3(Double.POSITIVE_INFINITY);
		max = vec3(Double.NEGATIVE_INFINITY);
	}

	public BoundingBox(Vec3 min, Vec3 max) {
		this.min = min;
		this.max = max;
	}

	public BoundingBox extend(BoundingBox bb) {
		return new BoundingBox(vec3(Math.min(min.x, bb.min.x), Math.min(min.y, bb.min.y), Math.min(min.z, bb.min.z)),
				vec3(Math.max(max.x, bb.max.x), Math.max(max.y, bb.max.y), Math.max(max.z, bb.max.z)));
	}

	public BoundingBox extend(Vec3 p) {
		return new BoundingBox(vec3(Math.min(min.x, p.x), Math.min(min.y, p.y), Math.min(min.z, p.z)),
				vec3(Math.max(max.x, p.x), Math.max(max.y, p.y), Math.max(max.z, p.z)));
	}

	public BoundingBox splitLeft() {
		Vec3 size2 = divide(subtract(max, min), 2);
		if (size2.x >= size2.y && size2.x >= size2.z) {
			return new BoundingBox(min, vec3(min.x + size2.x, max.y, max.z));
		} else if (size2.y >= size2.x && size2.y >= size2.z) {
			return new BoundingBox(min, vec3(max.x, min.y + size2.y, max.z));
		} else {
			return new BoundingBox(min, vec3(max.x, max.y, min.z + size2.z));
		}
	}

	public BoundingBox splitRight() {
		Vec3 size2 = divide(subtract(max, min), 2);
		if (size2.x >= size2.y && size2.x >= size2.z) {
			return new BoundingBox(vec3(min.x + size2.x, min.y, min.z), max);
		} else if (size2.y >= size2.x && size2.y >= size2.z) {
			return new BoundingBox(vec3(min.x, min.y + size2.y, min.z), max);
		} else {
			return new BoundingBox(vec3(min.x, min.y, min.z + size2.z), max);
		}
	}

	public BoundingBox transform(Mat4 xform) {
		BoundingBox result = new BoundingBox();

		result = result.extend(xform.transformPoint(min));
		result = result.extend(xform.transformPoint(vec3(min.x, min.y, max.z)));
		result = result.extend(xform.transformPoint(vec3(min.x, max.y, min.z)));
		result = result.extend(xform.transformPoint(vec3(min.x, max.y, max.z)));
		result = result.extend(xform.transformPoint(vec3(max.x, min.y, min.z)));
		result = result.extend(xform.transformPoint(vec3(max.x, min.y, max.z)));
		result = result.extend(xform.transformPoint(vec3(max.x, max.y, min.z)));
		result = result.extend(xform.transformPoint(max));

		return result;
	}

	public boolean contains(Vec3 v) {
		return min.x <= v.x && min.y <= v.y && min.z <= v.z && max.x >= v.x && max.y >= v.y && max.z >= v.z;
	}

	public boolean contains(BoundingBox bb) {
		return min.x <= bb.min.x && min.y <= bb.min.y && min.z <= bb.min.z && max.x >= bb.max.x && max.y >= bb.max.y
				&& max.z >= bb.max.z;
	}

	//
	// Adapted from
	// https://tavianator.com/cgit/dimension.git/tree/libdimension/bvh/bvh.c
	//
	public boolean intersect(Ray ray) {
		if (!enabled) {
			hits++;
			return true;
		}

		if (this.contains(ray.pointAt(ray.getTmin())))
			return true;
		if (this.contains(ray.pointAt(ray.getTmax())))
			return true;

		double dix = 1.0 / ray.getDirection().x;
		double diy = 1.0 / ray.getDirection().y;
		double diz = 1.0 / ray.getDirection().z;

		double tx1 = (min.x - ray.getOrigin().x) * dix;
		double tx2 = (max.x - ray.getOrigin().x) * dix;

		double tmin = Math.min(tx1, tx2);
		double tmax = Math.max(tx1, tx2);

		double ty1 = (min.y - ray.getOrigin().y) * diy;
		double ty2 = (max.y - ray.getOrigin().y) * diy;

		tmin = Math.max(tmin, Math.min(ty1, ty2));
		tmax = Math.min(tmax, Math.max(ty1, ty2));

		double tz1 = (min.z - ray.getOrigin().z) * diz;
		double tz2 = (max.z - ray.getOrigin().z) * diz;

		tmin = Math.max(tmin, Math.min(tz1, tz2));
		tmax = Math.min(tmax, Math.max(tz1, tz2));

		if (tmax >= tmin && ray.contains(tmin)) {
			hits++;
			return true;
		} else {
			misses++;
			return false;
		}
	}

	public Vec3 size() {
		return subtract(max, min);
	}

	public Vec3 center() {
		return divide(add(max, min), 2);
	}

	public BoundingBox scale(double factor) {
		Vec3 c = center();
		return new BoundingBox(add(multiply(factor, subtract(min, c)), c), add(multiply(factor, subtract(max, c)), c));
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof BoundingBox))
			return false;
		if (o == this)
			return true;
		BoundingBox v = (BoundingBox) o;
		return min.equals(v.min) && max.equals(v.max);
	}

	@Override
	public String toString() {
		return String.format("(BBox: %s %s)", min, max);
	}
}
