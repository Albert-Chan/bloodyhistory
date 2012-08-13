package data.collector;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class SinajsCrawler {

	public static void main(String[] args) {
		String url = "http://hq.sinajs.cn/list=sh600151,sz000830,s_sh000001,s_sz399001,s_sz399106,sz000777";
		try {
			URL u = new URL(url);
			byte[] bytes = new byte[256];
			InputStream in = null;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// while (true)
			{
				try {
					in = u.openStream();
					int size;
					while ((size = in.read(bytes)) != -1) {
						out.write(bytes, 0, size);
					}
					String result = out.toString("gb2312");
					String[] stocks = result.split(";");
					for (String stock : stocks) {
						String[] datas = stock.split(",");
						System.out.println(Arrays.deepToString(datas));
					}
					out.reset();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					if (in != null) {
						in.close();
					}
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
