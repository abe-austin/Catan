package shared.communication;

import game.board.Corner;

public class BuildSettlementParam {

	private String type;
	private int playerIndex;
	private Corner vertexLocation;
	private boolean free;
	
	public BuildSettlementParam(String type, int playerIndex, Corner vertexLocation, boolean free) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = free;
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

	public Corner getVertexLocation() {
		return vertexLocation;
	}

	public void setVertexLocation(Corner vertexLocation) {
		this.vertexLocation = vertexLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
	
	
}
