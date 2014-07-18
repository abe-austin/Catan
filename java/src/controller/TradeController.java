/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import game.GameModel;
import java.util.ArrayList;
import player.Player;
import shared.definitions.ResourceType;

/**
 *
 * @author Cory
 */
class TradeController {
    private GameModel gameModel;
    private Player player;
    
    public TradeController(Player player){
        this.player=player;
    }
    
     public void switchGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
    /**
	 * Called by the domestic trade view when the user clicks the domestic trade button.
	 */
	void domesticStartTrade(){//DomesticTradeController --goes in Trade
            
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
	void domesticCancelTrade(){//DomesticTradeController --goes in Trade
            
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
	public ArrayList<ArrayList<ResourceType>> maritimeStartTrade(){//MaritimeTradeController --goes in Trade
            ArrayList<ResourceType> playerResourceTypes =getPlayerResourceTypes();
            ArrayList<ResourceType> bankResourceTypes=getBankResourceTypes();
            ArrayList< ArrayList<ResourceType>> resources=new ArrayList<>();
            resources.add(playerResourceTypes);
            resources.add(bankResourceTypes);
            return resources;      
        }
	
	/**
	 * Make the specified trade with the bank.
	 */
	public void makeTrade(){//MaritimeTradeController --goes in Trade
            
        }
        
        public ArrayList<ResourceType> getBankResourceTypes(){
            ArrayList<ResourceType> resources = new ArrayList<>();
            if(gameModel==null)System.out.println("gameModel null");
            else if ( gameModel.getBank()== null)System.out.println("bank null");
            if ( gameModel.getBank().hasResource(ResourceType.WHEAT)){
                resources.add(ResourceType.WHEAT);
            }
            if ( gameModel.getBank().hasResource(ResourceType.BRICK)){
                resources.add(ResourceType.BRICK);
            }
            if ( gameModel.getBank().hasResource(ResourceType.ORE)){
                resources.add(ResourceType.ORE);
            }
            if ( gameModel.getBank().hasResource(ResourceType.SHEEP)){
                resources.add(ResourceType.SHEEP);
            }
            if ( gameModel.getBank().hasResource(ResourceType.WOOD)){
                resources.add(ResourceType.WOOD);
            }
            return resources;
        }
        
        public ArrayList<ResourceType> getPlayerResourceTypes(){
            
            //need to figure out if player has ports and what type first
            
            ArrayList<ResourceType> resources = new ArrayList<>();
            if ( player.hasResource(ResourceType.WHEAT)){
                resources.add(ResourceType.WHEAT);
            }
            if ( player.hasResource(ResourceType.BRICK)){
                resources.add(ResourceType.BRICK);
            }
            if ( player.hasResource(ResourceType.ORE)){
                resources.add(ResourceType.ORE);
            }
            if ( player.hasResource(ResourceType.SHEEP)){
                resources.add(ResourceType.SHEEP);
            }
            if ( player.hasResource(ResourceType.WOOD)){
                resources.add(ResourceType.WOOD);
            }
            return resources;
        }
}
