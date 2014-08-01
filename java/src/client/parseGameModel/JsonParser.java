package client.parseGameModel;

import game.GameModel;
import game.TradeOffer;
import game.TurnTracker;
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



