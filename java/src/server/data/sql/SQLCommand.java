package server.data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shared.definitions.Command;

public class SQLCommand extends SQLTable {
	
	public String addCommand(String command, int commandID, int gameID) {
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO Command(CommandID, GameID, Command) VALUES(?,?,?);");
			statement.setInt(1, getNextCommandID());
			statement.setInt(2, gameID);
			statement.setBytes(3, command.getBytes());
			statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			 command = null;
		}
		finally {
			endTransaction(true, conn);
		}
		return command;
	}
	
	public Command getCommand(int commandID) {
		// TODO get the specified command
		return null;
	}
	
	public List<String> getAllCommands(int gameID) {
		List<String> commands = new ArrayList<String>();
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"SELECT * FROM Command WHERE GameID = ?;");
			statement.setInt(1, gameID);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String command = rs.getString(2);
				commands.add(command);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			endTransaction(true, conn);
		}
		return commands;
	}
	
	public void deleteCommand(int commandID) {
		// TODO delete command from database
	}
	
	public int getNextCommandID() {
		int nextID = -1;
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"SELECT MAX(CommandID) FROM Command");
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
