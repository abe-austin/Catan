package client.parseJson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.GameModel;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;
import game.pieces.City;
import game.pieces.Road;
import game.pieces.Robber;
import game.pieces.Settlement;

import org.json.*;

import shared.definitions.CatanColor;
import shared.definitions.Command;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;
import player.Player;
import game.ChatLog;
import game.GameHistory;
import game.TradeOffer;
import game.TurnTracker;
import game.bank.Bank;
import game.board.BuildWorld;
import game.board.DesertTile;
import game.board.HexTile;
import game.board.NumberToken;
import game.board.OceanTile;
import game.board.PortTile;
import game.board.ResourceTile;

import org.json.*;

import client.parse.ParsedChat;
import client.parse.ParsedPort;
import client.parse.ParsedStructure;
import client.parse.ParsedTile;
import controller.ControllerFacade;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public class ParseGameModel {
	
	JSONObject jsonObject;
	GameModel game = new GameModel();
	
	public ParseGameModel(String jsonString) {
		this.jsonObject = new JSONObject(jsonString);	
		//System.out.println(jsonString.toString());
	}
	
	public GameModel doParse() {
		
		parsePlayers();
		parseBoard();
		parseBank();
		parseGameHistory();
		parseTurnTracker();
		parseTradeOffer();
		parseVersion();
		parseWinner();
		parseId();
		//parseRandomHexes();
		//parseRandomNumbers();
		//parseRandomPorts();
		parseCheckDiscard();
		parseGameName();
		
		return game;	
	}
	
	public BoardPiece parseBoardPiece(JSONObject boardPiece) {
		
		BoardPiece piece = null;
		if(boardPiece.getString("cost").equals("CITY")) {
			piece = new City();
		}
		if(boardPiece.getString("cost").equals("SETTLEMENT")) {
			piece = new Settlement();
		}
		if(boardPiece.getString("cost").equals("ROAD")) {
			piece = new Road();
		}
		piece.setActive(boardPiece.getBoolean("active"));
		piece.setPlayer(boardPiece.getString("player"));
                
		return piece;
	}
	
	public Set<BoardPiece> parseBoardPieces(JSONArray boardPieces) {
		
		Set<BoardPiece> pieces = new HashSet<BoardPiece>();
		
		for(int i=0; i<boardPieces.length(); i++) {
			BoardPiece boardPiece = parseBoardPiece(boardPieces.getJSONObject(i));
			pieces.add(boardPiece);
		}
		
		return pieces;
	}
	
//	public PortType parsePorts(JSONObject port) {
//		
//		PortType portType = null;
//	
//		switch(port.getString("portType")) {
//				case "WOOD":
//					portType = PortType.WOOD;
//					break;
//				case "BRICK":
//					portType = PortType.BRICK;
//					break;
//				case "SHEEP":
//					portType = PortType.SHEEP;
//					break;
//				case "WHEAT":
//					portType = PortType.WHEAT;
//					break;
//				case "ORE":
//					portType = PortType.ORE;
//					break;
//				case "THREE":
//					portType = PortType.THREE;
//					break;
//		}
//		
//		return portType;	
//	}
	
	public ResourceCard parseResourceCards(JSONObject card) {
		
		ResourceCard resourceCard = null;
		
		switch(card.getString("resourceType")) {
				case "BRICK":
					resourceCard = new ResourceCard(ResourceType.BRICK);
					break;
				case "WHEAT":
					resourceCard = new ResourceCard(ResourceType.WHEAT);
					break;
				case "SHEEP":
					resourceCard = new ResourceCard(ResourceType.SHEEP);
					break;
				case "ORE":
					resourceCard = new ResourceCard(ResourceType.ORE);
					break;
				case "WOOD":
					resourceCard = new ResourceCard(ResourceType.WOOD);
					break;
		}
		
		return resourceCard;
	}
	
	public DevelopmentCard parseDevelopmentCards(JSONObject card) {
		
		DevelopmentCard developmentCard = null;
		
		switch(card.getString("developmentType")) {
				case "YEAR_OF_PLENTY":
					developmentCard = new DevelopmentCard(DevCardType.YEAR_OF_PLENTY);
					break;
				case "MONOPOLY":
					developmentCard = new DevelopmentCard(DevCardType.MONOPOLY);
					break;
				case "MONUMENT":
					developmentCard = new DevelopmentCard(DevCardType.MONUMENT);
					break;
				case "SOLDIER":
					developmentCard = new DevelopmentCard(DevCardType.SOLDIER);
					break;
				case "ROAD_BUILDER":
					developmentCard = new DevelopmentCard(DevCardType.ROAD_BUILD);
					break;
		}
		boolean old = card.getBoolean("old");
                developmentCard.setOld(old);
		return developmentCard;
	}
	
	public SpecialCard parseSpecialCards(JSONObject card) {
		
		SpecialCard specialCard = null;
		
		if(card.get("specialType").equals("LARGEST_ARMY")) {
			specialCard = new SpecialCard(SpecialCardType.LARGEST_ARMY);
		}
		else if(card.get("specialType").equals("LONGEST_ROAD")) {
			specialCard = new SpecialCard(SpecialCardType.LONGEST_ROAD);
		}
		
		return specialCard;
	}
	
	public Player parsePlayer(JSONObject player) {
		
		Player newPlayer = new Player();
		newPlayer.setColor(CatanColor.valueOf(player.getString("color")));
		JSONArray boardPieces = player.getJSONArray("boardPieces");
		parseBoardPieces(boardPieces);
		
		JSONObject points = player.getJSONObject("points");
		for(int i = 0; i<points.getInt("points"); i++) {
			newPlayer.addPoint();
		}
		
		newPlayer.setSoldiersPlayed(player.getInt("soldiersPlayed"));
	
//		ArrayList<PortType> playerPorts = new ArrayList<PortType>();
//		JSONArray ports = player.getJSONArray("ports");
//		for(int i=0; i<ports.length(); i++) {
//			if(!ports.isNull(i)) {
//				PortType port = parsePorts(ports.getJSONObject(i));
//				playerPorts.add(port);	
//			}
//		}
//		newPlayer.setPlayerPorts(playerPorts);
		
		newPlayer.setIndex(player.getInt("index"));
		newPlayer.setUsername(player.getString("username"));
		newPlayer.setDiscarded(player.getBoolean("discarded"));
		
		HashSet<ResourceCard> resourceCards = new HashSet<ResourceCard>();
		JSONArray resources = player.getJSONArray("resourceCards");
		for(int i=0; i<resources.length(); i++) {
			if(!resources.isNull(i)) {
				ResourceCard resourceCard = parseResourceCards(resources.getJSONObject(i));
				resourceCards.add(resourceCard);	
			}
		}
		newPlayer.setResourceCards(resourceCards);
		
		HashSet<DevelopmentCard> developmentCards = new HashSet<DevelopmentCard>();
		JSONArray developments = player.getJSONArray("developmentCards");
		for(int i=0; i<developments.length(); i++) {
			if(!developments.isNull(i)) {
				DevelopmentCard developmentCard = parseDevelopmentCards(developments.getJSONObject(i));
				developmentCards.add(developmentCard);
			}
		}
		newPlayer.setDevelopmentCards(developmentCards);
	
		HashSet<SpecialCard> specialCards = new HashSet<SpecialCard>();
		JSONArray specials = player.getJSONArray("specialCards");
		for(int i=0; i<specials.length(); i++) {
			if(specials.isNull(i)) {
				SpecialCard specialCard = parseSpecialCards(specials.getJSONObject(i));
				specialCards.add(specialCard);
			}
		}
		newPlayer.setSpecialCards(specialCards);
		
		return newPlayer;
	}
	
	public void parsePlayers() {
		
		JSONArray players = jsonObject.getJSONArray("players");
		
		int playerCount = 0;
		for(int i=0; i<players.length(); i++) {
			if(!players.isNull(i))
				playerCount++;
		}

		Player[] gamePlayers = new Player[playerCount];
		Player[] test = new Player[4];
		for(int i=0; i<players.length(); i++) {
			if(!players.isNull(i)) {
				gamePlayers[i] = parsePlayer(players.getJSONObject(i));
			}
		}
		
		game.setPlayers(gamePlayers);
	}
	
	public void parseBoard() {
		JSONObject board = jsonObject.getJSONObject("board");
		List<HexTile> hexTiles = new ArrayList<HexTile>();
		
		//parse all tiles
		JSONArray tiles = board.getJSONArray("tiles");
		for(int i=0; i<tiles.length(); i++) {
			JSONObject tile = tiles.getJSONObject(i);
			HexTile hTile = parseTile(tile);
			hexTiles.add(hTile);
		}
		
		BuildWorld build = new BuildWorld(hexTiles);
		hexTiles = build.getTiles();
		
		
		ArrayList<ParsedTile> parsedTiles= new ArrayList<ParsedTile>();
		ArrayList<ParsedPort> parsedPorts = new ArrayList<ParsedPort>();
		
		for(int j = 0; j < hexTiles.size(); j++) {
			//See what hexTile this location is, determine if port or mainland
			int x = hexTiles.get(j).getX();
			int y = hexTiles.get(j).getY();
			
			if( (x == 1 && y == -3) || (x == 3 && y == -3)  || (x == 3 && y == -1) || (x == 2 && y == 1) || (x == 0 && y == 3) ||
				(x == -2 && y == 3)	|| (x == -3 && y == 2) || (x == -3 && y == 0) || (x == -1 && y == -2)) {
				String type = "";
				int ratio = 2;
				switch(((PortTile)hexTiles.get(j)).getPortType()) {
					case THREE: type = "three"; ratio = 3; break;
					case WHEAT: type = "wheat"; break;
					case ORE: type = "ore"; break;
					case WOOD: type = "wood"; break;
					case SHEEP: type = "sheep"; break;
					case BRICK: type = "brick"; break;
				}
				
				ParsedPort parsedPort = new ParsedPort(type, ratio, x, y, "");
				parsedPorts.add(parsedPort);
			}
			
			else if( (x != 0 || y != -3) && (x != 2 || y != -3) && (x != 3 || y != -2) && (x != 3 || y != 0) && (x != 1 || y != 2)
				  && (x != -1 || y != 3) && (x != -3 || y != 3) && (x != -3 || y != 1) && (x != -2 || y != -1)) {
				
				String type = "DESERT";
				int number = -1;
				
				if(!hexTiles.get(j).getType().equals(HexType.DESERT)) {
					switch(((ResourceTile)hexTiles.get(j)).getResourceType()) {
						case WHEAT: type = "wheat"; break;
						case ORE: type = "ore"; break;
						case WOOD: type = "wood"; break;
						case SHEEP: type = "sheep"; break;
						case BRICK: type = "brick"; break;
						default: break;
					}
					number = ((ResourceTile)hexTiles.get(j)).getToken().getValue();
				}
				ParsedTile parsedTile = new ParsedTile(x, y, type, number);
				parsedTiles.add(parsedTile);
			}	
		}
		
		
		game.getBoard().updateBoardResources(parsedTiles);
		game.getBoard().updateBoardPorts(parsedPorts);
		
		//Parse robber
		JSONObject robber = board.getJSONObject("rob");
		JSONObject loc = robber.getJSONObject("location");
		game.getBoard().updateRobber(loc.getInt("x"), loc.getInt("y"));
		//Do we need to get the view updated
		
		
		//Parse structures
		ArrayList<ParsedStructure> parsedStructures = new ArrayList<ParsedStructure>();
		JSONArray structures = board.getJSONArray("theStructures");
		for(int i=0; i<structures.length(); i++) {
			JSONObject structure = structures.getJSONObject(i);
			ParsedStructure parsedStructure = new ParsedStructure(structure.getInt("owner"), structure.getInt("x"),
					structure.getInt("y"), structure.getString("direction"), structure.getString("type"));
			parsedStructures.add(parsedStructure);
		}
		game.getBoard().updateStructures(parsedStructures);
	}
	
	
	public HexTile parseTile(JSONObject tile) {
		HexTile newTile;
		
		//Some tiles have
		if(tile.has("type")) {
			String type = tile.getString("type");
			
			if(tile.getInt("x") == -3 || tile.getInt("x") == 3 || tile.getInt("y") == -3 || tile.getInt("y") == 3 ||
				(tile.getInt("x") + tile.getInt("y") == 3) || (tile.getInt("x") + tile.getInt("y") == -3)) {//Is port
				
				if(type.equals("THREE"))
					newTile = new PortTile(PortType.THREE);		
				else if(type.equals("WOOD"))
					newTile = new PortTile(PortType.WOOD);
				else if(type.equals("WHEAT"))
					newTile = new PortTile(PortType.WHEAT);
				else if(type.equals("ORE"))
					newTile = new PortTile(PortType.ORE);
				else if(type.equals("BRICK"))
					newTile = new PortTile(PortType.BRICK);
				else
					newTile = new PortTile(PortType.SHEEP);
			}
			
			else { 
				JSONObject number = tile.getJSONObject("token");
				if(type.equals("WOOD")) 
					newTile = new ResourceTile(ResourceType.WOOD, new NumberToken(number.getInt("value")));
				else if(type.equals("ORE"))
					newTile = new ResourceTile(ResourceType.ORE, new NumberToken(number.getInt("value")));
				else if(type.equals("WHEAT"))
					newTile = new ResourceTile(ResourceType.WHEAT, new NumberToken(number.getInt("value")));
				else if(type.equals("BRICK"))
					newTile = new ResourceTile(ResourceType.BRICK, new NumberToken(number.getInt("value")));
				else
					newTile = new ResourceTile(ResourceType.SHEEP, new NumberToken(number.getInt("value")));
			}
		}
		
		else {//is ocean or desert
			if(tile.getInt("x") == -3 || tile.getInt("x") == 3 || tile.getInt("y") == -3 || tile.getInt("y") == 3 ||
					(tile.getInt("x") + tile.getInt("y") == 3) || (tile.getInt("x") + tile.getInt("y") == -3)) 
				newTile = new OceanTile();
			else 
				newTile = new DesertTile();
		}
		
		newTile.setCoordinates(tile.getInt("x"), tile.getInt("y"));
		newTile.setHasRobber(tile.getBoolean("hasRobber"));
		
		return newTile;
	}

	
	public void parseBank() {
		JSONObject bankJson = jsonObject.getJSONObject("bank");
		Bank bank = new Bank();
	
		JSONArray resourceCards = bankJson.getJSONArray("resourceCards");
		int brick = 0;
		int ore = 0;
		int sheep = 0;
		int wheat = 0;
		int wood = 0;
		for(int i=0; i<resourceCards.length(); i++) {
			JSONObject resourceCard = resourceCards.getJSONObject(i);
			String resource = resourceCard.getString("resourceType");
			switch(resource) {
				case "BRICK":
					brick++;
					break;
				case "ORE":
					ore++;
					break;
				case "SHEEP":
					sheep++;
					break;
				case "WHEAT":
					wheat++;
					break;
				case "WOOD":
					wood++;
					break;
			}
		}
		bank.setResources(brick, ore, sheep, wheat, wood);

		JSONArray devCards = bankJson.getJSONArray("developmentCards");
		int soldier = 0;
		int yearOfPlenty = 0;
		int monopoly = 0;
		int roadBuild = 0;
		int monument = 0;
		for(int i=0; i<devCards.length(); i++) {
			JSONObject devCard = devCards.getJSONObject(i);
			String dev = devCard.getString("developmentType");
			switch(dev) {
				case "SOLDIER":
					soldier++;
					break;
				case "YEAR_OF_PLENTY":
					yearOfPlenty++;
					break;
				case "MONOPOLY":
					monopoly++;
					break;
				case "ROAD_BUILD":
					roadBuild++;
					break;
				case "MONUMENT":
					monument++;
					break;
			}
		}
		bank.setDevCards(monopoly, roadBuild, yearOfPlenty, monument, soldier);
		
		JSONArray specialCards = bankJson.getJSONArray("specialCards");
		boolean longestRoad = false;
		boolean largestArmy = false;
		for(int i=0; i<specialCards.length(); i++) {
			JSONObject specialCard = specialCards.getJSONObject(i);
			String special = specialCard.getString("special");
			switch(special) {
				case "LONGEST_ROAD":
					longestRoad = true;
					break;
				case "LARGEST_ARMY":
					largestArmy = true;
					break;
			}
		}
		bank.setSpecialCards(largestArmy, longestRoad);
		
		game.setBank(bank);
	}
	
	public void parseGameHistory() {
		GameHistory gameHistory = new GameHistory();
		JSONObject gameHistoryJson = jsonObject.getJSONObject("gameHistory");
				
		if(!gameHistoryJson.isNull("chatlog")) {
			ChatLog chatlog = parseChat(gameHistoryJson.getJSONObject("chatlog"));
			gameHistory.setChatlog(chatlog);
		}
		
		if(!gameHistoryJson.isNull("gameCommands")) {
			List<Command> commands = parseGameCommands(
					gameHistoryJson.getJSONArray("gameCommands"));
			gameHistory.setGameCommands(commands);
		}
		
		game.setGameHistory(gameHistory);
	}
	
	public List<Command> parseGameCommands(JSONArray commandsJson) {
		List<Command> commands = new ArrayList<Command>();
		
		for(int i=0; i<commandsJson.length(); i++) {
			JSONObject commandJson = commandsJson.getJSONObject(i);
			Command command = new Command(
					commandJson.getString("source"),
					commandJson.getString("command"));
			commands.add(command);
		}
		
		return commands;
	}
	public ChatLog parseChat(JSONObject chatLogJson) {
		ChatLog chatlog = new ChatLog();
		JSONArray linesJson = chatLogJson.getJSONArray("lines");
		
		for(int i=0; i<linesJson.length(); i++) {
			JSONObject lineJson = linesJson.getJSONObject(i);			
			ParsedChat chatLine = new ParsedChat(
					lineJson.getString("source"), 
					lineJson.getString("message"));
			chatlog.addChatLine(chatLine);
		}
		
		return chatlog;
	}
	
	public void parseTurnTracker() {
		JSONObject turnTrackerJson = jsonObject.getJSONObject("turnTracker");
		TurnTracker turnTracker = new TurnTracker(turnTrackerJson.getString("status"),
				turnTrackerJson.getInt("currentTurn"),
				turnTrackerJson.getInt("longestRoad"),
				turnTrackerJson.getInt("largestArmy"));
                                turnTracker.setSetup(turnTrackerJson.getBoolean("setup"));
                                turnTracker.setFirstTurn(turnTrackerJson.getBoolean("firstTurn"));
		game.setTurnTracker(turnTracker);
	}
	
	public void parseTradeOffer() {
		JSONObject tradeOfferJson = jsonObject.getJSONObject("tradeOffer");
		TradeOffer tradeOffer = new TradeOffer(tradeOfferJson.getInt("senderIndex"),
				tradeOfferJson.getInt("receiverIndex"),
				tradeOfferJson.getInt("brick"),
				tradeOfferJson.getInt("ore"),
				tradeOfferJson.getInt("sheep"),
				tradeOfferJson.getInt("wood"),
				tradeOfferJson.getInt("wheat"));
		game.setTradeOffer(tradeOffer);
	}
	
	public void parseVersion() {
		game.setVersion(jsonObject.getInt("version"));
	}
	
	public void parseWinner() {
		game.setWinner(jsonObject.getInt("winner"));
	}
	
	public void parseId() {
		game.setGameId(jsonObject.getInt("id"));
	}
	
	public void parseRandomHexes() {
		game.setRandomHexes(jsonObject.getBoolean("randomHexes"));
	}
	
	public void parseRandomNumbers() {
		game.setRandomNumbers(jsonObject.getBoolean("randomNumbers"));
	}
	
	public void parseRandomPorts() {
		game.setRandomPorts(jsonObject.getBoolean("randomPorts"));
	}
	
	public void parseCheckDiscard() {
		game.setCheckDiscard(jsonObject.getBoolean("checkDiscard"));
	}
	
	public void parseGameName() {
		game.setGameName(jsonObject.getString("gameName"));
	}
}