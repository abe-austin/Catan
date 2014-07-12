package game.cards;

import shared.definitions.CardType;
import shared.definitions.SpecialCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class SpecialCard {
    private SpecialCardType special;

    public SpecialCard(SpecialCardType type) {
        special = type;
    }

    public CardType getCardType() {
        return CardType.SPECIAL;
    }

    /**
     * @return the special
     */
    public SpecialCardType getSpecial() {
        return special;
    }
}
