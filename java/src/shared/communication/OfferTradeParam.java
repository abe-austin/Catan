package shared.communication;

import java.util.List;
import game.cards.ResourceCard;

public class OfferTradeParam {

	private int playerIndex;
	private List<ResourceCard> offering;
	private int recieverIndex;
	
	public OfferTradeParam(int playerIndex, List<ResourceCard> offering,
			int recieverIndex) {
		super();
		this.playerIndex = playerIndex;
		this.offering = offering;
		this.recieverIndex = recieverIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public List<ResourceCard> getOffering() {
		return offering;
	}

	public void setOffering(List<ResourceCard> offering) {
		this.offering = offering;
	}

	public int getRecieverIndex() {
		return recieverIndex;
	}

	public void setRecieverIndex(int recieverIndex) {
		this.recieverIndex = recieverIndex;
	}
}
