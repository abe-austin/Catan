package game.cards;

import shared.definitions.CardType;
import shared.definitions.ResourceType;

/**
 *
 * @author Kevin MacMaster
 */
public class ResourceCard extends Card {
    private ResourceType resource;

    public ResourceCard(ResourceType type) {
        this.resource = type;
    }


    public CardType getCardType() {
        return CardType.RESOURCE;
    }

    /**
     * @return the resource
     */
    public ResourceType getResource() {
        return resource;
    }
}
