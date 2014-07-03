package game.bank;

import game.cards.*;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
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

        // Adding Resource Cards to Bank
        for(int i = 0; i < 19; i++) {
            addResourceCard(new ResourceCard(ResourceType.BRICK));
            addResourceCard(new ResourceCard(ResourceType.ORE));
            addResourceCard(new ResourceCard(ResourceType.WOOD));
            addResourceCard(new ResourceCard(ResourceType.SHEEP));
            addResourceCard(new ResourceCard(ResourceType.WHEAT));
        }

        // Adding Development Cards to Bank
        for(int i = 0; i < 14; i++) {
            if(i < 2) {
                addDevelopmentCard(new DevelopmentCard(DevCardType.MONOPOLY));
                addDevelopmentCard(new DevelopmentCard(DevCardType.ROAD_BUILD));
                addDevelopmentCard(new DevelopmentCard(DevCardType.YEAR_OF_PLENTY));
            }

            if(i < 5) {
                addDevelopmentCard(new DevelopmentCard(DevCardType.MONUMENT));
            }

            addDevelopmentCard(new DevelopmentCard(DevCardType.SOLDIER));
        }
    }
    
}
