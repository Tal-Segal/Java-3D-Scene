package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector; 

public class Tube extends RadialGeometry{

	Ray ray;
	public Tube(double _radius, Ray r) {
		super(_radius);
		ray=r;
	}
	@Override
	public Vector getNormal(Point3D _p){return null;}
}
