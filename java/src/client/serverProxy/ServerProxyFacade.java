package client.serverProxy;

import game.GameModel;
import game.cards.ResourceCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
	private boolean mockServer = false;
	private Gson gson;
	
	/**
     * check <code>mockServer</code> boolean to either create a mock server or to connect
     * to the real server through <code>ServerProxy</code>
     */
	public ServerProxyFacade() {
		
		gson = new Gson();
		if(mockServer) {
			server = new MockServer();
		}
		else {
			server = new ServerProxy();
		}
	}
	
	private String checkResponseStatus(Object response) {
		
		return (String)response;
	}
	
	private GameModel jsonToGameModel(Object response) {
		try {
			return gson.fromJson((String)response, GameModel.class);
		}
		catch(JsonSyntaxException e) {
			System.out.println("\t" + (String)response);
			return null;
		}
		//return (GameModel)response;
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
	public String loginUser(String username, String password) {
		
		//create param object and convert to json
		LoginUserParam param = new LoginUserParam(username, password);
		
		//make post to proper url using json as the body of the request
		String url = "/user/login";
		Object response = server.doPost(url, param);
				
		return checkResponseStatus(response); 
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
	public String registerUser(String username, String password) {
		
		//create param object and convert to json
		RegisterUserParam param = new RegisterUserParam(username, password);
		
		//make post to proper url using json as the body of the request
		String url = "/user/register";
		Object response = server.doPost(url, param);
		
		return checkResponseStatus(response); 
	}

    /**
     * gets the GameModel's for all current games
     * 
     * @return <code>Set<GameModel></code> that contains all current games
     */
	public Set<GameModel> getAllGames() {
		
		//make post to proper url using json as the body of the request
		String url = "/games/list";
		Object response = server.doGet(url);
		
		
		//TODO parse the response from the server

		return null; 
	};

    /**
     * creates an empty game of the server
     * 
     * @return <code>GameModel</code> of the created game
     */
	public CreateGameRes createGame(String name) {
		
		//create param object and convert to json
		CreateGameParam param = new CreateGameParam(name);
		
		//make post to proper url using json as the body of the request
		String url = "/games/create";
		Object response = server.doPost(url, param);
		
		CreateGameRes res = gson.fromJson((String)response, CreateGameRes.class);
		
		return res; 
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
	public String joinGame(int id, CatanColor color) {
		
		//create param object and convert to json
		JoinGameParam param = new JoinGameParam(id, color.toString().toLowerCase());
		
		//make post to proper url using json as the body of the request
		String url = "/games/join";
		Object response = server.doPost(url, param);
		
		return checkResponseStatus(response);  
	}
	
	public String saveGame(int id, String name) {
		
		SaveGameParam param = new SaveGameParam(id, name);
		
		String url = "/games/save";
		Object response = server.doPost(url, param);
		
		return checkResponseStatus(response); 
	}
	
	public GameModel loadGame(String name) {
		
		//create param object and convert to json
		LoadGameParam param = new LoadGameParam(name);
		
		//make post to proper url using json as the body of the request
		String url = "/games/load";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
	}

    /**
     * gets <code>GameModel</code> for the current game
     * 
     * @pre		player has valid catan.user and catan.game id
     * @return	<code>GameModel</code> of the current game
     */
	public GameModel getGameModel(int version) {
		
		//make post to proper url using json as the body of the request
		String url = "/game/model";
		Object response = server.doGet(url);
		
		return jsonToGameModel(response);
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
		Object response = server.doPost(url, null);
		
		return jsonToGameModel(response); 
    }
    
    /**
     * applies a list of <code>Command</code>'s to the current game
     * 
     * @pre   			player has valid catan.user and catan.game id
     * @param commands	List<Command> containing all the command's to be applied
     * @return			<code>true</code> if login was successful, 
     * 					<code>false</code> if it was not successful
     */
	public GameModel doGameCommands(List<Command> commands) {
		
		//create param object and convert to json
		DoGameCommandsParam param = new DoGameCommandsParam(commands);
		
		//make post to proper url using json as the body of the request
		String url = "/game/commands";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
		Object response = server.doGet(url);
		
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
	public String addAI(String AIType) {
		
		//create param object and convert to json
		AddAIParam param = new AddAIParam(AIType);
		
		//make post to proper url using json as the body of the request
		String url = "/game/commands";
		Object response = server.doPost(url, param);
		
		return checkResponseStatus(response);  
	}

//    /**
//     * gets a <code>Set<String></code> of the available AI types that may be added to the game
//     * 
//     * @return Set<String> containing the AI names that may be added to the game
//     */
//	public Set<String> getAIList() {
//
//		//make post to proper url using json as the body of the request
//		String url = "/game/listAI";
//		HttpResponse response = server.doGet(url);
//		
//		//TODO parse the response from the server
//		
//		return null; 
//	}
	
    /**
     * sends a chat message to other players
     * 
     * @post  				chat contains the new message at the end
     * @param playerIndex	the integer index of the player to chat
     * @param message		<code>String</code> message to send
     * @return				<code>true</code> if login was successful, 
     * 						<code>false</code> if it was not successful     
     */
	public GameModel sendChat(int playerIndex, String content) {
		
		//create param object and convert to json
		SendChatParam param = new SendChatParam("sendChat", playerIndex, content);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/sendChat";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel rollNumber(int playerIndex, int rollNumber) {
		
		//create param object and convert to json
		RollNumberParam param = new RollNumberParam("rollNumber", playerIndex, rollNumber);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/rollNumber";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response);
	}
	
	public GameModel robPlayer(int playerIndex, int victimIndex, Corner location) {
		
		//create param object and convert to json
		RobPlayerParam param = new RobPlayerParam("robPlayer", playerIndex, victimIndex, location);
		
		//make post to proper url using the json as the body of the request
		String url = "/moves/robPlayer";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel finishTurn(int playerIndex) {
		
		//create param object and convert to json
		FinishTurnParam param = new FinishTurnParam("finishTurn", playerIndex);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/finishTurn";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response);  
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
	public GameModel buyDevCard(int playerIndex) {
		
		//create param object and convert to json
		BuyDevCardParam param = new BuyDevCardParam("buyDevCard", playerIndex);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buyDevCard";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel playYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) {
		
		//create param object and convert to json
		PlayYearOfPlentyParam param = new PlayYearOfPlentyParam("Year_of_Plenty", playerIndex, resource1, resource2);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Year_Of_Plenty";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response);  
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
	public GameModel playRoadBuilding(int playerIndex, Edge edgeOne, Edge edgeTwo) {
		
		//create param object and convert to json
		PlayRoadBuildingParam param = new PlayRoadBuildingParam("Road_Building", playerIndex, edgeOne, edgeTwo);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Road_Building";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response);  
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
	public GameModel playSoldier(int playerIndex, int victimIndex, HexLocation location) {
		
		//create param object and convert to json
		PlaySoldierParam param = new PlaySoldierParam("Soldier", playerIndex, victimIndex, location);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Soldier";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel playMonopoly(int playerIndex, ResourceType resource) {
		
		//create param object and convert to json
		PlayMonopolyParam param = new PlayMonopolyParam("Monopoly", playerIndex, resource);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Monopoly";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response);  
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
	public GameModel playMonument(int playerIndex) { 
		
		//create param object and convert to json
		PlayMonumentParam param = new PlayMonumentParam("Monument", playerIndex);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/Monument";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel buildRoad(int playerIndex, Edge roadLocation) {
		
		//create param object and convert to json
		BuildRoadParam param = new BuildRoadParam("buildRoad", playerIndex, roadLocation);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildRoad";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel buildSettlement(int playerIndex, Corner vertexLocation, boolean free) {
		
		//create param object and convert to json
		BuildSettlementParam param = new BuildSettlementParam("buildSettlement", playerIndex, vertexLocation, free);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildSettlement";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel buildCity(int playerIndex, Corner vertexLocation, boolean free) {
		
		//create param object and convert to json
		BuildCityParam param = new BuildCityParam("buildCity", playerIndex, vertexLocation, free);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/buildCity";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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

	public GameModel offerTrade(int playerIndex, List<ResourceType> offer, int receiver) {
		
		//create param object and convert to json
		OfferTradeParam param = new OfferTradeParam("offerTrade", playerIndex, offer, receiver);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/offerTrade";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel acceptTrade(int playerIndex, boolean willAccept) {
		
		//create param object and convert to json
		AcceptTradeParam param = new AcceptTradeParam("acceptTrade", playerIndex, willAccept);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/acceptTrade";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel maritimeTrade(int playerIndex, int ratio, ResourceType input, ResourceType output) {
		
		//create param object and convert to json
		MaritimeTradeParam param = new MaritimeTradeParam("maritimeTrade", playerIndex, ratio, input, output);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/maritimeTrade";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public GameModel discardCards(int playerIndex, List<ResourceCard> discardedCards) {
		
		//create param object and convert to json
		DiscardCardsParam param = new DiscardCardsParam("discardCards", playerIndex, discardedCards);
		
		//make post to proper url using json as the body of the request
		String url = "/moves/discardCards";
		Object response = server.doPost(url, param);
		
		return jsonToGameModel(response); 
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
	public String changeLogLevel(LogLevel logLevel) {
		
		//create param object and convert to json
		ChangeLogLevelParam param = new ChangeLogLevelParam(logLevel);
		
		//make post to proper url using json as the body of the request
		String url = "/util/changeLogLevel";
		Object response = server.doPost(url, param);
		
		return checkResponseStatus(response);  
	}
}
