package geometries;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {

	double height;
	
	
	public Cylinder(double _radius, double h,Ray r) {
		super(_radius,r);
		height=h;
	}
	
	
	@Override
	public Vector getNormal(Point3D _p){return super.getNormal(_p);}
	@Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}
