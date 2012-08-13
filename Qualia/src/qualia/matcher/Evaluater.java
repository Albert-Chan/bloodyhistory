package qualia.matcher;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Evaluater
{
	/**
	 * Key: the factor attribute
	 * Value: the value TreeMap
	 */
	static HashMap<String, TreeMap<Object, Integer>> distribution = new HashMap<String, TreeMap<Object, Integer>>( );
	
	static HashMap<String, Integer> population = new HashMap<String, Integer>( );
	
	static HashMap<String, TreeMap<Object, Float>> index;
	
	/**
	 * Key: the factor value
	 * Value: the value occurrence counter.
	 */
	//TreeMap<Object, Integer> factorSorter;
	
	public static synchronized void add( Factor factor )
	{
		TreeMap<Object, Integer> factorSorter;
		if ( !distribution.containsKey( factor.attribute ) )
		{
			factorSorter = new TreeMap<Object, Integer>( );
			distribution.put( factor.attribute, factorSorter );
			population.put( factor.attribute, 0 );
		}
		else
		{
			factorSorter = distribution.get( factor.attribute );
		}

		int counter = 0;
		Integer iCounter = factorSorter.get( factor.value );
		if ( iCounter != null )
		{
			counter = factorSorter.get( factor.value ).intValue( );
		}
		factorSorter.put( factor.value, counter + 1 );
		population.put( factor.attribute,
				population.get( factor.attribute ) + 1 );
	}

	/**
	 * The precision is the 5% of the population. 
	 * @param attribute
	 */
	public static synchronized void index( String attribute )
	{
		TreeMap<Object, Integer> factorSorter;
		if ( !distribution.containsKey( attribute ) )
		{
			// log?
			return;
		}
		else
		{
			factorSorter = distribution.get( attribute );
			int precision = (int) ( factorSorter.size( ) * 0.05 );
			if ( precision <= 0 )
			{
				precision = 1;
			}
			int number = 0;
			int times = 1;
			TreeMap<Object, Float> thumbs = new TreeMap<Object, Float>( );
			index.put( attribute, thumbs );
			for ( Iterator<Object> i = factorSorter.keySet( ).iterator( ); i
					.hasNext( ); )
			{
				Object key = i.next( );
				number += factorSorter.get( key );
				while ( number >= precision * times )
				{
					thumbs.put( key, number / (float) population.get( attribute ) );
					times++;
				}
			}
		}
	}
	
	public static synchronized float rate( Factor factor )
	{
		TreeMap<Object, Float> thumbs = index.get( factor.attribute );
		Entry<Object, Float> cap = thumbs.ceilingEntry( factor.value );
		Entry<Object, Float> bottom = thumbs.floorEntry( factor.value );
		cap.getKey( );
		bottom.getKey( );
		TreeMap<Object, Integer> factorSorter = distribution.get( factor.attribute );
		
		int count = 0;
		for ( Iterator<Object> i = factorSorter
				.subMap( bottom.getKey( ), factor.value ).keySet( ).iterator( ); i
				.hasNext( ); )
		{
			Object key = i.next( );
			count += factorSorter.get( key );
		}
		
		// size * p(bottom) + Sigma( count ) > size* p(cap)
		int size = population.get( factor.attribute );
		if ( bottom.getValue( )* size + count > size * cap.getValue( ) )
		{
			index( factor.attribute );
			return rate( factor );
		}
		else
		{
			return cap.getValue( ) + bottom.getValue( ) / 2; 
		}
	}
}

//class PervasiveComparator<Object> implements Comparator
//{
//}
