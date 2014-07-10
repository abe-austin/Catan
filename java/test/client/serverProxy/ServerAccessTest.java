package client.serverProxy;


import java.util.ArrayList;

import game.GameModel;
import game.board.Corner;
import game.board.Edge;
import game.cards.ResourceCard;

import org.junit.Test;

import shared.communication.CreateGameRes;
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
		server = new ServerProxyFacade();
	}
	
	@Test
	public void registerUser() {
		System.out.println("Register user:");
		String register = server.registerUser("usrname", "password");
		System.out.println("\tResponse: " + register);
	}
	
	@Test
	public void loginUser() {
		System.out.println("Login user:");
		String login = server.loginUser("usrname", "password");
		System.out.println("\tResponse: " + login);
	}
	
	//TODO games/list
	
	@Test
	public void createGame() {
		System.out.println("Create game:");
		CreateGameRes game = server.createGame("New Game Name");
		System.out.println("\tID: " + game.getId());
		System.out.println("\tTitle: " + game.getTitle());
		System.out.println("\tPlayers: " + game.getPlayers());

	}
	
	@Test
	public void joinGame() {
		System.out.println("Join game:");
		String join = server.joinGame(0, CatanColor.ORANGE);
		System.out.println("\t Response: " + join);
	}
	
	@Test
	public void saveGame() {
		System.out.println("Save game:");
		String save = server.saveGame(5, "Game Name");
		System.out.println("\t Response: " + save);
	}
	
	@Test
	public void loadGame() {
		System.out.println("Load game:");
		GameModel load = server.loadGame("New Game Name");
		System.out.println("\t Response: " + load);
	}
	
	@Test
	public void getGameModel() {
		System.out.println("Get GameModel:");
		GameModel model = server.getGameModel(0);
		System.out.println("\t Response: " + model);
	}
	
	@Test
	public void resetGame() {
		System.out.println("Reset game:");
		GameModel reset = server.resetGame();
		System.out.println("\t Response: " + reset);
	}
	
	@Test
	public void doGameCommands() {
		System.out.println("Do game commands:");
		GameModel commands = server.doGameCommands(new ArrayList<Command>());
		System.out.println("\t Response: " + commands);
	}
	
	//TODO getCommands
	
	@Test
	public void addAI() {
		System.out.println("Add AI:");
		String ai = server.addAI("AI TYPE");
		System.out.println("\t Response: " + ai);
	}
	
	@Test
	public void sendChat() {
		System.out.println("Send chat:");
		GameModel chat = server.sendChat(0, "message");
		System.out.println("\t Response: " + chat);
	}
	
	@Test
	public void rollNumber() {
		System.out.println("Roll number:");
		GameModel roll = server.rollNumber(0, 5);
		System.out.println("\t Response: " + roll);
	}
	
	@Test
	public void robPlayer() {
		System.out.println("Rob player:");
		GameModel rob = server.robPlayer(0, 2, new Corner(new ArrayList<VertexLocation>()));
		System.out.println("\t Response: " + rob);
	}
	
	@Test
	public void finishTurn() {
		System.out.println("Finish turn:");
		GameModel finish = server.finishTurn(0);
		System.out.println("\t Response: " + finish);
	}
	
	@Test
	public void buyDevCard() {
		System.out.println("Buy dev card:");
		GameModel buy = server.buyDevCard(0);
		System.out.println("\t Response: " + buy);
	}
	
	@Test
	public void playYearOfPlenty() {
		System.out.println("Play year of plenty:");
		GameModel year = server.playYearOfPlenty(0, ResourceType.ORE, ResourceType.WHEAT);
		System.out.println("\t Response: " + year);
	}
	
	@Test
	public void playRoadBuilding() {
		System.out.println("Play road building:");
		GameModel road = server.playRoadBuilding(0, new Edge(new ArrayList<EdgeLocation>()),
				new Edge(new ArrayList<EdgeLocation>()));
		System.out.println("\t Response: " + road);
	}
	
	@Test
	public void playSoldier() {
		System.out.println("Play soldier:");
		GameModel soldier = server.playSoldier(0, 1, new HexLocation(0,0));
		System.out.println("\t Response: " + soldier);
	}
	
	@Test
	public void playMonopoly() {
		System.out.println("Play monopoly:");
		GameModel monopoly = server.playMonopoly(0, ResourceType.ORE);
		System.out.println("\t Response: " + monopoly);
	}
	
	@Test
	public void playMonument() {
		System.out.println("Play monument:");
		GameModel monument = server.playMonument(0);
		System.out.println("\t Response: " + monument);
	}
	
	@Test
	public void buildRoad() {
		System.out.println("Build road:");
		GameModel road = server.buildRoad(0, new Edge(new ArrayList<EdgeLocation>()));
		System.out.println("\t Response: " + road);
	}
	
	@Test
	public void buildSettlement() {
		System.out.println("Build settlement:");
		GameModel settlemet = server.buildSettlement(0,  new Corner(new ArrayList<VertexLocation>()), true);
		System.out.println("\t Response: " + settlemet);
	}
	
	@Test
	public void buildCity() {
		System.out.println("Build city:");
		GameModel city = server.buildCity(0,  new Corner(new ArrayList<VertexLocation>()), true);
		System.out.println("\t Response: " + city);
	}
	
	@Test
	public void offerTrade() {
		System.out.println("Offer trade:");
		GameModel trade = server.offerTrade(0, new ArrayList<ResourceType>(), 3);
		System.out.println("\t Response: " + trade);
	}
	
	@Test
	public void acceptTrade() {
		System.out.println("Accept trade:");
		GameModel trade = server.acceptTrade(0, true);
		System.out.println("\t Response: " + trade);
	}
	
	@Test
	public void maritimeTrade() {
		System.out.println("Maritime trade:");
		GameModel trade = server.maritimeTrade(0, 3, ResourceType.ORE, ResourceType.SHEEP);
		System.out.println("\t Response: " + trade);
	}
	
	@Test
	public void discardCards() {
		System.out.println("Discard cards:");
		GameModel trade = server.discardCards(0, new ArrayList<ResourceCard>());
		System.out.println("\t Response: " + trade);
	}
	
	@Test
	public void changeLogLevel() {
		System.out.println("Change log level:");
		String trade = server.changeLogLevel(LogLevel.FINE);
		System.out.println("\t Response: " + trade);
	}
}
