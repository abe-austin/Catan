package client.serverProxy;


import java.util.ArrayList;

import game.GameModel;
import game.board.Corner;
import game.board.Edge;
import game.cards.ResourceCard;

import org.junit.Test;

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
		System.out.println("Register user:");
		ServerResponse register = server.registerUser("usrname", "password");
		System.out.println("\t Code: " + register.getCode());
		System.out.println("\t Body: " + register.getBody());
	}
	
	@Test
	public void loginUser() {
		System.out.println("Login user:");
		ServerResponse login = server.loginUser("usrname", "password");
		System.out.println("\t Code: " + login.getCode());
		System.out.println("\t Body: " + login.getBody());
	}
	
	@Test
	public void getAllGames() {
		System.out.println("Get all games:");
		ServerResponse login = server.getAllGames();
		System.out.println("\t Code: " + login.getCode());
		System.out.println("\t Body: " + login.getBody());
	}	
	@Test
	public void createGame() {
		System.out.println("Create game:");
		ServerResponse game = server.createGame("New Game Name");
		System.out.println("\t Code: " + game.getCode());
		System.out.println("\t Body: " + game.getBody());
	}
	
	@Test
	public void joinGame() {
		System.out.println("Join game:");
		ServerResponse join = server.joinGame(0, CatanColor.ORANGE);
		System.out.println("\t Code: " + join.getCode());
		System.out.println("\t Body: " + join.getBody());
	}
	
	@Test
	public void saveGame() {
		System.out.println("Save game:");
		ServerResponse save = server.saveGame(5, "Game Name");
		System.out.println("\t Code: " + save.getCode());
		System.out.println("\t Body: " + save.getBody());
	}
	
