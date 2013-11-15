package auth;
import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * 
 */
public interface AuthenticationCenter extends Remote {

	public String register(String user, String password) throws RemoteException;

	public String authenticate(String name, String pass) throws RemoteException;

	public String changePassword(String name, String oldPass, String newPass)
			throws RemoteException;

}