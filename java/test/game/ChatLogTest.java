/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cory
 */
public class ChatLogTest {
    
    public ChatLogTest() {
    }

    /**
     * Test of addChatLine method, of class ChatLog.
     */
    @Test
    public void testAddChatLine() {
        System.out.println("addChatLine");
        String line = "";
        ChatLog instance = new ChatLog();
        instance.addChatLine(line);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChatLines method, of class ChatLog.
     */
    @Test
    public void testGetChatLines() {
        System.out.println("getChatLines");
        ChatLog instance = new ChatLog();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getChatLines();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
