package shared.communication;

import java.util.List;
import game.cards.ResourceCard;

public class DiscardCardsParam {
	
	private int playerIndex;
	private List<ResourceCard> cards;
	
	public DiscardCardsParam(){}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public List<ResourceCard> getCards() {
		return cards;
	}

	public void setCards(List<ResourceCard> cards) {
		this.cards = cards;
	}
}
