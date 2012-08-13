package qualia.matcher;


public class Threshold
{
	String attribute;
	Object value;
	
	/**
	 * a must or just an option.
	 */
	boolean must;
	
	float preference;
	
	public float evaluate( Factor factor )
	{
		return preference * Evaluater.rate( factor );
	}
}