/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.serverProxy;
import controller.ControllerFacade;
import game.GameModel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Cory
 */
public class ServerPoller {
    private ServerProxyFacade serverProxy;  
    private GameModel gameModel;
    private Timer timer;
    private ControllerFacade controllerFacade;
    /**
     * empty constructor
     */
    public ServerPoller(ControllerFacade controller){
        serverProxy=null;
        gameModel=null;
        controllerFacade=controller;
    }
    /**
     * @pre the server proxy being pass in is valid
     * sets the server proxy that it will connect to
     * @param serverProxy  the serverProxy
     */
    public void setServerProxy( ServerProxyFacade serverProxy){
        this.serverProxy = serverProxy;
    }
    /**
     * 
     * @return the serverProxyFacade currently assigned, null if none
     */
    public ServerProxyFacade getServerProxy(){
        return serverProxy;
    }
    /**
     * sends the current game model to the model
     */
    public GameModel getGameModel(){
        return gameModel;
    }
    /**
     * @pre the server poller has a valid server proxy already set
     * polls the server to see if there have been changes in the game model and 
     * gets the game model if there were changes
     */
    public void poll(){
        if(serverProxy!=null){
            gameModel=serverProxy.getGameModel(0);
        }
    }
   
}
