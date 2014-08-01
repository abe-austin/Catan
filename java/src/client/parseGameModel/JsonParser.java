package client.parseGameModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import game.GameModel;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;
import game.pieces.City;
import game.pieces.Road;
import game.pieces.Settlement;

import org.json.*;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import player.Player;
import game.TradeOffer;
import game.TurnTracker;
import game.bank.Bank;
import game.board.DesertTile;
import game.board.HexTile;
import game.board.NumberToken;
import game.board.OceanTile;
import game.board.PortTile;
import game.board.ResourceTile;

import org.json.*;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;

public class JsonParser {
	
	JSONObject jsonObject;
	GameModel game = new GameModel();
	
	public JsonParser(String jsonString) {
		
		this.jsonObject = new JSONObject(jsonString);	
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
	
	public void parsePorts() {
		
	}
	
	public ResourceCard parseResourceCards(JSONObject card) {
		
		ResourceCard resourceCard = null;
		
		if(card.get("resourceType").equals("BRICK")) {
			resourceCard = new ResourceCard(ResourceType.BRICK);
		}
		if(card.get("resourceType").equals("WHEAT")) {
			resourceCard = new ResourceCard(ResourceType.WHEAT);
		}
		if(card.get("resourceType").equals("SHEEP")) {
			resourceCard = new ResourceCard(ResourceType.SHEEP);
		}
		if(card.get("resourceType").equals("ORE")) {
			resourceCard = new ResourceCard(ResourceType.ORE);
		}
		if(card.get("resourceType").equals("WOOD")) {
			resourceCard = new ResourceCard(ResourceType.WOOD);
		}
		
		return resourceCard;
	}
	
	public DevelopmentCard parseDevelopmentCards(JSONObject card) {
		
		DevelopmentCard developmentCard = null;
		
		if(card.get("developmentType").equals("YEAR_OF_PLENTY")) {
			developmentCard = new DevelopmentCard(DevCardType.YEAR_OF_PLENTY);
		}
		if(card.get("developmentType").equals("MONOPOLY")) {
			developmentCard = new DevelopmentCard(DevCardType.MONOPOLY);
		}
		if(card.get("developmentType").equals("MONUMENT")) {
			developmentCard = new DevelopmentCard(DevCardType.MONUMENT);
		}
		if(card.get("developmentType").equals("SOLDIER")) {
			developmentCard = new DevelopmentCard(DevCardType.SOLDIER);
		}
		if(card.get("developmentType").equals("ROAD_BUILDER")) {
			developmentCard = new DevelopmentCard(DevCardType.ROAD_BUILD);
		}
		
		return developmentCard;
	}
	
	public SpecialCard parseSpecialCards(JSONObject card) {
		
		SpecialCard specialCard = null;
		
//		if() {
//		developmentCard = new DevelopmentCard(DevCardType.SOLDIER);
//		}
//		if() {
//		developmentCard = new DevelopmentCard(DevCardType.ROAD_BUILD);
//		}
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
	
		ArrayList<PortType> playerPorts = new ArrayList<PortType>();
		//for each port
		
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
	
//		HashSet<SpecialCard> specialCards = new HashSet<SpecialCard>();
//		for(int i=0; i<player.getJSONArray("specialCards").length(); i++) {
//			parseSpecialCards();
//			//specialCards.add();
//		}
		
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
		
		//parse all tiles
		JSONArray tiles = board.getJSONArray("tiles");
		for(int i=0; i<tiles.length(); i++) {
			JSONObject tile = tiles.getJSONObject(i);
			parseTile(tile);
		}
		
		//Not sure why for pieces we are getting a sequence of objects that have nothing but null objects
	}
	
	public void parseTile(JSONObject tile) {
		HexTile newTile;
		String type = "ocean";
		
		//Some tiles have
		if(tile.has("type")) {
			type = tile.getString("type");
			
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
		
		newTile.setCoordinates(tile.getInt("x"), tile.getInt("x"));
		newTile.setHasRobber(tile.getBoolean("hasRobber"));
		
		//Now need to add to newTile the edges/corners
		//Add each tile to a list of tiles to be stored in our board object
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
		
	}
	
	public void parseTurnTracker() {
		JSONObject turnTrackerJson = jsonObject.getJSONObject("turnTracker");
		TurnTracker turnTracker = new TurnTracker(turnTrackerJson.getString("status"),
				turnTrackerJson.getInt("currentTurn"),
				turnTrackerJson.getInt("longestRoad"),
				turnTrackerJson.getInt("largestArmy"));
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



