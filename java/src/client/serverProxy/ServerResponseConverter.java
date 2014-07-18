package client.serverProxy;

import java.util.ArrayList;

import shared.communication.ServerResponse;
import client.data.GameInfo;

import com.google.gson.Gson;

public class ServerResponseConverter {
	
	private Gson gson;
	
	public ServerResponseConverter() {
		gson = new Gson();
	}
	
	public void convert(ServerResponse response, Class<?> type) {
		
		if(response.getCode() == 200) {
			response.setBody(gson.fromJson((String)response.getBody(), type));
			return;
		}
	}
	
	public void convertGameInfo(ServerResponse response) {
		
		if(response.getCode() == 200) {
			response.setBody(gson.fromJson((String)response.getBody(), ArrayList.class));
			ArrayList<GameInfo> games = (ArrayList<GameInfo>)response.getBody();
			ArrayList<GameInfo> gameInfo = new ArrayList<GameInfo>();
			for(Object game : games) {
				String test = gson.toJson(game);
				gameInfo.add(gson.fromJson(test, GameInfo.class));
			}
			response.setBody(gameInfo);
			return;
		}
	}
} 
