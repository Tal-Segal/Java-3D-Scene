package renderer;
import geometries.Intersectable.GeoPoint;
import primitives.Vector;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import static primitives.Util.*;
import elements.*;
import geometries.*;
import scene.Scene;
import java.util.*;

public class Render {
	private static final double DELTA = 0.1;
    private Scene _scene;
    private ImageWriter _imageWriter;

    public Render(Scene _scene) {
        this._scene = _scene;
    }

    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    public Scene get_scene() {
        return _scene;
    }

    /**
	  * building the image from a scene
	  */
	public void renderImage() {
		
		//details from scene
		Camera camera = _scene.getCamera();
		Intersectable geometries = _scene.getGeometries();
		java.awt.Color background = _scene.getBackground().getColor();
       double distance = _scene.getDistance();

		//width and height are the number of pixels in the rows
       //and columns of the view plane
       int width = (int) _imageWriter.getWidth();
       int height = (int) _imageWriter.getHeight();

       //Nx and Ny are the width and height of the image.
       int Nx = _imageWriter.getNx(); //columns
       int Ny = _imageWriter.getNy(); //rows
       
       for (int row = 0; row < Ny; ++row) 
       {
           for (int column = 0; column < Nx; ++column) 
           {
               Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height); //finding the ray from camera to view plane
               
               List<GeoPoint> intersectionPoints = geometries.findIntersections(ray); //finding intersections points in view plane with that ray
               
               if (intersectionPoints == null) //if there are no intersection points
               {
                   _imageWriter.writePixel(column, row, background); //paint everything with the same color and write this pixels
               } 
               else 
               {
                   GeoPoint closestPoint = getClosestPoint(intersectionPoints); //find the closest point by our function below
                   java.awt.Color pixelColor = calcColor(closestPoint).getColor(); //calculate the specific color for this intersection point by our function below
                   _imageWriter.writePixel(column, row, pixelColor); //write this pixels
               }
           }
       }
   }
    
//    private GeoPoint findClosestIntersection(Ray ray) {
//        GeoPoint closestPoint = null;
//        double closestDistance = Double.MAX_VALUE;
//        Point3D ray_p0 = ray.getPoint();
//
//        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
//        if (intersections == null)
//            return null;
//
//        for (GeoPoint geoPoint : intersections) {
//            double distance = ray_p0.distance(geoPoint.getPoint());
//            if (distance < closestDistance) {
//                closestPoint = geoPoint;
//                closestDistance = distance;
//            }
//        }
//        return closestPoint;
//    }


    /**
     * Finding the closest point to the P0 of the camera.
     *
     * @param intersectionPoints list of points, the function finds the closet point
     * from the list to P0 of the camera in the scene.
     * @return the closest point to the camera
     */

private GeoPoint getClosestPoint(List<GeoPoint> points){
		
		GeoPoint closestPoint;
		
		double minDistance; //for checking who is the minimum distance to camera
		double tmp; //for distance in loop
		
		Point3D p0=_scene.getCamera().getPoint(); //the camera point
		
		minDistance=p0.distance(points.get(0).getPoint()); //the first distance
		closestPoint=points.get(0); //if the first point is closest
		
		for (int i=1; i<points.size(); i++)
		{
			tmp=p0.distance(points.get(i).getPoint());
			
			if(tmp<minDistance)
			{
				minDistance=tmp;
				closestPoint=points.get(i);
			}
		}
		return closestPoint;
	}

    /**
     * Printing the grid with a fixed interval between lines.
     * The grid does not destroy the picture, but above it.
     *
     * @param interval The interval between the lines.
     * @param Color
     */
public void printGrid(int interval, java.awt.Color color) {
	
	double rows = this._imageWriter.getNy();
    double collumns = _imageWriter.getNx();
    
    //Writing the lines.
    for (int row = 0; row < rows; ++row) 
    {
        for (int collumn = 0; collumn < collumns; ++collumn) 
        {
            if (collumn % interval == 0 || row % interval == 0) 
            {
                _imageWriter.writePixel(collumn, row, color);
            }
        }
    }
}

    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * Calculate the color intensity in a point
     *
     * @param gp intersection the point for which the color is required
     * @return the color intensity
     */
    private Color calcColor(GeoPoint intersection) {
    	Color color = _scene.getAmbientLight().getIntensity();
    	color = color.add(intersection.geometry.getEmmission());
    	Vector v = intersection.point.subtract(_scene.getCamera().getPoint()).normalize();
    	Vector n = intersection.geometry.getNormal(intersection.point);
    	Material material =intersection.geometry.getMaterial();
    	int nShininess = material.getNshininess();
    	double kd = material.getKd();
    	double ks = material.getKs();
    	for (LightSource lightSource : _scene.getLights()) {
    	Vector l = lightSource.getL(intersection.point);
    	if (sign(n.dotProduct(l)) == sign(n.dotProduct(v))) {
    	Color lightIntensity = lightSource.getIntensity(intersection.point);
    	color = color.add(calcDiffusive(kd, l.dotProduct(n), lightIntensity),
    	calcSpecular(ks, l, n, v, nShininess, lightIntensity));
    	}
    	}
    	return color;
    	}
    
    private boolean sign(double d) {
    	if (d>=0)
    		return true;
    	return false;
    }
    
    private Color calcSpecular(double ks, Vector l, Vector n, Vector V, int nShininess, Color ip) {
        double p = nShininess;
        double nl=l.dotProduct(n);
        if (isZero(nl)) {
            throw new IllegalArgumentException("nl cannot be Zero when scaling the normal vector");
        }
        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double VR = alignZero(V.dotProduct(R));
        if (VR >= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        return ip.scale(ks * Math.pow(-1d * VR, p));
    }

    private Color calcDiffusive(double kd, double nl, Color ip) {
        return ip.scale(Math.abs(nl) * kd);
    }


}