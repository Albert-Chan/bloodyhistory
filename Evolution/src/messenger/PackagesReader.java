package messenger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PackagesReader {

	private HashMap<String, PackageDescriptor> map = new HashMap<String, PackageDescriptor>();

	public PackagesReader(String path) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					path), "UTF-8"));

			Visitor visitor = new Visitor();
			String line = null;
			while ((line = br.readLine()) != null) {
				visitor.visit(line);
			}
		} finally {
			if (null != br) {
				br.close();
			}
		}
	}

	public HashMap<String, PackageDescriptor> getPackageMap() {
		return map;
	}

	class Visitor {
		public void visit(String line) {
			if (line.startsWith("[")) {
				startPackage(line);
			} else if (line.startsWith("/[")) {
				endPackage(line);
			} else if (line.startsWith("#")) {
				startContent();
			} else if (line.startsWith("/#")) {
				endContent();
			} else if (line.startsWith("@@")) {
				handleParam(line);
			} else {
				handlePackageString(line);
			}
		}

		private String currentPackageName = null;
		private PackageDescriptor currentPackage = null;

		private void startPackage(String line) {
			String packageName = line.substring(1, line.length() - 1);
			if (map.containsKey(packageName)) {
				System.err.println("duplicate package name.");
			} else {
				currentPackage = new PackageDescriptor(packageName);
				map.put(packageName, currentPackage);
				currentPackageName = packageName;
			}
		}

		private void endPackage(String line) {
			String packageName = line.substring(2, line.length() - 1);
			if (currentPackageName != null
					&& currentPackageName.equals(packageName)) {
				currentPackageName = null;
				currentPackage = null;
			} else {
				System.err.println("package name does not match: "
						+ packageName);
			}
		}

		boolean isInContent = false;

		private void startContent() {
			if (!isInContent)
				isInContent = true;
			else
				System.err.println("cotent does not match: ");
		}

		private void endContent() {
			if (isInContent)
				isInContent = false;
			else
				System.err.println("cotent does not match: ");
		}

		private void handleParam(String line) {
			if (currentPackage == null) {
				System.err.println("Not in a package.");
				return;
			}
			Parameter p = new Parameter(line.trim());
			currentPackage.addParameter(p);
		}

		private void handlePackageString(String line) {
			if (currentPackage == null) {
				System.err.println("Not in a package.");
				return;
			}
			if (isInContent)
				currentPackage.appendContent(line);
			else
				currentPackage.appendHeader(line);
		}

	}

}
