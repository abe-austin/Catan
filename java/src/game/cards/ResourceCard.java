package game.cards;

import shared.definitions.CardType;
import shared.definitions.ResourceType;

/**
 *
 * @author Kevin MacMaster
 */
public class ResourceCard {
    private ResourceType resourceType;

    public ResourceCard(ResourceType type) {
        this.resourceType = type;
    }


    public CardType getCardType() {
        return CardType.RESOURCE;
    }

    /**
     * @return the resource
     */
    public ResourceType getResourceType() {
        return resourceType;
    }
}
