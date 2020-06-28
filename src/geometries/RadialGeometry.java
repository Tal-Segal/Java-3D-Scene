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
	public RadialGeometry(Color emissionLight, double radius, Material material, Box b) {
        super(emissionLight, material, b);
        setRadius(radius);
    }
	/**
	 * Constructor with color and radius
	 * @param emissionLight
	 * @param radius
	 */
    public RadialGeometry(Color emissionLight, double radius, Box b) {
        super(emissionLight, b);
        setRadius(radius);
    }
    /**
     * Constructor with radius
     * @param radius
     */
    public RadialGeometry(double radius, Box b) {
        super(b);
        setRadius(radius);
    }
    /**
     * Constructor copy
     * @param other
     */
    public RadialGeometry(RadialGeometry other) {
        super(other.getEmmission(), other._material, other.myBox);
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
