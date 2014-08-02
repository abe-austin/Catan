package client.serverProxy;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.parse.DoParse;
import client.parseJson.*;

import com.google.gson.Gson;

import game.GameModel;

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
		
//		if(response.getCode() == 200) {
//			//response.setBody(gson.fromJson((String)response.getBody(), type));
//                    DoParse parser =new DoParse();
//                    parser.process((String)response.getBody());
//                    response.setBody(parser.getGameModel());
//                     return;
//		}
			//System.out.println((String)response.getBody());
    		if(response.getCode() == 200) {
//    			response.setBody(gson.fromJson((String)response.getBody(), GameModel.class));
//                return;
    			ParseGameModel parser = new ParseGameModel((String)response.getBody());
    			response.setBody(parser.doParse());
    		}
	}
	
	public void convertGameInfo(ServerResponse response) {
            ParseGameInfo parseGameInfo = new ParseGameInfo((String)response.getBody());
            response.setBody(parseGameInfo.doParse());
//		if(response.getCode() == 200) {
//			response.setBody(gson.fromJson((String)response.getBody(), ArrayList.class));
//			ArrayList<GameInfo> games = (ArrayList<GameInfo>)response.getBody();
//			ArrayList<GameInfo> gameInfo = new ArrayList<GameInfo>();
//			for(Object game : games) {
//				String test = gson.toJson(game);
//				GameInfo info = gson.fromJson(test, GameInfo.class);
//				ArrayList<PlayerInfo> playerInfoList = new ArrayList<PlayerInfo>();
//				info.resetPlayer();
//				for(Object player : info.getPlayers()) {
//					String playerString = gson.toJson(player);
//					PlayerInfo playerInfo = gson.fromJson(playerString, PlayerInfo.class);
//					playerInfo.setColor(playerInfo.getColor());
//					playerInfoList.add(playerInfo);
//				}
//				info.setPlayerInfo(playerInfoList);
//				gameInfo.add(info);
//			}
//			response.setBody(gameInfo);
//			return;
//		}
//		else {
//			ArrayList<GameInfo> gameInfo = new ArrayList<GameInfo>();
//			response.setBody(gameInfo);
//			return;
//		}
	}
} 
