package shared.communication;

public class PlayMonumentParam {

	private int playerIndex;
	
	public PlayMonumentParam(int playerIndex) {
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
