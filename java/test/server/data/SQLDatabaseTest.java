package server.data;

import static org.junit.Assert.*;

import org.junit.Test;

import server.data.sql.SQLDataAccess;
import shared.communication.LoginUserParam;
import shared.communication.RegisterUserParam;

public class SQLDatabaseTest {

	@Test
	public void test() {
		addUser();
		getUser();
	}
	
	public void addUser() {
		SQLDataAccess database = new SQLDataAccess();
		database.createUser(new RegisterUserParam("dad", "test1"));
	}
	public void getUser() {
		SQLDataAccess database = new SQLDataAccess();
		database.getUser(new LoginUserParam("Graham", "passy"));
	}
}
