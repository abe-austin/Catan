package game.pieces;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.Cost;
import shared.definitions.PieceType;

/**
 *
 * @author Kevin MacMaster
 */
public class CityTest {

    /**
     * Test of getPieceType method, of class City.
     */
    @Test
    public void testGetPieceType() {
        System.out.println("getPieceType");
        City instance = new City(null);
        PieceType expResult = PieceType.CITY;
        PieceType result = instance.getPieceType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCost method, of class City.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        City instance = new City(null);
        Cost expResult = Cost.CITY;
        Cost result = instance.getCost();
        assertEquals(expResult, result);
    }

}