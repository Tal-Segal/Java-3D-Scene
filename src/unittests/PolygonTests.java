/**
 * 
 */
package unittests;
import geometries.Intersectable.GeoPoint;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * @author Avital & Tal
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
	
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertex on the side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last points the first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    
    
    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() 
	{
		Triangle triangle =new Triangle(new Point3D(0,0,3),new Point3D(1,0,0), new Point3D(-6,0,0));
		
		 // ============ Equivalence Partitions Tests ==============		
		 //TC01 Ray starts Inside triangle
		 assertEquals("Ray Inside the triangle",List.of(new GeoPoint(triangle,new Point3D(0,0,0.5))),triangle.findIntersections((new Ray(new Vector(0, -1, 0),new Point3D(0, 2, 0.5)))));
		 //TC02 Ray starts Outside against edge
		 assertEquals("Ray starts outside against edge",null,triangle.findIntersections((new Ray(new Vector(0, 8, 0),new Point3D(1, -4, -1)))));
		 //TC03 Ray starts Outside against vertex
		 assertEquals("Ray starts outside against vertex",null,triangle.findIntersections((new Ray(new Vector(0, 7, 0),new Point3D(1.5, -2, -0.2)))));
		 
		// =============== Boundary Values Tests ==================
		//TC04 	Point on edge
		 assertEquals("Ray's point is on the edge",null,triangle.findIntersections((new Ray(new Vector(0, 5, 0),new Point3D(1, -4, 0)))));
		//TC05 The point is in vertex
		 assertEquals("Ray's point is in vertex",null,triangle.findIntersections((new Ray(new Vector(0, 5, 0),new Point3D(1, -1, 0)))));
		//TC06 The point is On edge's continuation
		 assertEquals("Ray's point is on edge's continuation",null,triangle.findIntersections((new Ray(new Vector(0, 11, 0),new Point3D(3, -3, 0)))));
    }

}