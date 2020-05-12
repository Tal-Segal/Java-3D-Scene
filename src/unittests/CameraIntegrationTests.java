package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.*;
import primitives.*;


public class CameraIntegrationTests {

	
    Camera cam1 = new Camera(Vector.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
	
	
    @Test
    public void constructRayThroughPixelWithSphere1()  {
        
        Sphere sph =  new Sphere(1, new Point3D(0, 0, 3));

        int count = 0;
        //number of pixels
        int Nx =3;
        int Ny =3;
        //construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                Ray ray = cam1.constructRayThroughPixel(3,3,j,i,1,3,3);
                List<Point3D> results = sph.findIntersections(ray);
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",2,count);
        System.out.println("count: "+count);

    }

    @Test
    public void constructRayThroughPixelWithSphere2() {
        Sphere sph =  new Sphere(2.5, new Point3D(0, 0, 2.5));

        List<Point3D> results;
        int count = 0;
        // number of pixels 
        int Nx =3;
        int Ny =3;

        // construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",18,count);
        System.out.println("count: "+count);
    }

    @Test
    public void constructRayThroughPixelWithSphere3() {
        Sphere sph =  new Sphere(2, new Point3D(0, 0, 2));

        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        // construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",10,count);
        System.out.println("count: "+count);
    }

    @Test
    public void constructRayThroughPixelWithSphere4() { //decide which camera
        Sphere sph =  new Sphere(4, new Point3D(0, 0, -0.5));

        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        // construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",9,count);
        System.out.println("count: "+count);
    }
    
    @Test
    public void constructRayThroughPixelWithSphere5() { //decide which camera
        Sphere sph =  new Sphere(0.5, new Point3D(0, 0, -1));

        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        // construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",0,count);
        System.out.println("count: "+count);
    }

    @Test
    public void constructRayThroughPixelWithPlane1() { //decide which camera
        Plane pl =  new Plane(new Point3D(0,0,5), new Point3D(0,-3,5), new Point3D(3,-3,5));
        
        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        // construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = pl.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",9,count);
        System.out.println("count: "+count);
    }
    
    
    @Test
    public void constructRayThroughPixelWithPlane2() { //decide which camera
    	Plane pl =  new Plane(new Point3D(0,0,2), new Vector(0,1,4));
        
        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        //construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = pl.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",9,count);
        System.out.println("count: "+count);
    }
    
    
    @Test
    public void constructRayThroughPixelWithPlane3() { //decide which camera
    	Plane pl =  new Plane(new Point3D(0,0,5), new Vector(0,4,1));
        
        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        //construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = pl.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",6,count);
        System.out.println("count: "+count);
    }
    
    @Test
    public void constructRayThroughPixelWithTriangle1() { //decide which camera
        Triangle t = new Triangle(new Point3D(0,-1,2), new Point3D(1,1,2), new Point3D(-1,1,2));
        
        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        // construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = t.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",1,count);
        System.out.println("count: "+count);
    }
    
    @Test
    public void constructRayThroughPixelWithTriangle2() { //decide which camera
        Triangle t = new Triangle(new Point3D(0,-20,2), new Point3D(1,1,2), new Point3D(-1,1,2));
        
        List<Point3D> results;
        int count = 0;
        // number of pixels
        int Nx =3;
        int Ny =3;

        // construct ray throw every pixel on the screen
        for (int i = 0; i < Nx; ++i) {
            for (int j = 0; j < Ny; ++j) {
                results = t.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad",2,count);
        System.out.println("count: "+count);
    }
}
