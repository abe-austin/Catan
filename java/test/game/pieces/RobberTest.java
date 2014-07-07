package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.PieceType;
import shared.locations.HexLocation;

/**
 *
 * @author Kevin MacMaster
 */
public class RobberTest {
    /**
     * Test of getPieceType method, of class Robber.
     */
    @Test
    public void testGetPieceType() {
        System.out.println("getPieceType");
        Robber instance = new Robber(null);
        PieceType expResult = PieceType.ROBBER;
        PieceType result = instance.getPieceType();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateLocation method, of class Robber.
     */
    @Test
    public void testUpdateLocation() {
        System.out.println("updateLocation");
        HexLocation loc = new HexLocation(1,2);
        Robber instance = new Robber(null);
        instance.updateLocation(loc);
        HexLocation result = instance.getLocation();
        assertEquals(loc, result);
    }
}