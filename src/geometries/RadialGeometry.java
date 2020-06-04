package geometries;

import primitives.Color;
import primitives.Material;

public abstract class RadialGeometry extends Geometry { 
	
	double _radius;
	
	/**
	 * Constructor with color, radius and material
	 * @param emissionLight
	 * @param radius
	 * @param material
	 */
	public RadialGeometry(Color emissionLight, double radius, Material material) {
        super(emissionLight, material);
        setRadius(radius);
    }
	/**
	 * Constructor with color and radius
	 * @param emissionLight
	 * @param radius
	 */
    public RadialGeometry(Color emissionLight, double radius) {
        super(emissionLight);
        setRadius(radius);
    }
    /**
     * Constructor with radius
     * @param radius
     */
    public RadialGeometry(double radius) {
        super();
        setRadius(radius);
    }
    /**
     * Constructor copy
     * @param other
     */
    public RadialGeometry(RadialGeometry other) {
        super(other.getEmmission(), other._material);
        setRadius(other._radius);
    }
    
    /**
     * Get the radius
     * @return double - the radius
     */
	public double getRadius() {return _radius;}
	/**
	 * Set the radius
	 * @param r
	 */
	public void setRadius(double r) {_radius = r;}

}
