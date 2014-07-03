package client.serverProxy;

/**
 * acts as a mock server, takes requests and sends static Strings
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
	public String doPost(String url, String json) { return null; }

	@Override
	public String doGet(String url) { return null; }
	
	private String loginUser(String json){ return null; }
	
	private String registerUser(String json){ return null; }
	
	private String getAllGames(){ return null; }
	
	private String createGame(String json){ return null; }

	private String joinGame(String json){ return null; }

	private String getGameModel(){ return null; }

	private String resetGame(){ return null; }

	private String getGameCommands(){ return null; }

	private String doGameCommands(String json){ return null; }

	private String getAIList(){ return null; }

	private String addAI(String json){ return null; }
	
	private String changeLogLevel(String json){ return null; }

	private String sendChat(String json){ return null; }

	private String acceptTrade(String json){ return null; }

	private String discardCards(String json){ return null; }

	private String rollNumber(String json){ return null; }

	private String buildRoad(String json){ return null; }

	private String buildSettlement(String json){ return null; }

	private String buildCity(String json){ return null; }

	private String offerTrade(String json){ return null; }

	private String maritimeTrade(String json){ return null; }

	private String finishTurn(){ return null; }

	private String buyDevCard(String json){ return null; }
	
	private String playYearOfPlenty(String json){ return null; }

	private String playRoadBuilding(String json){ return null; }

	private String playSoldier(String json){ return null; }

	private String playMonopoly(String json){ return null; }

	private String playMonument(String json){ return null; }

}