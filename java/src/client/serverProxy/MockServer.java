package client.serverProxy;

import javax.xml.ws.Response;

/**
 * acts as a mock server, takes requests and sends static responses
 * 
 * @author Brent
 *
 */
public class MockServer implements Server{

	/**
	 * empty constructor
	 */
	public MockServer(){}
	
	@Override
	public Response doPost(String url, String json) { return null; }

	@Override
	public Response doGet(String url) { return null; }
	
	private Response loginUser(String json){ return null; }
	
	private Response registerUser(String json){ return null; }
	
	private Response getAllGames(){ return null; }
	
	private Response createGame(String json){ return null; }

	private Response joinGame(String json){ return null; }

	private Response getGameModel(){ return null; }

	private Response resetGame(){ return null; }

	private Response getGameCommands(){ return null; }

	private Response doGameCommands(String json){ return null; }

	private Response getAIList(){ return null; }

	private Response addAI(String json){ return null; }
	
	private Response changeLogLevel(String json){ return null; }

	private Response sendChat(String json){ return null; }

	private Response acceptTrade(String json){ return null; }

	private Response discardCards(String json){ return null; }

	private Response rollNumber(String json){ return null; }

	private Response buildRoad(String json){ return null; }

	private Response buildSettlement(String json){ return null; }

	private Response buildCity(String json){ return null; }

	private Response offerTrade(String json){ return null; }

	private Response maritimeTrade(String json){ return null; }

	private Response finishTurn(){ return null; }

	private Response buyDevCard(String json){ return null; }
	
	private Response playYearOfPlenty(String json){ return null; }

	private Response playRoadBuilding(String json){ return null; }

	private Response playSoldier(String json){ return null; }

	private Response playMonopoly(String json){ return null; }

	private Response playMonument(String json){ return null; }

}