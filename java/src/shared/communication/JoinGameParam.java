package shared.communication;

import shared.definitions.CatanColor;

public class JoinGameParam {

	private int gameID;
	private CatanColor color;
	
	public JoinGameParam(){}
	
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public CatanColor getColor() {
		return color;
	}
	public void setColor(CatanColor color) {
		this.color = color;
	}
}
