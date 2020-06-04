package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

	protected Point3D _position;
	protected double _kC;
	protected double _kL;
	protected double _kQ;
	
	/**
	 * Constructor with point, 3 doubles and a color
	 * @param p
	 * @param kc
	 * @param kl
	 * @param kq
	 * @param c
	 */
	public PointLight(Point3D p, double kc, double kl, double kq, Color c)
	{
		super(c);
		_position=p;
		_kC=kc;
		_kL=kl;
		_kQ=kl;
	}
	
	/**
	 * Get the intensity
	 * @param Point3D p
	 * @return Color
	 */
    @Override
    public Color getIntensity(Point3D p) {
        double distanceSquared = p.distanceSquared(this._position);
        double d = p.distance(this._position);
        return (_intensity.reduce(_kC + _kL * d + _kQ * distanceSquared));
    }
    /**
	 * Get the direction of the light, considering the position of the light
	 * @param p - Point3D
	 * @return Vector
	 */
	@Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();
    }

}
