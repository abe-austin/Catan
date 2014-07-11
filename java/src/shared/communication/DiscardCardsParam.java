package shared.communication;

import java.util.List;
import game.cards.ResourceCard;

public class DiscardCardsParam {
	
	private String type;
	private int playerIndex;
	private List<ResourceCard> discardedCards;
	
	public DiscardCardsParam(String type, int playerIndex, List<ResourceCard> discardedCards) {
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

	public List<ResourceCard> getDiscardedCards() {
		return discardedCards;
	}

	public void setDiscardedCards(List<ResourceCard> discardedCards) {
		this.discardedCards = discardedCards;
	}
}
