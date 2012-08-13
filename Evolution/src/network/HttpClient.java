package network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class HttpClient {
	private String hostName;
	private int port = 80;

	public HttpClient(String hostName) {
		this.hostName = hostName;
	}

	public HttpClient(String hostName, int port) {
		this(hostName);
		this.port = port;
	}
	
	public Response send(String msg) throws IOException {
		byte[] response = sendMessage(msg);
		if (null == response) {
			return null;
		}
		ResponseUngzipHandler handler = new ResponseUngzipHandler(response);
		return handler.getResponse();
	}

	private byte[] sendMessage(String message) throws IOException {
		Socket socket = null;
		try {
			InetAddress addr = InetAddress.getByName(hostName);
			socket = new Socket(addr, port);
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			out.print(message);
			out.flush();
			byte[] bytes = getResponse(socket.getInputStream());
			socket.close();
			return bytes;
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