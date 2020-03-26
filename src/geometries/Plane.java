package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{

	Point3D p;
	Vector v;
	
	public Plane(Point3D _p, Vector _v) {
		p=_p;
		v=_v;
	}
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		p=p1;
		v=new Vector(p3.subtract(p2));
	}
	public Point3D getPoint() {return p;}
	public Vector getVector() {return v;}
	@Override
    public String toString() {
        return "" + p.toString() +" "+ v.toString();
    }
	@Override
	public Vector getNormal(Point3D _p) {return null;}
	

}
