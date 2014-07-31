package shared.communication;

import java.util.List;
import shared.definitions.ResourceType;

public class DiscardCardsParam {
	
	private String type;
	private int playerIndex;
	private List<ResourceType> discardedCards;
	
	public DiscardCardsParam(String type, int playerIndex, List<ResourceType> discardedCards) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.discardedCards = discardedCards;
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

	public List<ResourceType> getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(List<ResourceType> discardedCards) {
		this.discardedCards = discardedCards;
	}
}
