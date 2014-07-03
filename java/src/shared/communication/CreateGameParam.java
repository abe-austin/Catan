package shared.communication;

public class CreateGameParam {
	
	private String gameName;

	public CreateGameParam(String gameName) {
		super();
		this.gameName = gameName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

}
