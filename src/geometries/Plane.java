package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

import java.util.List;



public class Plane implements Geometry{

	Point3D p;
	Vector v; //normal
	
	public Plane(Point3D _p, Vector _v) {
		p=_p;
		v=_v;
	}
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		p=p1;
		Vector v1= p1.subtract(p2);
		Vector v2= p1.subtract(p3);
		v=v1.crossProduct(v2);	
	}
	public Point3D getPoint() {return p;}
	public Vector getVector() {return v;}
	@Override
    public String toString() {
        return "" + p.toString() +" "+ v.toString();
    }
	@Override
	public Vector getNormal(Point3D _p) {
		return v.normalize();}
		
	@Override
	public List<Point3D> findIntersections (Ray ray)
	{
		// TODO Auto-generated method stub
		  Vector p0Q;
	        try {
	            p0Q = p.subtract(ray.getPoint());
	        } catch (IllegalArgumentException e) {
	            return null; // ray starts from point Q - no intersections
	        }

	        double nv = v.dotProduct(ray.getVector());
	        if (isZero(nv)) // ray is parallel to the plane - no intersections
	            return null;

	        double t = alignZero(v.dotProduct(p0Q) / nv);

	        return t <= 0 ? null : List.of(ray.getTargetPoint(t));
	 }

	}

