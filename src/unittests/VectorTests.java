package unittests;
import static primitives.Util.*;
import static org.junit.Assert.*;
import primitives.Vector;

import org.junit.Test;

/**
 * Testing Vectors
 * @author Avital & Tal
 *
 */
public class VectorTests {
	/**
     * Test method for {@link primitives.Vector#LengthSquared(primitives.Point3D)}.
     */
	@Test
	public void testLengthSquared() {
		Vector v1 = new Vector (1, 2, 3);
		assertTrue("Length() wrong result length",isZero(v1.lengthSquared() - 14));
	}
	/**
     * Test method for {@link primitives.Vector#Length(primitives.Point3D)}.
     */
	@Test
	public void testLength() {
		assertTrue("ERROR: length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
	}
	/**
     * Test method for {@link primitives.Vector#Add(primitives.Point3D)}.
     */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1,7,-8);
		Vector v2 = new Vector(3,7,0);
		assertEquals("Add() wrong result length", new Vector(4,14,-8), v1.add(v2));
	}
	/**
     * Test method for {@link primitives.Vector#Subtract(primitives.Point3D)}.
     */

	@Test
	public void testSubtract() {
		Vector v1 = new Vector(1,7,-8);
		Vector v2 = new Vector(3,7,0);
		assertEquals("Add() wrong result length", v1.getPoint().subtract(v2.getPoint()), v1.subtract(v2));
	}

	/**
     * Test method for {@link primitives.Vector#DotProduct(primitives.Point3D)}.
     */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);
		assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
	
	}
	/**
     * Test method for {@link primitives.Vector#CrossProduct(primitives.Point3D)}.
     */

	@Test
    public void testCrossProduct() { 
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }

	/**
     * Test method for {@link primitives.Vector#NormalizeAndNormalized(primitives.Point3D)}.
     */

	@Test
	public void testNormalizeAndNomalized() {
		Vector v = new Vector(1, 2, 3);
		Vector vCopy = new Vector(v);
		Vector vCopyNormalize = vCopy.normalize();
		assertTrue("ERROR: normalize() function creates a new vector",vCopy == vCopyNormalize);
		assertTrue("ERROR: normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));
		Vector u = v.normalized();
		assertFalse("ERROR: normalizated() function does not create a new vector",u==v);
	}


}
