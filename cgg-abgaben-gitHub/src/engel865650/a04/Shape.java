package engel865650.a04;

import engel865650.a03.Hit;
import engel865650.a03.Ray;

public interface Shape {
	public Hit intersect(Ray r);
}
