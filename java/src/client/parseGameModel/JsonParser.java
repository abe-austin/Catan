package client.parseGameModel;

import game.GameModel;
import game.TradeOffer;
import game.TurnTracker;
import game.bank.Bank;
import game.board.HexTile;
import game.board.NumberToken;
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
		parseRandomHexes();
		parseRandomNumbers();
		parseRandomPorts();
		parseCheckDiscard();
		parseGameName();
		
		return game;	
	}
	
	public void parsePlayer() {
	}
	
	public void parsePlayers() {
		
		JSONArray players = jsonObject.getJSONArray("players");
		JSONObject player = players.getJSONObject(0);
		
		
	}
	
	public void parseBoard() {
		JSONObject board = jsonObject.getJSONObject("board");
		
		//parse all tiles
		JSONArray tiles = board.getJSONArray("tiles");
		for(int i=0; i<tiles.length(); i++) {
			JSONObject tile = tiles.getJSONObject(i);
			parseTile(tile);
		}
	}
	
	public void parseTile(JSONObject tile) {
		HexTile newTile;
		
		//Some tiles have
		if(tile.has("type")) {
			String type = tile.getString("type");
		}
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



