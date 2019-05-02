package engel865650.a04;

import java.util.ArrayList;
import java.util.List;

import engel865650.a03.Hit;
import engel865650.a03.Ray;

public class Group implements Shape {

	private List<Shape> shapes = new ArrayList<Shape>();
	// ArrayList is not necessary -> array would do, too!
	// Also, array would be faster.

	public Group(List<Shape> s) {
		this.shapes = s;
	}

	@Override
	public Hit intersect(Ray r) {
		Hit h = null;
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

		return h;
	}

}