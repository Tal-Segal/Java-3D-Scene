package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {

	private Vector _direction;
	
	/**
	 * Constructor with vector, 3 doubles and a color
	 * @param v
	 * @param p
	 * @param kc
	 * @param kl
	 * @param kq
	 * @param c
	 */
	public SpotLight(Vector v,Point3D p, double kc, double kl, double kq, Color c)
	{
		super(p, kc, kl, kq, c);
		_direction=v.normalize();
	}
	
	/**
	 * Get the intensity, considering the position and direction
	 */
    @Override
    public Color getIntensity(Point3D p) {
        double x = _direction.dotProduct(getL(p));
        return super.getIntensity(p).scale(java.lang.Math.max(0, x)); // if x=0 the direction is
                                                                      // orthogonal and the intensity is 0
    }
}
