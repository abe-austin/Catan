package game.cards;

import shared.definitions.CardType;
import shared.definitions.SpecialCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class SpecialCard extends Card {
    private SpecialCardType special;

    @Override
    public CardType getCardType() {
        return CardType.SPECIAL;
    }

    /**
     * @return the special
     */
    public SpecialCardType getSpecial() {
        return special;
    }

    /**
     * @param special the special to set
     */
    public void setSpecial(SpecialCardType special) {
        this.special = special;
    }
}
