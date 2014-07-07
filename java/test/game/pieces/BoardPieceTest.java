package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import player.Player;

/**
 *
 * @author Kevin MacMaster
 */
public class BoardPieceTest {

    /**
     * Test of getOwner method, of class BoardPiece.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        Player expResult = new Player(null);
        BoardPiece instance = new Road(expResult);
        Player result = instance.getOwner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isActive method, of class BoardPiece.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        BoardPiece instance = new Road(null);
        instance.setActive(true);
        boolean expResult = true;
        boolean result = instance.isActive();
        assertEquals(expResult, result);
    }
}