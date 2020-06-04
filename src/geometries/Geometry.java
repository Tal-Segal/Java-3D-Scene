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
	
	/**
	 * Constructor with color and material
	 * @param emission
	 * @param material
	 */
	public Geometry(Color emission, Material material) {
        _emmission = emission;
        _material = material;
    }
	/**
	 * Constructor with color
	 * @param _emission
	 */
    public Geometry(Color _emission) {
        this(_emission, new Material(0d, 0d, 0));
    }
    /**
     * Default constructor
     */
    public Geometry() {
        this(Color.BLACK);
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
}
