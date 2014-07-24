package shared.communication;

import shared.locations.EdgeLocation;

public class BuildRoadParam {

	private String type;
	private int playerIndex;
	private MapLocationParam roadLocation;
	private boolean free;
	
	public BuildRoadParam(String type, int playerIndex, EdgeLocation roadLocation, boolean free) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.roadLocation = new MapLocationParam(roadLocation);
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

	public MapLocationParam getRoadLocation() {
		return roadLocation;
	}

	public void setRoadLocation(MapLocationParam edgeLocation) {
		this.roadLocation = edgeLocation;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}
}
