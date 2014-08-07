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
		// TODO check if user exists, create user, add user, return user
		return null;
	}

	@Override
	public User getUser(LoginUserParam param) {
		// TODO get user from database and return it
		return null;
	}

	@Override
	public GameModel createGame(CreateGameParam param) {
		// TODO check if game exists, create game, add game, return game
		return null;
	}

	@Override
	public void deleteGame(String gameName) {
		// TODO delete game
		
	}

	@Override
	public GameModel getGame(GetGameModelParam param) {
		// TODO get game from database and return it
		return null;
	}

	@Override
	public void addCommand(Command command) {
		// TODO add command to database
		
	}

	@Override
	public List<Command> getCommands(String gameName) {
		// TODO get a list of all commands from database and return it
		return null;
	}

	@Override
	public List<GameInfo> getAllGames(User user) {
		// TODO get a list of all games from database that user can join, create GameInfo for each, return list of GameInfo
		return null;
	}

}
