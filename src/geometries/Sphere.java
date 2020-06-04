package geometries;
import primitives.Vector;
import static primitives.Util.*;
import java.util.ArrayList;
import java.util.List;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;

public class Sphere extends RadialGeometry{
	
	Point3D p;
	
	/**
	 * Constructor with color, material, radius and center point
	 * @param emissionLight
	 * @param material
	 * @param radius
	 * @param center
	 */
	public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, radius, material);
        this.p = new Point3D(center);
    }
	/**
	 * Constructor with color, radius and center point
	 * @param emissionLight
	 * @param radius
	 * @param center
	 */
    public Sphere(Color emissionLight, double radius, Point3D center) {
       this(emissionLight,new Material(0,0,0),radius,center);
    }
    /**
     * Constructor with radius and center point
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point3D center) {
        this(Color.BLACK,new Material(0,0,0),radius,center);
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
    public List<GeoPoint> findIntersections(Ray ray)
    {
    	Vector u;
    	if (ray.getPoint() != Vector.ZERO) //p0 != (0,0,0)
    	{
    	Vector L1 = new Vector(p); //p0  	
        Vector L2=new Vector(ray.getPoint());
         try {
          u=new Vector(L1.subtract(L2));//O-p0
             }
         catch(IllegalArgumentException e)
         {
         return List.of(new GeoPoint(
                 this,
                 ray.getTargetPoint(this._radius)));
         }
    	}
    	else
    	{
    		u = new Vector(p);
    	}

           double tm = u.dotProduct(ray.getVector().normalize());//tm=l*v
           double d = Math.sqrt(Math.pow(u.length(), 2) - Math.pow(tm, 2));//(|u|^2+tm^2)^0.5
           List<GeoPoint> intersectionPoints =  new ArrayList<GeoPoint>();
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
               intersectionPoints.add(new GeoPoint(this, P1));
           }
           if (t2 > 0)
           {
               Point3D P2 = new Point3D(ray.getTargetPoint(t2));
               intersectionPoints.add(new GeoPoint(this, P2));
           }
           
           if(!intersectionPoints.isEmpty())
           {
             return intersectionPoints; 
           }
           return null;
       }

	
    }

