package server.data;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

import client.data.GameInfo;
import server.data.sql.SQLDataAccess;
import shared.communication.CreateGameParam;
import shared.communication.GetGameModelParam;
import shared.communication.LoginUserParam;
import shared.communication.RegisterUserParam;
import system.User;

public class SQLDatabaseTest {

	@Test
	public void test() {
//		addUser();
//		getUser();
//		addGame();
//		getGame();
		getAllGames();
	}
	
	public void addUser() {
		SQLDataAccess database = new SQLDataAccess();
		database.createUser(new RegisterUserParam("frog", "asdf"));
	}
	
	public void getUser() {
		SQLDataAccess database = new SQLDataAccess();
		database.getUser(new LoginUserParam("Graham", "passy"));
	}
	
	public void addGame() {
		SQLDataAccess database = new SQLDataAccess();
		database.createGame(new CreateGameParam("cZc nadsame", true, true, true));
	}
	
	public void getGame() {
		SQLDataAccess database = new SQLDataAccess();
		GetGameModelParam param = new GetGameModelParam(-1);
		param.setName("cZc nadsame");
		database.getGame(param);
	}
	
	public void getAllGames() {
		SQLDataAccess database = new SQLDataAccess();
		ArrayList<GameInfo> gameInfo = new ArrayList<GameInfo>( database.getAllGames(new User()));
		for(GameInfo info : gameInfo) {
		}
	}
}
