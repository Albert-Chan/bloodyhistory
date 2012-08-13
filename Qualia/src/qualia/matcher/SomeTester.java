package qualia.matcher;

import java.io.FileOutputStream;

public class SomeTester
{
	public static void main(String[] args)
	{
		try
		{
			throwExceptions();
		}
		catch ( Exception e )
		{
			System.out.println("caught" + e);	
		}
		System.out.println("end");
	}
	
	public static void throwExceptions() throws Exception
	{
		FileOutputStream out = new FileOutputStream("D:/someeee.txt");
		out.write( 111 );
		out.close( );
	}
}
