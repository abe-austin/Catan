package client.serverProxy;

import client.data.GameInfo;
import client.parse.DoParse;
import com.google.gson.Gson;
import java.util.ArrayList;
import shared.communication.ServerResponse;

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
        
        public void convertGameModel(ServerResponse response) {
		
		if(response.getCode() == 200) {
			//response.setBody(gson.fromJson((String)response.getBody(), type));
                    DoParse parser =new DoParse();
                    parser.process((String)response.getBody());
                    response.setBody(parser.getGameModel());
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
				GameInfo info = gson.fromJson(test, GameInfo.class);
				info.resetPlayer();
				gameInfo.add(info);
			}
			response.setBody(gameInfo);
			return;
		}
	}
} 
