/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.serverProxy;

import game.GameModel;

import org.junit.Test;

import client.serverProxy.ServerPoller;
import client.serverProxy.ServerProxyFacade;
import static org.junit.Assert.*;

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
        server.getAllGames();
        
        ServerPoller instance = new ServerPoller();
        instance.poll();
        assertNull(instance.getGameModel());
        
        ServerProxyFacade mockServer = new ServerProxyFacade(true);
        instance.setServerProxy(mockServer);
        instance.poll();
        GameModel expectedGameModel= new GameModel();
       // assertEquals(expectedGameModel,instance.getGameModel());
        assertTrue(instance.getGameModel()!=null);
    }
    
}
