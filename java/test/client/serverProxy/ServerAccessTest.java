package client.serverProxy;


import java.util.ArrayList;

import junit.framework.Assert;
import game.GameModel;
import game.board.Corner;
import game.board.Edge;
import game.cards.ResourceCard;

import org.junit.Test;

import shared.communication.CreateGameRes;
import shared.communication.ServerResponse;
import shared.definitions.CatanColor;
import shared.definitions.Command;
import shared.definitions.LogLevel;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class ServerAccessTest {

	ServerProxyFacade server;
	
	public ServerAccessTest(){
		server = new ServerProxyFacade(false);
	}
	
	@Test
	public void registerUser() {
		
		ServerResponse register = server.registerUser("usrname", "password");
		assertResponseCorrect(register, String.class);
	}
	
	@Test
	public void loginUser() {
		
		ServerResponse login = server.loginUser("usrname", "password");
		assertResponseCorrect(login, String.class);
	}
	
	@Test
	public void getAllGames() {
		
		ServerResponse login = server.getAllGames();
		assertResponseCorrect(login, ArrayList.class);
	}	
	@Test
	public void createGame() {
		
		ServerResponse game = server.createGame("New Game Name");
		assertResponseCorrect(game, CreateGameRes.class);
	}
	
	@Test
	public void joinGame() {
		
		ServerResponse join = server.joinGame(0, CatanColor.ORANGE);
		assertResponseCorrect(join, String.class);
	}
	
	@Test
	public void saveGame() {
		
		ServerResponse save = server.saveGame(5, "Game Name");
		assertResponseCorrect(save, String.class);
	}
	
//	@Test
//	public void loadGame() {
//
//		ServerResponse load = server.loadGame("New Game Name");
//	}
	
	@Test
	public void getGameModel() {
		
		ServerResponse model = server.getGameModel(0);
		assertResponseCorrect(model, GameModel.class);
	}
	
	@Test
	public void resetGame() {
		
		ServerResponse reset = server.resetGame();
		assertResponseCorrect(reset, GameModel.class);
	}
	
	@Test
	public void doGameCommands() {
		
		ServerResponse commands = server.doGameCommands(new ArrayList<Command>());
		assertResponseCorrect(commands, GameModel.class);
	}
	
	@Test
	public void getGameCommands() {
		
		ServerResponse commands = server.getGameCommands();
		assertResponseCorrect(commands, String.class);
	}
	
	@Test
	public void addAI() {
		
		ServerResponse ai = server.addAI("AI TYPE");
		assertResponseCorrect(ai, String.class);
	}
	
	@Test
	public void getAIList() {
		
		ServerResponse ai = server.getAIList();
		assertResponseCorrect(ai, ArrayList.class);
	}
	
	@Test
	public void sendChat() {
		
		ServerResponse chat = server.sendChat(0, "message");
		assertResponseCorrect(chat, GameModel.class);
	}
	
	@Test
	public void rollNumber() {
		
		ServerResponse roll = server.rollNumber(0, 5);
		assertResponseCorrect(roll, GameModel.class);
	}
	
	@Test
	public void robPlayer() {
		
		ServerResponse rob = server.robPlayer(0, 2, new Corner(new ArrayList<VertexLocation>()));
		assertResponseCorrect(rob, GameModel.class);
	}
	
	@Test
	public void finishTurn() {
		
		ServerResponse finish = server.finishTurn(0);
		assertResponseCorrect(finish, GameModel.class);
	}
	
	@Test
	public void buyDevCard() {
		
		ServerResponse buy = server.buyDevCard(0);
		assertResponseCorrect(buy, GameModel.class);
	}
	
	@Test
	public void playYearOfPlenty() {
		
		ServerResponse year = server.playYearOfPlenty(0, ResourceType.ORE, ResourceType.WHEAT);
		assertResponseCorrect(year, GameModel.class);
	}
	
	@Test
	public void playRoadBuilding() {
		
		ServerResponse road = server.playRoadBuilding(0, new Edge(new ArrayList<EdgeLocation>()),
				new Edge(new ArrayList<EdgeLocation>()));
		assertResponseCorrect(road, GameModel.class);
	}
	
	@Test
	public void playSoldier() {
		
		ServerResponse soldier = server.playSoldier(0, 1, new HexLocation(0,0));	
		assertResponseCorrect(soldier, GameModel.class);
	}
	
	@Test
	public void playMonopoly() {
		
		ServerResponse monopoly = server.playMonopoly(0, ResourceType.ORE);
		assertResponseCorrect(monopoly, GameModel.class);
	}
	
	@Test
	public void playMonument() {
		
		ServerResponse monument = server.playMonument(0);
		assertResponseCorrect(monument, GameModel.class);
	}
	
	@Test
	public void buildRoad() {
		
		ServerResponse road = server.buildRoad(0, new Edge(new ArrayList<EdgeLocation>()));
		assertResponseCorrect(road, GameModel.class);
	}
	
	@Test
	public void buildSettlement() {
		
		ServerResponse settlemet = server.buildSettlement(0,  new Corner(new ArrayList<VertexLocation>()), true);
		assertResponseCorrect(settlemet, GameModel.class);
	}
	
	@Test
	public void buildCity() {
		
		ServerResponse city = server.buildCity(0,  new Corner(new ArrayList<VertexLocation>()), true);
		assertResponseCorrect(city, GameModel.class);
	}
	
	@Test
	public void offerTrade() {
		
		ServerResponse trade = server.offerTrade(0, new ArrayList<ResourceType>(), 3);
		assertResponseCorrect(trade, GameModel.class);
	}
	
	@Test
	public void acceptTrade() {
		
		ServerResponse trade = server.acceptTrade(0, true);	
		assertResponseCorrect(trade, GameModel.class);
	}
	
	@Test
	public void maritimeTrade() {
		
		ServerResponse trade = server.maritimeTrade(0, 3, ResourceType.ORE, ResourceType.SHEEP);
		assertResponseCorrect(trade, GameModel.class);
	}
	
	@Test
	public void discardCards() {
		
		ServerResponse discard = server.discardCards(0, new ArrayList<ResourceCard>());
		assertResponseCorrect(discard, GameModel.class);
	}
	
	@Test
	public void changeLogLevel() {
		
		ServerResponse logLevel = server.changeLogLevel(LogLevel.FINE);
		assertResponseCorrect(logLevel, String.class);
	}
	
	private void assertResponseCorrect(ServerResponse response, Class<?> type) {
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCode());
		if(response.getCode() == 200){
			Assert.assertEquals(response.getBody().getClass(), type);
		}
		else if(response.getCode() == 521) {
			Assert.assertEquals(response.getBody(), "Server connection failed");
		}
	}
}
