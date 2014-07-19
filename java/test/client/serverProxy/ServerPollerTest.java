/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.serverProxy;


import client.data.GameInfo;
import client.serverProxy.ServerPoller;
import client.serverProxy.ServerProxyFacade;
import game.GameModel;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Cory
 */
public class ServerPollerTest {
    
    public ServerPollerTest() {
    }

    /**
     * Test of setServerProxy method, of class ServerPoller.
     */
    @Test
    public void testSetServerProxy() {
        System.out.println("setServerProxy");
        ServerProxyFacade serverProxy = null;
        ServerPoller instance = new ServerPoller();
        instance.setServerProxy(serverProxy);
        assertNull(instance.getServerProxy());
        
        serverProxy= new ServerProxyFacade(true);
        instance.setServerProxy(serverProxy);
        assertEquals(serverProxy,instance.getServerProxy());
        
    }

    /**
     * Test of poll method, of class ServerPoller.
     */
    @Test
    public void testPoll() {
        System.out.println("poll");
        ServerProxyFacade server = new ServerProxyFacade(false);
        server.registerUser("test", "testing");
        server.createGame("crashGame", true, true, true);
        ArrayList<GameInfo> gameInfo =(ArrayList<GameInfo>)server.getAllGames().getBody();
        int gameID=0;
        if (!gameInfo.isEmpty()){
            gameID=gameInfo.get(0).getId();
        }
        System.out.println("gameID "+gameID);
        ServerPoller instance = new ServerPoller();
        instance.setServerProxy(server);
        instance.poll(gameID);
        //assertNull(instance.getGameModel());
        
        ServerProxyFacade mockServer = new ServerProxyFacade(true);
        instance.setServerProxy(mockServer);
       // instance.poll();
        GameModel expectedGameModel= new GameModel();
       // assertEquals(expectedGameModel,instance.getGameModel());
       // assertTrue(instance.getGameModel()!=null);
    }
    
}
