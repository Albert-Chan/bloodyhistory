package data.crawler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	protected static final SimpleDateFormat DATE_FORMAT_YMD = new SimpleDateFormat(
			"yyyy-MM-dd");

	protected static final SimpleDateFormat DATE_FORMAT_YMDHMS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:SS");

	private static final int MAX_CONNECTION = 16;

	private static String baseDir = null;

	public static void main(String[] args) {
		ThreadPoolTest crawler = new ThreadPoolTest();
		crawler.start();
	}

	public void start() {
		Timer timer = new Timer();
		// every 12h
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		timer.schedule(new TaskThread(), calendar.getTime(), 12 * 3600 * 1000);
	}

	class TaskThread extends TimerTask {

		public void run() {
			doRun(1);
			// sleep 10mS
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			doRun(2);
		}

		private void doRun( int id )
		{
			System.out.println("========================================================Startting up " + id +"\t" + DATE_FORMAT_YMDHMS.format(new Date()));
			ExecutorService executor = Executors
					.newFixedThreadPool(MAX_CONNECTION);

			for (int i = 0 ; i< 1000; i++) {
				executor.execute(new TestThread(id, i));
			}
			
			executor.shutdown();
			System.out.println("========================================================Shutting down " + id +"\t" + DATE_FORMAT_YMDHMS.format(new Date()));
		}
		
	}
	
	class TestThread extends Thread
	{
		int id;
		int i;
		TestThread(int id, int i)
		{
			this.id= id;
			this.i= i;
		}
		public void run()
		{
			System.out.println("TestThread " + i + " in " + id);
		}
	}

}
