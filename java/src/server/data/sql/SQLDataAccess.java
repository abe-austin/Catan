package server.data.sql;

import game.GameModel;

import java.util.List;

import client.data.GameInfo;
import server.data.IDataAccess;
import shared.communication.CreateGameParam;
import shared.communication.GetGameModelParam;
import shared.communication.LoginUserParam;
import shared.communication.RegisterUserParam;
import shared.definitions.Command;
import system.User;

public class SQLDataAccess implements IDataAccess {

	@Override
	public User createUser(RegisterUserParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(LoginUserParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameModel createGame(CreateGameParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGame(String gameName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameModel getGame(GetGameModelParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCommand(Command command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Command> getCommands(String gameName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameInfo> getAllGames(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
