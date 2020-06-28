package geometries;
import primitives.Point3D;
import primitives.Color;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

import java.util.List;


public class Plane extends Geometry{

	Point3D p; //point on the plane
	Vector v; //normal
	/**
	 * Constructor with color, material and 3 points
	 * @param emissionLight
	 * @param material
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Color emissionLight, Material material,Point3D p1, Point3D p2, Point3D p3) {
		super(emissionLight, material,
	    		new Box(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,
	    				Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY));
		p=p1;
		Vector v1= p1.subtract(p2);
		Vector v2= p1.subtract(p3);
		v=v1.crossProduct(v2);	
	}
	/**
	 * Constructor with color and 3 points
	 * @param emissionLight
	 * @param p1
	 * @param p2
	 * @param p3
	 */
    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
    }
    /**
     * Constructor with 3 points
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, p1, p2, p3);
    }
/**
 * Constructor with point and vector
 * @param _p
 * @param _normal
 */
    public Plane(Point3D _p, Vector _normal) {
        super(Color.BLACK, new Material(0, 0, 0),
        		new Box(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,
            			Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY));

        this.p = new Point3D(_p);
        this.v = new Vector(_normal);
    }
	@Override
    public String toString() {
        return "" + p.toString() +" "+ v.toString();
    }
	@Override
	public Vector getNormal(Point3D _p) {
		return v.normalize();}
		
    public List<GeoPoint> findIntersections(Ray ray) {
    	if (!IsIntersectionBox(ray))
    		return null;
        Vector p0Q;
        try {
            p0Q = p.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv =v.dotProduct(ray.getVector());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(v.dotProduct(p0Q) / nv);

        if (t <= 0) {
            return null;
        } else {
            return List.of(new GeoPoint(this, ray.getTargetPoint(t)));
        }
    }
    @Override
    public boolean IsIntersectionBox(Ray ray) {
    	return this.myBox.IntersectionBox(ray);
    }
    
    @Override
    public Point3D getPositionPoint() {return p;}
}

