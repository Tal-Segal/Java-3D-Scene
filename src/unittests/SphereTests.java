package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Spheres
 * @author Avital & Tal
 *
 */
public class SphereTests {
	/**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
		Point3D p= new Point3D(1, 1, 6);
		Point3D p1=new Point3D(1,1,1);
		Sphere s = new Sphere(5, p1);
		Vector v= p.subtract(p1).normalize();
		assertEquals("Bad normal to sphere",v,s.getNormal(p));
	}
	/**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
	@Test
	public void testFindIntersections() throws Exception
	{
	 Sphere sphere = new Sphere(1d,new Point3D(1, 0, 0));

	// ============ Equivalence Partitions Tests ==============

	// TC01: Ray's line is outside the sphere
 	sphere.findIntersections(new Ray(new Vector(1, 1, 0), new Point3D(-1, 0, 0)));

	// TC02: Ray starts before and crosses the sphere 
	        GeoPoint p1 = new GeoPoint(sphere, new Point3D(0.0651530771650466, 0.355051025721682, 0));
	        GeoPoint p2 = new GeoPoint(sphere,new Point3D(1.53484692283495, 0.844948974278318, 0));
	        List<GeoPoint> result = sphere.findIntersections(new Ray(new Vector(3, 1, 0), new Point3D(-1, 0, 0)));
	        assertEquals("Wrong number of points", 2, result.size());
	        if (result.get(0).point.getX().get() > result.get(1).point.getX().get())
	            result = List.of(result.get(1), result.get(0));
	        
	        assertEquals("Ray crosses sphere", List.of(p1, p2), result);
	 
	// TC03: Ray starts inside the sphere 
	Sphere sphere2=new Sphere(2.0,new Point3D(0,-1,0));
	        Ray ray1 = new Ray (new Vector(3,-1,0),new Point3D(-1,0,0));
	ArrayList<GeoPoint> myintersectionPoints=(ArrayList<GeoPoint>) sphere2.findIntersections(ray1);
	ArrayList<GeoPoint> intersectionPoints=new ArrayList<GeoPoint>();
	intersectionPoints.add(new GeoPoint(sphere2, new Point3D(2,-1,0)));
	assertEquals(intersectionPoints,myintersectionPoints);
	     
	// TC04: Ray starts right after the sphere
	Ray ray2 = new Ray (new Vector(3,2,0),new Point3D(-3,0,0));
	ArrayList<GeoPoint> myintersectionPoints1=(ArrayList<GeoPoint>) sphere2.findIntersections(ray2);
	assertEquals(null,myintersectionPoints1);
	// =============== Boundary Values Tests ==================

	// **** Group: Ray's line crosses the sphere (but not the center)
	// TC11: Ray starts at sphere and goes inside 
	Ray ray3 = new Ray (new Vector(0,2,2),new Point3D(0,-3,0));
	intersectionPoints.clear();
	intersectionPoints.add(new GeoPoint(sphere2, new Point3D(0,-1,2)));
	ArrayList<GeoPoint> myintersectionPoints2=(ArrayList<GeoPoint>) sphere2.findIntersections(ray3);
	assertEquals(intersectionPoints,myintersectionPoints2);

	  // TC12: Ray starts at sphere and goes outside
	Ray ray4 = new Ray (new Vector(5,-2,6),new Point3D(0,-3,0));
	ArrayList<GeoPoint> myintersectionPoints3=(ArrayList<GeoPoint>) sphere2.findIntersections(ray4);
	assertEquals(null,myintersectionPoints3);
	  // **** Group: Ray's line goes through the center
	  // TC13: Ray starts before the sphere 
	Ray ray5 = new Ray (new Vector(0,7,0),new Point3D(0,-4,0));
	intersectionPoints.clear();
	intersectionPoints.add(new GeoPoint(sphere2, new  Point3D(0,-3,0)));
	intersectionPoints.add(new GeoPoint(sphere2, new Point3D(0,1,0)));
	ArrayList<GeoPoint> myintersectionPoints4=(ArrayList<GeoPoint>) sphere2.findIntersections(ray5);
	assertEquals(intersectionPoints,myintersectionPoints4);
	 
	  // TC14: Ray starts at sphere and goes inside 
	Ray ray6 = new Ray (new Vector(0,-4,0),new Point3D(0,1,0));
	intersectionPoints.clear();
	intersectionPoints.add(new GeoPoint(sphere2, new  Point3D(0,-3,0)));
	ArrayList<GeoPoint> myintersectionPoints5=(ArrayList<GeoPoint>) sphere2.findIntersections(ray6);
	assertEquals(intersectionPoints,myintersectionPoints5);

	  // TC15: Ray starts inside
	Ray ray7 = new Ray (new Vector(0,5,0),new Point3D(0,-2,0));
	intersectionPoints.clear();
	intersectionPoints.add(new GeoPoint(sphere2, new  Point3D(0,1,0)));
	ArrayList<GeoPoint> myintersectionPoints6=(ArrayList<GeoPoint>) sphere2.findIntersections(ray7);
	assertEquals(intersectionPoints,myintersectionPoints6);

	  // TC16: Ray starts at the center
	Ray ray8=new Ray(new Vector(0, 1, 0),new Point3D(1, 0, 0));
	intersectionPoints.clear();
	intersectionPoints.add(new GeoPoint(sphere, new Point3D(1,1,0)));
	   List<GeoPoint> myintersectionPoints7 =sphere.findIntersections(ray8);
	        assertEquals( intersectionPoints, myintersectionPoints7);

	  // TC17: Ray starts at sphere and goes outside
	Ray ray9 = new Ray (new Vector(0,11,0),new Point3D(0,4,0));
	ArrayList<GeoPoint> myintersectionPoints8=(ArrayList<GeoPoint>) sphere2.findIntersections(ray9);
	assertEquals(null,myintersectionPoints8);
	 
	  // TC18: Ray starts after sphere
	Ray ray10 = new Ray (new Vector(0,1,0),new Point3D(0,2,0));
	ArrayList<GeoPoint> myintersectionPoints9=(ArrayList<GeoPoint>) sphere2.findIntersections(ray10);
	assertEquals(null,myintersectionPoints9);
	  // **** Group: Ray's line is tangent to the sphere
	  // TC19: Ray starts before the tangent point
	Ray ray11 = new Ray (new Vector(2.5,1,0),new Point3D(-1,1,0));
	ArrayList<GeoPoint> myintersectionPoints10=(ArrayList<GeoPoint>) sphere2.findIntersections(ray11);
	assertEquals(null,myintersectionPoints10);
	  // TC20: Ray starts at the tangent point
	Ray ray12 = new Ray (new Vector(2,0,0),new Point3D(0,1,0));
	ArrayList<GeoPoint> myintersectionPoints11=(ArrayList<GeoPoint>) sphere2.findIntersections(ray12);
	assertEquals(null,myintersectionPoints11);
	  // TC21: Ray starts after the tangent point
	Ray ray13 = new Ray (new Vector(2,0,0),new Point3D(1,1,0));
	ArrayList<GeoPoint> myintersectionPoints12=(ArrayList<GeoPoint>) sphere2.findIntersections(ray13);
	assertEquals(null,myintersectionPoints12);

	// **** Group: Special cases
	  // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
	Ray ray14 = new Ray (new Vector(2,0,0),new Point3D(0,6,0));
	ArrayList<GeoPoint> myintersectionPoints13=(ArrayList<GeoPoint>) sphere2.findIntersections(ray14);
	assertEquals(null,myintersectionPoints13);

	}
}
