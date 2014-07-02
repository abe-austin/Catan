package shared.communication;

public class BuildSettlementParam {

	private int playerIndex;
	private Corner corner;
	private boolean free;
	
	public BuildSettlementParam(){}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public Corner getCorner() {
		return corner;
	}

	public void setCorner(Corner corner) {
		this.corner = corner;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
	
}
