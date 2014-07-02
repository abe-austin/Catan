package game.cards;

import shared.definitions.DevCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class DevelopmentCard extends Card {
    private DevCardType development;

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

    /**
     * @param development the development to set
     */
    public void setDevelopment(DevCardType development) {
        this.development = development;
    }
}
