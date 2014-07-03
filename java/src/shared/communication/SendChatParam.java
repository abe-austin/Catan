package shared.communication;

public class SendChatParam {
	
	private int playerIndex;
	private String message;
	
	public SendChatParam(int playerIndex, String message) {
		super();
		this.playerIndex = playerIndex;
		this.message = message;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
