package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Testing basic shadows
 * 
 * @author Dan
 */
public class ShadowTests {

	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 */
	@Test
	public void SphereTriangleInitial() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), 
				60, new Point3D(0, 0, 200)), 
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), 
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Vector(1, -1, 3), new Point3D(-100, 100, -200), 
				1, 0.000001, 0.00000001, new Color(400, 240, 0)));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleInitial", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Sphere-Triangle shading - move triangle up-right
	 */
	@Test
	public void SphereTriangleMove1() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-60, 30, 0), new Point3D(-30, 60, 0), new Point3D(-58, 58, 4)));

		scene.addLights(new SpotLight(
				new Vector(1, -1, 3), new Point3D(-100, 100, -200), 1, 0.000001, 0.00000001, new Color(400, 240, 0)));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleMove1", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Sphere-Triangle shading - move triangle upper-righter
	 */
	@Test
	public void SphereTriangleMove2() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-50, 20, 0), new Point3D(-20, 50, 0), new Point3D(-48, 48, 4)));

		scene.addLights(new SpotLight(new Vector(1, -1, 3), new Point3D(-100, 100, -200), 1, 0.000001, 0.00000001
				,new Color(400, 240, 0)));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleMove2", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Sphere-Triangle shading - move spot closer
	 */
	@Test
	public void SphereTriangleSpot1() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), 
				60, new Point3D(0, 0, 200)), 
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), 
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(
				new Vector(1, -1, 3), new Point3D(-85, 85, -125),1, 0.000001, 0.00000001
				,new Color(500, 300, 0)));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleSpot1", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}	
	
	/**
	 * Sphere-Triangle shading - move spot even more close
	 */
	@Test
	public void SphereTriangleSpot2() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), 
				60, new Point3D(0, 0, 200)),
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), 
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(
				new Vector(1, -1, 3), new Point3D(-70, 70, -60), 1, 0.000001, 0.00000001
				,new Color(400, 240, 0)));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleSpot2", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}	
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere producing a shading
	 */
	@Test
	public void trianglesSphere() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(Color.BLACK);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( 
				new Triangle(Color.BLACK, new Material(0, 0.8, 60), 
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), 
				new Triangle(Color.BLACK, new Material(0, 0.8, 60), 
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), 
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), 
						30, new Point3D(0, 0, 115)));

		scene.addLights(new SpotLight(
				new Vector(-1, 1, 4), new Point3D(40, -40, -115), 1, 0.000001, 0.00000001
				,new Color(700, 400, 400)));

		ImageWriter imageWriter = new ImageWriter("trianglesSphere", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

}
