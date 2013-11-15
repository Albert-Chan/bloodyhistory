package auth;
import java.rmi.Naming;

public class Server {

	public Server() {
		try {
			AuthenticationCenter ac = new AuthenticationCenterImpl();
			Naming.rebind("rmi://Hall/server", ac);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// System.setSecurityManager(new RMISecurityManager());
		new Server();
	}
}