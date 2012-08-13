package qualia.matcher;
import java.util.HashMap;

public class Matcher
{
	HashMap<String, Demand> demands;
	
	public Matcher( HashMap<String, Demand> demands )
	{
		this.demands = demands;
	}
	
	public void match( User user )
	{
//		for ( Factor factor : user.factors )
//		{
//			Demand demand = demands.get( factor.attribute );
//			if ( demand != null )
//			{
//				demand.evaluate( factor );
//			}
//		}
	} 
	
}







