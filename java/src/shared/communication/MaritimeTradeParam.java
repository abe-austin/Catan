package shared.communication;

import shared.definitions.ResourceType;

public class MaritimeTradeParam {

	private String type;
	private int playerIndex;
	private int ratio;
	private ResourceType inputResource;
	private ResourceType outputResource;
	
	public MaritimeTradeParam(String type, int playerIndex, int ratio, ResourceType inputResource,
			ResourceType outputResource) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
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

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public ResourceType getInputResource() {
		return inputResource;
	}

	public void setInputResource(ResourceType inputResource) {
		this.inputResource = inputResource;
	}

	public ResourceType getOutputResource() {
		return outputResource;
	}

	public void setOutputResource(ResourceType outputResource) {
		this.outputResource = outputResource;
	}
	
	
}
