package geometries;
import primitives.Vector;
import primitives.Point3D;

public class Sphere extends RadialGeometry{
	
	Point3D p;
	
	public Sphere(double r, Point3D _p) {
		super(r);
		p=_p;
	}
	@Override
	public Vector getNormal(Point3D _p){return null;}
}
