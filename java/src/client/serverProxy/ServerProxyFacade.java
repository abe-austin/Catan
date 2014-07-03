package client.serverProxy;

import game.GameModel;
import game.cards.Card;
import game.cards.ResourceCard;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import game.board.Edge;
import game.board.Corner;
import shared.locations.HexLocation;
import shared.communication.*;
import shared.definitions.CatanColor;
import shared.definitions.Command;
import shared.definitions.LogLevel;
import shared.definitions.ResourceType;

/**
 * A proxy between the client controller and the server
 * Takes information from the communicator and formats it the passes
 * that information to the server to be processed
 * Receives responses from the server and reformats it then sends the 
 * reformatted information to the client controller
 * 
 * @author Brent
 *
 */
public class ServerProxyFacade {
	private Server server;
	private boolean mockServer = true;
	private Gson gson;
	
	/**
     * check <code>mockServer</code> boolean to either create a mock server or to connect
     * to the real server through <code>ServerProxy</code>
     */
	private ServerProxyFacade() {
		gson = new Gson();
		if(mockServer) {
			server = new MockServer();
		}
		else {
			server = new ServerProxy();
		}
	}

    /**
     * logs an existing user into the game server
     * 
     * @post			if successful, catan.user cookie is set
     * @param username 	the string of the username
     * @param password 	the string of the password
     * @return			<code>true</code> if login was successful, 
     *                  <code>false</code> if it was not successful
     */
	public boolean loginUser(String username, String password) {
		
		//create param object and convert to json
		LoginUserParam param = new LoginUserParam(username, password);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/user/login";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false; 
	}

    /**
     * creates a new user account and logs that user into the game server
     * 
     * @post  			if successful, user account is created and catan.user cookie is set
     * @param username	the string of the username
     * @param password	the string of the password
     * @return			<code>true</code> if login was successful, 
     * 					<code>false</code> if it was not successful
     */
	public boolean registerUser(String username, String password) {
		
		//create param object and convert to json
		RegisterUserParam param = new RegisterUserParam(username, password);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/user/register";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server

		return false;
	}

    /**
     * gets the GameModel's for all current games
     * 
     * @return <code>Set<GameModel></code> that contains all current games
     */
	public Set<GameModel> getAllGames() {
		
		//make post to proper url using json as the body of the request
		String url = "/games/list";
		String response = server.doPost(url, null);
		
		//TODO parse the response from the server

		return null; 
	};

    /**
     * creates an empty game of the server
     * 
     * @return <code>GameModel</code> of the created game
     */
	public GameModel createGame(String gameName) {
		
		//create param object and convert to json
		CreateGameParam param = new CreateGameParam(gameName);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/game/create";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server

		return null; 
	}

    /**
     * adds a player to the game and sets their game cookie
     * 
     * @pre 			player has valid catan.user cookie set
     * @pre				they are already in the game or there is space to add a new player
     * @pre				valid CatanColor is used
     * @post			he player is in game with the specified CatanColor
     * @post			catan.game cookie is set for player
     * @param gameID	the integer of the game id
     * @param color		the <code>CatanColor</code> of the color for player to use
     * @return 			<code>GameModel</code> of the joined game
     */
	public GameModel joinGame(int gameID, CatanColor color) {
		
		//create param object and convert to json
		JoinGameParam param = new JoinGameParam(gameID, color);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/games/join";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server

		return null; 
	}

    /**
     * gets <code>GameModel</code> for the current game
     * 
     * @pre		player has valid catan.user and catan.game id
     * @return	<code>GameModel</code> of the current game
     */
	public GameModel getGameModel(int version) {
		
		//make post to proper url using json as the body of the request
		String url = "/game/model?version=" + version;
		String response = server.doPost(url, null);
		
		//TODO parse the response from the server
		
		return null; 
	}

    /**
     * resets the game to how it was after all the players joined
     * 
     * @pre		the player has valid catan.user and catan.game id
     * @return	<code>GameModel</code> of the reset game
     */
    public GameModel resetGame() { 
		
		//make post to proper url using json as the body of the request
		String url = "/game/reset";
		String response = server.doPost(url, null);
		
		//TODO parse the response from the server
		
    	return null; 
    }

