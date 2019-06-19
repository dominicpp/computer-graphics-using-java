package engel865650.a09;

import engel865650.a09.Hit;
import engel865650.a03.Ray;

public interface Shape {
	public Hit intersect(Ray r);
}
