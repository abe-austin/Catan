package shared.communication;

import shared.definitions.ResourceType;

public class PlayYearOfPlentyParam {

	private int playerIndex;
	private ResourceType resourceOne;
	private ResourceType resourceTwo;
	
	public PlayYearOfPlentyParam(int playerIndex, ResourceType resourceOne,
			ResourceType resourceTwo) {
		super();
		this.playerIndex = playerIndex;
		this.resourceOne = resourceOne;
		this.resourceTwo = resourceTwo;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public ResourceType getResourceOne() {
		return resourceOne;
	}

	public void setResourceOne(ResourceType resourceOne) {
		this.resourceOne = resourceOne;
	}

	public ResourceType getResourceTwo() {
		return resourceTwo;
	}

	public void setResourceTwo(ResourceType resourceTwo) {
		this.resourceTwo = resourceTwo;
	}
	
	
}
