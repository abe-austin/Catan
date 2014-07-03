package shared.communication;

public class RollNumberParam {

	private int playerIndex;
	private int numberRolled;
	
	public RollNumberParam(int playerIndex, int numberRolled) {
		super();
		this.playerIndex = playerIndex;
		this.numberRolled = numberRolled;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getNumberRolled() {
		return numberRolled;
	}

	public void setNumberRolled(int numberRolled) {
		this.numberRolled = numberRolled;
	}
	
	
}
