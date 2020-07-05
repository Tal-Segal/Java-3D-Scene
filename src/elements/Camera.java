package elements;

import primitives.*;
import static primitives.Util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
		catch(Exception e) {System.out.println("Vectors are not orthogonal");}


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
		//reach the screen
		Point3D Pc = new Point3D(p.add(Vto.scale(screenDistance)));
		
		//height and width of a single pixel
		double Ry = screenHeight / nY;
		double Rx = screenWidth / nX;
		//center of pixel:
		//j is the pixel column
		//i is the pixel row
		double yi = (i - nY / 2d)*Ry + Ry / 2d; //go to the beginning of pixel, then to the center
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
	//SuperSampling
	public List<Ray> constructRaysThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth,
			double screenHeight, int raysAmount)
	{
		List<Ray> Rays = new ArrayList<Ray>();
		int numOfRays = (int)Math.floor(Math.sqrt(raysAmount)); //number of rays in each row or column
		
		if (numOfRays==1) return List.of(constructRayThroughPixel(nX, nY, j, i, screenDistance, screenWidth, screenHeight));
		
		Point3D Pc;
		if (screenDistance!=0)
			Pc = new Point3D(p.add(Vto.scale(screenDistance))); //reach the view plane
		else
			Pc = new Point3D(p);
		
		//height and width of a single pixel
		double Ry = screenHeight / nY;
		double Rx = screenWidth / nX;
		//center of pixel:
		double yi = (i - nY / 2d)*Ry + Ry / 2d;
		double xj = (j - nX / 2d)*Rx + Rx / 2d;
		
		Point3D Pij = Pc;
		
        if (xj != 0)
        {
        	Pij = Pij.add(this.Vright.scale(-xj)); //move Pij to width center
        }
        if (yi != 0)
        {
        	Pij = Pij.add(this.Vup.scale(-yi)); //move Pij to height center
        }
        Vector Vij=Pij.subtract(p);
        Rays.add(new Ray(Vij,p)); //the original vector
        
        double PRy = Ry / numOfRays; //height distance between each ray
        double PRx = Rx / numOfRays; //width distance between each ray
        
        Point3D tmp = Pij; //center
        
        //creating a grid in the pixel:
        for (int row=0; row<numOfRays; row++) {
        	double Pxj = (row - (numOfRays/2d)) * PRx + PRx/2d;//the distance to move on x, and its whole column
        	for (int column=0; column<numOfRays; column++) {
        		double Pyi = (column - (numOfRays/2d)) * PRy + PRy/2d;
        		if (Pxj != 0)
        			Pij = Pij.add(this.Vright.scale(-Pxj));
        		if (Pyi != 0)
        			Pij = Pij.add(this.Vup.scale(-Pyi)); 
        		Rays.add(new Ray(Pij.subtract(p), p));
        		Pij = tmp; //restart
        	}
        }
        return Rays;
	}
	
	
	
}
