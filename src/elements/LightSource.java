package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * LightSource interface that every kind of light implements
 * @author Avital & Tal
 */


public interface LightSource {
	
	/**
	 * Get the Intensity
	 * @param p - Point3D
	 * @return Color
	 */
	public Color getIntensity(Point3D p);
	/**
	 * Get the direction of the light
	 * @param p - Point3D
	 * @return Vector
	 */
	public Vector getL(Point3D p);
	/**
	 * Get the distance to a point
	 * @param point
	 * @return double
	 */
	double getDistance(Point3D point);
}
