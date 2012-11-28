package network;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpConnectionTest {
	private String hostName;
	private int port = 80;

	private IResponseHandler responseHandler = null;

	public HttpConnectionTest(String hostName) {
		this.hostName = hostName;
	}

	public HttpConnectionTest(String hostName, int port) {
		this(hostName);
		this.port = port;
	}

	// public void mm() {
	// URL url = new URL("https://myhost.com/index.html");
	// URLConnection conn = url.openConnection();// Retrieve information from
	// // HTTPS: GET
	// InputStream
	// istream =
	// conn.getInputStream();
	// while ((ch = istream.read()) !=
	// -1) {
	// ........}
	// istream.close();
	//
	// URL url = new URL("https://myhost.com/cgi-bin/sendData");
	// URLConnection conn = url.openConnection();// Sending information through
	// // HTTPS: POST
	// OutputStream
	// ostream =
	// conn.getOutputStream();
	// ostream.write(....);
	//
	// ostream.close();
	//
	// }

	public static String getCookie(String cookieValue, String key) {
		String[] cookies = cookieValue.split(";");
		for (String cookie : cookies) {
			String[] splited = cookie.split("=");
			if (key.equals(splited[0])) {
				return cookie;
			}
		}
		return null;
	}

	public static void main1(String[] args) throws Exception {
		String url = "http://uni108.ogame.tw/game/reg/login2.php?login=albert&pass=11111111&kid=&v=2";

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("Host", "uni108.ogame.tw");
		map1.put("Connection", "keep-alive");
		map1.put(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11");
		map1.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// map1.put("Accept-Encoding", "gzip,deflate,sdch");
		map1.put("Accept-Encoding", "deflate,sdch");
		map1.put("Accept-Language",
				"en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2");
		map1.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");

		byte[] bytes = new byte[256];
		InputStream in = null;
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			for (String key : map1.keySet()) {
				conn.setRequestProperty(key, map1.get(key));
			}
			in = conn.getInputStream();

			Map<String, List<String>> m = conn.getHeaderFields();
			for (String key : m.keySet()) {
				System.out.print(key + ": ");
				List<String> list = m.get(key);
				System.out.println(Arrays.deepToString(list.toArray()));
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int size;
			while ((size = in.read(bytes)) != -1) {
				out.write(bytes, 0, size);
			}
			String result = out.toString("utf-8");

			out.close();
			System.out.println(result);
			// return result;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public static void main2(String[] args) throws Exception {

		String sid;

		// 1.
		String url = "http://ogame.tw/";
		HashMap<String, String> map1 = new HashMap<String, String>();

		map1.put("Host", "ogame.tw");
		map1.put("Connection", "keep-alive");
		map1.put(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11");
		map1.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// map1.put("Accept-Encoding", "gzip,deflate,sdch");
		map1.put("Accept-Encoding", "gzip,deflate,sdch");
		map1.put("Accept-Language",
				"en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2");
		map1.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");

		byte[] bytes = new byte[256];
		InputStream in = null;
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			for (String key : map1.keySet()) {
				conn.setRequestProperty(key, map1.get(key));
			}
			in = conn.getInputStream();

			Map<String, List<String>> m = conn.getHeaderFields();
			for (String key : m.keySet()) {
				System.out.print(key + ": ");
				List<String> list = m.get(key);
				System.out.println(Arrays.deepToString(list.toArray()));
			}

			sid = getCookie(conn.getHeaderField("set-cookie"), "SID");

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int size;
			while ((size = in.read(bytes)) != -1) {
				out.write(bytes, 0, size);
			}
			String result = out.toString("utf-8");

			out.close();
			// System.out.println(result);
			// return result;
		} finally {
			if (in != null) {
				in.close();
			}
		}

		// 2.
		url = "http://ogame.tw/ajax/main/index/";

		map1.put("Host", "ogame.tw");
		map1.put("Connection", "keep-alive");
		map1.put(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11");
		map1.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		// map1.put("Accept-Encoding", "gzip,deflate,sdch");
		map1.put("Accept-Encoding", "deflate,sdch");
		map1.put("Accept-Language",
				"en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2");
		map1.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");

		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			for (String key : map1.keySet()) {
				conn.setRequestProperty(key, map1.get(key));
			}
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			conn.setRequestProperty("Accept", "text/html, */*; q=0.01");
			conn.setRequestProperty("Referer", "http://ogame.tw/");
			conn.setRequestProperty("Cookie", sid);

			in = conn.getInputStream();

			Map<String, List<String>> m = conn.getHeaderFields();
			for (String key : m.keySet()) {
				System.out.print(key + ": ");
				List<String> list = m.get(key);
				System.out.println(Arrays.deepToString(list.toArray()));
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int size;
			while ((size = in.read(bytes)) != -1) {
				out.write(bytes, 0, size);
			}
			String result = out.toString("utf-8");

			out.close();
			// System.out.println(result);
			// return result;
		} finally {
			if (in != null) {
				in.close();
			}
		}

		// 3.
		url = "http://ogame.tw/main/login";

		// StringBuilder sb = new StringBuilder();
		// sb.append("kid=&uni=").append("uni108.ogame.tw").append("&login=")
		// .append("albert").append("&pass=").append("11111111");
		// String msg = sb.toString();
		String msg = "kid=&uni=uni108.ogame.tw&login=albert&pass=11111111";

		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			for (String key : map1.keySet()) {
				conn.setRequestProperty(key, map1.get(key));
			}
			conn.setRequestProperty("Content-Length", "51");
			// Integer.toString(msg.length()));
			conn.setRequestProperty("Cache-Control", "max-age=0");
			conn.setRequestProperty("Origin", "http://ogame.tw");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Referer", "http://ogame.tw/");
			conn.setRequestProperty("Cookie", sid);

			conn.connect();

			DataOutputStream dataOutputStream = new DataOutputStream(
					conn.getOutputStream());

			dataOutputStream.write(msg.getBytes("utf-8"));
			// dataOutputStream.writeUTF(msg);

			byte[] bs = msg.getBytes("utf-8");
			for (byte b : bs) {
				System.out.print(Integer.toHexString(b) + " ");
			}

			dataOutputStream.flush();
			dataOutputStream.close();

			in = conn.getInputStream();

			Map<String, List<String>> m = conn.getHeaderFields();
			for (String key : m.keySet()) {
				System.out.print(key + ": ");
				List<String> list = m.get(key);
				System.out.println(Arrays.deepToString(list.toArray()));
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int size;
			while ((size = in.read(bytes)) != -1) {
				out.write(bytes, 0, size);
			}
			String result = out.toString("utf-8");

			out.close();
			System.out.println(result);
			// return result;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public static void main(String[] args) {
		HttpConnectionTest client = new HttpConnectionTest("uni108.ogame.tw", 80);
		client.registerResponseHandler(new UngzipHandler());

		String msg = "GET http://uni108.ogame.tw/game/reg/login2.php?login=albert&pass=11111111&kid=&v=2 HTTP/1.1\r\n"
				+ "Host: uni108.ogame.tw\r\n"
				+ "Connection: keep-alive\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.1 Safari/537.11\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
				+ "Accept-Encoding: gzip,deflate,sdch\r\n"
				+ "Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,zh-TW;q=0.2\r\n"
				+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n\r\n";

		try {
			Response response = client.send(msg);
			System.out.println(response.getHttpHeader());
			System.out.println(new String(response.getHttpContent(), "utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void registerResponseHandler(IResponseHandler handler) {
		this.responseHandler = handler;
	}

	public Response send(String message) throws IOException {
		Socket socket = null;
		try {
			InetAddress addr = InetAddress.getByName(hostName);
			socket = new Socket(addr, port);
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			out.print(message);
			out.flush();
			Response response = responseHandler.handle(getResponse(socket
					.getInputStream()));
			socket.close();
			return response;
		} finally {
			if (null != socket)
				socket.close();
		}
	}

	private byte[] getResponse(InputStream input) {
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