package SoftReference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCacheTriggerGC {

	ConcurrentHashMap<String, SoftReference<Cacheable>> cacheMap = new ConcurrentHashMap<String, SoftReference<Cacheable>>();
	Thread remover;
	ReferenceQueue<Cacheable> clearedRefs;

	public SoftCacheTriggerGC() {

		clearedRefs = new ReferenceQueue<Cacheable>();
		// start thread to delete cleared references from the cache
		// remover = new Remover(clearedRefs, cacheMap);
		// remover.start();
	}

	public void put(String key, Cacheable value) {
		cacheMap.put(key, new SoftReference<Cacheable>(value));
	}

	public Cacheable get(String key) {
		SoftReference<Cacheable> sr = cacheMap.get(key);
		if (sr != null)
			return sr.get();
		return null;
	}

	public static void main(String[] args) {
		SoftCacheTriggerGC softCacheGC = new SoftCacheTriggerGC();
		Random r = new Random();
		int count = 0;
		while (true) {
			String i = String.valueOf(r.nextInt(1000000));
			Cacheable o = softCacheGC.get(i);
			if (o == null) {
				o = new Cacheable(i);
				softCacheGC.put(i, o);
				o = null;
				//System.out.println("create " + i);
			} else {
				//System.out.println("hit " + o.key);
			}
			System.out.println(count++);
		}

	}

	// private class Remover extends Thread {
	// ReferenceQueue<Cacheable> refQ;
	// ConcurrentHashMap<String, Cacheable> cache;
	//
	// public Remover(ReferenceQueue<Cacheable> rq,
	// ConcurrentHashMap<String, Cacheable> v) {
	// super();
	// refQ = rq;
	// cache = v;
	// setDaemon(true);
	// }
	//
	// public void run() {
	// try {
	// while (true) {
	// Object o = refQ.remove();
	// synchronized (cache) {
	// cache.removeElement(o);
	// System.out.println("Removing " + o);
	// }
	// }
	// } catch (InterruptedException e) {
	// ;
	// }
	// }
	// }
}

class Cacheable {
	String key;
	byte[] content = new byte[64];

	Cacheable(String key) {
		this.key = key;
	}
}
