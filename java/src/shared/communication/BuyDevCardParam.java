package shared.communication;

public class BuyDevCardParam {

	private int playerIndex;
	
	public BuyDevCardParam(int playerIndex) {
		super();
		this.playerIndex = playerIndex;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
}
