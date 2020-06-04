package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import java.util.List;

public class Triangle extends Polygon {

	/**
	 * Constructor with color, material, and 3 points
	 * @param emissionLight
	 * @param material
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	 public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
	        super(emissionLight,material,p1,p2,p3);
	     }
	 /**
	  * Constructor with color and 3 points
	  * @param emission
	  * @param p1
	  * @param p2
	  * @param p3
	  */
	    public Triangle(Color emission, Point3D p1, Point3D p2, Point3D p3) {
	    	super(emission, p1, p2, p3);
	      }
	    /**
	     * Constructor with 3 points
	     * @param p1
	     * @param p2
	     * @param p3
	     */
	    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
	        super(p1, p2, p3);
	    }
	
	    
	public String toString() {
        String result = "";
        for (Point3D p : _vertices) {
            result += p.toString();
        }
        return result;
    }

	@Override
	public List<GeoPoint> findIntersections (Ray ray)
	{
		//Find ray's intersection points with triangle
		List<GeoPoint> planeIntersections = _plane.findIntersections(ray);
	    if (planeIntersections == null) return null;

	    Point3D p0 = ray.getPoint();
	    Vector v = ray.getVector();
	    
        GeoPoint geoPoint = new GeoPoint(this, planeIntersections.get(0).point);
	    Vector v1 = _vertices.get(0).subtract(p0).normalize();
	    Vector v2 = _vertices.get(1).subtract(p0).normalize();
	    Vector v3 = _vertices.get(2).subtract(p0).normalize();


	    double s1 = v.dotProduct(v1.crossProduct(v2));
	    if (Util.isZero(s1)) return null;
	    double s2 = v.dotProduct(v2.crossProduct(v3));
	    if (Util.isZero(s2)) return null;
	    double s3 = v.dotProduct(v3.crossProduct(v1));
	    if (Util.isZero(s3)) return null;
	    
	    
        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? List.of(geoPoint) : null;
		
	}

}
