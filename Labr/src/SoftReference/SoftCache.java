package SoftReference;
import java.lang.ref.*;
import java.util.Vector;

public class SoftCache {
	Vector vector = null;
	Thread remover;
	ReferenceQueue clearedRefs;

	public SoftCache() {
		vector = new Vector();
		clearedRefs = new ReferenceQueue();
		// start thread to delete cleared references from the cache
		remover = new Remover(clearedRefs, vector);
		remover.start();
	}

	public void put(Object o) {
		synchronized (vector) {
			vector.addElement(new SoftReference(o, clearedRefs));
		}
	}

	public Object get() {
		synchronized (vector) {
			if (vector.size() > 0) {
				SoftReference sr = (SoftReference)vector.remove(0);
				return sr.get();
			}
		}
		return null;
	}

	private class Remover extends Thread {
		ReferenceQueue refQ;
		Vector cache;

		public Remover(ReferenceQueue rq, Vector v) {
			super();
			refQ = rq;
			cache = v;
			setDaemon(true);
		}

		public void run() {
			try {
				while (true) {
					Object o = refQ.remove();
					synchronized (cache) {
						cache.removeElement(o);
						System.out.println("Removing " + o);
					}
				}
			} catch (InterruptedException e) {
				;
			}
		}
	}
}
