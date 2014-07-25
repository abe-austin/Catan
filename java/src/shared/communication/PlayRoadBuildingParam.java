package shared.communication;

import game.board.Edge;
import shared.locations.EdgeLocation;

public class PlayRoadBuildingParam {

	private String type;
	private int playerIndex;
	private MapLocationParam spot1;
	private MapLocationParam spot2;
	
	public PlayRoadBuildingParam(String type, int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.spot1 = new MapLocationParam(spot1);
		this.spot2 = new MapLocationParam(spot2);
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

	public MapLocationParam getSpot1() {
		return spot1;
	}

	public void setSpot1(MapLocationParam spot1) {
		this.spot1 = spot1;
	}

	public MapLocationParam getSpot2() {
		return spot2;
	}

	public void setSpot2(MapLocationParam spot2) {
		this.spot2 = spot2;
	}
	
	
}
