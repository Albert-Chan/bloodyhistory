package auth;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/** 
 * 
 */
public class AuthenticationCenterImpl extends UnicastRemoteObject implements
		AuthenticationCenter {

	private static final long serialVersionUID = 1332210841581669086L;

	private HashMap<String, String> registry = new HashMap<String, String>();

	public AuthenticationCenterImpl() throws RemoteException {
		super();
	}

	public String register(String user, String password) throws RemoteException {
		if (registry.containsKey(user)) {
			return "The user already existed.";
		}

		String generatedSecuredPasswordHash = BCrypt.hashpw(password,
				BCrypt.gensalt());
		registry.put(user, generatedSecuredPasswordHash);
		return "Sign up successfully.";
	}

	public String changePassword(String user, String oldPass, String newPass)
			throws RemoteException {

		if (auth(user, oldPass)) {
			String generatedSecuredPasswordHash = BCrypt.hashpw(newPass,
					BCrypt.gensalt());
			registry.put(user, generatedSecuredPasswordHash);
			return "Password changed successfully.";
		}
		return "You are not authenticated.";
	}

	public String authenticate(String user, String pass) throws RemoteException {
		if (auth(user, pass)) {
			return "Login succeed.";
		} else {
			return "Bad user name or password.";
		}
	}

	private boolean auth(String user, String pass) {
		if (registry.containsKey(user)) {
			//String plain = decrypt(pass);

			boolean matched = BCrypt.checkpw(pass, registry.get(user));
			if (matched) {
				return true;
			}
		}
		return false;

	}


}