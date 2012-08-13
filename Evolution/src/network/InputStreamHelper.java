package network;
import java.io.IOException;
import java.io.InputStream;


public class InputStreamHelper {
	public static byte[] getBytesFromInputStream(InputStream input){
		try {
			byte[] bytes = null;
			byte[] tmpBytes = new byte[1024];
			int num;
			while ((num = input.read(tmpBytes)) != -1) {
				if (bytes == null) {
					bytes = new byte[num];
					System.arraycopy(tmpBytes, 0, bytes, 0, num);
				} else {
					byte[] oldBytes = bytes;
					bytes = new byte[oldBytes.length + num];
					System.arraycopy(oldBytes, 0, bytes, 0, oldBytes.length);
					System.arraycopy(tmpBytes, 0, bytes, oldBytes.length, num);
				}
			}
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