    /**
     * get a list of all the <code>Command</code>'s played on a game
     * 
     * @pre		player has valid catan.user and catan.game id
     * @return	<code>List<Command></code> containing all the command's played on the game
     */
	public List<Command> getGameCommands() {
		
		//make get to proper url
		String url = "/game/commands";
		String response = server.doGet(url);
		
		//TODO parse the response from the server
		
		return null; 
	}

    /**
     * applies a list of <code>Command</code>'s to the current game
     * 
     * @pre   			player has valid catan.user and catan.game id
     * @param commands	List<Command> containing all the command's to be applied
     * @return			<code>true</code> if login was successful, 
     * 					<code>false</code> if it was not successful
     */
	public boolean doGameCommands(List<Command> commands) {
		
		//create param object and convert to json
		DoGameCommandsParam param = new DoGameCommandsParam(commands);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/game/commands";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		return false;
	}

    /**
     * gets a <code>Set<String></code> of the available AI types that may be added to the game
     * 
     * @return Set<String> containing the AI names that may be added to the game
     */
	public Set<String> getAIList() {

		//make post to proper url using json as the body of the request
		String url = "/game/listAI";
		String response = server.doPost(url, null);
		
		//TODO parse the response from the server
		
		return null; 
	}
	
    /**
     * adds an AI to the game
     * 
     * @pre			player has valid catan.user and catan.game id
     * @pre			there is space for an AI player in game
     * @post		the AI player is added to the next open spot in the game in the poster's catan.game cookie
     * @post		the AI player uses a <code>CatanColor</code> not taken by any other player
     * @param ai	the <code>String</code> name of AI
     * @return		<code>true</code> if login was successful, 
     * 				<code>false</code> if it was not successful     
     */
	public boolean addAI(String ai) {
		
		//create param object and convert to json
		AddAIParam param = new AddAIParam(ai);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/game/commands";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false; 
	}
	
    /**
     * sets the servers logging level
     * 
     * @pre   			a valid <code>LogLevel</code> is given
     * @post  			server uses the given <code>LogLevel</code>
     * @param logLevel	the <code>LogLevel</code> to be applied
     * @return			<code>true</code> if login was successful, 
     * 					<code>false</code> if it was not successful
     */
	public boolean changeLogLevel(LogLevel logLevel) {
		
		//create param object and convert to json
		ChangeLogLevelParam param = new ChangeLogLevelParam(logLevel);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/util/changeLogLevel";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false; 
	}
	
