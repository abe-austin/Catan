package client.parseGameModel;

import game.GameModel;

import org.json.*;



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
		
		String username = player.getString("username");
		System.out.println(username);
	}
	
	public void parsePlayers() {
		
		JSONArray players = jsonObject.getJSONArray("players");
		JSONObject player = players.getJSONObject(0);
		
		
	}
	
	public void parseBoard() {
		
	}
	
	public void parseBank() {
		
	}
	
	public void parseGameHistory() {
		
	}
	
	public void parseTurnTracker() {
		
	}
	
	public void parseTradeOffer() {
		
	}
	
	public void parseVersion() {
		
	}
	
	public void parseWinner() {
		
	}
	
	public void parseId() {
		
	}
	
	public void parseRandomHexes() {
		
	}
	
	public void parseRandomNumbers() {
		
	}
	
	public void parseRandomPorts() {
		
	}
	
	public void parseCheckDiscard() {
		
	}
	
	public void parseGameName() {
		
	}
}



