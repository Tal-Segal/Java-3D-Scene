package primitives;
import primitives.Coordinate;

public final class Point3D {

	Coordinate x;
	Coordinate y;
	Coordinate z;
	
	public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) 
	{
		x=_x;
		y=_x;
		z=_z;
	}
	public Point3D(double _x, double _y, double _z) 
	{
		x = new Coordinate(_x);
		y = new Coordinate(_y);
		z = new Coordinate(_z);
	}
	
	public Point3D(Point3D p)
	{
		x=p.x;
		y=p.y;
		z=p.z;
	}
	public Coordinate getX() {return x;}
	public Coordinate getY() {return y;}
	public Coordinate getZ() {return z;}
	
	@Override
	   public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Point3D)) return false;
	      Point3D oth = (Point3D)obj;
	      return getX().equals(oth.getX()) && getY().equals(oth.getY()) && getZ().equals(oth.getZ());
	   }
	 @Override
	    public String toString() {
	        return "" + x.toString() +" "+ y.toString() +" "+ z.toString();
	    }
	 public Vector subtract(Point3D _p)
	 {		 
		 return new Vector(this.getX().get() - _p.getX().get(), this.getY().get() - _p.getY().get(), this.getZ().get() - _p.getZ().get());
	 }
	 public Point3D add(Vector v)
	 {		 
		 return new Point3D(v.p.getX().get()+getX().get(),v.p.getY().get()+getY().get(), v.p.getZ().get()+getZ().get());
	 }
	 public double distanceSquared(Point3D _p)
	 {
		 Vector tmp= subtract(_p);
		 return tmp.p.getX().get()*tmp.p.getX().get() + tmp.p.getY().get()*tmp.p.getY().get() + tmp.p.getZ().get()*tmp.p.getZ().get();
	 }
	 public double distance(Point3D _p)
	 {
		 return Math.sqrt(distanceSquared(_p));
	 }
}