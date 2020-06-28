package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
public abstract class Geometry implements Intersectable {

	public abstract Vector getNormal(Point3D p);
	
	protected Color _emmission;
	protected Material _material;
	protected Box myBox;
	/**
	 * Constructor with color and material
	 * @param emission
	 * @param material
	 */
	public Geometry(Color emission, Material material, Box b) {
        _emmission = emission;
        _material = material;
        myBox=b;
    }
	/**
	 * Constructor with color
	 * @param _emission
	 */
    public Geometry(Color _emission,Box b) {
        this(_emission, new Material(0d, 0d, 0),b);
    }
    /**
     * Default constructor
     */
    public Geometry(Box b) {
        this(Color.BLACK,b);
    }
	/**
	 * Returns the emmission light
	 * @return Color
	 */
	public Color getEmmission() {return _emmission;}
	/**
	 * Returns the material
	 * @return Material
	 */
	public Material getMaterial() {
		return _material;
	}
	/**
	 * Returns the box
	 * @return Box
	 */
	public Box getMyBox() {
		return myBox;
	}
	/**
	 * Returns point on the geometry - for Bounding Volume Hierarchy
	 * @return Point3D
	 */
	public abstract Point3D getPositionPoint();
}
