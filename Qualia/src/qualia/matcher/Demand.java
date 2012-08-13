package qualia.matcher;

import java.util.ArrayList;

public class Demand
{

	/**
	 * The thresholds sorted by preference
	 */
	ArrayList<Threshold> thresholds;

	public float match( User user )
	{
		float score = -1;
		for ( Threshold threshold : thresholds )
		{
			Factor factor = user.factors.get( threshold.attribute );
			if ( threshold.must )
			{
				if ( factor == null )
				{
					return -1;
				}
			}
			score += threshold.evaluate( factor );
		}
		return score;
	}
}
