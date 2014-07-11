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
        assertEquals(instance.getChatLines().size(), 1);
    }

    /**
     * Test of getChatLines method, of class ChatLog.
     */
    @Test
    public void testGetChatLines() {
        System.out.println("getChatLines");
        ChatLog instance = new ChatLog();
        ArrayList<String> expResult = new ArrayList<String>();
        ArrayList<String> result = instance.getChatLines();
        assertEquals(expResult, result);
    }
    
}
