package client.serverProxy;

import game.board.Corner;
import game.board.Edge;
import game.cards.ResourceCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.communication.*;
import shared.definitions.CatanColor;
import shared.definitions.Command;
import shared.definitions.LogLevel;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

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
	private boolean mockServer;
	private ServerResponseConverter converter;
	
	/**
     * check <code>mockServer</code> boolean to either create a mock server or to connect
     * to the real server through <code>ServerProxy</code>
     */
	public ServerProxyFacade(boolean mock) {
	
		mockServer = mock;
		converter = new ServerResponseConverter();
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
     * @return			<code>ServerResponse</code> object
     */
	public ServerResponse loginUser(String username, String password) {
		
		//create param object and convert to json
		LoginUserParam param = new LoginUserParam(username, password);
		
		//make post to proper url using json as the body of the request
		String url = "/user/login";
		ServerResponse response = server.doPost(url, param);
		response.setUserId(server.getCookies().getUserId());

		//converter.convert(response, String.class);
		return response;
	}

    /**
     * creates a new user account and logs that user into the game server
     * 
     * @post  			if successful, user account is created and catan.user cookie is set
     * @param username	the string of the username
     * @param password	the string of the password
     * @return			<code>ServerResponse</code> object
     */
	public ServerResponse registerUser(String username, String password) {
		
		//create param object and convert to json
		RegisterUserParam param = new RegisterUserParam(username, password);
		
		//make post to proper url using json as the body of the request
		String url = "/user/register";
		ServerResponse  response = server.doPost(url, param);
		response.setUserId(server.getCookies().getUserId());
		
		//converter.convert(response, String.class);
		return response;
	}

    /**
     * gets the GameModel's for all current games
     * 
     * @return	<code>ServerResponse</code> object
     */
	public ServerResponse getAllGames() {
		
		//make post to proper url using json as the body of the request
		String url = "/games/list";
		ServerResponse  response = server.doGet(url);
		
		converter.convertGameInfo(response);
		return response; 
	}

    /**
     * creates an empty game of the server
     * 
     * @return	<code>ServerResponse</code> object
     */
	public ServerResponse createGame(String name, boolean randomHexes,
			boolean randomNumbers, boolean randomPorts) {
		
		//create param object and convert to json
		CreateGameParam param = new CreateGameParam(name, randomHexes, randomNumbers, randomPorts);
		
		//make post to proper url using json as the body of the request
		String url = "/games/create";
		 ServerResponse  response = server.doPost(url, param);
		
		converter.convert(response, CreateGameRes.class);
		return response; 
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
     * @return			<code>ServerResponse</code> object
     */
	public ServerResponse joinGame(int id, CatanColor color) {
		
		//create param object and convert to json
		JoinGameParam param = new JoinGameParam(id, color.toString().toLowerCase());
		
		//make post to proper url using json as the body of the request
		String url = "/games/join";
		ServerResponse  response = server.doPost(url, param);
		
		converter.convert(response, String.class);
		return response; 

	}
	
    /**
     * saves the current state of the game
     * 
     * @pre 			player has valid catan.user cookie set
     * @pre 			player has valid catan.game cookie set
     * @param id		the integer of the game id
     * @param name		name to save the game as
     * @return			<code>ServerResponse</code> object
     */
	public ServerResponse saveGame(int id, String name) {
		
		SaveGameParam param = new SaveGameParam(id, name);
		
		String url = "/games/save";
		ServerResponse response = server.doPost(url, param);
		
		converter.convert(response, String.class);
		return response; 
	}
	
    /**
     * loads a game from memory
     * 
     * @pre 			player has valid catan.user cookie set
     * @pre 			player has valid catan.game cookie set
     * @param name		name of the game to load
     * @return			<code>ServerResponse</code> object
     */
	public ServerResponse loadGame(String name) {
		
		//create param object and convert to json
		LoadGameParam param = new LoadGameParam(name);
		
		//make post to proper url using json as the body of the request
		String url = "/games/load";
		ServerResponse response = server.doPost(url, param);
		
		converter.convert(response, String.class);
		return response;
	}

    /**
     * gets <code>GameModel</code> for the current game
     * 
     * @pre		player has valid catan.user and catan.game id
     * @return	<code>ServerResponse</code> object
     */
	public ServerResponse getGameModel(int version) {
		
		//create param object and convert to json
		GetGameModelParam param = new GetGameModelParam(version);
		
		//make post to proper url using json as the body of the request
		String url = "/game/model";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
                converter.convertGameModel(response);
		return response;
	}

    /**
     * resets the game to how it was after all the players joined
     * 
     * @pre		the player has valid catan.user and catan.game id
     * @return	<code>ServerResponse</code> object
     */
    public ServerResponse resetGame() { 
		
		//make post to proper url using json as the body of the request
		String url = "/game/reset";
		ServerResponse response = server.doPost(url, null);
		
		//converter.convert(response, GameModel.class);
                converter.convertGameModel(response);
		return response;
    }
    
    /**
     * applies a list of <code>Command</code>'s to the current game
     * 
     * @pre   			player has valid catan.user and catan.game id
     * @param commands	List<Command> containing all the command's to be applied
     * @return			<code>ServerResponse</code> object
     */
	public ServerResponse doGameCommands(List<Command> commands) {
		
		//create param object and convert to json
		DoGameCommandsParam param = new DoGameCommandsParam(commands);
		
		//make post to proper url using json as the body of the request
		String url = "/game/commands";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
	}

    /**
     * get a list of all the <code>Command</code>'s played on a game
     * 
     * @pre		player has valid catan.user and catan.game id
     * @return	<code>ServerResponse</code> object
     */
	public ServerResponse getGameCommands() {
		
		//make get to proper url
		String url = "/game/commands";
		ServerResponse  response = server.doGet(url);
		
		converter.convert(response, List.class);
		return response; 
	}
	
    /**
     * adds an AI to the game
     * 
     * @pre				player has valid catan.user and catan.game id
     * @pre				there is space for an AI player in game
     * @post			the AI player is added to the next open spot in the game in the poster's catan.game cookie
     * @post			the AI player uses a <code>CatanColor</code> not taken by any other player
     * @param AIType	the <code>String</code> name of AI
     * @return			<code>ServerResponse</code> object    
     */
	public ServerResponse addAI(String AIType) {
		
		//create param object and convert to json
		AddAIParam param = new AddAIParam(AIType);
		
		//make post to proper url using json as the body of the request
		String url = "/game/commands";
		ServerResponse  response = server.doPost(url, param);
		
		converter.convert(response, String.class);
		return response;

	}

    /**
     * gets a <code>Set<String></code> of the available AI types that may be added to the game
     * 
     * @return	<code>ServerResponse</code> object
     */
	public ServerResponse getAIList() {

		//make post to proper url using json as the body of the request
		String url = "/game/listAI";
		ServerResponse response = server.doGet(url);
		
		converter.convert(response, ArrayList.class);
		return response; 
	}
	
    /**
     * sends a chat message to other players
     * 
     * @post  				chat contains the new message at the end
     * @param playerIndex	the integer index of the player to chat
     * @param content		<code>String</code> message to send
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse sendChat(int playerIndex, String content) {
		
		//create param object and convert to json
		SendChatParam param = new SendChatParam("sendChat", playerIndex, content);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/sendChat";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;

	}
	
    /**
     * rolls a number for the player
     * 
     * @pre 				the client model is in the state 'Rolling'
     * @pre 				is it the players turn
     * @post 				the client model is in the state 'Discarding', 'Robbing', or 'Playing'
     * @param playerIndex	the integer of the players index of roller 
     * @param rollNumber	the integer number rolled
     * @return				<code>ServerResponse</code> object    
     */
	public ServerResponse rollNumber(int playerIndex, int rollNumber) {
		
		//create param object and convert to json
		RollNumberParam param = new RollNumberParam("rollNumber", playerIndex, rollNumber);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/rollNumber";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;

	}
	
	public ServerResponse robPlayer(int playerIndex, int victimIndex, Corner location) {
		
		//create param object and convert to json
		RobPlayerParam param = new RobPlayerParam("robPlayer", playerIndex, victimIndex, location);
		
		//make post to proper url using the json as the body of the request
		String url = "/moves/robPlayer";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
	}
	
    /**
     * player ends turn
     * 
     * @pre 				the client model is in state 'Playing'
     * @pre					it is players turn
     * @pre					the client model is in state 'Playing'
     * @post 				it is the next players turn
     * @param playerIndex	the integer of the players index
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse finishTurn(int playerIndex) {
		
		//create param object and convert to json
		FinishTurnParam param = new FinishTurnParam("finishTurn", playerIndex);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/finishTurn";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse buyDevCard(int playerIndex) {
		
		//create param object and convert to json
		BuyDevCardParam param = new BuyDevCardParam("buyDevCard", playerIndex);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buyDevCard";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @param resource1		the <code>ResourceType</code> of first resource to receive
     * @param resource2		the <code>ResourceType</code> of second resource to receive
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse playYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) {
		
		//create param object and convert to json
		PlayYearOfPlentyParam param = new PlayYearOfPlentyParam("Year_of_Plenty", playerIndex, resource1, resource2);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Year_Of_Plenty";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @param spot1			the <code>Edge</code> to place road on
     * @param spot2			the <code>Edge</code> to place road on
     * @return				<code>ServerResponse</code> object   
     */
	public ServerResponse playRoadBuilding(int playerIndex, Edge spot1, Edge spot2) {
		
		//create param object and convert to json
		PlayRoadBuildingParam param = new PlayRoadBuildingParam("Road_Building", playerIndex, spot1, spot2);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Road_Building";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @param location			the <code>HexLocation</code> to place the robber on
     * @return					<code>ServerResponse</code> object
     */
	public ServerResponse playSoldier(int playerIndex, int victimIndex, HexLocation location) {
		
		//create param object and convert to json
		PlaySoldierParam param = new PlaySoldierParam("Soldier", playerIndex, victimIndex, location);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Soldier";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse playMonopoly(int playerIndex, ResourceType resource) {
		
		//create param object and convert to json
		PlayMonopolyParam param = new PlayMonopolyParam("Monopoly", playerIndex, resource);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Monopoly";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse playMonument(int playerIndex) { 
		
		//create param object and convert to json
		PlayMonumentParam param = new PlayMonumentParam("Monument", playerIndex);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Monument";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @param roadLocation	the <code>Edge</code> to build the road on
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse buildRoad(int playerIndex, Edge roadLocation) {
		
		//create param object and convert to json
		BuildRoadParam param = new BuildRoadParam("buildRoad", playerIndex, roadLocation);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildRoad";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
	}
	
    /**
     * player builds a settlement
     * 
     * @pre 					settlement location is open
     * @pre	    				settlement location is connected to at least one of players roads
     * @pre						settlement location is not on water
     * @pre						player has necessary resources
     * @pre     				it is players turn
     * @pre						the client model is in state 'Playing'
     * @post  					players spends necessary resources
     * @post		  			map lists the new settlement
     * @param playerIndex 		the integer of the players index
     * @param vertexLocation	<code>Corner</code> the Corner to build settlement on
     * @param free				<code>boolean</code> whether the <code>Corner</code> is free or not
     * @return					<code>ServerResponse</code> object
     */
	public ServerResponse buildSettlement(int playerIndex, Corner vertexLocation, boolean free) {
		
		//create param object and convert to json
		BuildSettlementParam param = new BuildSettlementParam("buildSettlement", playerIndex, vertexLocation, free);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildSettlement";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
	}
	
    /**
     * player builds a city
     * 
     * @pre						city location is where player currently has settlement
     * @pre						player has necessary resources
     * @pre						it is players turn
     * @pre						the client model is in state 'Playing'
     * @post  					players spends necessary resources
     * @post					player gets settlement back
     * @post					map lists the new city
     * @param playerIndex 		the integer of the players index
     * @param vertexLocation	<code>Corner</code> the Corner to build city on
     * @param free				<code>boolean</code> whether the <code>Corner</code> is free or not
     * @return					<code>ServerResponse</code> object
     */
	public ServerResponse buildCity(int playerIndex, Corner vertexLocation, boolean free) {
		
		//create param object and convert to json
		BuildCityParam param = new BuildCityParam("buildCity", playerIndex, vertexLocation, free);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildCity";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
	}
	
    /**
     * offer a trade to another player
     * 
     * @pre					player has offered resources
     * @pre					it is players turn
     * @pre					the client model is in state 'Playing'
     * @post 				trade is offered to another player
     * @param playerIndex 	the integer of the players index
     * @param offer 		<code>Map<ResourceType,Integer></code> cards to trade
     * @param receiver	the integer index of player to trade with
     * @return			<code>ServerResponse</code> object
     */

	public ServerResponse offerTrade(int playerIndex, Map<ResourceType,Integer> offer, int receiver) {
		
		//create param object and convert to json
		OfferTradeParam param = new OfferTradeParam("offerTrade", playerIndex, offer, receiver);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/offerTrade";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @return			<code>ServerResponse</code> object    
     */
	public ServerResponse acceptTrade(int playerIndex, boolean willAccept) {
		
		//create param object and convert to json
		AcceptTradeParam param = new AcceptTradeParam("acceptTrade", playerIndex, willAccept);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/acceptTrade";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @return				<code>ServerResponse</code> object
     */
	public ServerResponse maritimeTrade(int playerIndex, int ratio, ResourceType input, ResourceType output) {
		
		//create param object and convert to json
		MaritimeTradeParam param = new MaritimeTradeParam("maritimeTrade", playerIndex, ratio, input, output);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/maritimeTrade";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
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
     * @return			<code>ServerResponse</code> object    
     */
	public ServerResponse discardCards(int playerIndex, List<ResourceCard> discardedCards) {
		
		//create param object and convert to json
		DiscardCardsParam param = new DiscardCardsParam("discardCards", playerIndex, discardedCards);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/discardCards";
		ServerResponse  response = server.doPost(url, param);
		
		//converter.convert(response, GameModel.class);
		converter.convertGameModel(response);
                return response;
	}
	
    /**
     * sets the servers logging level
     * 
     * @pre   			a valid <code>LogLevel</code> is given
     * @post  			server uses the given <code>LogLevel</code>
     * @param logLevel	the <code>LogLevel</code> to be applied
     * @return			<code>ServerResponse</code> object
     */
	public ServerResponse changeLogLevel(LogLevel logLevel) {
		
		//create param object and convert to json
		ChangeLogLevelParam param = new ChangeLogLevelParam(logLevel);
		
		//make post to proper url using json as the body of the request
		String url = "/util/changeLogLevel";
		ServerResponse  response = server.doPost(url, param);
		
		converter.convert(response, String.class);
		return response;
	}
}
