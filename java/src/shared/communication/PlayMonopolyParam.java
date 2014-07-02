package shared.communication;

import shared.definitions.ResourceType;

public class PlayMonopolyParam {

	private int playerIndex;
	private ResourceType resource;
	
	public PlayMonopolyParam(){}

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
