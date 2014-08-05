package game.cards;

import shared.definitions.CardType;
import shared.definitions.DevCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class DevelopmentCard {
	private DevCardType developmentType;
        private boolean old = false;

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

        public boolean isOld() {
            return old;
        }

        public void setOld(boolean old) {
            this.old = old;
        }
        
        
}
