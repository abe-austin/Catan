package shared.communication;

public class AcceptTradeParam {

	private int playerIndex;
	private boolean doesAccept;
		
	public AcceptTradeParam(int playerIndex, boolean doesAccept) {
		super();
		this.playerIndex = playerIndex;
		this.doesAccept = doesAccept;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public boolean isDoesAccept() {
		return doesAccept;
	}
	public void setDoesAccept(boolean doesAccept) {
		this.doesAccept = doesAccept;
	}
	
}
