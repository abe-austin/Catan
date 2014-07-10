package shared.communication;

import game.board.Edge;

public class BuildRoadParam {

	private String type;
	private int playerIndex;
	private Edge roadLocation;
	
	public BuildRoadParam(String type, int playerIndex, Edge roadLocation) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.roadLocation = roadLocation;
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

	public Edge getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(Edge roadLocation) {
		this.roadLocation = roadLocation;
	}
	
	
}
