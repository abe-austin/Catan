package server.data.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import game.GameModel;

public class SQLGameModel extends SQLTable {
	
	public String addGameModel(int gameID, String game, String name) {
		System.out.println("GAME MODEL ADDED");
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"INSERT INTO GameModel(GameID, Model, name) VALUES(?, ?, ?);");
			statement.setInt(1, gameID);
			statement.setBytes(2, game.getBytes());
			statement.setString(3, name);
			statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			game = null;
		}
		finally {
			endTransaction(true, conn);
		}
		return game;
	}
	
	public String updateGameModel(int gameID, String game) {
		System.out.println("GAME MODEL UPDATED");
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"UPDATE GameModel SET Model = ? WHERE GameID = ?;");
			statement.setBytes(1, game.getBytes());
			statement.setInt(2, gameID);
			statement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			game = null;
		}
		finally {
			endTransaction(true, conn);
		}
		return game;
	}
	
	public String getGameModel(String name) {
		String gameModel = null;
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"SELECT Model FROM GameModel WHERE Name = ?;");
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				gameModel = rs.getString(1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			gameModel = null;
		}
		finally {
			endTransaction(true, conn);
		}
		return gameModel;

	}
	
	public List<String> getAllGameModels() {
		List<String> gameModels = new ArrayList<String>();
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"SELECT * FROM GameModel;");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String gameModel = rs.getString(3);
				gameModels.add(gameModel);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			endTransaction(true, conn);
		}
		return gameModels;
	}
	
	public void deleteGameModel(int gameID) {
		// TODO delete GameModel from database
	}
	
	public int getNextGameModelID() {
		int nextID = -1;
		Connection conn = startTransaction();
		try {
			PreparedStatement statement = conn.prepareStatement(
					"SELECT MAX(GameID) FROM GameModel");
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
