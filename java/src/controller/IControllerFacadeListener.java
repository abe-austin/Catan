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
public interface IControllerFacadeListener {
    public void gameModelChanged(GameModel gameModel);
    
}
