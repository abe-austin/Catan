package game.cards;

import shared.definitions.CardType;
import shared.definitions.DevCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class DevelopmentCard extends Card {
    private DevCardType development;

    public DevelopmentCard(DevCardType development) {
        this.development = development;
    }

    @Override
    public CardType getCardType() {
        return CardType.DEVELOPMENT;
    }

    /**
     * @return the development
     */
    public DevCardType getDevelopment() {
        return development;
    }
}
