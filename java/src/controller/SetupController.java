package controller;

import shared.definitions.CatanColor;
import system.User;
import game.GameModel;

/**
 * JoinGameController, PlayerWaitingController, LoginController
 */
class SetupController {
     @SuppressWarnings("unused")
	private GameModel gameModel;
    
    public void switchGameModel(GameModel gameModel){
       
    	this.gameModel = gameModel;
    }

	public SetupController() {
	
	}
    
    /**
	 * Displays the join game view
	 */
	void joingGameStart(){} //JoinGameController --goes in Setup !!Not sure if needed
            
	/**
	 * Called by the join game view when the user clicks "Create new game" button.
	 * Displays the new game view.
	 */
 
	/**
	 * Called by the new game view when the user clicks the "Create Game" button
	 */
	void createNewGame(){} //JoinGameController --goes in Setup !!Not sure if needed
            
	/**
	 * Called by the select color view when the user clicks the "Join Game" button
	 * 
	 * @param color The color selected by the user
	 */
	void joinGame(CatanColor color, User user){ //JoinGameController --goes in Setup

    }
	
     /**
	 * Displays the player waiting view
	 */
	void playerWaitingStart(){} //PlayerWaitingController --goes in Setup !!Not sure if needed
            
	/**
	 * Called when the "Add AI" button is clicked in the player waiting view
	 */
	void addAI(String AIType){ //PlayerWaitingController --goes in Setup
		
	}
            
	/**
	 * Called when the user clicks the "Sign in" button in the login view
	 */
	void signIn(String username, String password){ //LoginController --goes in Setup
		
    }
	
	/**
	 * Called when the user clicks the "Register" button in the login view
	 */
	void register(String username, String password){ //LoginController --goes in Setup
		
    }
}
