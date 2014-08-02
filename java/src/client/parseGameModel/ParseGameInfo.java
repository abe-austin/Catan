package client.parseGameModel;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import shared.definitions.CatanColor;
import client.data.GameInfo;
import client.data.PlayerInfo;

public class ParseGameInfo {

	JSONObject jsonObject;
	GameInfo gameInfo;
	
	ParseGameInfo(String jsonString) {
		this.jsonObject = new JSONObject(jsonString);
	}
	
	public GameInfo doParse() {
		
		parseId();
		parseTitle();
		parsePlayerInfos();
	
		return gameInfo;
	}
	
	public void parseId() {
		gameInfo.setId(jsonObject.getInt("id"));
	}
	
	public void parseTitle() {
		gameInfo.setTitle(jsonObject.getString("title"));
	}
	
	public PlayerInfo parsePlayerInfo(JSONObject player, int index) {
	
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setId(player.getInt("id"));
		playerInfo.setPlayerIndex(index);
		playerInfo.setCatanColor(CatanColor.valueOf(player.getString("catanColor")));
		playerInfo.setColor(player.getString("color").toLowerCase());
		
		return playerInfo;
	}
	
	public void parsePlayerInfos() {
		
		JSONArray players = jsonObject.getJSONArray("players");
		List<PlayerInfo> playersInfos = null;
		
		for(int i=0; i<players.length(); i++) {
			if(!players.isNull(i)) {
				PlayerInfo playerInfo = parsePlayerInfo(players.getJSONObject(i), i);
				playersInfos.add(playerInfo);
			}
		}
	}
}

