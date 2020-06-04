package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable
{
     
	private List<Intersectable> lst = new ArrayList<Intersectable>(); 
	/*We chose ArrayList over LinkedList because we think that in this
	case Quick Access is more important than Adding quickly to the list*/
	
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
    }
    @Override
	/**
	 * Finds intersection points between a ray and all geometries in a list
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		
		List<GeoPoint> intersec=null; //if there is no need- do not initial the list
		
		for(Intersectable geo: lst)
		{
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


			
		
	
	


