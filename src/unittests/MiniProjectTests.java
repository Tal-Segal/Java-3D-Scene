package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
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
	 *Produce the final picture
	 */
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
