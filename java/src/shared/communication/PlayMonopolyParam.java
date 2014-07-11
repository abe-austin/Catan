package shared.communication;

import shared.definitions.ResourceType;

public class PlayMonopolyParam {

	private String type;
	private int playerIndex;
	private ResourceType resource;
	
	public PlayMonopolyParam(String type, int playerIndex, ResourceType resource) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.resource = resource;
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

	public ResourceType getResource() {
		return resource;
	}

	public void setResource(ResourceType resource) {
		this.resource = resource;
	}
}
