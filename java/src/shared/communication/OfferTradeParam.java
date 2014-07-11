package shared.communication;

import java.util.List;

import shared.definitions.ResourceType;
import game.cards.ResourceCard;

public class OfferTradeParam {

	String type;
	private int playerIndex;
	private List<ResourceType> offer;
	private int receiver;
	
	public OfferTradeParam(String type, int playerIndex, List<ResourceType> offer,
			int receiver) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.offer = offer;
		this.receiver = receiver;
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

	public List<ResourceType> getOffer() {
		return offer;
	}

	public void setOffer(List<ResourceType> offer) {
		this.offer = offer;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiverr(int receiver) {
		this.receiver = receiver;
	}
}
