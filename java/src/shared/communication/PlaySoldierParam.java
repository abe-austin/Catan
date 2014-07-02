package shared.communication;

import game.board.boardCoord;

public class PlaySoldierParam {

	private int playerIndex;
	private int victimIndex;
	private boardCoord newRobberLocation;
	
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

	public boardCoord getNewRobberLocation() {
		return newRobberLocation;
	}

	public void setNewRobberLocation(boardCoord newRobberLocation) {
		this.newRobberLocation = newRobberLocation;
	}
	
	
}
