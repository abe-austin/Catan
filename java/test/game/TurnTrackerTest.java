/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.GameState;

/**
 *
 * @author Cory
 */
public class TurnTrackerTest {
    
    public TurnTrackerTest() {
    }

    /**
     * Test of getStatus method, of class TurnTracker.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        TurnTracker instance = new TurnTracker();
        GameState expResult = null;
        GameState result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class TurnTracker.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        GameState status = null;
        TurnTracker instance = new TurnTracker();
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentTurn method, of class TurnTracker.
     */
    @Test
    public void testGetCurrentTurn() {
        System.out.println("getCurrentTurn");
        TurnTracker instance = new TurnTracker();
        int expResult = 0;
        int result = instance.getCurrentTurn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentTurn method, of class TurnTracker.
     */
    @Test
    public void testSetCurrentTurn() {
        System.out.println("setCurrentTurn");
        int currentTurn = 0;
        TurnTracker instance = new TurnTracker();
        instance.setCurrentTurn(currentTurn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
