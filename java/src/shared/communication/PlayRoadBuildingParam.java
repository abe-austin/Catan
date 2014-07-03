package shared.communication;

import game.board.Edge;

public class PlayRoadBuildingParam {

	private int playerIndex;
	private Edge edgeOne;
	private Edge edgeTwo;
	
	public PlayRoadBuildingParam(int playerIndex, Edge edgeOne, Edge edgeTwo) {
		super();
		this.playerIndex = playerIndex;
		this.edgeOne = edgeOne;
		this.edgeTwo = edgeTwo;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public Edge getEdgeOne() {
		return edgeOne;
	}

	public void setEdgeOne(Edge edgeOne) {
		this.edgeOne = edgeOne;
	}

	public Edge getEdgeTwo() {
		return edgeTwo;
	}

	public void setEdgeTwo(Edge edgeTwo) {
		this.edgeTwo = edgeTwo;
	}
	
	
}
