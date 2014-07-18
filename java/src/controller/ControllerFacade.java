/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import client.base.IAction;
import client.data.GameInfo;
import client.data.RobPlayerInfo;
import client.serverProxy.ServerPoller;
import client.serverProxy.ServerProxyFacade;
import game.GameModel;
import game.board.Corner;
import game.board.Edge;
import game.board.HexTile;
import game.board.PortTile;
import game.cards.CardOwner;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import player.Player;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.GameState;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import system.User;

/**
 *
 * @author Cory
 */
public class ControllerFacade {
    private static ControllerFacade singleton = new ControllerFacade();
    
    public static ControllerFacade getSingleton() {
        return singleton;
    }
    
    private SetupController setupController;
    private GamePlayController gamePlayController;
    private TradeController tradeController;
    private GameInfoController gameInfoController;
    private GameModel currentGameModel;
    private ServerPoller serverPoller;
    private ServerProxyFacade serverProxyFacade;
    private Timer timer;   
    private GameState gameState;
    private User user;
    private Player clientPlayer;
    
    private ControllerFacade(){
        setupController= new SetupController();
        gamePlayController = new GamePlayController(clientPlayer);
        clientPlayer=new Player(CatanColor.BLUE,null,1);//testing purposes
        tradeController= new TradeController(clientPlayer);
        gameInfoController= new GameInfoController();
        currentGameModel= new GameModel();
        serverPoller= new ServerPoller();
        serverProxyFacade = new ServerProxyFacade(false);
        gameState= GameState.Login;
        reassignControllers();
        startPolling();
    }
    
