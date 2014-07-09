/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.Command;

/**
 *
 * @author Cory
 */
public class GameHistoryTest {
    
    public GameHistoryTest() {
    }

    /**
     * Test of addCommand method, of class GameHistory.
     */
    @Test
    public void testAddCommand() {
        System.out.println("addCommand");
        Command command = null;
        GameHistory instance = new GameHistory();
        instance.addCommand(command);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommands method, of class GameHistory.
     */
    @Test
    public void testGetCommands() {
        System.out.println("getCommands");
        GameHistory instance = new GameHistory();
        List<Command> expResult = null;
        List<Command> result = instance.getCommands();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChatlog method, of class GameHistory.
     */
    @Test
    public void testGetChatlog() {
        System.out.println("getChatlog");
        GameHistory instance = new GameHistory();
        ChatLog expResult = null;
        ChatLog result = instance.getChatlog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
