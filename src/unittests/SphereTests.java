package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
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

}
