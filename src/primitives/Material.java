package primitives;

public class Material {
	public double _kD;
	public double _kS;
	public int _nShininess;
	
	/**
	 * Constructor with 2 doubles and int- shininess
	 * @param kd
	 * @param ks
	 * @param n
	 */
	public Material(double kd, double ks, int n) {
		_kD=kd;
		_kS=ks;
		_nShininess=n;
	}
	 /**
	  * Get the kD
	  * @return double
	  */
	public double getKd()
	{
		return _kD;
	}
	/**
	  * Get the kS
	  * @return double
	  */
	public double getKs()
	{
		return _kS;
	}
	/**
	  * Get the shininess
	  * @return int
	  */
	public int getNshininess()
	{
		return _nShininess;
	}
}
