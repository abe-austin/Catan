package client.serverProxy;

import java.util.List;
import java.util.Set;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;

public class ServerProxyFacade {
	private Server server;
	private boolean mockServer;
	
	public boolean loginUser(String username, String password){ return false; }
	public boolean registerUser(String username, String password){ return false; }
	public Set<GameModel> getAllGames(){ return null; };
	public GameModel createGame(){ return null; }
	public GameModel joinGame(int gameID, CatanColor color){ return null; }
	public GameModel getGameModel(){ return null; }
	public Set<Command> getGameCommands(){ return null; }
	public boolean doGameCommands(List<Command> commands){ return false; }
	public Set<AI> getAIList(){ return null; }
	public boolean addAI(AI ai){ return false; }
	public boolean changeLogLevel(LogLevel logLevel){ return false; }
	public boolean sendChat(int playerIndex, String message){ return false; }
	public boolean acceptTrade(int playerIndex, boolean willAccept){ return false; }
	public boolean discardCards(int playerIndex, List<Card> cards){ return false; }
	public boolean rollNumber(int playerIndex, int rollNumber){ return false; }
	public boolean buildRoad(int playerIndex, Edge edge){ return false; }
	public boolean buildSettlement(int playerIndex, Corner corner, boolean free){ return false; }
	public boolean buildCity(int playerIndex, Corner corner, boolean free){ return false; }
	public boolean offerTrade(int playerIndex, List<Card> offer, int recieveIndex){ return false; }
	public boolean maritimetrade(int playerIndex, int ratio, ResourceType input, ResourceType output){ return false; }
	public boolean finishTurn(){ return false; }
	public Card buyDevCard(int playerInex){ return false; }
	public Card[] playYearOfPlenty(int playerIndex, ResourceType resourceOne, ResourceType resourceTwo){ return null; }
	public boolean playRoadBuilder(int playerIndex, Edge edgeOne, Edge edgeTwo){ return false; }
	public Card playSoldier(int playerIndex, int victimIndex, HexTile newRobberLocation){ return null; }
	public List<Card> playMonopoly(int playerIndex, ResourceType resource){ return null; }
	public boolean playMonument(int playerIndex){ return false; }
	
}