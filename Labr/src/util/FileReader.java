package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileReader {
	private static final String FILE_NAME = "someFile.txt";

	public static ArrayList<String> getContent() throws IOException {
		ArrayList<String> contents = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(FILE_NAME), "utf-8"));
			String line;
			while (null != (line = reader.readLine())) {
				line = line.trim();
				// ignore empty line
				if (line.isEmpty())
					continue;
				// ignore commented out line
				if (line.indexOf("//") == 0) {
					continue;
				}
				contents.add(line);
			}
			return contents;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

}
