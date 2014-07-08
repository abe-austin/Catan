package shared.communication;

public class SaveGameParam {

	int gameID;
	String gameName;
	
	public SaveGameParam(int gameID, String gameName) {
		super();
		this.gameID = gameID;
		this.gameName = gameName;
	}

	public int getGameID() {
		return gameID;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
}
