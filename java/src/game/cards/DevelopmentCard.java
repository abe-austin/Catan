package game.cards;

import shared.definitions.CardType;
import shared.definitions.DevCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class DevelopmentCard {
    private DevCardType developmentType;

    public DevelopmentCard(DevCardType development) {
        this.developmentType = development;
    }

    public CardType getCardType() {
        return CardType.DEVELOPMENT;
    }

    /**
     * @return the development
     */
    public DevCardType getDevelopmentType() {
        return developmentType;
    }
}
