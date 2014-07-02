package client.serverProxy;

import java.util.List;
import java.util.Set;

import shared.definitions.CatanColor;
import shared.definitions.ResourceType;

public class ServerProxyFacade {
	private Server server;
	private boolean mockServer;
	
	/**
     * empty constructor
     */
	private ServerProxyFacade(){}

    /**
     * logs an existing user into the game server
     * @post if successful, catan.user cookie is set
     * @param String username String password
     * @return true if login was successful, false if it was not successful
     */
	public boolean loginUser(String username, String password){ return false; }

    /**
     * creates a new user account and logs that user into the game server
     * @post  if successful, user account is created and catan.user cookie is set
     * @param String username String password
     * @return tru is successful false if not
     */
	public boolean registerUser(String username, String password){ return false; }

    /**
     * gets the GameModel's for all current games
     * @return Set<GameModel> that contains all current games
     */
	public Set<GameModel> getAllGames(){ return null; };

    /**
     * creates an empty game of the server
     * @return GameModel of the created game
     */
	public GameModel createGame(){ return null; }

    /**
     * adds a player to the game and sets their game cookie
     * @pre   player has valid catan.user cookie set
     * 		  they are already in the game or there is space to add a new player
     * 		  valid CatanColor is used
     * @post  the player is in game with the specified CatanColor
     * 		  catan.game cookie is set for player
     * @param int id of the game to join CatanColor of the color for player to use
     * @return GameModel of the joined game
     */
	public GameModel joinGame(int gameID, CatanColor color){ return null; }

    /**
     * gets GameModel for the current game
     * @pre   player has valid catan.user and catan.game id
     * @return GameModel of the current game
     */
	public GameModel getGameModel(){ return null; }

    /**
     * resets the game to how it was after all the players joined
     * @pre   the player has valid catan.user and catan.game id
     * @return GameModel of the reset game
     */
    public GameModel resetGame(){ return null; }

    /**
     * get a list of all the commands played on a game
     * @pre   player has valid catan.user and catan.game id
     * @return List<Command> containing all the commands played on the game
     */
	public List<Command> getGameCommands(){ return null; }

    /**
     * applies a list of Command's to the current game
     * @pre   player has valid catan.user and catan.game id
     * @param List<Command> containing all the Command's to be applied
     * @return true is operation as successful, false if not
     */
	public boolean doGameCommands(List<Command> commands){ return false; }

    /**
     * gets a list of the available AI types that may be added to the game
     * @return a Set<String> containing the AI names that may be added to the game
     */
	public Set<String> getAIList(){ return null; }
	
    /**
     * adds an AI to the game
     * @pre   player has valid catan.user and catan.game id
     * 		  there is space for an AI player in game
     * @post  the AI player is added to the next open spot in the game in the poster's catan.game cookie
     * 		  the AI player uses a CatanColor not taken by any other player
     * @param String name of AI
     * @return true if operation was successful, false if not
     */
	public boolean addAI(String ai){ return false; }
	
    /**
     * sets the servers logging level
     * @pre   a valid LogLevel is given
     * @post  server uses the given LogLevel
     * @param LogLevel to be applied
     * @return true if operation was successful, false if not
     */
	public boolean changeLogLevel(LogLevel logLevel){ return false; }
	
    /**
     * sends a chat message to other players
     * @post  chat contains the new message at the end
     * @param int index of the to chat String message to send
     * @return true if operation was successful, false if not
     */
	public boolean sendChat(int playerIndex, String message){ return false; }
	
    /**
     * either accept or decline a trade
     * @pre   a domestic trade has been offered to player
     * 		  if accepting then the player must have the required resources
     * @post  if accepting then traders swap resources
     * 		  trade offer is removed
     * @param int index of the player to trade boolean whether trade is accepted or not
     * @return true if operation was successful, false if not
     */
	public boolean acceptTrade(int playerIndex, boolean willAccept){ return false; }
	
    /**
     * discard any number of cards from hand
     * @pre   client model is in the state 'Discarding'
     * 		  player has over 7 cards
     * 		  player has the cards they choose to discard
     * @post  if player is the last to discard the client model status changes to 'Robbing'
     * 		  player gives up specified cards
     * @param LogLevel to be applied
     * @return true if operation was successful, false if not
     */
	public boolean discardCards(int playerIndex, List<Card> cards){ return false; }
	
    /**
     * rolls a number for the player
     * @pre   the client model is in the state 'Rolling'
     * 		  is it the players turn
     * @post  the client model is in the state 'Discarding', 'Robbing', or 'Playing'
     * @param int player index of roller int number rolled
     * @return true if operation was successful, false if not
     */
	public boolean rollNumber(int playerIndex, int rollNumber){ return false; }
	
    /**
     * player builds a road
     * @pre road location is open
     * 	    road location is connected to another of players roads
     * 		road location is not on water
     * 		player has necessary resources
     * 		it is players turn
     * 		the client model is in state 'Playing'
     * @post  players spends necessary resources
     * 		  map lists the new road
     * @param int player index of builder, edge to build the road on
     * @return true if operation was successful, false if not
     */
	public boolean buildRoad(int playerIndex, Edge edge){ return false; }
	
