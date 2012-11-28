package messenger;

import java.io.IOException;
import java.util.HashMap;

public class PackageGenerator {
	private HashMap<String, PackageDescriptor> map = new HashMap<String, PackageDescriptor>();

	public PackageGenerator() {
		try {
			PackagesReader reader = new PackagesReader("httpPackages.txt");
			map = reader.getPackageMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String generate(String name, Parameter... paramters) {
		PackageDescriptor pack = map.get(name);
		return pack.getPackage(paramters);
	}

}