     /**
     * 
     */
    public final void startPolling(){
        timer= new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                serverPoller.poll();
                switchGameModel(serverPoller.getGameModel());
                //reassignControllers();
                //updateGui();
            }
        }, 1000, 1000);//timer to execute poll every second
    }
    /**
     * replaces the current gameModel with the new GameModel
     * @param gameModel 
     */
    public final void switchGameModel(GameModel gameModel){
        currentGameModel = gameModel;
    }
    /**
     * reassigns the controllers after the gameModel is replaced
     */
    public final void reassignControllers(){
        gamePlayController.switchGameModel(currentGameModel);
        tradeController.switchGameModel(currentGameModel);
        setupController.switchGameModel(currentGameModel);
        gameInfoController.switchGameModel(currentGameModel);
    }
    
    public void setUser(User user){
        this.user=user;
    }
    
    public User getUser(){
        return user;
    }
    
    public void sendMessage(String message){//chat controller-- goes in gameInfo
        
    }
    
    public Player getClientPlayer() {
        return clientPlayer;
    }
    
    /**
     * This exchanges a Resource between two card owners
     *
     * @param receiver
     * @param giver
     * @param resource card type
     * @pre giver has resource card of given type
     * @post receiver has resource card of given type
     */
    public void changeOwnerResource(CardOwner receiver, CardOwner giver, ResourceType resource) {
        gamePlayController.changeOwnerResource(receiver, giver, resource);
    }

    /**
     * This exchanges a Special Card between two card owners
     *
     * @param receiver
     * @param giver
     * @param special card type
     * @pre giver has special card of given type
     * @post receiver has special card of given type
     */
    public void changeOwnerSpecial(CardOwner receiver, CardOwner giver, SpecialCardType special) {
        gamePlayController.changeOwnerSpecial(receiver, giver, special);
    }
    
    
    //gamehistory is empty

    //buyDevCards is empty
    
        /**
	 * This method displays the "buy dev card" view.
         * @return true if player has enough resources to buy devCard
	 */
	public boolean startBuyCard(){//DevCardController --goes in GamePlay !!Not sure it is needed
            switch(gameState){
                case GamePlay:
                    return gamePlayController.startBuyCard();
                default:
                    return false;
            }
        }
	
	/**
	 * This method is called when the user cancels out of buying a development card.
	 */
	public void cancelBuyCard(){
            switch(gameState){
                case GamePlay:
                    
                default:
            }
            
        }//DevCardController --goes in GamePlay !!Not sure it is needed
	
	/**
	 * This method is called when the user buys a development card.
	 */
	public void buyCard(){//DevCardController and resourecBarController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.buyCard();
                default:
                    
            }
        }
	
	/**
	 * This method displays the "play dev card" view.
         * @return list of player's current devCards
	 */
	public ArrayList<DevCardType> startPlayCard(){
            switch(gameState){
                case GamePlay:
                    return gamePlayController.startPlayCard();
                default:
                    return null;
                    
            }
        }//DevCardController --goes in GamePlay !!Not sure it is needed
	
	/**
	 * This method is called when the user plays a monopoly development card.
	 * 
	 * @param resource The resource to take from other players
	 */
	public void playMonopolyCard(ResourceType resource){//DevCardController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.playMonopolyCard(resource);
                default:
                    
            }
        }
	
	/**
	 * This method is called when the user plays a monument development card.
	 */
	public void playMonumentCard(){//DevCardController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.playMonumentCard();
                default:
                    
            }
        }
	
	/**
	 * This method is called when the user plays a road build development card.
	 */
	public void playRoadBuildCard(){//DevCardController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.playRoadBuildCard();
                default:
                    
            }
        }
	
	/**
	 * This method is called when the user plays a soldier development card.
	 */
	public void playSoldierCard(){//DevCardController and MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.playSoldierCard();
                default:
                    
            }
        }
	
	/**
	 * This method is called when the user plays a year of plenty development card.
	 * 
	 * @param resource1 The first resource to gain
	 * @param resource2 The second resource to gain
	 */
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2){//DevCardController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.playYearOfPlentyCard(resource1, resource2);
                default:
                    
            }
        }
        /**
	 * This method is called when the user increases the amount of the specified resource.
	 * 
	 * @param resource The resource that was increased
         * @param number current number of cards to discard before change
         * @return true if player has more of given resource type
	 */
	public boolean increaseAmount(ResourceType resource, int number){//DiscardController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    return gamePlayController.increaseAmount(resource, number);
                default:
                    return false;
            }
        }

	/**
	 * This method is called when the user decreases the amount of the specified resource.
	 * 
	 * @param resource The resource that was decreased
         * @param number current number of cards to discard before change
         * @return true if number > 0
	 */
	public boolean decreaseAmount(ResourceType resource, int number){//DiscardController --goes in GamePlay
            return gamePlayController.decreaseAmount(resource, number);
        }
	
	/**
	 * This method is called when the user clicks the discard button.
         * @param toDiscard resource list to discard
         * @return true if discard happened, false if not enough cards for discard
	 */
	public boolean discard(ArrayList<ResourceType> toDiscard){//DiscardController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    return gamePlayController.discard(toDiscard);
                default:
                    return false;
            }
        }
        /**
	 * Called by the domestic trade view when the user clicks the domestic trade button.
         * @return 
	 */
	public ArrayList<ResourceType> domesticStartTrade(){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    ArrayList<ResourceType> playerResourceTypes =tradeController.getPlayerResourceTypes();
                    return playerResourceTypes;
            }
            return null;
        }
	
	/**
	 * Called by the domestic trade overlay when the user decreases the amount of a resource.
	 * 
	 * @param resource The resource whose amount is being decreased
	 */
	public void decreaseResourceAmount(ResourceType resource){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the domestic trade overlay when the user increases the amount of a resource.
	 * 
	 * @param resource The resource whose amount is being increased
	 */
	public void increaseResourceAmount(ResourceType resource){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the domestic trade overlay when the user clicks the trade button.
	 */
	public void sendTradeOffer(){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the domestic trade overlay when the user selects a player to trade with.
	 * 
	 * @param playerIndex The index [0, 3] of the selected trading partner, or -1 if "None" was selected
	 */
	public void setPlayerToTradeWith(int playerIndex){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay://Need to return that player or something...not sure how to get the player object from the index at this point though
                    break;
            }
        }
	
	/**
	 * Called by the domestic trade overlay when the user selects a resource to be received.
	 * 
	 * @param resource The resource to be received
	 */
	public void setResourceToReceive(ResourceType resource){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                	break;
            }
        }
	
	/**
	 * Called by the domestic trade overlay when the user selects a resource to be sent.
	 * 
	 * @param resource The resource to be sent
	 */
	public void setResourceToSend(ResourceType resource){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the domestic trade overlay when user selects "none" for a resource.
	 * 
	 * @param resource The resource for which "none" was selected
	 */
	public void unsetResource(ResourceType resource){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the domestic trade overlay when the user cancels a trade.
	 */
	public void domesticCancelTrade(){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }

	/**
	 * Called by the accept trade overlay when the user either accepts or rejects a trade.
	 * 
	 * @param willAccept Whether or not the user accepted the trade
	 */
	public void acceptTrade(boolean willAccept){//DomesticTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
        /**
	 * Called by the maritime trade view when the user clicks the maritime trade button.
         * @return 
	 */
	public ArrayList<ArrayList<ResourceType>> maritimeStartTrade(){//MaritimeTradeController --goes in Trade
gameState=GameState.GamePlay;//for testing purposes
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    return tradeController.maritimeStartTrade();               
            }
            return null;
        }

	/**
	 * Make the specified trade with the bank.
	 */
	public void makeTrade(){//MaritimeTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the maritime trade overlay when the user cancels a trade.
	 */
	public void maritimeCancelTrade(){//MaritimeTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called when the user selects the resource to get.
	 * 
	 * @param resource The selected "get" resource
	 */
	public void setGetResource(ResourceType resource){//MaritimeTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called when the user selects the resource to give.
	 * 
	 * @param resource The selected "give" resource
	 */
	public int setGiveResource(ResourceType resource){//MaritimeTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    return tradeController.setGetResource(resource);
            }
            return -1;
        }
	
	/**
	 * Called when the player "undoes" their get selection.
	 */
	public void unsetGetValue(){//MaritimeTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called when the player "undoes" their give selection.
	 */
	public void unsetGiveValue(){//MaritimeTradeController --goes in Trade
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
        /**
	 * Displays the join game view
	 */
	public void joinGameStart(){//JoinGameController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                	setupController.joingGameStart();
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the join game view when the user clicks "Create new game" button.
	 * Displays the new game view.
	 */
	public void startCreateNewGame(){//JoinGameController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                	setupController.startCreateNewGame();
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the new game view when the user clicks the "Cancel" button
	 */
	public void cancelCreateNewGame(){//JoinGameController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                	setupController.cancelCreateNewGame();
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the new game view when the user clicks the "Create Game" button
	 */
	public void createNewGame(){//JoinGameController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                	setupController.createNewGame();
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the join game view when the user clicks a "Join" or "Re-join" button.
	 * Displays the select color view.
	 * 
	 * @param game The game that the user is joining
	 */
	public void startJoinGame(GameInfo game){//JoinGameController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                	setupController.startJoingGame(game);
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the select color view when the user clicks the "Cancel" button
	 */
	public void cancelJoinGame(){//JoinGameController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                	setupController.cancelJoinGame();
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called by the select color view when the user clicks the "Join Game" button
	 * 
	 * @param color The color selected by the user
	 */
	public void joinGame(CatanColor color){//JoinGameController --goes in Setup
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                	User user = new User();
                	setupController.joinGame(color, user);
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
        /**
	 * Displays the player waiting view
	 */
	public void playerWaitingStart(){//PlayerWaitingController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                	setupController.playerWaitingStart();
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called when the "Add AI" button is clicked in the player waiting view
	 */
	public void addAI(){//PlayerWaitingController --goes in Setup
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                	String AIType = null;
                	setupController.addAI(AIType);
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
        /**
	 * Displays the login view
	 */
	public void loginStart(){//LoginController --goes in Setup !!Not sure if needed
            switch(gameState){
                case Login:
                	setupController.loginStart();
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called when the user clicks the "Sign in" button in the login view
	 */
	public void signIn(){//LoginController --goes in Setup
            switch(gameState){
                case Login:
                	String username = "";
                	String password = "";
                	setupController.signIn(username, password);
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	/**
	 * Called when the user clicks the "Register" button in the login view
	 */
	public void register(){//LoginController --goes in Setup
            switch(gameState){
                case Login:
                	String username = "";
                	String password = "";
                	setupController.register(username, password);
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
	
	public int getPortType(int x, int y) {
		PortTile hTile = (PortTile) gamePlayController.getGameModel().getBoard().getHexTileAt(x, y);
		switch (hTile.getPortType()) {
			case BRICK: return 1;
			case WHEAT: return 2;
			case ORE: return 3;
			case SHEEP: return 4;
			case WOOD: return 5;
			case THREE: return 6;
		}
		return 0;
	}
	
	/**
	 * This method is called whenever the user is trying to place a road on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the road to be placed at the specified location.
	 * 
	 * @param edgeLoc The proposed road location
	 * @return true if the road can be placed at edgeLoc, false otherwise
	 */
	public boolean canPlaceRoad(EdgeLocation edgeLoc){//MapController --goes in GamePlay
            switch(gameState){
                case Login:
                	return false;
                case JoinGame:
                    return false;
                case PlayerWaiting:
                    return false;
                case Setup:
                    return false;
                case GamePlay:
                    return gamePlayController.canPlaceRoad(edgeLoc);
            }
            return false;
        }
	
	/**
	 * This method is called whenever the user is trying to place a settlement on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the settlement to be placed at the specified location.
	 * 
	 * @param vertLoc The proposed settlement location
	 * @return true if the settlement can be placed at vertLoc, false otherwise
	 */
	public boolean canPlaceSettlement(VertexLocation vertLoc){//MapController --goes in GamePlay
            switch(gameState){
                case Login:
                    return false;
                case JoinGame:
                    return false;
                case PlayerWaiting:
                    return false;
                case Setup:
                    return false;
                case GamePlay:
                    return gamePlayController.canPlaceSettlement(vertLoc);
            }
            return false;
        }
	
	/**
	 * This method is called whenever the user is trying to place a city on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the city to be placed at the specified location.
	 * 
	 * @param vertLoc The proposed city location
	 * @return true if the city can be placed at vertLoc, false otherwise
	 */
	public boolean canPlaceCity(VertexLocation vertLoc){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    return gamePlayController.canPlaceCity(vertLoc);
                default:
                    return false;
            }
        }
	
	/**
	 * This method is called whenever the user is trying to place the robber on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the robber to be placed at the specified location.
	 * 
	 * @param hexLoc The proposed robber location
	 * @return true if the robber can be placed at hexLoc, false otherwise
	 */
	public boolean canPlaceRobber(HexLocation hexLoc){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.canPlaceRobber(hexLoc);
                default:
                    return false;
            }
        }
	
	/**
	 * This method is called when the user clicks the mouse to place a road. 
	 * 
	 * @param edgeLoc The road location
	 */
	public void placeRoad(EdgeLocation edgeLoc){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                	HexTile theHex = gamePlayController.getGameModel().getBoard().getHexTileAt(edgeLoc.getHexLoc().getX(), edgeLoc.getHexLoc().getY());
                	Edge e = null;
                	
                	switch(edgeLoc.getDir()) {
                		case North: e = theHex.northEdge; break;
                		case NorthEast: e = theHex.northEastEdge; break;
                		case SouthEast: e = theHex.southEastEdge; break;
                		case South: e = theHex.southEdge; break;
                		case SouthWest: e = theHex.southWestEdge; break;
                		case NorthWest: e = theHex.northWestEdge; break;
                	}
                	gamePlayController.placeRoad(edgeLoc);
                	serverProxyFacade.buildRoad(gamePlayController.getPlayer().getIndex(), e);
                	break;
                default:
            }
        }
	
	/**
	 * This method is called when the user clicks the mouse to place a settlement. 
	 * 
	 * @param vertLoc The settlement location
	 */
	public void placeSettlement(VertexLocation vertLoc){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                	HexTile theHex = gamePlayController.getGameModel().getBoard().getHexTileAt(vertLoc.getHexLoc().getX(), vertLoc.getHexLoc().getY());
                	Corner c = null;
                	
                	switch(vertLoc.getDir()) {
                		case East: c = theHex.eastCorner; break;
                		case NorthEast: c = theHex.northEastCorner; break;
                		case SouthEast: c = theHex.southEastCorner; break;
                		case West: c = theHex.westCorner; break;
                		case SouthWest: c = theHex.southWestCorner; break;
                		case NorthWest: c = theHex.northWestCorner; break;
                	}
                	gamePlayController.placeSettlement(vertLoc);
                	serverProxyFacade.buildSettlement(gamePlayController.getPlayer().getIndex(), c, true);
                	break;
                default:
            }
        }
	
	/**
	 * This method is called when the user clicks the mouse to place a city. 
	 * 
	 * @param vertLoc The city location
	 */
	public void placeCity(VertexLocation vertLoc){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                	HexTile theHex = gamePlayController.getGameModel().getBoard().getHexTileAt(vertLoc.getHexLoc().getX(), vertLoc.getHexLoc().getY());
                	Corner c = null;
                	
                	switch(vertLoc.getDir()) {
                		case East: c = theHex.eastCorner; break;
                		case NorthEast: c = theHex.northEastCorner; break;
                		case SouthEast: c = theHex.southEastCorner; break;
                		case West: c = theHex.westCorner; break;
                		case SouthWest: c = theHex.southWestCorner; break;
                		case NorthWest: c = theHex.northWestCorner; break;
                	}
                	gamePlayController.placeCity(vertLoc);
                	serverProxyFacade.buildCity(gamePlayController.getPlayer().getIndex(), c, true);
                	break;
                default:
            }
        }
	
	/**
	 * This method is called when the user clicks the mouse to place the robber. 
	 * 
	 * @param hexLoc The robber location
	 */
	public void placeRobber(HexLocation hexLoc){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                	//ServerProxy does not have a method to move the robber
                default:
            }
        }
	
	/**
	 * This method is called when the user requests to place a piece on the map (road, city, or settlement)
	 * 
	 * @param pieceType The type of piece to be placed
	 * @param isFree true if the piece should not cost the player resources, false otherwise.
	 * 				Set to true during initial setup and when a road building card is played.
	 * @param allowDisconnected true if the piece can be disconnected, false otherwise.
	 * 				Set to true only during initial setup.
	 */
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                	gamePlayController.startMove(pieceType, isFree, allowDisconnected);
                default:
            }
        }
	
	/**
	 * This method is called from the modal map overlay when the cancel button is pressed.
	 */
	public void cancelMove(){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                	gamePlayController.cancelMove();
                default:
            }
        }
	
	/**
	 * This method is called when the user plays a "road building" progress development card.
	 * It should initiate the process of allowing the player to place two roads.
	 */
	public void playRoadBuildingCard(){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.playRoadBuildingCard();
                default:
            }
        }
	
	/**
	 * This method is called by the Rob View when a player to rob is selected via a button click.
	 * 
	 * @param victim The player to be robbed
         * @post client player receives Resource Card from victim at random
	 */
	public void robPlayer(RobPlayerInfo victim){//MapController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    gamePlayController.robPlayer(victim);
                default:
            }
        }
        
        //PointsController is empty
        
        /**
	 * Called by the view then the user requests to build a road
         * @return true if player has enough resources and an available road
	 */
	public boolean buildRoad(){//ResourceBarController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    return gamePlayController.buildRoad();
                default:
                    return false;
            }
        }
	
	/**
	 * Called by the view then the user requests to build a settlement
         * @return true if player has enough resources and an available settlement
	 */
	public boolean buildSettlement(){//ResourceBarController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    return gamePlayController.buildSettlement();
                default:
                    return false;
            }
        }

	/**
	 * Called by the view then the user requests to build a city
         * @return true if player has enough resources and an available city
	 */
	public boolean buildCity(){//ResourceBarController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    return gamePlayController.buildCity();
                default:
                    return false;
            }
        }
	
	/**
	 * Called by the view then the user requests to play a card
         * @return true if player has at least one devCard
	 */
	public boolean playCard(){//ResourceBarController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    return gamePlayController.playCard();
                default:
                    return false;
            }
        }
        /**
	 * Called when the user clicks the "Roll!" button in the roll view
         * @return dice roll value (2-12)
	 */

	public int rollDice(){//RollController --goes in GamePlay
            switch(gameState){
                case GamePlay:
                    int roll = gamePlayController.rollDice();
                    if(roll != 7)
                        gamePlayController.rollResourceDistribution(roll);
                    
                    // IF seven roll, player moves robber and hand size checked
                    
                    return roll;
                default:
                    return -1;
            }
        }
        /**
	 * This is called when the local player ends their turn
	 */
	public void endTurn(){//TurnTrackerController --goes in GameInfo
            switch(gameState){
                case Login:
                    break;
                case JoinGame:
                    break;
                case PlayerWaiting:
                    break;
                case Setup:
                    break;
                case GamePlay:
                    break;
            }
        }
        
        /**
         * Gives the number of a given DevCardType the client player has
         * 
         * @param type of DevCard
         * @return number of cards
         */
        public int getNumOfDevCards(DevCardType type) {
            switch(gameState){
                case GamePlay:
                    return gamePlayController.getNumOfDevCards(type);
                default:
                    return -1;
            }
        }
        
        /**
         * Gives the number of a given ResourceType the client player has
         * 
         * @param type of Resource
         * @return number of cards
         */
        public int getNumOfResourceCards(ResourceType type) {
            switch(gameState){
                case GamePlay:
                    return gamePlayController.getNumOfResourceCards(type);
                default:
                    return -1;
            }
        }
        
        /**
         * Allows access to robber/soldier action
         * 
         * @param action 
         */
        public void setRobberAction(IAction action) {
            gamePlayController.setRobberAction(action);
        }
}
