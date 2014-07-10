package shared.communication;

public class SendChatParam {
	
	private String type;
	private int playerIndex;
	private String content;
	
	public SendChatParam(String type, int playerIndex, String content) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
