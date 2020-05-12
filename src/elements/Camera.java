package elements;

import primitives.*;
import static primitives.Util.*;
/**
 * Class Camera is the class representing a scene's camera
 * 
 * @author Avital & Tal
 */
public class Camera {
	/**
     * The point value is the start location and 3 vectors are spreading 3D space
     */
	Point3D p;
	Vector Vup;
	Vector Vto;
	Vector Vright;
	/**
     * point value getter
     * 
     * @return point3D value
     */
	public Point3D getPoint() {return p;}
	/**
     * up-vector value getter
     * 
     * @return vector value
     */
	public Vector getUp() {return Vup;}
	/**
     * to-vector value getter
     * 
     * @return vector value
     */
	public Vector getTo() {return Vto;}
	/**
     * right-vector value getter
     * 
     * @return vector value
     */
	public Vector getRight() {return Vright;}
	
    /**
     * Camera constructor receiving 2 vectors and point values
     * 
     * @param _p Point3D value,up Vector value, to Vector value 
     * @throws Exception 
     */
	public Camera(Point3D _p, Vector to, Vector up)
	{
		try {
		p = _p;
		Vup = up.normalized();
		Vto = to.normalized();
		Vright = (to.crossProduct(up)).normalize();
		}
		catch(Exception e) {System.out.println("Vectors are not parallel");}


	}
	
	/**
     * constructRayThroughPixel function receiving pixel's width and height, screen's width and height, 
     * specific pixel location and distance from screen
     * 
     * @param nX int value, nY int value, j int value, i int value, 
     * screenDistance double value, screenWidth double value, screenHeight double value
     * 
     *  @return ray
     */
	
	public Ray constructRayThroughPixel (int nX, int nY,int j, int i, double screenDistance,double screenWidth, double screenHeight)
	{
		Point3D Pc = new Point3D(p.add(Vto.scale(screenDistance)));
		double Ry = screenHeight / nY;
		double Rx = screenWidth / nX;
		
		double yi = (i - nY / 2d)*Ry + Ry / 2d;
		double xj = (j - nX / 2d)*Rx + Rx / 2d;
		
		Point3D Pij = Pc;

        if (!isZero(xj)) {
            Pij = Pij.add(Vright.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.add(Vup.scale(-yi));
        }
        
        Vector Vij=Pij.subtract(p);
        return new Ray(Vij,p);
        
	}

	
	
}
