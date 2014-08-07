package client.parseJson;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import shared.definitions.CatanColor;
import client.data.GameInfo;
import client.data.PlayerInfo;

public class ParseGameInfo {

	private JSONArray jsonArray;
	private GameInfo gameInfo;
	private JSONObject jsonObject;
	
	public ParseGameInfo(String jsonString) {
		
		if(!jsonString.equals("[]")) {
            this.jsonArray = new JSONArray(jsonString);
            gameInfo = new GameInfo();    
        }	
	}
	
	public ArrayList<GameInfo> doParse() {
		
		ArrayList<GameInfo> gameInfos = new ArrayList<GameInfo>();
		
		if(jsonArray == null){
			return gameInfos;
        }
		
		for(int i=0; i<jsonArray.length(); i++) {
                        gameInfo= new GameInfo();
			jsonObject = jsonArray.getJSONObject(i);
			parseId();
			parseTitle();
			parsePlayerInfos();
                        gameInfos.add(gameInfo);
		}
		
		return gameInfos;
	}
	
	public void parseId() {
		
		if(!jsonObject.isNull("id")) {
			gameInfo.setId(jsonObject.getInt("id"));
		}
	}
	
	public void parseTitle() {
		
		if(!jsonObject.isNull("title")) {
			gameInfo.setTitle(jsonObject.getString("title"));
		}
	}
	
	public PlayerInfo parsePlayerInfo(JSONObject player, int index) {
	
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setId(player.getInt("id"));
		playerInfo.setPlayerIndex(index);
		playerInfo.setName(player.getString("name"));
		playerInfo.setCatanColor(CatanColor.valueOf(player.getString("catanColor")));
		playerInfo.setColor(player.getString("catanColor").toLowerCase());
	
		return playerInfo;
	}
	
	public void parsePlayerInfos() {
		
		JSONArray players = jsonObject.getJSONArray("players");
		ArrayList<PlayerInfo> playersInfos = new ArrayList<PlayerInfo>();
		
		for(int i=0; i<players.length(); i++) {
			if(!players.isNull(i)) {
				PlayerInfo playerInfo = parsePlayerInfo(players.getJSONObject(i), i);
				playersInfos.add(playerInfo);
			}
		}
		gameInfo.setPlayerInfo(playersInfos);
	}
	
}
