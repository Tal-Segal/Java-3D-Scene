package primitives;
import primitives.Vector;
import static primitives.Util.*;
public final class Ray {

	
	private static final double DELTA = 0.1;
	
	
	Vector v;
	Point3D p;
	
	public Ray(Vector _v, Point3D _p) {
		try
		{
			/*if (_v.length()!=1)
				throw new IllegalArgumentException("Vector must be normalized");*/
			v=_v;
			p=_p;
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e);
		}
	}
	
    public Ray(Point3D point, Vector direction, Vector normal)
    {
	    //head+ normal.scale(±DELTA)
          v = new Vector(direction).normalized();
          double nv = normal.dotProduct(direction);
	      Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
	      p = point.add(normalDelta);
	 }
	
	
	public Ray(Ray r) {
		v=r.v;
		p=r.p;
	}
	
	public Vector getVector() {return v;}
	
	
	public Point3D getPoint() {return p;}
	
	
	@Override
	   public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Ray)) return false;
	      Ray oth = (Ray)obj;
	      return p.equals(oth.p) && v.equals(oth.v);
	   }
	@Override
	public String toString() {
		return "" + p.toString() +" "+ v.toString();
	
 }
	
		
	public Point3D getTargetPoint(double length) 
    {
         return isZero(length ) ?p : p.add(v.scale(length));
    }
}
