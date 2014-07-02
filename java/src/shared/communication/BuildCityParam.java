package shared.communication;

import game.board.corner;

public class BuildCityParam {

	private int playerIndex;
	private corner c;
	private boolean free;
	
	public BuildCityParam(){}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	public corner getCorner() {
		return c;
	}
	public void setCorner(corner c) {
		this.c = c;
	}
	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	
	
}
