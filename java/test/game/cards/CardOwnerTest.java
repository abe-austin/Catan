package game.cards;

import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class CardOwnerTest {

    /**
     * Test of giveResourceCard method, of class CardOwner.
     */
    @Test
    public void testGiveResourceCard() {
        System.out.println("giveResourceCard");
        ResourceType resourceType = ResourceType.BRICK;
        CardOwner instance = new CardOwner();
        ResourceCard expResult = new ResourceCard(resourceType);
        instance.addResourceCard(expResult);
        ResourceCard result = instance.giveResourceCard(resourceType);
        assertEquals(expResult, result);
    }

    /**
     * Test of giveDevelopmentCard method, of class CardOwner.
     */
    @Test
    public void testGiveDevelopmentCard() {
        System.out.println("giveDevelopmentCard");
        DevCardType devCardType = DevCardType.MONOPOLY;
        CardOwner instance = new CardOwner();
        DevelopmentCard expResult = new DevelopmentCard(devCardType);
        instance.addDevelopmentCard(expResult);
        DevelopmentCard result = instance.giveDevelopmentCard(devCardType);
        assertEquals(expResult, result);
    }

    /**
     * Test of giveSpecialCard method, of class CardOwner.
     */
    @Test
    public void testGiveSpecialCard() {
        System.out.println("giveSpecialCard");
        SpecialCardType specialCardType = SpecialCardType.LARGEST_ARMY;
        CardOwner instance = new CardOwner();
        SpecialCard expResult = new SpecialCard(specialCardType);
        instance.giveSpecialCard(specialCardType);
        SpecialCard result = instance.giveSpecialCard(specialCardType);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasResource method with single count, of class CardOwner.
     *   Test true and false
     */
    @Test
    public void testHasResource_ResourceType_single() {
        System.out.println("hasResource");
        ResourceType resourceType = ResourceType.BRICK;
        CardOwner instance = new CardOwner();
        boolean expResult = false;
        boolean result = instance.hasResource(resourceType);
        assertEquals(expResult, result);

        instance.addResourceCard(new ResourceCard(resourceType));
        expResult = true;
        result = instance.hasResource(resourceType);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasResource method with multiple counts, of class CardOwner.
     */
    @Test
    public void testHasResource_ResourceType_multiple() {
        System.out.println("hasResource");
        ResourceType resourceType = ResourceType.BRICK;
        int number = 2;
        CardOwner instance = new CardOwner();
        boolean expResult = false;
        instance.addResourceCard(new ResourceCard(resourceType));
        boolean result = instance.hasResource(resourceType, number);
        assertEquals(expResult, result);

        instance.addResourceCard(new ResourceCard(resourceType));
        expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of hasDevelopmentCard method, of class CardOwner.
     */
    @Test
    public void testHasDevelopmentCard() {
        System.out.println("hasDevelopmentCard");
        DevCardType devCardType = DevCardType.MONOPOLY;
        CardOwner instance = new CardOwner();
        boolean expResult = false;
        instance.addDevelopmentCard(new DevelopmentCard(DevCardType.SOLDIER));
        boolean result = instance.hasDevelopmentCard(devCardType);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasLongestRoad method, of class CardOwner.
     */
    @Test
    public void testHasLongestRoad() {
        System.out.println("hasLongestRoad");
        CardOwner instance = new CardOwner();
        boolean expResult = true;
        instance.addSpecialCard(new SpecialCard(SpecialCardType.LONGEST_ROAD));
        boolean result = instance.hasLongestRoad();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasLargestArmy method, of class CardOwner.
     */
    @Test
    public void testHasLargestArmy() {
        System.out.println("hasLargestArmy");
        CardOwner instance = new CardOwner();
        boolean expResult = false;
        instance.addSpecialCard(new SpecialCard(SpecialCardType.LONGEST_ROAD));
        boolean result = instance.hasLargestArmy();
        assertEquals(expResult, result);
    }

}