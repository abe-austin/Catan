/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.serverProxy;

import org.junit.Test;
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendGameModel method, of class ServerPoller.
     */
    @Test
    public void testSendGameModel() {
        System.out.println("sendGameModel");
        ServerPoller instance = new ServerPoller();
        instance.sendGameModel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of poll method, of class ServerPoller.
     */
    @Test
    public void testPoll() {
        System.out.println("poll");
        ServerPoller instance = new ServerPoller();
        instance.poll();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
