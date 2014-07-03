package shared.communication;

import game.board.Edge;

public class BuildRoadParam {

	private int playerIndex;
	private Edge e;
	
	public BuildRoadParam(int playerIndex, Edge e) {
		super();
		this.playerIndex = playerIndex;
		this.e = e;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public Edge getEdge() {
		return e;
	}

	public void setEdge(Edge e) {
		this.e = e;
	}
	
	
}
