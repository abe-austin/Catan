package shared.communication;

public class BuyDevCardParam {

	String type;
	private int playerIndex;
	
	public BuyDevCardParam(String type, int playerIndex) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
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
}
