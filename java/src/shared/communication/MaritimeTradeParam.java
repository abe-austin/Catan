package shared.communication;

import shared.definitions.ResourceType;

public class MaritimeTradeParam {

	private int playerIndex;
	private int ratio;
	private ResourceType input;
	private ResourceType output;
	
	public MaritimeTradeParam(){}

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

	public ResourceType getInput() {
		return input;
	}

	public void setInput(ResourceType input) {
		this.input = input;
	}

	public ResourceType getOutput() {
		return output;
	}

	public void setOutput(ResourceType output) {
		this.output = output;
	}
	
	
}
