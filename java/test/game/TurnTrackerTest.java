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
     * Test of setStatus method, of class TurnTracker.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        GameState expResult = GameState.FirstRound;
        TurnTracker instance = new TurnTracker();        
        instance.setStatus(expResult);
        GameState result = instance.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentTurn method, of class TurnTracker.
     */
    @Test
    public void testSetCurrentTurn() {
        System.out.println("setCurrentTurn");
        int expResult = 3;
        TurnTracker instance = new TurnTracker();
        instance.setCurrentTurn(expResult);
        int result = instance.getCurrentTurn();
        assertEquals(expResult, result);
    }

    /**
     * Test of next turn incrementation
     */
    @Test
    public void testNextTurn() {
        System.out.println("nextTurn");
        int expResult = 1;
        TurnTracker instance = new TurnTracker();
        instance.nextTurn();
        int result = instance.getCurrentTurn();
        assertEquals(expResult, result);

        instance.setCurrentTurn(3);
        expResult = 0;
        instance.nextTurn();
        result = instance.getCurrentTurn();
        assertEquals(expResult, result);
    }
    
}
