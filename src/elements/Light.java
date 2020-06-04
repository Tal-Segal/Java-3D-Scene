package elements;

import primitives.Color;
/**
 * Light class that every specific light extends
 * @author Avital & Tal
 * _intensity - Color type
 */
abstract class Light {
	
	
	protected Color _intensity;
	
	
	
	/**
	 * Constructor with color
	 * @param c
	 */
	public Light(Color c){_intensity=c;}
	
	
	/**
	 * Get the intensity
	 * @return Color
	 */
	public Color getIntensity() {return _intensity;}
}
