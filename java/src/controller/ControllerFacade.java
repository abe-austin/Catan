/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import client.data.GameInfo;
import client.data.RobPlayerInfo;
import client.serverProxy.ServerPoller;
import client.serverProxy.ServerProxyFacade;
import game.GameModel;
import java.util.Timer;
import java.util.TimerTask;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 *
 * @author Cory
 */
public class ControllerFacade {
    private SetupController setupController;
    private GamePlayController gamePlayController;
    private TradeController tradeController;
    private GameInfoController gameInfoController;
    private GameModel currentGameModel;
    private ServerPoller serverPoller;
    private ServerProxyFacade serverProxyFacade;
    private Timer timer;
    
    public ControllerFacade(){
        
    }
    
    
     /**
     * 
     */
    public void startTimer(){
        timer= new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                serverPoller.poll();
                //ServerPoller.this.sendGameModel();
                
            }
        }, 1000, 1000);//timer to execute every second
    }
    /**
     * replaces the current gameModel with the new GameModel
     * @param gameModel 
     */
    void switchGameModel(GameModel gameModel){
        
    }
    /**
     * reassigns the controllers after the gameModel is replaced
     */
    void reassignControllers(){
        
    }
    
    void sendMessage(String message){//chat controller-- goes in gameInfo
        
    }
    
    //gamehistory is empty

    //buyDevCards is empty
    
    /**
	 * This method displays the "buy dev card" view.
	 */
	void startBuyCard(){//DevCardController --goes in GamePlay !!Not sure it is needed
            
        }
	
	/**
	 * This method is called when the user cancels out of buying a development card.
	 */
	void cancelBuyCard(){}//DevCardController --goes in GamePlay !!Not sure it is needed
	
	/**
	 * This method is called when the user buys a development card.
	 */
	void buyCard(){//DevCardController --goes in GamePlay
            
        }
	
	/**
	 * This method displays the "play dev card" view.
	 */
	void startPlayCard(){}//DevCardController --goes in GamePlay !!Not sure it is needed
	
	/**
	 * This method is called when the user cancels out of playing a development card.
	 */
	void cancelPlayCard(){}//DevCardController --goes in GamePlay !!Not sure it is needed
	
	/**
	 * This method is called when the user plays a monopoly development card.
	 * 
	 * @param resource The resource to take from other players
	 */
	void playMonopolyCard(ResourceType resource){//DevCardController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user plays a monument development card.
	 */
	void playMonumentCard(){//DevCardController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user plays a road build development card.
	 */
	void playRoadBuildCard(){//DevCardController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user plays a soldier development card.
	 */
	void playSoldierCard(){//DevCardController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user plays a year of plenty development card.
	 * 
	 * @param resource1 The first resource to gain
	 * @param resource2 The second resource to gain
	 */
	void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2){//DevCardController --goes in GamePlay
            
        }
        /**
	 * This method is called when the user increases the amount of the specified resource.
	 * 
	 * @param resource The resource that was increased
	 */
	void increaseAmount(ResourceType resource){//DiscardController --goes in GamePlay
            
        }

	/**
	 * This method is called when the user decreases the amount of the specified resource.
	 * 
	 * @param resource The resource that was decreased
	 */
	void decreaseAmount(ResourceType resource){//DiscardController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user clicks the discard button.
	 */
	void discard(){//DiscardController --goes in GamePlay
            
        }
        /**
	 * Called by the domestic trade view when the user clicks the domestic trade button.
	 */
	void startTrade(){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when the user decreases the amount of a resource.
	 * 
	 * @param resource The resource whose amount is being decreased
	 */
	void decreaseResourceAmount(ResourceType resource){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when the user increases the amount of a resource.
	 * 
	 * @param resource The resource whose amount is being increased
	 */
	void increaseResourceAmount(ResourceType resource){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when the user clicks the trade button.
	 */
	void sendTradeOffer(){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when the user selects a player to trade with.
	 * 
	 * @param playerIndex The index [0, 3] of the selected trading partner, or -1 if "None" was selected
	 */
	void setPlayerToTradeWith(int playerIndex){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when the user selects a resource to be received.
	 * 
	 * @param resource The resource to be received
	 */
	void setResourceToReceive(ResourceType resource){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when the user selects a resource to be sent.
	 * 
	 * @param resource The resource to be sent
	 */
	void setResourceToSend(ResourceType resource){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when user selects "none" for a resource.
	 * 
	 * @param resource The resource for which "none" was selected
	 */
	void unsetResource(ResourceType resource){//DomesticTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the domestic trade overlay when the user cancels a trade.
	 */
	void cancelTrade(){//DomesticTradeController --goes in Trade
            
        }

	/**
	 * Called by the accept trade overlay when the user either accepts or rejects a trade.
	 * 
	 * @param willAccept Whether or not the user accepted the trade
	 */
	void acceptTrade(boolean willAccept){//DomesticTradeController --goes in Trade
            
        }
        /**
	 * Called by the maritime trade view when the user clicks the maritime trade button.
	 */
	void startTrade(){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Make the specified trade with the bank.
	 */
	void makeTrade(){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the maritime trade overlay when the user cancels a trade.
	 */
	void cancelTrade(){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Called when the user selects the resource to get.
	 * 
	 * @param resource The selected "get" resource
	 */
	void setGetResource(ResourceType resource){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Called when the user selects the resource to give.
	 * 
	 * @param resource The selected "give" resource
	 */
	void setGiveResource(ResourceType resource){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Called when the player "undoes" their get selection.
	 */
	void unsetGetValue(){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Called when the player "undoes" their give selection.
	 */
	void unsetGiveValue(){//MaritimeTradeController --goes in Trade
            
        }
    /**
	 * Displays the join game view
	 */
	void start(){//JoinGameController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called by the join game view when the user clicks "Create new game" button.
	 * Displays the new game view.
	 */
	void startCreateNewGame(){//JoinGameController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called by the new game view when the user clicks the "Cancel" button
	 */
	void cancelCreateNewGame(){//JoinGameController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called by the new game view when the user clicks the "Create Game" button
	 */
	void createNewGame(){//JoinGameController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called by the join game view when the user clicks a "Join" or "Re-join" button.
	 * Displays the select color view.
	 * 
	 * @param game The game that the user is joining
	 */
	void startJoinGame(GameInfo game){//JoinGameController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called by the select color view when the user clicks the "Cancel" button
	 */
	void cancelJoinGame(){//JoinGameController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called by the select color view when the user clicks the "Join Game" button
	 * 
	 * @param color The color selected by the user
	 */
	void joinGame(CatanColor color){//JoinGameController --goes in Setup
            
        }
        /**
	 * Displays the player waiting view
	 */
	void start(){//PlayerWaitingController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called when the "Add AI" button is clicked in the player waiting view
	 */
	void addAI(){//PlayerWaitingController --goes in Setup
            
        }
        /**
	 * Displays the login view
	 */
	void start(){//LoginController --goes in Setup !!Not sure if needed
            
        }
	
	/**
	 * Called when the user clicks the "Sign in" button in the login view
	 */
	void signIn(){//LoginController --goes in Setup
            
        }
	
	/**
	 * Called when the user clicks the "Register" button in the login view
	 */
	void register(){//LoginController --goes in Setup
            
        }
	/**
	 * This method is called whenever the user is trying to place a road on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the road to be placed at the specified location.
	 * 
	 * @param edgeLoc The proposed road location
	 * @return true if the road can be placed at edgeLoc, false otherwise
	 */
	boolean canPlaceRoad(EdgeLocation edgeLoc){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called whenever the user is trying to place a settlement on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the settlement to be placed at the specified location.
	 * 
	 * @param vertLoc The proposed settlement location
	 * @return true if the settlement can be placed at vertLoc, false otherwise
	 */
	boolean canPlaceSettlement(VertexLocation vertLoc){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called whenever the user is trying to place a city on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the city to be placed at the specified location.
	 * 
	 * @param vertLoc The proposed city location
	 * @return true if the city can be placed at vertLoc, false otherwise
	 */
	boolean canPlaceCity(VertexLocation vertLoc){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called whenever the user is trying to place the robber on the map. 
	 * It is called by the view for each "mouse move" event. The returned value tells 
	 * the view whether or not to allow the robber to be placed at the specified location.
	 * 
	 * @param hexLoc The proposed robber location
	 * @return true if the robber can be placed at hexLoc, false otherwise
	 */
	boolean canPlaceRobber(HexLocation hexLoc){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user clicks the mouse to place a road. 
	 * 
	 * @param edgeLoc The road location
	 */
	void placeRoad(EdgeLocation edgeLoc){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user clicks the mouse to place a settlement. 
	 * 
	 * @param vertLoc The settlement location
	 */
	void placeSettlement(VertexLocation vertLoc){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user clicks the mouse to place a city. 
	 * 
	 * @param vertLoc The city location
	 */
	void placeCity(VertexLocation vertLoc){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user clicks the mouse to place the robber. 
	 * 
	 * @param hexLoc The robber location
	 */
	void placeRobber(HexLocation hexLoc){//MapController --goes in GamePlay
            
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
	void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called from the modal map overlay when the cancel button is pressed.
	 */
	void cancelMove(){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user plays a "soldier" development card.
	 * It should initiate robber placement. 
	 */
	void playSoldierCard(){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called when the user plays a "road building" progress development card.
	 * It should initiate the process of allowing the player to place two roads.
	 */
	void playRoadBuildingCard(){//MapController --goes in GamePlay
            
        }
	
	/**
	 * This method is called by the Rob View when a player to rob is selected via a button click.
	 * 
	 * @param victim The player to be robbed
	 */
	void robPlayer(RobPlayerInfo victim){//MapController --goes in GamePlay
            
        }
        
        //PointsController is empty
        
        /**
	 * Called by the view then the user requests to build a road
	 */
	void buildRoad(){//ResourceBarController --goes in GamePlay
            
        }
	
	/**
	 * Called by the view then the user requests to build a settlement
	 */
	void buildSettlement(){//ResourceBarController --goes in GamePlay
            
        }

	/**
	 * Called by the view then the user requests to build a city
	 */
	void buildCity(){//ResourceBarController --goes in GamePlay
            
        }
	
	/**
	 * Called by the view then the user requests to buy a card
	 */
	void buyCard(){//ResourceBarController --goes in GamePlay
            
        }
	
	/**
	 * Called by the view then the user requests to play a card
	 */
	void playCard(){//ResourceBarController --goes in GamePlay
            
        }
        /**
	 * Called when the user clicks the "Roll!" button in the roll view
	 */
	void rollDice(){//RollController --goes in GamePlay or GameInfo not sure where I want it
            
        }
        /**
	 * This is called when the local player ends their turn
	 */
	void endTurn(){//TurnTrackerController --goes in GameInfo
            
        }
}
