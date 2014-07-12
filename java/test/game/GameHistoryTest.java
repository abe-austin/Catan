/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.util.ArrayList;
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
        ArrayList<Command> list = new ArrayList<>();
        instance.addCommand(new Command("first command"));
        instance.addCommand(new Command("second command"));
        assertEquals(2,instance.getCommands().size());
    }

    /**
     * Test of getCommands method, of class GameHistory.
     */
    @Test
    public void testGetCommands() {
        System.out.println("getCommands");
        GameHistory instance = new GameHistory();
        instance.addCommand(new Command("first command"));
        instance.addCommand(new Command("second command"));
        assertEquals(2,instance.getCommands().size());
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
        //assertEquals(expResult, result);
        instance.getChatlog().addChatLine("chatter");
        assertEquals(1,instance.getChatlog().getChatLines().size());
        assertTrue("chatter".equals(instance.getChatlog().getChatLines().get(0)));
        
    }
    
}