    /**
     * sends a chat message to other players
     * 
     * @post  				chat contains the new message at the end
     * @param playerIndex	the integer index of the player to chat
     * @param message		<code>String</code> message to send
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful     
     */
	public boolean sendChat(int playerIndex, String message) {
		
		//create param object and convert to json
		SendChatParam param = new SendChatParam(playerIndex, message);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/games/sendChat";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * either accept or decline a trade
     * 
     * @pre					a domestic trade has been offered to player
     * @pre					if accepting then the player must have the required resources
     * @post				if accepting then traders swap resources
     * @post				trade offer is removed
     * @param playerIndex	the integer index of the player to trade 
     * @param willAccept	the <code>boolean</code> whether trade is accepted or not
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful     
     */
	public boolean acceptTrade(int playerIndex, boolean willAccept) {
		
		//create param object and convert to json
		AcceptTradeParam param = new AcceptTradeParam(playerIndex, willAccept);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/acceptTrade";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * discard any number of cards from hand
     * 
     * @pre 				client model is in the state 'Discarding'
     * @pre 				player has over 7 cards
     * @pre 				player has the cards they choose to discard
     * @post 				if player is the last to discard the client model status changes to 'Robbing'
     * @post 				player gives up specified cards
     * @param playerIndex 	the integer of the players index
     * @param cards 		the <code>List<Card></code> of the cards to discard
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful     
     */
	public boolean discardCards(int playerIndex, List<ResourceCard> cards) {
		
		//create param object and convert to json
		DiscardCardsParam param = new DiscardCardsParam(playerIndex, cards);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/discardCards";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * rolls a number for the player
     * 
     * @pre 				the client model is in the state 'Rolling'
     * @pre 				is it the players turn
     * @post 				the client model is in the state 'Discarding', 'Robbing', or 'Playing'
     * @param playerIndex	the integer of the players index of roller 
     * @param rollNumber	the integer number rolled
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful     
     */
	public boolean rollNumber(int playerIndex, int rollNumber) {
		
		//create param object and convert to json
		RollNumberParam param = new RollNumberParam(playerIndex, rollNumber);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/rollNumber";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * player builds a road
     * 
     * @pre 				road location is open
     * @pre					road location is connected to another of players roads
     * @pre 				road location is not on water
     * @pre					player has necessary resources
     * @pre					it is players turn
     * @pre					the client model is in state 'Playing'
     * @post 				players spends necessary resources
     * @post 				map lists the new road
     * @param playerIndex 	the integer of the players index
     * @param e				the <code>Edge</code> to build the road on
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful 
     */
	public boolean buildRoad(int playerIndex, Edge e) {
		
		//create param object and convert to json
		BuildRoadParam param = new BuildRoadParam(playerIndex, e);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildRoad";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * player builds a settlement
     * 
     * @pre 				settlement location is open
     * @pre	    			settlement location is connected to at least one of players roads
     * @pre					settlement location is not on water
     * @pre					player has necessary resources
     * @pre     			it is players turn
     * @pre					the client model is in state 'Playing'
     * @post  				players spends necessary resources
     * @post		  		map lists the new settlement
     * @param playerIndex 	the integer of the players index
     * @param c				<code>Corner</code> the Corner to build settlement on
     * @param free			<code>boolean</code> whether the <code>Corner</code> is free or not
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful 
     */
	public boolean buildSettlement(int playerIndex, Corner c, boolean free) {
		
		//create param object and convert to json
		BuildSettlementParam param = new BuildSettlementParam(playerIndex, c, free);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildSettlement";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * player builds a city
     * 
     * @pre					city location is where player currently has settlement
     * @pre					player has necessary resources
     * @pre					it is players turn
     * @pre					the client model is in state 'Playing'
     * @post  				players spends necessary resources
     * @post				player gets settlement back
     * @post				map lists the new city
     * @param playerIndex 	the integer of the players index
     * @param c				<code>Corner</code> the Corner to build city on
     * @param free			<code>boolean</code> whether the <code>Corner</code> is free or not
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful 
     */
	public boolean buildCity(int playerIndex, Corner c, boolean free) {
		
		//create param object and convert to json
		BuildCityParam param = new BuildCityParam(playerIndex, c, free);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildCity";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * offer a trade to another player
     * 
     * @pre					player has offered resources
     * @pre					it is players turn
     * @pre					the client model is in state 'Playing'
     * @post 				trade is offered to another player
     * @param playerIndex 	the integer of the players index
     * @param offer 		<code>List<Card></code> cards to trade
     * @param recieveIndex	the integer index of player to trade with
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful 
     */

	public boolean offerTrade(int playerIndex, List<ResourceCard> offer, int recieveIndex) {
		
		//create param object and convert to json
		OfferTradeParam param = new OfferTradeParam(playerIndex, offer, recieveIndex);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/offerTrade";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false; 
	}
	
    /**
     * make a trade at a port
     * 
     * @pre 				player has offered resources
     * @pre 				it is players turn
     * @pre 				the client model is in state 'Playing'
     * @param playerIndex 	the integer of the players index
     * @param ratio			the integer of the trade ratio
     * @param input			the <code>ResourceType</code> of resources given
     * @param output		the <code>ResourceType</code> of the resource received
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful 
     */
	public boolean maritimetrade(int playerIndex, int ratio, ResourceType input, ResourceType output) {
		
		//create param object and convert to json
		MaritimeTradeParam param = new MaritimeTradeParam(playerIndex, ratio, input, output);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/maritimeTrade";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
    /**
     * player ends turn
     * 
     * @pre 	the client model is in state 'Playing'
     * @pre		it is players turn
     * @pre		the client model is in state 'Playing'
     * @post 	it is the next players turn
     * @return	<code>true</code> if login was successful, 
     * 			<code>false</code> if it was not successful
     */
	public boolean finishTurn() {
		
		//make post to proper url using json as the body of the request
		String url = "/moves/finishTurn";
		String response = server.doPost(url, null);
		
		//TODO parse the response from the server
		
		return false; 
	}
	
    /**
     * player buys a development card
     * 
     * @pre 				player has necessary resources
     * @pre					there are development cards left in the deck
     * @pre					it is players turn
     * @pre					the client model is in state 'Playing'
     * @post 				player has the new card
     * @param playerIndex	the integer of the players index
     * @return 				<code>Card</code> the bought development card
     */
	public Card buyDevCard(int playerIndex) {
		
		//create param object and convert to json
		BuyDevCardParam param = new BuyDevCardParam(playerIndex);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buyDevCard";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return null;
	}
	
    /**
     * player plays a year of plenty card
     * 
     * @pre 				the specified <code>ResourceTypes</code> are in the bank
     * @pre					player has specified development card
     * @pre					player has not played a development card yet this turn
     * @pre					it is players turn
     * @post 				player gains two of the specified <code>ResourceType</code>'s
     * @param playerIndex	the integer of the players index
     * @param resourceOne	the <code>ResourceType</code> of first resource to receive
     * @param resourceTwo	the <code>ResourceType</code> of second resource to receive
     * @return 				<code>Card[]</code> an array of two cards of the specified <code>ResourceType</code>'s
     */
	public Card[] playYearOfPlenty(int playerIndex, ResourceType resourceOne, ResourceType resourceTwo) {
		
		//create param object and convert to json
		PlayYearOfPlentyParam param = new PlayYearOfPlentyParam(playerIndex, resourceOne, resourceTwo);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Year_Of_Plenty";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return null; 
	}
	
    /**
     * player plays a road building card
     * 
     * @pre 				the first road is connected to one of players roads
     * @pre					the second road is connected to one of players roads of previous road
     * @pre					neither location is on water
     * @pre					player has two roads
     * @pre					player has specified development card
     * @pre					player has not played a development card yet this turn
     * @pre					it is players turn
     * @post 				player uses two roads
     * @post		 		the map correctly lists the roads played
     * @param playerIndex	the integer of the players index
     * @param edgeOne		the <code>Edge</code> to place road on
     * @param edgeTwo		the <code>Edge</code> to place road on
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful    
     */
	public boolean playRoadBuilding(int playerIndex, Edge edgeOne, Edge edgeTwo) {
		
		//create param object and convert to json
		PlayRoadBuildingParam param = new PlayRoadBuildingParam(playerIndex, edgeOne, edgeTwo);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Road_Building";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false; 
	}
	
    /**
     * player plays a soldier card
     * 
     * @pre 					the robber is being moved
     * @pre						the player to rob has hards
     * @pre     				player has specified development card
     * @pre						player has not played a development card yet this turn
     * @pre						it is players turn
     * @post 					the robber is in new location
     * @post		 			the player to rob gives one random resource card to the player playing soldier card
     * @param playerIndex		the integer of the players index
     * @param victimIndex		the integer of the victim index
     * @param newRobberLocation	the <code>HexLocation</code> to place the robber on
     * @return 					<code>Card</code> the card stolen by the player from victim
     */
	public Card playSoldier(int playerIndex, int victimIndex, HexLocation newRobberLocation) {
		
		//create param object and convert to json
		PlaySoldierParam param = new PlaySoldierParam(playerIndex, victimIndex, newRobberLocation);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Soldier";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return null;
	}
	
    /**
     * player plays a monopoly card
     * 
     * @pre     			player has specified development card
     * @pre					player has not played a development card yet this turn
     * @pre					it is players turn
     * @post 				all other players lost their cards of specified <code>ResourceType</code>
     * @post				the player of card get an equal number of those lost
     * @param playerIndex	the integer of the players index
     * @param resource		the <code>ResourceType</code> of the resource to get
     * @return				<code>List<Card></code> containing all the cards gained by player of monopoly card
     */
	public List<Card> playMonopoly(int playerIndex, ResourceType resource) {
		
		//create param object and convert to json
		PlayMonopolyParam param = new PlayMonopolyParam(playerIndex, resource);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Monopoly";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return null; 
	}
	
    /**
     * player plays a monument card
     * 
     * @pre 				player has specified development card
     * @pre					player has not played a development card yet this turn
     * @pre					it is players turn
     * @pre					the client model is in state 'Playing'
     * @post 				player gains a victory point
     * @param playerIndex	the integer of the players index
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful      */
	public boolean playMonument(int playerIndex) { 
		
		//create param object and convert to json
		PlayMonumentParam param = new PlayMonumentParam(playerIndex);
		String jsonParam = gson.toJson(param);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Monument";
		String response = server.doPost(url, jsonParam);
		
		//TODO parse the response from the server
		
		return false;
	}
	
}
