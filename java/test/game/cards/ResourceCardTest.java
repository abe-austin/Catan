package game.cards;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.CardType;
import shared.definitions.ResourceType;

/**
 *
 * @author Kevin MacMaster
 */
public class ResourceCardTest {

    /**
     * Test of getCardType method, of class ResourceCard.
     */
    @Test
    public void testGetCardType() {
        System.out.println("getCardType");
        ResourceCard instance = new ResourceCard(null);
        CardType expResult = CardType.RESOURCE;
        CardType result = instance.getCardType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getResource method, of class ResourceCard.
     */
    @Test
    public void testGetResource() {
        System.out.println("getResource");
        ResourceType expResult = ResourceType.BRICK;
        ResourceCard instance = new ResourceCard(expResult);
        ResourceType result = instance.getResource();
        assertEquals(expResult, result);
    }

}