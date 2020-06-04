package elements;

import primitives.Color;

public class AmbientLight extends Light {
	
	/**
	 * Ambient Light constructor accepting color intensity and double Attenuation coefficient parameters,
	 * @param _intensity Color type
	 * @param ka double type
	 */
    public AmbientLight(Color Ia, double ka) {
        super(Ia.scale(ka));
    }


}