package shared.communication;

import game.board.Edge;

public class PlayRoadBuildingParam {

	private String type;
	private int playerIndex;
	private Edge spot1;
	private Edge spot2;
	
	public PlayRoadBuildingParam(String type, int playerIndex, Edge spot1, Edge spot2) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.spot1 = spot1;
		this.spot2 = spot2;
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

	public Edge getSpot1() {
		return spot1;
	}

	public void setSpot1(Edge spot1) {
		this.spot1 = spot1;
	}

	public Edge getSpot2() {
		return spot2;
	}

	public void setSpot2(Edge spot2) {
		this.spot2 = spot2;
	}
	
	
}
