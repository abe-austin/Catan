package shared.communication;

public class DiscardCardsParam {
	
	private int playerIndex;
	private List<Card> cards;
	
	public DiscardCardsParam(){}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
