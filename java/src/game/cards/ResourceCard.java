package game.cards;

import shared.definitions.ResourceType;

/**
 *
 * @author Kevin MacMaster
 */
public class ResourceCard extends Card {
    private ResourceType resource;


    public CardType getCardType() {
        return CardType.RESOURCE;
    }

    /**
     * @return the resource
     */
    public ResourceType getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(ResourceType resource) {
        this.resource = resource;
    }
}
