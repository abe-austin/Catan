package shared.communication;

import java.util.List;
import java.util.Map;
import shared.definitions.ResourceType;

public class OfferTradeParam {

	String type;
	private int playerIndex;
	private OfferParam offer;
	private int receiver;
	
	public OfferTradeParam(String type, int playerIndex, Map<ResourceType,Integer> offer,
			int receiver) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.offer = new OfferParam(offer);
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

	public OfferParam getOffer() {
		return offer;
	}

	public void setOffer(OfferParam offer) {
		this.offer = offer;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiverr(int receiver) {
		this.receiver = receiver;
	}
}
