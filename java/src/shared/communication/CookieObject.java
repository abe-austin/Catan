package shared.communication;

public class CookieObject {
	
	private String username;
	private String password;
	private int ID;
	private int gameID;
	
	public CookieObject() {
		username = null;
		password = null;
		ID = -1;
		gameID = -1;
	}

	public CookieObject(String username, String password, int ID, int gameID) {
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
}
