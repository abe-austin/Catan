package client.serverProxy;

import org.apache.http.HttpResponse;

/**
 * acts as a mock server, takes requests and sends static Strings
 * 
 * @author Brent
 *
 */
public class MockServer implements Server{
	
	boolean success = true;

	/**
	 * empty constructor
	 */
	public MockServer(){}
	
	@Override
	public HttpResponse doPost(String url, Object json) {
		
		switch(url) {
			
			case "/user/login":
				return successOrFail();
				
			case "/user/register":
				return successOrFail();
				
			case "/game/create":
				//TODO Info on new game
				
			case "/games/join":
				return successOrFail();
				
			case "/games/save":
				return successOrFail();
	
			case "/games/load":
				return staticGameModel();
	
			case "/game/reset":
				return staticGameModel();
				
			case "/game/commands":
				return staticGameModel();
			
			case "game/addAI":
				return successOrFail();
			
			case "/moves/sendChat":
				return staticGameModel();
				
			case "/moves/rollNumber":
				return staticGameModel();
				
			case "/moves/robPlayer":
				return staticGameModel();
				
			case "/moves/finishTurn":
				return staticGameModel();
			
			case "/moves/buyDevCard":
				return staticGameModel();
			
			case "/moves/Year_Of_Plenty":
				return staticGameModel();
				
			case "/moves/Road_Building":
				return staticGameModel();
				
			case "/moves/Soldier":
				return staticGameModel();
				
			case "/moves/Monopoly":
				return staticGameModel();
				
			case "/moves/Monument":
				return staticGameModel();
				
			case "/moves/buildRoad":
				return staticGameModel();
				
			case "/moves/buildSettlement":
				return staticGameModel();
				
			case "/moves/buildCity":
				return staticGameModel();
				
			case "/moves/offerTrade":
				return staticGameModel();
				
			case "/moves/acceptTrade":
				return staticGameModel();
				
			case "/moves/maritimeTrade":
				return staticGameModel();
				
			case "/moves/discardCards":
				return staticGameModel();
				
			case "/util/changeLogLevel":
				return successOrFail();
		}
		return null; 
	}

	@Override
	public HttpResponse doGet(String url) {
		
		switch(url){
			
			case "/games/list":
				//TODO List of all the games
			
			case "/game/model":
				return staticGameModel();
				
			case "/game/commands":
				//TODO list of commands
				
			case "game/listAI":
				//TODO list of AI
		}
		return null; 
	}
		
	private HttpResponse staticGameModel() {
		return null;
	}
	
	private HttpResponse successOrFail() {
		return null;
	}

}