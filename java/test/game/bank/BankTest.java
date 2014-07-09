package game.bank;

import game.cards.DevelopmentCard;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.definitions.DevCardType;
/**
 * @author Cory
 */
public class BankTest{
    /**
     * test giveDevelopmentCard
     */
    @Test
    public void testGiveDevelopmentCard(){
        System.out.println("giveDevelopmentCard");
        Bank bank = new Bank();
        int startSize=bank.getDevelopmentCards().size();
        DevelopmentCard devCard = bank.giveDevelopmentCard(DevCardType.MONUMENT);
        System.out.println(devCard.getCardType());
        assertEquals(startSize-1,bank.getDevelopmentCards().size());
    }
}