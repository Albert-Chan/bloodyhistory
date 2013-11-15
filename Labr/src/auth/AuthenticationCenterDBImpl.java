package auth;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * 
 */
public class AuthenticationCenterDBImpl extends UnicastRemoteObject implements
		AuthenticationCenter {

	private static final long serialVersionUID = -5892415829659910951L;
	private static final String CONNECTION_STRING = "/db/h2.db";

	private H2Db connector = null;

	public AuthenticationCenterDBImpl() throws RemoteException {
		super();
		try {
			connector = new H2Db(CONNECTION_STRING);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String register(String user, String password) throws RemoteException {
		try {
			connector.startTransaction();
			String passInDB = getPassword(user);
			if (passInDB != null) {
				connector.rollBack();
				return "The user already existed.";
			}

			String generatedSecuredPasswordHash = BCrypt.hashpw(password,
					BCrypt.gensalt());
			if (!insertNewUser(user, generatedSecuredPasswordHash)) {
				connector.rollBack();
				return "DB failure.";
			} else {
				connector.commitTransaction();
				return "Sign up successfully.";
			}
		} catch (SQLException e) {
			return "DB failure.";
		}
	}

	public String changePassword(String user, String oldPass, String newPass)
			throws RemoteException {

		if (auth(user, oldPass)) {
			String generatedSecuredPasswordHash = BCrypt.hashpw(newPass,
					BCrypt.gensalt());
			if (!insertNewUser(user, generatedSecuredPasswordHash))
				return "DB failure.";
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
		String passInDB = getPassword(user);
		if (passInDB != null) {
			boolean matched = BCrypt.checkpw(pass, passInDB);
			if (matched) {
				return true;
			}
		}
		return false;
	}

	private String getPassword(String user) {
		ResultSet rs = null;
		String passInDB = null;
		try {
			String sql = "SELECT pass FROM users WHERE name=?";
			PreparedStatement getPassword = connector.prepareStatement(sql);
			getPassword.setString(1, user);
			rs = getPassword.executeQuery();

			if (rs.next()) {
				passInDB = rs.getString("pass");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return passInDB;
	}

	private boolean insertNewUser(String user, String pass) {
		try {
			String sql = "INSERT INTO users(name, pass) values(?,?)";
			PreparedStatement addUser = connector.prepareStatement(sql);
			addUser.setString(1, user);
			addUser.setString(2, pass);
			connector.update(addUser);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}