package server.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class SQLTable {
	
	protected Connection startTransaction() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database/CatanData.sqlite");
			conn.setAutoCommit(false);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	protected void endTransaction(boolean commit, Connection conn) {
		try {
			if(commit) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Connection failed");
		}
	}
}
