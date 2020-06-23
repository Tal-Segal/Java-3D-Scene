package unittests;

import org.junit.Test;

import elements.*;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author Avital & Tal
 *
 */
public class ReflectionRefractionTests {

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3,  0.3, 0, 100), 50,
						new Point3D(0, 0, 50)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

		scene.addLights(new SpotLight( new Vector(-1, 1, 2), new Point3D(-100, 100, -500), 1,
				0.000001, 0.0000001,new Color(1000, 600, 0)));

		ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(10000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.addGeometries(
				new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 0.5, 0, 20), 400, new Point3D(-950, 900, 1000)),
				new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 1, 0), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
				new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0.5, 0), new Point3D(1500, 1500, 1500),
						new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

		scene.addLights(new SpotLight(new Vector(-1, 1, 4), 
				   new Point3D(-750, 750, 150), 1, 0.000001, 0.0000001,new Color(1020, 400, 400)));

		ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
		
   
		
	}
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 0.6, 0, 30), // )
						30, new Point3D(60, -50, 50)));

		scene.addLights(new SpotLight(//
				new Vector(0, 0, 1), new Point3D(60, -50, 0), 1, 4E-5, 2E-7,
				new Color(700, 400, 400)));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 *Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
	 *  producing partial shadow 
	 */
	@Test
	public void trianglesTransparentSphere2() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(1, 1, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 65), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2,  0.6, 0, 30), // )
						30, new Point3D(65, -50, 50)));

		scene.addLights(new SpotLight( //
				new Vector(0, 0, 1), new Point3D(60, -50, 0), 1, 4E-5, 2E-7,
				new Color(700, 350, 400)));

		ImageWriter imageWriter = new ImageWriter("shadow with transparency2", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
	
	
	
	
	
	
	@Test
	public void trianglesSphere() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(10, -10, -1000), new Vector(0, 0, 50), new Vector(50, -100, -20)));
		scene.setDistance(600);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 0.6, 0, 30), 
						30, new Point3D(60, -50, 50)),
				new Sphere(new Color(java.awt.Color.MAGENTA), new Material(0.5, 0.5, 70), 
						30, new Point3D(0, 0, 115)),
				new Sphere(new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 0.5, 0, 50), 15, new Point3D(0, -100, 70)),
				new Sphere(new Color(java.awt.Color.GREEN), new Material(0.5,0, 0.5,0, 100), 15, new Point3D(50, -80, 90)),
				new Sphere(new Color(java.awt.Color.CYAN), new Material(0.5, 0.5, 100),20, new Point3D(-50, -10, 80)),
				new Triangle(Color.BLACK, new Material(1, 1, 60), 
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), 
				new Triangle(Color.BLACK, new Material(0.5, 0.5, 0, 0.25, 50), 
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)),
				new Sphere(new Color(java.awt.Color.darkGray), new Material(0.2, 0.5, 0.6, 0, 30), 
						15, new Point3D(20, -60, 10)));

		scene.addLights(//new SpotLight(
				//new Vector(-1, 1, 4), new Point3D(40, -40, -115), 1, 0.000001, 0.00000001
				//,new Color(700, 400, 400)));
				new SpotLight(new Vector(-1, 2, 3), new Point3D(70, -70, -60), 1, 0.000001, 0.00000001
						,new Color(java.awt.Color.WHITE)),
				new DirectionalLight(new Vector(-10,20,30),new Color(java.awt.Color.BLACK)),
				new PointLight(new Point3D(-50, 50, 100),2.5, 0.0000001, 0.0000000001
						,new Color(java.awt.Color.WHITE)));

		ImageWriter imageWriter = new ImageWriter("Final", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
}
