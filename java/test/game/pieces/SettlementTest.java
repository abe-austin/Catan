package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.Cost;
import shared.definitions.PieceType;

/**
 *
 * @author Kevin MacMaster
 */
public class SettlementTest {

    /**
     * Test of getPieceType method, of class City.
     */
    @Test
    public void testGetPieceType() {
        System.out.println("getPieceType");
        Settlement instance = new Settlement(null);
        PieceType expResult = PieceType.SETTLEMENT;
        PieceType result = instance.getPieceType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCost method, of class City.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        Settlement instance = new Settlement(null);
        Cost expResult = Cost.SETTLEMENT;
        Cost result = instance.getCost();
        assertEquals(expResult, result);
    }

}