    /**
     * player builds a settlement
     * @pre settlement location is open
     * 	    settlement location is connected to at least one of players roads
     * 		settlement location is not on water
     * 		player has necessary resources
     *      it is players turn
     * 		the client model is in state 'Playing'
     * @post  players spends necessary resources
     * 		  map lists the new settlement
     * @param int player index of builder, corner to build the settlement on
     * @return true if operation was successful, false if not
     */
	public boolean buildSettlement(int playerIndex, Corner corner, boolean free){ return false; }
	
    /**
     * player builds a city
     * @pre	city location is where player currently has settlement
     * 		player has necessary resources
     * 		it is players turn
     * 		the client model is in state 'Playing'
     * @post  players spends necessary resources
     * 		  player gets settlement back
     * 		  map lists the new city
     * @param int player index of builder, corner to build the city on
     * @return true if operation was successful, false if not
     */
	public boolean buildCity(int playerIndex, Corner corner, boolean free){ return false; }
	
    /**
     * offer a trade to another player
     * @pre player has offered resources
     * 		it is players turn
     * 		the client model is in state 'Playing'
     * @post trade is offered to another player
     * @param int index of player offering trade, List<Card> cards to trade, int index of player to trade with
     * @return true if operation was successful, false if not
     */
	public boolean offerTrade(int playerIndex, List<Card> offer, int recieveIndex){ return false; }
	
    /**
     * make a trade at a port
     * @pre player has offered resources
     * 		it is players turn
     * 		the client model is in state 'Playing'
     * @param int index of player offering trade, int ratio of trade in, ResourceType of what player will give
     * ResourceType of what they will receive
     * @return true if operation was successful, false if not
     */
	public boolean maritimetrade(int playerIndex, int ratio, ResourceType input, ResourceType output){ return false; }
	
    /**
     * player ends turn
     * @pre the client model is in state 'Playing'
     * 		it is players turn
     * 		the client model is in state 'Playing'
     * @post it is the next players turn
     * @return true if operation was successful, false if not
     */
	public boolean finishTurn(){ return false; }
	
    /**
     * player buys a development card
     * @pre player has necessary resources
     * 		there are development cards left in the deck
     * 		it is players turn
     * 		the client model is in state 'Playing'
     * @post player has the new card
     * @param int player index of the player that is buying
     * @return Card the bought development card
     */
	public Card buyDevCard(int playerInex){ return false; }
	
    /**
     * player plays a year of plenty card
     * @pre the specified ResourceTypes are in the bank
     * 		player has specified development card
     * 		player has not played a development card yet this turn
     * 		it is players turn
     * @post player gains two of the specified ResourceType's
     * @param int player index of card player, ResourceType resource to gain, ResourceType another resource to gain
     * @return Card[] an array of two cards of the specified ResourceType's
     */
	public Card[] playYearOfPlenty(int playerIndex, ResourceType resourceOne, ResourceType resourceTwo){ return null; }
	
    /**
     * player plays a road building card
     * @pre the first road is connected to one of players roads
     * 		the second road is connected to one of players roads of previous road
     * 		neither location is on water
     * 		player has two roads
     * 		player has specified development card
     * 		player has not played a development card yet this turn
     * 		it is players turn
     * @post player uses two roads
     * 		 the map correctly lists the roads played
     * @param int index of player playing card, edge to place road, edge to place another road
     * @return true if operation was successful, false if not
     */
	public boolean playRoadBuilder(int playerIndex, Edge edgeOne, Edge edgeTwo){ return false; }
	
    /**
     * player plays a soldier card
     * @pre the robber is being moved
     * 		the player to rob has hards
     *      player has specified development card
     * 		player has not played a development card yet this turn
     * 		it is players turn
     * @post the robber is in new location
     * 		 the player to rob gives one random resource card to the player playing soldier card
     * @param int index of player playing card, int player index of player to rob, HexTile new location of the robber
     * @return Card the card stolen by the player from victim
     */
	public Card playSoldier(int playerIndex, int victimIndex, HexTile newRobberLocation){ return null; }
	
    /**
     * player plays a monopoly card
     * @pre
     * @post all other players lost their cards of specified ResourceType
     * 		 the player of card get an equal number of those lost
     * @param int index of player playing card, ResourceType of resource to be lost/gained
     * @return List<Card> containing all the cards gained by player of monopoly card
     */
	public List<Card> playMonopoly(int playerIndex, ResourceType resource){ return null; }
	
    /**
     * player plays a monument card
     * @pre player has specified development card
     * 		player has not played a development card yet this turn
     * 		it is players turn
     * 		the client model is in state 'Playing'
     * @post player gains a victory point
     * @param int index of player playing card
     * @return true if operation was successful, false if not
     */
	public boolean playMonument(int playerIndex){ return false; }
	
}
