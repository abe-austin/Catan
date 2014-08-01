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



public class JsonParser {
	
	JSONObject jsonObject;
	GameModel game = new GameModel();
	
	public JsonParser(String jsonString) {
		
		this.jsonObject = new JSONObject(jsonString);	
		doParse();
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
		Player[] gamePlayers = new Player[players.length()];
		
		for(int i=0; i<players.length(); i++) {
			if(!players.isNull(i)) {
				gamePlayers[i] = parsePlayer(players.getJSONObject(i));
			}
		}
		
		game.setPlayers(gamePlayers);
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



