package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.Cost;
import shared.definitions.PieceType;

/**
 *
 * @author Kevin MacMaster
 */
public class RoadTest {

    /**
     * Test of getPieceType method, of class Road.
     */
    @Test
    public void testGetPieceType() {
        System.out.println("getPieceType");
        Road instance = new Road(null);
        PieceType expResult = PieceType.ROAD;
        PieceType result = instance.getPieceType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCost method, of class Road.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        Road instance = new Road(null);
        Cost expResult = Cost.ROAD;
        Cost result = instance.getCost();
        assertEquals(expResult, result);
    }

}