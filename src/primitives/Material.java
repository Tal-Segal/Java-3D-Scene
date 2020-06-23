package primitives;

public class Material {
	public double _kD;
	public double _kS;
	public double _kT;
	public double _kR;
	public int _nShininess;
	
	/**
	 * Constructor with 2 doubles and int- shininess
	 * @param kd
	 * @param ks
	 * @param n
	 */
	public Material(double kd, double ks, int n) {
		this(kd,ks,0,0,n);
	}
	public Material(double kd, double ks,double kt,double kr ,int n) {
		_kD=kd;
		_kS=ks;
		_nShininess=n;
		_kT=kt;
		_kR=kr;
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
	 * Get the kR
	 * @return double
	 */
	public double getKr()
	{
		return _kR;
	}
	/**
	 * Get the kT;
	 * @return double
	 */
	public double getKt()
	{
		return _kT;
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
