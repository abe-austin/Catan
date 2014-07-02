package shared.communication;

import shared.locations.HexLocation;

public class PlaySoldierParam {

	private int playerIndex;
	private int victimIndex;
	private HexLocation newRobberLocation;
	
	public PlaySoldierParam(){}

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

	public HexLocation getNewRobberLocation() {
		return newRobberLocation;
	}

	public void setNewRobberLocation(HexLocation newRobberLocation) {
		this.newRobberLocation = newRobberLocation;
	}
	
	
}
