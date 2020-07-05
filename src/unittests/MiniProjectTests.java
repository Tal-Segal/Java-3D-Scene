package unittests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import K_Means.Cluster;
import K_Means.Point;
import K_Means.k_means;
import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class MiniProjectTests {
	
	/**
	 *Produce the final picture with manual distribution to boxes
	 */
	/*
	@Test
	public void FinalImage1() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(10, -10, -1000), new Vector(0, 0, 50), new Vector(50, -100, -20)));
		scene.setDistance(600);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		Sphere A =new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 0.6, 0, 30), 
						30, new Point3D(60, -50, 50));
		Sphere B=new Sphere(new Color(java.awt.Color.MAGENTA), new Material(0.5, 0.5, 70), 
						30, new Point3D(0, 0, 115));
		Sphere C=new Sphere(new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 0.5, 0, 50), 15, new Point3D(0, -100, 70));
		Sphere E=new Sphere(new Color(java.awt.Color.GREEN), new Material(0.5,0, 0.5,0, 100), 15, new Point3D(50, -80, 90));
		Sphere G=new Sphere(new Color(java.awt.Color.CYAN), new Material(0.5, 0.5, 100),20, new Point3D(-50, -10, 80));
		Triangle D=	new Triangle(Color.BLACK, new Material(1, 1, 60), 
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150));
		Triangle F=	new Triangle(Color.BLACK, new Material(0.5, 0.5, 0, 0.25, 50), 
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150));
		Sphere H=new Sphere(new Color(java.awt.Color.darkGray), new Material(0.2, 0.5, 0.6, 0, 30), 
						15, new Point3D(20, -60, 10));
		
		
		Geometries geos1 = new Geometries(A, B, C);
		Geometries geos2 = new Geometries(D, E, G);
		Geometries geos3 = new Geometries(geos2, F, geos1, H);
		Geometries geos=new Geometries(geos2, geos3,geos1);
			
		scene.addGeometries(geos);
		
		scene.addLights(
				new SpotLight(new Vector(-1, 2, 3), new Point3D(70, -70, -60), 1, 0.000001, 0.00000001
						,new Color(java.awt.Color.WHITE)),
				new DirectionalLight(new Vector(-10,20,30),new Color(java.awt.Color.BLACK)),
				new PointLight(new Point3D(-50, 50, 100),2.5, 0.0000001, 0.0000000001
						,new Color(java.awt.Color.WHITE)));

		ImageWriter imageWriter = new ImageWriter("Final1", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
*/
	/**
	 * Produce final picture with Bounding Volume Hierarchy
	 */
	@Test
	public void FinalImage2() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(10, -10, -1000), new Vector(0, 0, 50), new Vector(50, -100, -20)));
		scene.setDistance(600);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		//Create 10 special spheres and triangles
		Sphere A =new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 0.6, 0, 30), 
						30, new Point3D(60, -50, 50));
		Sphere B=new Sphere(new Color(java.awt.Color.MAGENTA), new Material(0.5, 0.5, 70), 
						30, new Point3D(0, 0, 115));
		Sphere C=new Sphere(new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 0.5, 0, 50), 15, new Point3D(0, -100, 70));
		Sphere E=new Sphere(new Color(java.awt.Color.GREEN), new Material(0.5,0, 0.5,0, 100), 15, new Point3D(50, -80, 90));
		Sphere G=new Sphere(new Color(java.awt.Color.CYAN), new Material(0.5, 0.5, 100),20, new Point3D(-50, -10, 80));
		Triangle D=	new Triangle(Color.BLACK, new Material(1, 1, 60), 
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150));
		Triangle F=	new Triangle(Color.BLACK, new Material(0.5, 0.5, 0, 0.25, 50), 
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150));
		Sphere H=new Sphere(new Color(java.awt.Color.darkGray), new Material(0.2, 0.5, 0.6, 0, 30), 
						15, new Point3D(20, -60, 10));
		Sphere I=new Sphere(new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 70), 
				10, new Point3D(0, 60, 115));
		Sphere J=new Sphere(new Color(java.awt.Color.red), new Material(0.5, 0.5, 70), 
				10, new Point3D(20, 60, 115));
		
		//Bounding Volume Hierarchy
		List<Point> points=new ArrayList<Point>();
		points.add(new Point(A));
		points.add(new Point(B));
		points.add(new Point(C));
		points.add(new Point(D));
		points.add(new Point(E));
		points.add(new Point(F));
		points.add(new Point(G));
		points.add(new Point(H));
		points.add(new Point(I));
		points.add(new Point(J));
		
		//Create 25 more spheres
		Random r= new Random();
		Color Co = new Color();
		for (int i=0; i<25; i++) {
			if (i%2==0) Co = new Color(java.awt.Color.CYAN);
			if (i%3==0) Co = new Color(java.awt.Color.RED);
			if (i%5==0) Co = new Color(java.awt.Color.MAGENTA);
			if (i%6==0) Co = new Color(java.awt.Color.BLUE);
			if (i%7==0) Co = new Color(java.awt.Color.GREEN);
			if (i%9==0) Co = new Color(java.awt.Color.ORANGE);
			Sphere X = new Sphere(Co, new Material(0.5,0.4,70), 
			r.nextInt(10)+5, new Point3D(r.nextInt(160)-50, r.nextInt(200)-80, r.nextInt(40)-30));
			points.add(new Point(X));
		}
		
		k_means Kmeans = new k_means();
        Kmeans.init(points);
        Kmeans.calculate(); 
        List<Cluster> clusters=Kmeans.getClusters(); //Divide all the geometries into 3 clusters
        for(Cluster c: clusters) { //Put all of the geometries in this cluster in one box
        	Geometries geos = new Geometries();       	
        	for(Point p: c.getPoints()) {
        		geos.add(p.getGeometry());
        	}
        	scene.addGeometries(geos);
        	
        }
		scene.addLights(
				new SpotLight(new Vector(-1, 2, 3), new Point3D(70, -70, -60), 1, 0.000005, 0.00000005
						,new Color(java.awt.Color.WHITE)),
				new DirectionalLight(new Vector(-10,20,30),new Color(java.awt.Color.BLACK)),
				new PointLight(new Point3D(-30, 70, 140),2.5, 0.0000005, 0.0000000005
						,new Color(java.awt.Color.BLUE)),
				new PointLight(new Point3D(-50, 50, 100),2.5, 0.0000005, 0.0000000005
						,new Color(java.awt.Color.YELLOW)));

		ImageWriter imageWriter = new ImageWriter("Final2", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	
	}
}
