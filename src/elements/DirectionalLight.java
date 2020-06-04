package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

	private Vector _direction;
	
	/**
	 * Constructor with vector and a color
	 * @param v
	 * @param c
	 */
	public DirectionalLight(Vector v, Color c)
	{
		super(c);
		_direction=v.normalize();
	}
	
	
	/**
	 * Get the intensity from Light class
	 * @param Point3D p
	 * @return Color
	 */
	@Override
	public Color getIntensity(Point3D p)
	{
		return super.getIntensity();
	}
	/**
	 * Get the direction of the light
	 * @param Point3D p
	 * @return Vector
	 */
	@Override
	public Vector getL(Point3D p)
	{
		return _direction;
	}
	
}
