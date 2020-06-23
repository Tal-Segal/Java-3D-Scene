package unittests;
import static org.junit.Assert.*;
//import static primitives.Util.*;
//import java.util.List;

import org.junit.Test;


import geometries.Geometries;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 * Testing Geometries in general
 * @author Avital & Tal
 *
 */
public class GeometriesTests {

	/**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
	@Test
	public void testFindIntersections()
	{
		   Plane plane = new Plane(new Point3D(0,0,0), new Vector(1,0,0));
		   Triangle triangle = new Triangle(new Point3D(2,0,1),new Point3D(2,0,-1),new Point3D(2,1,0));
		   Sphere sphere = new Sphere(1d,new Point3D(4,0,0));
		   Polygon polygon = new Polygon(new Point3D(9,0,1),new Point3D(9,3,1),new Point3D(9,3,-1),new Point3D(9,0,-1));
		   Geometries _geometries = new Geometries(plane, triangle, sphere, polygon);
		   Geometries _geometries2 = new Geometries();//an empty list of geometries
		   
		   
		       // ============ Equivalence Partitions Tests ==============

		       //TC01: a few geometries have intersection points
		       assertEquals("Some geometries have intersection points",1,
		        _geometries.findIntersections(new Ray(new Vector(1, 0, 0), new Point3D(-1,2,0))).size());

		     
		       // =============== Boundary Values Tests ==================
		       
		       // TC11: empty list
		        assertEquals("An empty list",null,
		        _geometries2.findIntersections(new Ray(new Vector(1, 0, 0), new Point3D(1,1,0))));
		       // TC12: no geometry has intersection points
		       assertEquals("Non geometry has intersection points",null,
		        _geometries.findIntersections(new Ray(new Vector(0,1, 0), new Point3D(-1,0,0))));
		      // TC13: all geometries has intersection points
		       assertEquals("All geometries has intersection points", 4,
		        _geometries.findIntersections(new Ray(new Vector(1,0,0), new Point3D(-1,0.5,0))).size());
		       // TC14: one geometry has intersection points with the sphere
		       assertEquals("one geometry has intersection points", 2,_geometries.findIntersections(new Ray(new Vector(4,0,0), new Point3D(2.5,0,0))).size());
		}
			

}
