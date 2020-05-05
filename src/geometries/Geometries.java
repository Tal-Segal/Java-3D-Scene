package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.Point3D;
import primitives.Ray;

public class Geometries implements Intersectable
{
     
	private List<Intersectable> lst = new ArrayList<Intersectable>(); 
	/*We chose ArrayList over LinkedList because we think in that
	case Quick Access is more important than Adding to the list*/
	

    public Geometries(Intersectable... _geometries)
    {
        add( _geometries);
    }
    

    public void add(Intersectable... geometries) {
        for (Intersectable g : geometries ) {
            lst.add(g);
        }
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;

        for (Intersectable geo : lst) {
            List<Point3D> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) 
            {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
     }
}


			
		
	
	


