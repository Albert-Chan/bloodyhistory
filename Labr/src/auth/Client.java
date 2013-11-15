package auth;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class Client {

	private AuthenticationCenter ac = null;
	
	public Client() {
		try {
			ac = (AuthenticationCenter) Naming.lookup("rmi://Hall/server");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String signUp(String user, String plainPass) throws RemoteException,
			Exception {
		String password = MD5BASE64(plainPass);
		return ac.register(user, password);
	}

	public String signIn(String user, String plainPass) throws RemoteException,
			Exception {
		String password = MD5BASE64(plainPass);
		return ac.authenticate(user, password);
	}

	public String changePassword(String user, String oldPass, String newPass)
			throws RemoteException, Exception {
		oldPass = MD5BASE64(oldPass);
		newPass = MD5BASE64(newPass);
		return ac.changePassword(user, oldPass, newPass);
	}

	private String MD5BASE64(String original) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(original.getBytes("utf-8"));
		byte[] digestBytes = md5.digest();

		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(digestBytes);
	}

	public static void main(String args[]) {
		try {
			
			Client c = new Client();
			System.out.println(c.signUp("Albert", "pass"));
			System.out.println(c.signUp("Albert", "pass"));
			System.out.println(c.signUp("Albert", "pass1"));
			System.out.println(c.signIn("Albert", "pass"));
			System.out.println(c.signIn("Albert", "pass1"));
			System.out.println(c.changePassword("Albert", "pass", "pass1"));
			System.out.println(c.changePassword("Albert", "pass", "pass1"));
			System.out.println(c.signIn("Albert", "pass1"));
			System.out.println(c.signIn("Albert", "pass"));
					
					
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}