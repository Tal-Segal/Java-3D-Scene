package unittests;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import java.util.List;

import org.junit.Test;
/**
 * Testing Planes
 * @author Avital & Tal
 *
 */

public class PlaneTests {
	
	/**
     * Test method for {@link geometries.Plane#GetNormal(primitives.Point3D)}.
     */
	@Test
	public void testGetNormal() {
		
        // ============ Equivalence Partitions Tests ==============
		Point3D p1= new Point3D(1,2,0);
		Point3D p2= new Point3D(-4,7,0);
		Point3D p3= new Point3D(1,0,5);
		Plane p= new Plane(p1,p2, p3);
		Vector v1=new Vector(p1.subtract(p2));
		Vector v2=new Vector(p2.subtract(p3));
		Vector v3=new Vector(p3.subtract(p1));
		Vector n=p.getNormal(p1);
		assertTrue("ERROR: Bad normal to plane", isZero(v1.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v2.dotProduct(n)));
		assertTrue("ERROR: Bad normal to plane", isZero(v3.dotProduct(n)));
	}
	
	/**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
    	
    	
    	Plane plane =new Plane(new Point3D(1,1,1), new Vector(1,0,0));

		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray intersects the plane

		GeoPoint p1 = new GeoPoint(plane,new Point3D(1,2,2));
		        List<GeoPoint> result = plane.findIntersections(new Ray(new Vector(-0.5,0,0), new Point3D(2,2,2)));
		        assertEquals("Wrong number of points",1, result.size());
		        assertEquals("Ray crosses plane", List.of(p1), result);
		
		// TC02: Ray and plane are parallel
        List<GeoPoint> result1 = plane.findIntersections(new Ray(new Vector(-1.35, -11.06, -0.49), new Point3D(-1.52, -5.18, 3.45)));
        assertEquals("Ray crosses plane", null, result1);

		
		
        // =============== Boundary Values Tests ==================
		
        // TC03:Ray is parallel to the plane,the ray not included in the plan
	       
        List<GeoPoint> result2 = plane.findIntersections(new Ray(new Vector(-1.35, -11.06, -0.49), new Point3D(-1.52, -5.18, 3.45)));
        assertEquals("Ray crosses plane", null, result2);
    
        // TC04: Ray is orthogonal to the plane and starts before the plane

        List<GeoPoint> result3 = plane.findIntersections(new Ray(new Vector(-15.14, 1.36,11.16), new Point3D(0,0,0)));
        assertEquals("Ray crosses plane", null, result3);
   
        // TC05: Ray is orthogonal to the plane and in the plane

        List<GeoPoint> result4 = plane.findIntersections(new Ray(new Vector(-1,0,0), new Point3D(2,2,2)));
        assertEquals("Wrong number of points",1, result4.size());
        assertEquals("Ray crosses plane", List.of(new GeoPoint(plane,new Point3D(1.0,2.0,2.0))), result4);
       
        // TC06: Ray is orthogonal to the plane and after the plane

        List<GeoPoint> result5 = plane.findIntersections(new Ray(new Vector(-1,0,0), new Point3D(-2,-2,-2)));
        assertEquals("Ray crosses plane", null, result5);
       
    }

}
