package shared.communication;

public class PlaySoldierParam {

	private int playerIndex;
	private int victimIndex;
	private HexTile newRobberLocation;
	
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

	public HexTile getNewRobberLocation() {
		return newRobberLocation;
	}

	public void setNewRobberLocation(HexTile newRobberLocation) {
		this.newRobberLocation = newRobberLocation;
	}
	
	
}