//	@Test
//	public void loadGame() {
//		System.out.println("Load game:");
//		ServerResponse load = server.loadGame("New Game Name");
//		System.out.println("\t Code: " + load.getCode());
//		System.out.println("\t Body: " + load.getBody());
//	}
	
	@Test
	public void getGameModel() {
		System.out.println("Get GameModel:");
		ServerResponse model = server.getGameModel(0);
		System.out.println("\t Code: " + model.getCode());
		System.out.println("\t Body: " + model.getBody());
	}
	
	@Test
	public void resetGame() {
		System.out.println("Reset game:");
		ServerResponse reset = server.resetGame();
		System.out.println("\t Code: " + reset.getCode());
		System.out.println("\t Body: " + reset.getBody());
	}
	
	@Test
	public void doGameCommands() {
		System.out.println("Do game commands:");
		ServerResponse commands = server.doGameCommands(new ArrayList<Command>());
		System.out.println("\t Code: " + commands.getCode());
		System.out.println("\t Body: " + commands.getBody());
	}
	
	@Test
	public void getGameCommands() {
		System.out.println("Add AI:");
		ServerResponse commands = server.getGameCommands();
		System.out.println("\t Code: " + commands.getCode());
		System.out.println("\t Body: " + commands.getBody());
	}
	
	@Test
	public void addAI() {
		System.out.println("Add AI:");
		ServerResponse ai = server.addAI("AI TYPE");
		System.out.println("\t Code: " + ai.getCode());
		System.out.println("\t Body: " + ai.getBody());
	}
	
	@Test
	public void getAIList() {
		System.out.println("Get AI list:");
		ServerResponse ai = server.getAIList();
		System.out.println("\t Code: " + ai.getCode());
		System.out.println("\t Body: " + ai.getBody());
	}
	
	@Test
	public void sendChat() {
		System.out.println("Send chat:");
		ServerResponse chat = server.sendChat(0, "message");
		System.out.println("\t Code: " + chat.getCode());
		System.out.println("\t Body: " + chat.getBody());
	}
	
	@Test
	public void rollNumber() {
		System.out.println("Roll number:");
		ServerResponse roll = server.rollNumber(0, 5);
		System.out.println("\t Code: " + roll.getCode());
		System.out.println("\t Body: " + roll.getBody());
	}
	
	@Test
	public void robPlayer() {
		System.out.println("Rob player:");
		ServerResponse rob = server.robPlayer(0, 2, new Corner(new ArrayList<VertexLocation>()));
		System.out.println("\t Code: " + rob.getCode());
		System.out.println("\t Body: " + rob.getBody());
	}
	
	@Test
	public void finishTurn() {
		System.out.println("Finish turn:");
		ServerResponse finish = server.finishTurn(0);
		System.out.println("\t Code: " + finish.getCode());
		System.out.println("\t Body: " + finish.getBody());
	}
	
	@Test
	public void buyDevCard() {
		System.out.println("Buy dev card:");
		ServerResponse buy = server.buyDevCard(0);
		System.out.println("\t Code: " + buy.getCode());
		System.out.println("\t Body: " + buy.getBody());
	}
	
	@Test
	public void playYearOfPlenty() {
		System.out.println("Play year of plenty:");
		ServerResponse year = server.playYearOfPlenty(0, ResourceType.ORE, ResourceType.WHEAT);
		System.out.println("\t Code: " + year.getCode());
		System.out.println("\t Body: " + year.getBody());
	}
	
	@Test
	public void playRoadBuilding() {
		System.out.println("Play road building:");
		ServerResponse road = server.playRoadBuilding(0, new Edge(new ArrayList<EdgeLocation>()),
				new Edge(new ArrayList<EdgeLocation>()));
		System.out.println("\t Code: " + road.getCode());
		System.out.println("\t Body: " + road.getBody());
	}
	
	@Test
	public void playSoldier() {
		System.out.println("Play soldier:");
		ServerResponse soldier = server.playSoldier(0, 1, new HexLocation(0,0));
		System.out.println("\t Code: " + soldier.getCode());
		System.out.println("\t Body: " + soldier.getBody());
	}
	
	@Test
	public void playMonopoly() {
		System.out.println("Play monopoly:");
		ServerResponse monopoly = server.playMonopoly(0, ResourceType.ORE);
		System.out.println("\t Code: " + monopoly.getCode());
		System.out.println("\t Body: " + monopoly.getBody());
	}
	
	@Test
	public void playMonument() {
		System.out.println("Play monument:");
		ServerResponse monument = server.playMonument(0);
		System.out.println("\t Code: " + monument.getCode());
		System.out.println("\t Body: " + monument.getBody());
	}
	
	@Test
	public void buildRoad() {
		System.out.println("Build road:");
		ServerResponse road = server.buildRoad(0, new Edge(new ArrayList<EdgeLocation>()));
		System.out.println("\t Code: " + road.getCode());
		System.out.println("\t Body: " + road.getBody());
	}
	
	@Test
	public void buildSettlement() {
		System.out.println("Build settlement:");
		ServerResponse settlemet = server.buildSettlement(0,  new Corner(new ArrayList<VertexLocation>()), true);
		System.out.println("\t Code: " + settlemet.getCode());
		System.out.println("\t Body: " + settlemet.getBody());
	}
	
	@Test
	public void buildCity() {
		System.out.println("Build city:");
		ServerResponse city = server.buildCity(0,  new Corner(new ArrayList<VertexLocation>()), true);
		System.out.println("\t Code: " + city.getCode());
		System.out.println("\t Body: " + city.getBody());
	}
	
	@Test
	public void offerTrade() {
		System.out.println("Offer trade:");
		ServerResponse trade = server.offerTrade(0, new ArrayList<ResourceType>(), 3);
		System.out.println("\t Code: " + trade.getCode());
		System.out.println("\t Body: " + trade.getBody());
	}
	
	@Test
	public void acceptTrade() {
		System.out.println("Accept trade:");
		ServerResponse trade = server.acceptTrade(0, true);
		System.out.println("\t Code: " + trade.getCode());
		System.out.println("\t Body: " + trade.getBody());
	}
	
	@Test
	public void maritimeTrade() {
		System.out.println("Maritime trade:");
		ServerResponse trade = server.maritimeTrade(0, 3, ResourceType.ORE, ResourceType.SHEEP);
		System.out.println("\t Code: " + trade.getCode());
		System.out.println("\t Body: " + trade.getBody());
	}
	
	@Test
	public void discardCards() {
		System.out.println("Discard cards:");
		ServerResponse trade = server.discardCards(0, new ArrayList<ResourceCard>());
		System.out.println("\t Code: " + trade.getCode());
		System.out.println("\t Body: " + trade.getBody());
	}
	
	@Test
	public void changeLogLevel() {
		System.out.println("Change log level:");
		ServerResponse trade = server.changeLogLevel(LogLevel.FINE);
		System.out.println("\t Code: " + trade.getCode());
		System.out.println("\t Body: " + trade.getBody());
	}
}
