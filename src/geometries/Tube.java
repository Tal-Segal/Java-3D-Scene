package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import static primitives.Util.*;

import java.util.List;

import primitives.Ray;
import primitives.Vector; 

public class Tube extends RadialGeometry{

	Ray ray;
	
	/**
	 * Construction with color, material, radius and ray
	 * @param emissionLight
	 * @param _material
	 * @param _radius
	 * @param _ray
	 */
	public Tube(Color emissionLight, Material _material, double _radius, Ray _ray) {
        super(Color.BLACK, _radius, null);
        this._material = _material;
        this.ray = new Ray(_ray);

    }
/**
 * Constructor with radius and ray
 * @param _radius
 * @param _ray
 */
    public Tube(double _radius, Ray _ray) {
        this(Color.BLACK, new Material(0, 0, 0), _radius, _ray);
    }
/**
 * Constructor with color, radius and ray
 * @param emissionLight
 * @param _radius
 * @param _ray
 */
    public Tube(Color emissionLight, double _radius, Ray _ray) {
        this(emissionLight, new Material(0, 0, 0), _radius, _ray);
    }
    
	
	@Override
	public Vector getNormal(Point3D _p){
		Point3D o= ray.getPoint();
		Vector v= ray.getVector();
		double t= _p.subtract(o).dotProduct(v);
		if (!isZero(t))
			o= o.add(v.scale(t));
		return _p.subtract(o).normalize();
	}
	
	
	@Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
	
	@Override
	public boolean IsIntersectionBox(Ray ray) {
		return true;
	}
}
