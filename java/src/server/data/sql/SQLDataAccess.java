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
	public User createUser(RegisterUserParam param) {
		
		User user = new User(
				new Username(param.getUsername()), 
				new Password(param.getPassword()), 
				sqlUser.getNextUserID());
		
		user = sqlUser.addUser(user);
		return user;
	}

	@Override
	public User getUser(LoginUserParam param) {
		User user = sqlUser.getUser(param.getUsername(), param.getPassword());
		return user;
	}

	@Override
	public GameModel createGame(CreateGameParam param) {
		GameModel gameModel = new GameModel();
		gameModel.setRandomHexes(param.isRandomHexes());
		gameModel.setRandomNumbers(param.isRandomNumbers());
		gameModel.setRandomPorts(param.isRandomPorts());
		gameModel.buildBoard();
		gameModel.setGameName(param.getName());
		gameModel.setGameId(sqlGameModel.getNextGameModelID());
		
		String gameAsString = sqlGameModel.addGameModel(
				sqlGameModel.getNextGameModelID(), 
				toXML(gameModel),
				param.getName());
		
		if(gameAsString != null) {
			gameModel = (GameModel)fromXML(gameAsString);
		}
		else {
			gameModel = null;
		}
		
		return gameModel;
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
		// TODO add command to database
		
	}

	@Override
	public List<Command> getCommands(String gameName) {
		// TODO get a list of all commands from database and return it
		return null;
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
