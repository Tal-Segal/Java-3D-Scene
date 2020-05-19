package scene;

import elements.*;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {
    private final String _name;
    private final Geometries _geometries = new Geometries();

    private Color _background;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;
    public AmbientLight getAmbientLight() {
        return _ambientLight;
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

    public Scene(String _sceneName) {
        this._name = _sceneName;
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
    
    public void addGeometries(Intersectable... intersectables) {
        for (Intersectable i : intersectables) {
            _geometries.add(i);
        }
    }

}
