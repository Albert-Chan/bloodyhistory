package SoftReference;
import java.lang.ref.SoftReference;

public class SoftRef {

	SoftReference sr = null;

	public SoftReference create() {
		Object obj = new Object();
		sr = new SoftReference(obj);
		obj = null;
		return sr;
	}

	public Object get() {
		Object obj2;
		obj2 = sr.get();
		if (obj2 == null) // GC freed this
			sr = new SoftReference(obj2 = new Object());
		return obj2;
	}

	// the garbage collector could run between the creation of the new Object
	// and the call to get(). obj2 would still be null.
	public Object get2() {
		Object obj2;
		obj2 = sr.get();
		if (obj2 == null) {
			sr = new SoftReference(new Object());
			obj2 = sr.get();
		}
		return obj2;
	}
}
