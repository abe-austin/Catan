package shared.communication;

public class PlayMonumentParam {

	private String type;
	private int playerIndex;
	
	public PlayMonumentParam(String type, int playerIndex) {
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
