package primitives;
import primitives.Point3D;
import java.lang.Math;

public final class Vector {

	Point3D p;
	public final Point3D ZERO= new Point3D(0, 0, 0);
	
	public Vector(Point3D _p) {

			if(_p.equals(ZERO))
				throw new IllegalArgumentException("Vector could not equals zero");
			p=_p;

	}
	public Vector(Vector v) {
		p=v.p;
	}
	public Vector(double _x, double _y, double _z)
	{

			Point3D tmp=new Point3D(_x,_y,_z);
			if(tmp.equals(ZERO))
				throw new IllegalArgumentException("Vector could not equals zero");
			p=new Point3D(_x,_y,_z);

	}
	public Vector(Coordinate _x, Coordinate _y, Coordinate _z)
	{

			Point3D tmp=new Point3D(_x,_y,_z);
			if(tmp.equals(ZERO))
				throw new IllegalArgumentException("Vector could not equals zero");
			p=new Point3D(_x,_y,_z);

	}
	
	public Point3D getPoint() {return p;}
	@Override
	   public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Vector)) return false;
	      Vector oth = (Vector)obj;
	      return p.equals(oth.p);
	   }
	@Override
    public String toString() {
        return "" + p.toString();
    }
	public double lengthSquared()
	{
		return p.getX().get()*p.getX().get() + p.getY().get()*p.getY().get() + p.getZ().get()*p.getZ().get();
	}
	public double length()
	{
		return Math.sqrt(lengthSquared());
	}
	public Vector add(Vector _v)
	{
		return new Vector(p.add(_v));
	}
	public Vector subtract(Vector _v)
	{
		return new Vector(p.subtract(_v.p));
	}
	public Vector scale(double num)
	{
		return new Vector(p.getX().get()*num,p.getY().get()*num,p.getZ().get()*num);
	}
	public double dotProduct(Vector _v)
	{
		return  p.getX().get()*_v.getPoint().getX().get()+
				p.getY().get()*_v.getPoint().getY().get()+
				p.getZ().get()*_v.getPoint().getZ().get();
	}
	public Vector crossProduct(Vector _v)
	{
	
			if(_v.p.getX().get()/p.getX().get()==_v.p.getY().get()/p.getY().get() && _v.p.getY().get()/p.getY().get()==_v.p.getZ().get()/p.getZ().get())
				throw new IllegalArgumentException("Parallel vectors cannot be cross producted"); 
			return new Vector(p.getY().get()*_v.getPoint().getZ().get()-p.getZ().get()*_v.getPoint().getY().get(),
					p.getZ().get()*_v.getPoint().getX().get()-p.getX().get()*_v.getPoint().getZ().get(),
					p.getX().get()*_v.getPoint().getY().get()-p.getY().get()*_v.getPoint().getX().get());

	}
	public Vector normalize()
	{
		double l=length();
		p.x=new Coordinate(p.getX().get()/l);
		p.y=new Coordinate(p.getY().get()/l);
		p.z=new Coordinate(p.getZ().get()/l);
		return this;
	}
	public Vector normalized()
	{
		return new Vector(p.getX().get()/length(),p.getY().get()/length(),p.getZ().get()/length());
	}
	
}
