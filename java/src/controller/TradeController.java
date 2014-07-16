/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import game.GameModel;
import shared.definitions.ResourceType;

/**
 *
 * @author Cory
 */
class TradeController {
    private GameModel gameModel;
    
    public TradeController(){
        
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
	void maritimeStartTrade(){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Make the specified trade with the bank.
	 */
	void makeTrade(){//MaritimeTradeController --goes in Trade
            
        }
	
	/**
	 * Called by the maritime trade overlay when the user cancels a trade.
	 */
	void maritimeCancelTrade(){//MaritimeTradeController --goes in Trade
            
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
    
}
