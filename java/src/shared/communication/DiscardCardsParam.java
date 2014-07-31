package shared.communication;

import java.util.List;
import shared.definitions.ResourceType;

public class DiscardCardsParam {
	
	private String type;
	private int playerIndex;
	private OfferParam discardedCards;
	
	public DiscardCardsParam(String type, int playerIndex, List<ResourceType> discardedCards) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.discardedCards = new OfferParam(discardedCards);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public OfferParam getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(OfferParam discardedCards) {
		this.discardedCards = discardedCards;
	}
}
