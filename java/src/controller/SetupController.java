/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import shared.definitions.CatanColor;
import client.base.IAction;
import client.data.GameInfo;

/**
 * JoinGameController, PlayerWaitingController, LoginController
 */
class SetupController {
	
	public SetupController() {
		
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
}
