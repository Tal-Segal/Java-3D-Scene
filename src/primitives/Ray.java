package primitives;
import primitives.Vector;

public final class Ray {

	Vector v;
	Point3D p;
	
	public Ray(Vector _v, Point3D _p) {
		try
		{
			if (_v.length()!=1)
				throw new IllegalArgumentException("Vector must be normalized");
			v=_v;
			p=_p;
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e);
		}
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
}
