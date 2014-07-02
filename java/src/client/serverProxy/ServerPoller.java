/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.serverProxy;

/**
 *
 * @author Cory
 */
public class ServerPoller {
    private ServerProxyFacade serverProxy;  
    private GameModel gameModel;
    /**
     * empty constructor
     */
    public ServerPoller(){}
    /**
     * sets the server proxy that it will connect to
     * @param serverProxy  the serverProxy
     */
    public setServerProxy( ServerProxyFacade serverProxy){}
    /**
     * sends the current game model to the model
     * @param gameModel the current gameModel received from the serverProxy
     */
    public sendGameModel(GameModel gameModel){}
    /**
     * polls the server to see if there have been changes
     */
    public poll(){}

}
