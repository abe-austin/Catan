/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import game.GameModel;

/**
 *
 * @author Cory
 */
class GameInfoController {
    private GameModel gameModel;
    
    public void switchGameModel(GameModel gameModel){
        this.gameModel = gameModel;
    }
    
     /**
	 * This is called when the local player ends their turn
	 */
	void endTurn(){//TurnTrackerController --goes in GameInfo
            
        }
}
