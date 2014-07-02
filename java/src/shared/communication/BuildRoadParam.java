package shared.communication;

import game.board.edge;

public class BuildRoadParam {

	private int playerIndex;
	private edge e;
	
	public BuildRoadParam(){}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public edge getEdge() {
		return e;
	}

	public void setEdge(edge e) {
		this.e = e;
	}
	
	
}
