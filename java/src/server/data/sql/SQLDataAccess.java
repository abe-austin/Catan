package server.data.sql;

import game.GameModel;

import java.util.ArrayList;
import java.util.List;

import player.Player;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import client.data.GameInfo;
import client.data.PlayerInfo;
import server.data.IDataAccess;
import shared.communication.CreateGameParam;
import shared.communication.GetGameModelParam;
import shared.communication.LoginUserParam;
import shared.communication.RegisterUserParam;
import shared.definitions.Command;
import system.Password;
import system.User;
import system.Username;

public class SQLDataAccess implements IDataAccess {
	
	private SQLUser sqlUser;
	private SQLGameModel sqlGameModel;
	private SQLCommand sqlCommand;
	
	public SQLDataAccess() {
		this.sqlUser = new SQLUser();
		this.sqlGameModel = new SQLGameModel();
		this.sqlCommand = new SQLCommand();
	}

	@Override
	public User createUser(User user) {
		user = sqlUser.addUser(user);
		return user;
	}

	@Override
	public User getUser(LoginUserParam param) {
		User user = sqlUser.getUser(param.getUsername(), param.getPassword());
		return user;
	}
	
    @Override
    public List<User> getAllUsers() {
        return sqlUser.getAllUsers();
    }

	@Override
	public GameModel createGame(GameModel game) {
		
		String gameAsString = sqlGameModel.addGameModel(
				game.getGameId(), 
				toXML(game),
				game.getGameName());
		
		if(gameAsString != null) {
			game = (GameModel)fromXML(gameAsString);
		}
		else {
			game = null;
		}
		
		return game;
	}

	@Override
	public void deleteGame(String gameName) {
		// TODO delete game
		
	}

	@Override
	public GameModel getGame(GetGameModelParam param) {
		GameModel gameModel;
		String game = sqlGameModel.getGameModel(param.getName());
		
		if(game != null) {
			gameModel = (GameModel)fromXML(game);
		}
		else {
			gameModel = null;
		}

		return gameModel;
	}

	@Override
	public void addCommand(Command command) {
		sqlCommand.addCommand(toXML(command));
	}

	@Override
	public List<Command> getCommands(String gameName) {
		List<String> commandsString = sqlCommand.getAllCommands();
		List<Command> commands = new ArrayList<Command>();
		for(String commandString : commandsString) {
			Command command = (Command)fromXML(commandString);
			commands.add(command);
		}
		return commands;
	}

	@Override
	public List<GameInfo> getAllGames(User user) {
		List<String> modelsAsString = sqlGameModel.getAllGameModels();
		List<GameInfo> gameInfo = new ArrayList<GameInfo>();
		
    	for(String gameString : modelsAsString) {
    		
    		GameModel game = (GameModel)fromXML(gameString);
    		Player[] players = game.getPlayers();
    		ArrayList<PlayerInfo> playerList = new ArrayList<PlayerInfo>();
    		
    		for(Player player : players) {
    			
    			if(player != null) {
	    			PlayerInfo playerInfo = new PlayerInfo();
	    			playerInfo.setCatanColor(player.getColor());
	    			playerInfo.setName(player.getUsername());
	    			playerInfo.setId(player.getUser().getId());
	    			playerList.add(playerInfo);
    			}
    			
    		}
    		
    		GameInfo info = new GameInfo(game.getGameId(), game.getGameName(), playerList);
    		gameInfo.add(info);
    	}
    	
		return gameInfo;
	}
	
    @Override
    public List<GameModel> getAllGames() {
		List<String> modelsAsString = sqlGameModel.getAllGameModels();
		List<GameModel> games = new ArrayList<GameModel>();
    	for(String gameString : modelsAsString) {
    		GameModel game = (GameModel)fromXML(gameString);
    		games.add(game);
    	}
    	return games;
    }

	private String toXML(Object toConvert) {
		XStream xstream = new XStream(new DomDriver());
		String converted = xstream.toXML(toConvert);
		return converted;
	}
	
	private Object fromXML(String xml) {
		XStream xstream = new XStream(new DomDriver());
		Object converted = xstream.fromXML(xml);
		return converted;
	}
}
