package unittests;

import geometries.*;
import primitives.*;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;


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

}
