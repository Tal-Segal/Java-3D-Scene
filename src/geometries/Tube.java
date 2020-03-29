package geometries;
import primitives.Point3D;
import static primitives.Util.*;
import primitives.Ray;
import primitives.Vector; 

public class Tube extends RadialGeometry{

	Ray ray;
	public Tube(double _radius, Ray r) {
		super(_radius);
		ray=r;
	}
	@Override
	public Vector getNormal(Point3D _p){
		Point3D o= ray.getPoint();
		Vector v= ray.getVector();
		double t= _p.subtract(o).dotProduct(v);
		if (!isZero(t))
			o= o.add(v.scale(t));
		return _p.subtract(o).normalize();
	}
}
