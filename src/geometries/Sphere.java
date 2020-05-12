package geometries;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public class Sphere extends RadialGeometry{
	
	Point3D p;
	
	public Sphere(double r, Point3D _p) {
		super(r);
		p=new Point3D(_p);
	}
	@Override
	public Vector getNormal(Point3D _p){
		return _p.subtract(p).normalize();
	}
	@Override
    public String toString() 
	{
	        return "center:"+p;
	} 
    @Override
    public List<Point3D> findIntersections (Ray ray)
    {
    	Vector u;
    	if (ray.getPoint() != Vector.ZERO) //p0 != (0,0,0)
    	{
    	Vector L1 = new Vector(p); //p0  	
        Vector L2=new Vector(ray.getPoint());
         try {
          u=new Vector(L1.subtract(L2));//o-p0
             }
         catch(IllegalArgumentException e)
         {
         return List.of(ray.getTargetPoint(_radius));
         }
    	}
    	else
    	{
    		u = new Vector(p);
    	}

           double tm = u.dotProduct(ray.getVector().normalize());//tm=l*v
           double d = Math.sqrt(Math.pow(u.length(), 2) - Math.pow(tm, 2));//(|u|^2+tm^2)^0.5
           List<Point3D> intersectionPoints =  new ArrayList<Point3D>();
           if (d > _radius) 
           {
               return null;
           }
           double th = Math.sqrt(Math.pow(_radius, 2) - Math.pow(d, 2));//(r^2-t^2)^0.5
           double t1 = tm - th;
           double t2 = tm + th;
           if (t1 > 0)
           {    
               Point3D P1 = new Point3D(ray.getTargetPoint(t1));
               intersectionPoints.add(P1);
           }
           if (t2 > 0)
           {
               Point3D P2 = new Point3D(ray.getTargetPoint(t2));
               intersectionPoints.add(P2);
           }
           if(!intersectionPoints.isEmpty())
           {
        return intersectionPoints;
           }
           return null;
       }

	
    }

