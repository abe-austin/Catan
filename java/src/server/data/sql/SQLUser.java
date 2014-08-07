package server.data.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import system.Password;
import system.User;
import system.Username;

public class SQLUser extends SQLTable {
	
	public User addUser(User user) {
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO User(UserID, Username, Password) VALUES(?,?,?);");
			statement.setInt(1, user.getId());
			statement.setString(2, user.getUsername().getUsername());
			statement.setString(3, user.getPassword().getPassword());
			statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			user = null;
		}
		finally {
			endTransaction(true, conn);
		}
		return user;
	}
	
	public User getUser(String username, String password) {
		User user = null;
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"SELECT * FROM User WHERE Username = ? AND Password = ?;");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(new Username(rs.getString(2)));
				user.setPassword(new Password(rs.getString(3)));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			user = null;
		}
		finally {
			endTransaction(true, conn);
		}
		return user;
	}
	
	public List<User> getAllUsers() {
		// TODO get a list of all users
		return null;
	}
	
	public void deleteUser(int userID) {
		// TODO delete user from database
	}
	
	public int getNextUserID() {
		int nextID = -1;
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"SELECT MAX(UserID) FROM User");
			ResultSet rs = statement.executeQuery();
			nextID = rs.getInt(1) + 1;
		}
		catch(Exception e) {
			e.printStackTrace();
			nextID = -1;
		}
		finally {
			endTransaction(true, conn);
		}
		return nextID;
	}
}
