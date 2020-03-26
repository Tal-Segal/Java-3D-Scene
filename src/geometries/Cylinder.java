package geometries;
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
	public Vector getNormal(Point3D _p){return null;}
}
