package controller;

import player.Player;
import shared.definitions.CatanColor;
import system.User;
import client.data.GameInfo;
import client.serverProxy.ServerProxyFacade;
import game.GameModel;

/**
 * JoinGameController, PlayerWaitingController, LoginController
 */
class SetupController {
     private GameModel gameModel;
    
    public void switchGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
	
	ServerProxyFacade serverProxyFacade;
	//this controller doesnt have access to the serverProxy, the controllerFacade does.
        //any method that needs to send things to the server should return it to the controller Facade
	public SetupController() {
		serverProxyFacade = new ServerProxyFacade(true);
	}
    
    /**
	 * Displays the join game view
	 */
	void joingGameStart(){} //JoinGameController --goes in Setup !!Not sure if needed
            
	/**
	 * Called by the join game view when the user clicks "Create new game" button.
	 * Displays the new game view.
	 */
	void startCreateNewGame(){} //JoinGameController --goes in Setup !!Not sure if needed
            
	/**
	 * Called by the new game view when the user clicks the "Cancel" button
	 */
	void cancelCreateNewGame(){} //JoinGameController --goes in Setup !!Not sure if needed
 
	/**
	 * Called by the new game view when the user clicks the "Create Game" button
	 */
	void createNewGame(){} //JoinGameController --goes in Setup !!Not sure if needed
            
	/**
	 * Called by the join game view when the user clicks a "Join" or "Re-join" button.
	 * Displays the select color view.
	 * 
	 * @param game The game that the user is joining
	 */
	void startJoingGame(GameInfo game){} //JoinGameController --goes in Setup !!Not sure if needed
            
	/**
	 * Called by the select color view when the user clicks the "Cancel" button
	 */
	void cancelJoinGame(){} //JoinGameController --goes in Setup !!Not sure if needed
           
	/**
	 * Called by the select color view when the user clicks the "Join Game" button
	 * 
	 * @param color The color selected by the user
	 */
	void joinGame(CatanColor color, User user){ //JoinGameController --goes in Setup
            serverProxyFacade.joinGame(user.getId(), color);
    }
	
        /**
	 * Displays the player waiting view
	 */
	void playerWaitingStart(){} //PlayerWaitingController --goes in Setup !!Not sure if needed
            
	/**
	 * Called when the "Add AI" button is clicked in the player waiting view
	 */
	void addAI(String AIType){ //PlayerWaitingController --goes in Setup
		serverProxyFacade.addAI(AIType);
    }
	
        /**
	 * Displays the login view
	 */
	void loginStart(){} //LoginController --goes in Setup !!Not sure if needed
            
	/**
	 * Called when the user clicks the "Sign in" button in the login view
	 */
	void signIn(String username, String password){ //LoginController --goes in Setup
		serverProxyFacade.loginUser(username, password);
    }
	
	/**
	 * Called when the user clicks the "Register" button in the login view
	 */
	void register(String username, String password){ //LoginController --goes in Setup
		serverProxyFacade.registerUser(username, password);
    }
}
