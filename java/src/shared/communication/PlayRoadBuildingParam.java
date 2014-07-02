package shared.communication;

import game.board.edge;

public class PlayRoadBuildingParam {

	private int playerIndex;
	private edge edgeOne;
	private edge edgeTwo;
	
	public PlayRoadBuildingParam(){}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public edge getEdgeOne() {
		return edgeOne;
	}

	public void setEdgeOne(edge edgeOne) {
		this.edgeOne = edgeOne;
	}

	public edge getEdgeTwo() {
		return edgeTwo;
	}

	public void setEdgeTwo(edge edgeTwo) {
		this.edgeTwo = edgeTwo;
	}
	
	
}
