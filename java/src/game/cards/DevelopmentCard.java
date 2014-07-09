package game.cards;

import shared.definitions.CardType;
import shared.definitions.DevCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class DevelopmentCard extends Card {
    private DevCardType developmentType;

    public DevelopmentCard(DevCardType development) {
        this.developmentType = development;
    }

    @Override
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
