package game.bank;

import game.cards.*;
import shared.definitions.SpecialCardType;
/**
 * the bank the contains sets of ResourceCards, DevelopmentCards and SpecialCards
 * @author Cory
 */
public class Bank extends CardOwner {
    /**
     * Initializes all starting cards
     */
    public Bank() {
        addSpecialCard(new SpecialCard(SpecialCardType.LARGEST_ARMY));
        addSpecialCard(new SpecialCard(SpecialCardType.LONGEST_ROAD));

        
    }
    
}
