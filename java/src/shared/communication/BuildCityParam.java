package shared.communication;

import game.board.Corner;
import shared.locations.VertexLocation;

public class BuildCityParam {

	private String type;
	private int playerIndex;
	private MapLocationParam vertexLocation;
	private boolean free;
		
	public BuildCityParam(String type, int playerIndex, VertexLocation vertexLocation, boolean free) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.vertexLocation = new MapLocationParam(vertexLocation);
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
	public MapLocationParam getVertexLocation() {
		return vertexLocation;
	}
	public void setVertexLocation(MapLocationParam vertexLocation) {
		this.vertexLocation = vertexLocation;
	}
	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	
	
}
