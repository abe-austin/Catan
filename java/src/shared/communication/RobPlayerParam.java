package shared.communication;

import game.board.Corner;

public class RobPlayerParam {

	String type;
	int playerIndex;
	int victimIndex;
	Corner location;
	
	public RobPlayerParam(String type, int playerIndex, int victimIndex, Corner location) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
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
	
	public int getVictimIndex() {
		return victimIndex;
	}
	
	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}
	
	public Corner getLocation() {
		return location;
	}
	
	public void setLocation(Corner location) {
		this.location = location;
	}
	
	
}
