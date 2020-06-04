package geometries;
import java.util.List;

import primitives.*;

public interface Intersectable {

	/**
     * @param ray ray pointing toward a Geometry
     * @return List<GeoPoint> return values
     */
	List<GeoPoint> findIntersections(Ray ray);
	
	/**
	 * Class GeoPoint contains a geometry and a point above it
	 * @Autor Avital & Tal
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point3D point;
	    /**
	     * Constructor 
	     * @param g - geometry
	     * @param p - point
	     */
	    public GeoPoint(Geometry g, Point3D p)
	    {
	    	geometry=g;
	    	point=p;
	    }
	    
	    public Point3D getPoint()
	    {
	    	return point;
	    }
	    public Geometry getGeometry()
	    {
	    	return geometry;
	    }
	    @Override
		   public boolean equals(Object obj) {
		      if (this == obj) return true;
		      if (obj == null) return false;
		      if (!(obj instanceof GeoPoint)) return false;
		      GeoPoint oth = (GeoPoint)obj;
		      return point.equals(oth.point) && geometry.equals(oth.geometry);
		   }
	}

}
