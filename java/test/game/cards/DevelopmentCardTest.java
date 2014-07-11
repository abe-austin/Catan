package game.cards;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.CardType;
import shared.definitions.DevCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class DevelopmentCardTest {
    
    /**
     * Test of getCardType method, of class DevelopmentCard.
     */
    @Test
    public void testGetCardType() {
        System.out.println("getCardType");
        DevelopmentCard instance = new DevelopmentCard(null);
        CardType expResult = CardType.DEVELOPMENT;
        CardType result = instance.getCardType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDevelopment method, of class DevelopmentCard.
     */
    @Test
    public void testGetDevelopment() {
        System.out.println("getDevelopment");
        DevCardType expResult = DevCardType.MONOPOLY;
        DevelopmentCard instance = new DevelopmentCard(expResult);
        DevCardType result = instance.getDevelopmentType();
        assertEquals(expResult, result);
    }

}