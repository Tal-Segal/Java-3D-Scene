package geometries;
import java.util.List;

import primitives.*;
import geometries.Intersectable.Box;
public interface Intersectable {

	/**
     * @param ray ray pointing toward a Geometry
     * @return List<GeoPoint> return values
     */
	List<GeoPoint> findIntersections(Ray ray);
	
	public boolean IsIntersectionBox(Ray ray);
    public Box getMyBox();
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
	

    public static class Box {
		/**
		 * @return the x0
		 */
		public double getX0() {
			return x0;
		}
		/**
		 * @return the x1
		 */
		public double getX1() {
			return x1;
		}
		/**
		 * @return the y0
		 */
		public double getY0() {
			return y0;
		}
		/**
		 * @return the y1
		 */
		public double getY1() {
			return y1;
		}
		/**
		 * @return the z0
		 */
		public double getZ0() {
			return z0;
		}
		/**
		 * @return the z1
		 */
		public double getZ1() {
			return z1;
		}
		protected final double x0;
        protected final double x1;
        
        protected final double y0;
        protected final double y1;
        
        protected final double z0;
        protected final double z1;
		/**
		 * @param x0
		 * @param x1
		 * @param y0
		 * @param y1
		 * @param z0
		 * @param z1
		 */
		public Box(double x0, double x1, double y0, double y1, double z0, double z1) {
			super();
			this.x0 = x0;
			this.x1 = x1;
			this.y0 = y0;
			this.y1 = y1;
			this.z0 = z0;
			this.z1 = z1;
		}
		/**
		 * Returns true if the ray intersects the box
		 * @param r
		 * @return True/False
		 */
        public boolean IntersectionBox(Ray r)
        {
        	double txmin = (x0 - r.getPoint().getX().get()) / r.getVector().getPoint().getX().get(); 
        	double txmax = (x1 - r.getPoint().getX().get()) / r.getVector().getPoint().getX().get();    	 
    	    if (txmin > txmax) {
    	    	double temp=txmin;
    			txmin=txmax;
    			txmax=temp;
    	    } 	    
    	    double tymin = (y0 - r.getPoint().getY().get()) / r.getVector().getPoint().getY().get(); 
        	double tymax = (y1 - r.getPoint().getY().get()) / r.getVector().getPoint().getY().get();     	 
    	    if (tymin > tymax) {
    	    	double temp=tymin;
    			tymin=tymax;
    			tymax=temp;
    	    }    	 
    	    if ((txmin > tymax) || (tymin > txmax)) 
    	        return false;   	 
    	    if (tymin > txmin) 
    	        txmin = tymin; 	 
    	    if (tymax < txmax) 
    	        txmax = tymax; 
    	    double tzmin = (z0 - r.getPoint().getZ().get()) / r.getVector().getPoint().getZ().get(); 
        	double tzmax = (z1 - r.getPoint().getZ().get()) / r.getVector().getPoint().getZ().get();     	 
    	    if (tzmin > tzmax) {
    	    	double temp=tzmin;
    			tzmin=tzmax;
    			tzmax=temp;
    	    }   	 
    	    if ((txmin > tzmax) || (tzmin > txmax)) 
    	        return false;     	 
    	    if (tzmin > txmin) 
    	        txmin = tzmin;    	 
    	    if (tzmax < txmax) 
    	        txmax = tzmax;     	 
    	    return true;     	           
        }

        
    }

}

