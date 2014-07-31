package client.serverProxy;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import game.GameModel;
import game.board.Corner;
import game.board.Edge;
import game.board.HexTile;
import game.cards.ResourceCard;

import org.junit.Test;

import cs340.model.hexgrid.vertex.VertexDirection;
import client.data.GameInfo;
import shared.communication.CreateGameRes;
import shared.communication.ServerResponse;
import shared.definitions.CatanColor;
import shared.definitions.Command;
import shared.definitions.LogLevel;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class ServerAccessTest {

	ServerProxyFacade server;
	
	public ServerAccessTest(){
		server = new ServerProxyFacade(false);
	}
	
	@Test
	public void test() {
<<<<<<< HEAD
		this.registerUser();
=======
//		this.registerUser();
>>>>>>> c76a5fc326950f39deacf1f1fa07dd766b2528bd
//		this.createGame();
//		this.getAllGames();
		this.loginUser();
//		this.joinGame();
//		this.saveGame();
//		this.getGameModel();
//		this.resetGame();
//		this.getGameCommands();
//		this.doGameCommands();
//		this.addAI();
//		this.getAIList();
//		this.sendChat();
//		this.rollNumber();
////		this.robPlayer();
//		this.finishTurn();
//		this.buyDevCard();
//		this.playYearOfPlenty();
////		this.playRoadBuilding();
//		this.playSoldier();
//		this.playMonopoly();
//		this.playMonument();
//		this.buildRoad();
////		this.buildSettlement();
////		this.buildCity();
////		this.offerTrade();
//		this.acceptTrade();
//		this.maritimeTrade();
//		this.discardCards();
//		this.changeLogLevel();
	}
	
	private void registerUser() {
		
		ServerResponse register = server.registerUser("BRent", "brentPassword");
		assertResponseCorrect(register, Integer.class);
	}
	
	private void loginUser() {
		
		ServerResponse login = server.loginUser("BRent", "brentPassword");
		assertResponseCorrect(login, Integer.class);
	}
	
	private void getAllGames() {
		
		ServerResponse login = server.getAllGames();
		assertResponseCorrect(login, ArrayList.class);
	}
	
	private void createGame() {
		
		ServerResponse game = server.createGame("New Game Name", true, true, true);
		assertResponseCorrect(game, CreateGameRes.class);
	}
	
	private void joinGame() {
		
		ServerResponse join = server.joinGame(3, CatanColor.ORANGE);
		assertResponseCorrect(join, String.class);
	}
	
	private void saveGame() {
		
		ServerResponse save = server.saveGame(5, "Game Name");
		assertResponseCorrect(save, String.class);
	}
	
//	private void loadGame() {
//
//		ServerResponse load = server.loadGame("New Game Name");
//	}
	
	private void getGameModel() {
		
		ServerResponse model = server.getGameModel(0);
		assertResponseCorrect(model, GameModel.class);
	}
	
	private void resetGame() {
		
		ServerResponse reset = server.resetGame();
		assertResponseCorrect(reset, GameModel.class);
	}
	
	private void doGameCommands() {
		
		ServerResponse commands = server.doGameCommands(new ArrayList<Command>());
		assertResponseCorrect(commands, GameModel.class);
	}
	
	private void getGameCommands() {
		
		ServerResponse commands = server.getGameCommands();
		assertResponseCorrect(commands, ArrayList.class);
	}
	
	private void addAI() {
		
		ServerResponse ai = server.addAI("AI TYPE");
		assertResponseCorrect(ai, String.class);
	}
	
	private void getAIList() {
		
		ServerResponse ai = server.getAIList();
		assertResponseCorrect(ai, ArrayList.class);
	}
	
	private void sendChat() {
		
		ServerResponse chat = server.sendChat(0, "message");
		assertResponseCorrect(chat, GameModel.class);
	}
	
	private void rollNumber() {
		
		ServerResponse roll = server.rollNumber(0, 5);
		assertResponseCorrect(roll, GameModel.class);
	}
	
//	private void robPlayer() {
//		
//		ServerResponse rob = server.robPlayer(0, 2, new HexLocation());
//		assertResponseCorrect(rob, GameModel.class);
//	}
	
	private void finishTurn() {
		
		ServerResponse finish = server.finishTurn(0);
		assertResponseCorrect(finish, GameModel.class);
	}
	
	private void buyDevCard() {
		
		ServerResponse buy = server.buyDevCard(0);
		assertResponseCorrect(buy, GameModel.class);
	}
	
	private void playYearOfPlenty() {
		
		ServerResponse year = server.playYearOfPlenty(0, ResourceType.ORE, ResourceType.WHEAT);
		assertResponseCorrect(year, GameModel.class);
	}
	
//	private void playRoadBuilding() {
//		
//		ServerResponse road = server.playRoadBuilding(0, new Edge(new ArrayList<EdgeLocation>()),
//				new Edge(new ArrayList<EdgeLocation>()));
//		assertResponseCorrect(road, GameModel.class);
//	}
	
	private void playSoldier() {
		
		ServerResponse soldier = server.playSoldier(0, 1, new HexLocation(0,0));	
		assertResponseCorrect(soldier, GameModel.class);
	}
	
	private void playMonopoly() {
		
		ServerResponse monopoly = server.playMonopoly(0, ResourceType.ORE);
		assertResponseCorrect(monopoly, GameModel.class);
	}
	
	private void playMonument() {
		
		ServerResponse monument = server.playMonument(0);
		assertResponseCorrect(monument, GameModel.class);
	}
	
	private void buildRoad() {
		
		ServerResponse road = server.buildRoad(0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.North), true);
		assertResponseCorrect(road, GameModel.class);
	}
	
//	private void buildSettlement() {
//		
//		ServerResponse settlemet = server.buildSettlement(0,  new VertexLocation((new HexLocation(0,0), VertexDirection.NE), true);
//		assertResponseCorrect(settlemet, GameModel.class);
//	}
//	
//	private void buildCity() {
//		
//		ServerResponse city = server.buildCity(0,  new Corner(new ArrayList<VertexLocation>()), true);
//		assertResponseCorrect(city, GameModel.class);
//	}
//	
//	private void offerTrade() {
//		
//		ServerResponse trade = server.offerTrade(0, new ArrayList<ResourceType>(), 3);
//		assertResponseCorrect(trade, GameModel.class);
//	}
	
	private void acceptTrade() {
		
		ServerResponse trade = server.acceptTrade(0, true);	
		assertResponseCorrect(trade, GameModel.class);
	}
	
	private void maritimeTrade() {
		
		ServerResponse trade = server.maritimeTrade(0, 3, ResourceType.ORE, ResourceType.SHEEP);
		assertResponseCorrect(trade, GameModel.class);
	}
	
	private void discardCards() {
		
		ServerResponse discard = server.discardCards(0, new ArrayList<ResourceCard>());
		assertResponseCorrect(discard, GameModel.class);
	}
	
	private void changeLogLevel() {
		
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
		System.out.println(response.getCode());
		System.out.println(response.getBody());
	}
}
