package renderer;
import static primitives.Util.*;
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
	/**
	 * Constant double for distance between ray and light source
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 30;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private int raysAmount = 9;
	
    private Scene _scene;
    private ImageWriter _imageWriter;
    
    private int _threads = 1;
    private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private boolean _print = false; // printing progress percentage

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
       
       //for the multithreading:
       final Pixel thePixel = new Pixel(Ny, Nx);
       
       
       // Generate threads
       Thread[] threads = new Thread[_threads];
       for (int i = _threads - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel)) {
					if (raysAmount == 0)
				       {
						Ray ray = camera.constructRayThroughPixel(Nx, Ny, pixel.col, pixel.row, distance, width, height); //finding the ray from camera to view plane        	   
		    		     List<GeoPoint> intersectionPoints = geometries.findIntersections(ray); //finding intersections points in view plane with that ray
		               	 if (intersectionPoints == null) //if there are no intersection points
				         {
				         _imageWriter.writePixel(pixel.col, pixel.row, background); //paint everything with the same color and write this pixels
				          } 
				         else 
				         {
				         GeoPoint closestPoint = getClosestPoint(intersectionPoints); //find the closest point by our function below
				         java.awt.Color pixelColor = calcColor(closestPoint, ray).getColor(); //calculate the specific color for this intersection point by our function below
				         _imageWriter.writePixel(pixel.col, pixel.row,closestPoint == null ? _scene.getBackground().getColor()
				         :  pixelColor); //write this pixels
				         }	   			
				    	   		
				       }
				       else {    // supersampling
				                   List<Ray> rays = camera.constructRaysThroughPixel(Nx, Ny, pixel.col, pixel.row, distance, width, height, raysAmount);
				                   _imageWriter.writePixel(pixel.col, pixel.row, calcColor(rays).getColor());
				       }	
				}
			});
   }
    // Start threads
    		for (Thread thread : threads) thread.start();

    		// Wait for all threads to finish
    		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
    		if (_print) System.out.printf("\r100%%\n");
	}

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
    
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        Color color = calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
        color = color.add(_scene.getAmbientLight().getIntensity());
        return color;
    }


    /**
     * @param rays List of surrounding rays
     * @return average color
     */
    private Color calcColor(List<Ray> rays) {
        Color bkg = _scene.getBackground();
        Color color = Color.BLACK;
        for (Ray ray : rays) {
            GeoPoint gp = findClosestIntersection(ray);
            color = color.add(gp == null ? bkg : calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1d));
        }
        color = color.add(_scene.getAmbientLight().getIntensity());
        int size = rays.size();
        return (size == 1) ? color : color.reduce(size);
    }
    
    /**
     * Calculate the color intensity in a point
     * 
     * @param gp intersection the point for which the color is required
     * @return the color intensity
     */
    private Color calcColor(GeoPoint intersection,Ray inRay, int level, double k) {
    	
    	if (level == 1 || k < MIN_CALC_COLOR_K) return Color.BLACK; //too much levels or isn't reflected/refracted anymore
    	
    	Color color = intersection.geometry.getEmmission(); 
    	
    	Vector v = intersection.point.subtract(_scene.getCamera().getPoint()).normalize();
    	Vector n = intersection.geometry.getNormal(intersection.point);
    	Material material =intersection.geometry.getMaterial();
    	int nShininess = material.getNshininess();
    	
    	double kd = material.getKd();
    	double ks = material.getKs();
    	double kr = intersection.getGeometry().getMaterial().getKr();
        double kt = intersection.getGeometry().getMaterial().getKt();
        double kkr = k * kr;
        double kkt = k * kt;
        
    	for (LightSource lightSource : _scene.getLights()) {
    		Vector l = lightSource.getL(intersection.getPoint());
    		double nl = alignZero(n.dotProduct(l));
    		double nv = alignZero(n.dotProduct(v));
    		if (nl*nv > 0) {
    			double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color ip = lightSource.getIntensity(intersection.getPoint()).scale(ktr);
                    color = color.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n,  v, nShininess, ip));
                }
    		}
    	}
    	
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(intersection.getPoint(), inRay, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(intersection.getPoint(), inRay, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }
    
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getVector(), n);
    }

    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
        Vector v = inRay.getVector();
        double vn = v.dotProduct(n);
        if (vn == 0) {
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }

    
    /**
	 * Find intersections of a ray with geometries and get the
	 * closest intersection point to the ray head. 
	 * If there are no intersections, return null
     * @param ray
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D rayHead = ray.getPoint();

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
        if (intersections == null)
            return null;

        for (GeoPoint geoPoint : intersections) {
            double distance = rayHead.distance(geoPoint.getPoint());
            if (distance < closestDistance) {
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }
    
    private Color calcSpecular(double ks, Vector l, Vector n,  Vector V, int nShininess, Color ip) {
        double p = nShininess;
        double nl = n.dotProduct(l);
        if (isZero(nl)) {
            throw new IllegalArgumentException("nl cannot be Zero for scaling the normal vector");
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
    
    /**
     * Return true if a point is unshaded by another geometries
     * @param l-vector
     * @param n-vector
     * @param geopoint
     * @return
     */ 
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D point = geopoint.getPoint();
    	List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
    	
    	if (intersections==null) return true;
    	
    	double lightDistance = light.getDistance(geopoint.point);
    	
    	for (GeoPoint gp : intersections) 
    	{
    		if ((alignZero(gp.getPoint().distance(point) - lightDistance) <= 0)
    				&& (geopoint.getGeometry().getMaterial().getKt()==0))     			
    			return false;    	
    	}
    	return true;
    	
    }
    
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) 
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D pointGeo = geopoint.getPoint();

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) {
            return 1d;
        }
        double lightDistance = light.getDistance(pointGeo);
        double ktr = 1d;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0) {
                ktr *= gp.getGeometry().getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) {
                    return 0.0;
                }
            }
        }
        return ktr;
    	
    }
    /**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Avital & Tal
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				target.row = this.row;
		        target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}
	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
	}
}