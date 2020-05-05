package unittests;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 * Testing Tubes
 * @author Avital & Tal
 *
 */
public class TubeTests {
	
	// ============ Equivalence Partitions Tests ==============
	@Test
	public void testGetNormal() {
		Ray r= new Ray(new Vector(0,0,1), new Point3D(0,0,0));
		Tube t= new Tube(1,r);
		Point3D p = new Point3D(1,0,1);
		Vector n= t.getNormal(p);
		assertTrue("bad normal to tube",isZero(r.getVector().dotProduct(n)));
	}

}
