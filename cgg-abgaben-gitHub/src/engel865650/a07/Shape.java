package engel865650.a07;

import engel865650.a07.Hit;
import engel865650.a03.Ray;

public interface Shape {
	public Hit intersect(Ray r);
}
