package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable
{
    private Box myBox;
	private List<Intersectable> lst = new ArrayList<Intersectable>(); 
	/*We chose ArrayList over LinkedList because we think that in this
	case Quick Access is more important than Adding quickly to the list*/
	/**
	 * Creating a box containing all the geometries little boxes
	 */
	private void createBox() {
		//Restart
        double x1=Double.NEGATIVE_INFINITY;
		double x0=Double.POSITIVE_INFINITY;
		double y1=Double.NEGATIVE_INFINITY;
		double y0=Double.POSITIVE_INFINITY;
		double z1=Double.NEGATIVE_INFINITY;
		double z0=Double.POSITIVE_INFINITY;
		//Adjust the size of the box to contain exactly all the small boxes
        for(Intersectable geo: lst) {        	
        	if(geo.getMyBox().getX0()<x0) x0=geo.getMyBox().getX0();
        	if(geo.getMyBox().getX1()>x1) x1=geo.getMyBox().getX1();
        	if(geo.getMyBox().getY0()<y0) y0=geo.getMyBox().getY0();
        	if(geo.getMyBox().getY1()>y1) y1=geo.getMyBox().getY1();
        	if(geo.getMyBox().getZ0()<z0) z0=geo.getMyBox().getZ0();
        	if(geo.getMyBox().getZ1()>z1) z1=geo.getMyBox().getZ1();
        }
        this.myBox=new Box(x0,x1,y0,y1,z0,z1);
    }	
	 /**
	 * @return the box
		 */
		public Box getMyBox() {
			return myBox;
		}
		
		@Override
		public boolean IsIntersectionBox(Ray ray) {
			return this.myBox.IntersectionBox(ray);
		}
	/**
	 * Constructor of number of geometries
	 * @param _geometries
	 */
    public Geometries(Intersectable... _geometries)
    {
        add( _geometries);
    }
    
    /**
     * Add to the list of geometries
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable g : geometries ) {
            lst.add(g);
        }
        createBox();
    }
    @Override
	/**
	 * Finds intersection points between a ray and all geometries in a list
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		
		List<GeoPoint> intersec=null; //if there is no need- do not initial the list
		
		if (!IsIntersectionBox(ray))
			return null;
		
		for(Intersectable geo: lst) {
			List<GeoPoint> tmp=geo.findIntersections(ray);
			if(tmp!=null) //there are a few intersection points
			{
				if(intersec==null) //for the first time 
				{
					intersec= new ArrayList<>();
				}
				intersec.addAll(tmp);
			}
		}
		return intersec; //may be null
	}
}


			
		
	
	


