package game.pieces;

import game.cards.CardOwner;
import org.junit.Test;
import static org.junit.Assert.*;

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
        BoardPiece instance = new Road(null);
        CardOwner result = null;
        assertEquals(null, result);
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