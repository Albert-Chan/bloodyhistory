package util;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class LogInjector {
	
	static Logger perfLog;
	static 
	{
		try
		{
			String logFileName = System.getenv( ).get( "PERFLOG" );
			perfLog = Logger.getAnonymousLogger( );
			perfLog.setLevel( Level.ALL );
			FileHandler fileHandler = new FileHandler( logFileName );
			fileHandler.setLevel( Level.INFO );
			fileHandler.setFormatter( new Formatter( ) {

				public String format( LogRecord record )
				{
					return record.getMessage( );
				}
			} );
			perfLog.addHandler( fileHandler );
		}
		catch ( SecurityException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logPerformance( String msg )
	{
		perfLog.info( msg );
	}
}
