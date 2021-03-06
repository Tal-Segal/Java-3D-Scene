package geometries;
import geometries.Intersectable;
import java.util.LinkedList;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Author Avital & Tal
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> _vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane _plane;
    

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     * 
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex></li>
     *                                  </ul>
     */
    public Polygon(Color emissionLight, Material material, Point3D... vertices) {
    	
       super(emissionLight, material, null);
       
    	if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        _vertices = List.of(vertices);
        Box b = createBox();
        this.myBox = b;
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        _plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3) return; // no need for more tests for a Triangle

        Vector n = _plane.getNormal(_plane.p);

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }
    /**
     * Create Box according to the vertices
     * @return Box
     */
    private Box createBox() {
      	 
        double x1=Double.NEGATIVE_INFINITY;
		double x0=Double.POSITIVE_INFINITY;
		double y1=Double.NEGATIVE_INFINITY;
		double y0=Double.POSITIVE_INFINITY;
		double z1=Double.NEGATIVE_INFINITY;
		double z0=Double.POSITIVE_INFINITY;
		//Adjust the size of the box according to the vertices
        for(Point3D v: _vertices) {
        	if(v.getX().get()<x0) x0=v.getX().get();
        	if(v.getX().get()>x1) x1=v.getX().get();
        	if(v.getY().get()<y0) y0=v.getY().get();
        	if(v.getY().get()>y1) y1=v.getY().get();
        	if(v.getZ().get()<z0) z0=v.getZ().get();
        	if(v.getZ().get()>z1) z1=v.getZ().get();
        }
        return new Box(x0,x1,y0,y1,z0,z1);
		
    }
    /**
     * Constructor with color and list of points
     * @param emissionLight
     * @param vertices
     */
    public Polygon(Color emissionLight, Point3D... vertices) {
        this(emissionLight, new Material(0, 0, 0), vertices);
    }
    /**
     * Constructor with list of points
     * @param vertices
     */
    public Polygon(Point3D... vertices) {
        this(Color.BLACK, new Material(0, 0, 0), vertices);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal(_plane.p);
    }
    
    @Override
	public List<GeoPoint> findIntersections(Ray ray) {
    	if(!IsIntersectionBox(ray))
    	{
    		return null;
    	}
        List<GeoPoint> palaneIntersections = _plane.findIntersections(ray);
        if (palaneIntersections == null)
            return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getVector();

        Vector v1 = _vertices.get(1).subtract(p0);
        Vector v2 = _vertices.get(0).subtract(p0);
        double sign = v.dotProduct(v1.crossProduct(v2));
        if (isZero(sign))
            return null;

        boolean positive = sign > 0;

        for (int i = _vertices.size() - 1; i > 0; --i) {
            v1 = v2;
            v2 = _vertices.get(i).subtract(p0);
            sign = alignZero(v.dotProduct(v1.crossProduct(v2)));
            if (isZero(sign)) return null;
            if (positive != (sign > 0)) return null;
        }

        //for GeoPoint
        List<GeoPoint> result = new LinkedList<>();
        for (GeoPoint geo : palaneIntersections) {
            result.add(new GeoPoint(this, geo.getPoint()));
        }
        return result;
	}

    @Override
	public boolean IsIntersectionBox(Ray ray) {
		return this.myBox.IntersectionBox(ray);
	}
	@Override
	public Point3D getPositionPoint() {return _vertices.get(0);}
}
