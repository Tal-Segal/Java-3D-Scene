package elements;

import primitives.Color;

public class AmbientLight {
	
	Color Ia;
	double ka;
	
	protected Color _intensity;
	
	// ***************** Constructor ********************** //
	/**
	 * Ambient Light constructor accepting color intensity and double Attenuation coefficient parameters,
	 * @param _intensity Color type
	 * @param ka double type
	 */
    public AmbientLight(Color _intensity, double ka) {
        this._intensity = _intensity.scale(ka);
    }

    public Color getIntensity() {
        return new Color(_intensity);
    }

}