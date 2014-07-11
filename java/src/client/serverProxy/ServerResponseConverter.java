package client.serverProxy;

import shared.communication.ServerResponse;

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
} 
