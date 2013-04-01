package network;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ReferenceTest {

	CyclicBarrier barrier;

	public static void main(String[] args) {
		ReferenceTest rt = new ReferenceTest();
		rt.go();
	}

	public void go() {
		String context = "1";
		Context c = new Context("uninitialzed.");

		int N = 2;
		barrier = new CyclicBarrier(N, new BarrierDeamon(c.str));
		for (int i = 0; i < N; ++i)
			new Thread(new Worker(c, context)).start();
		System.out.println("end");
	}

	class BarrierDeamon implements Runnable {
		Context c;
		String contextString;

		BarrierDeamon(/*Context c, */String contextString) {
			//this.c = c;
			this.contextString = contextString;
		}

		public void run() {
			
			System.out.println(contextString);
		}
	}

	class Worker implements Runnable {
		Context c;
		String contextString;

		Worker(Context c, String contextString) {
			this.c = c;
			this.contextString = contextString;
		}

		public void run() {
			// while (!done()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.str = "processed.";
			contextString = "str processed.";
			try {
				barrier.await();
			} catch (InterruptedException ex) {
				return;
			} catch (BrokenBarrierException ex) {
				return;
			}
			// }
		}
	}
}

class Context {
	String str = null;

	Context(String str) {
		this.str = str;
	}

	public void print() {
		System.out.println(str);
	}
}
