package shared.communication;

import game.board.Corner;

public class BuildSettlementParam {

	private int playerIndex;
	private Corner c;
	private boolean free;
	
	public BuildSettlementParam(int playerIndex, Corner c, boolean free) {
		super();
		this.playerIndex = playerIndex;
		this.c = c;
		this.free = free;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public Corner getCorner() {
		return c;
	}

	public void setCorner(Corner c) {
		this.c = c;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
	
}
