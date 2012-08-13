package network;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;


public class ResponseUngzipHandler implements IResponseHandler {
	private byte[] input;

	Response response = null;

	public Response getResponse() {
		return response;
	}

	public ResponseUngzipHandler(byte[] bytes) {
		this.input = bytes;
		handle();
	}

	private void handle() {
		if (input == null)
			return;
		int breakPoint = getHttpHead(input);
		if (-1 == breakPoint)
			return;
		response = new Response();
		response.setHttpHeader(new String(input, 0, breakPoint));
		ByteArrayInputStream bats = new ByteArrayInputStream(input, breakPoint,
				input.length - breakPoint);
		response.setHttpContent(ungzip(bats));

	}

	private int getHttpHead(byte[] bytes) {
		boolean lineBreak = false;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == 0x0d) {
				if (bytes[++i] == 0x0a) {
					if (lineBreak)
						return i + 1;
					else
						lineBreak = true;
				} else {
					lineBreak = false;
				}
			} else {
				lineBreak = false;
			}
		}
		return -1;
	}

	private byte[] ungzip(InputStream input) {
		try {
			GZIPInputStream gzin = new GZIPInputStream(input);
			byte[] bytes = InputStreamHelper.getBytesFromInputStream(gzin);
			gzin.close();
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
