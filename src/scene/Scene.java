package scene;

import java.util.LinkedList;
import java.util.List;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {

    private final Geometries _geometries;
    private String _name;
    private Color _background;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;
    private List<LightSource> _lights=new LinkedList<LightSource>();
    
    
	/**
	 * Constructor
	 * @param name
	 */
	public Scene(String name) {
		_name = name;
		_geometries= new Geometries();
	}
	
	public String getName() {
		return _name;
	}
	
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public List<LightSource> getLights(){
    	return _lights;
    }
    
    public void addLights(LightSource... lights) { 
    	for (LightSource l : lights) {
            _lights.add(l);
    	}
    }
    
    public Camera getCamera() {
        return _camera;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public double getDistance() {
        return _distance;
    }

    public Color getBackground() {
        return this._background;
    }

    public void setBackground(Color c)
    {
    	this._background=c;
    }
    public void setCamera(Camera cam)
    {
    	this._camera=cam;
    }
    public void setDistance(double d)
    {
    	this._distance=d;
    }
    public void setAmbientLight(AmbientLight al)
    {
    	this._ambientLight=al;
    }
    
    public void setName(String name)
    {
    	this._name=name;
    }
    
    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.add(i);
        }
    }

}
