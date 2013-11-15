package auth;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Db {

	private Statement stat = null;
	private Connection conn = null;

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public H2Db(String db) throws SQLException {
		conn = DriverManager.getConnection("jdbc:h2:" + db);
		stat = conn.createStatement();
	}

	public void startTransaction() throws SQLException {
		conn.setAutoCommit(false);
	}

	public void commitTransaction() throws SQLException {
		conn.commit();
		conn.setAutoCommit(true);
	}

	public void rollBack() throws SQLException {
		conn.rollback();
		conn.setAutoCommit(true);
	}

	/**
	 * Create table, insert, update, etc...
	 * 
	 * @param sql
	 */
	public void update(String sql) throws SQLException {

		// stat.executeUpdate(
		// "create table tbl1(name varchar(20), col_int int);" );
		// stat.executeUpdate( "insert into tbl1 values('aaa',8888);" );
		stat.executeUpdate(sql);
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	public void update(PreparedStatement statement) throws SQLException {
		statement.executeUpdate();
	}
	
	public ResultSet query(PreparedStatement statement) throws SQLException {
		return statement.executeQuery();
	}

	public void close() throws SQLException {
		if (conn != null)
			conn.close();
	}

}
