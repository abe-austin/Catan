package shared.communication;

public class PlayRoadBuildingParam {

	private int playerIndex;
	private Edge edgeOne;
	private Edge edgeTwo;
	
	public PlayRoadBuildingParam(){}

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
