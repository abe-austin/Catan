package game.cards;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.CardType;
import shared.definitions.SpecialCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class SpecialCardTest {

    /**
     * Test of getCardType method, of class SpecialCard.
     */
    @Test
    public void testGetCardType() {
        System.out.println("getCardType");
        SpecialCard instance = new SpecialCard(null);
        CardType expResult = CardType.SPECIAL;
        CardType result = instance.getCardType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpecial method, of class SpecialCard.
     */
    @Test
    public void testGetSpecial() {
        System.out.println("getSpecial");
        SpecialCardType expResult = SpecialCardType.LARGEST_ARMY;
        SpecialCard instance = new SpecialCard(expResult);
        SpecialCardType result = instance.getSpecial();
        assertEquals(expResult, result);
    }

}