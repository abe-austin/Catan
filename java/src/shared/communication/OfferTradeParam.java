package shared.communication;

public class OfferTradeParam {

	private int playerIndex;
	private List<Card> offering;
	private int recieverIndex;
	
	public OfferTradeParam(){}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public List<Card> getOffering() {
		return offering;
	}

	public void setOffering(List<Card> offering) {
		this.offering = offering;
	}

	public int getRecieverIndex() {
		return recieverIndex;
	}

	public void setRecieverIndex(int recieverIndex) {
		this.recieverIndex = recieverIndex;
	}
}
