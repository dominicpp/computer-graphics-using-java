package engel865650.a09;

import java.util.ArrayList;
import java.util.List;

import engel865650.a09.Hit;
import engel865650.a03.Ray;

public class Group implements Shape {

	private List<Shape> shapes = new ArrayList<Shape>();
	private Transform transform = null;

	public Group(List<Shape> s, Transform t) {
		this.shapes = s;
		this.transform = t;
	}

	@Override
	public Hit intersect(Ray r) {
		Hit h = null;
		r = transform.transformRay(r);
		for (Shape shape : shapes) {
			Hit hit = shape.intersect(r);
			if (hit == null) {
				continue;
			} else {
				if (h == null) {
					h = hit;
				} else {
					if (hit.getRayParameter() < h.getRayParameter()) {
						h = hit;
					}

				}
			}

		}
		if (h != null) {
			h = transform.transformHit(h);
		}
		return h;
	}
}