package shared.communication;

import shared.definitions.ResourceType;

public class MaritimeTradeParam {

	private String type;
	private int playerIndex;
	private int ratio;
	private String inputResource;
	private String outputResource;
	
	public MaritimeTradeParam(String type, int playerIndex, int ratio, ResourceType inputResource,
			ResourceType outputResource) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		this.ratio = ratio;
                if(inputResource==ResourceType.BRICK){
                     this.inputResource="brick";
                }
                else if(inputResource==ResourceType.ORE){
                    this.inputResource="ore";
                }
                else if(inputResource==ResourceType.SHEEP){
                    this.inputResource="sheep";
                }
                else if(inputResource==ResourceType.WHEAT){
                    this.inputResource="wheat";
                }
                else if(inputResource==ResourceType.WOOD){
                    this.inputResource="wood";
                }
                
                if(outputResource==ResourceType.BRICK){
                     this.outputResource="brick";
                }
                else if(outputResource==ResourceType.ORE){
                    this.outputResource="ore";
                }
                else if(outputResource==ResourceType.SHEEP){
                    this.outputResource="sheep";
                }
                else if(outputResource==ResourceType.WHEAT){
                    this.outputResource="wheat";
                }
                else if(outputResource==ResourceType.WOOD){
                    this.outputResource="wood";
                }	
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

	public String getInputResource() {
		return inputResource;
	}

	public void setInputResource(String inputResource) {
		this.inputResource = inputResource;
	}

	public String getOutputResource() {
		return outputResource;
	}

	public void setOutputResource(String outputResource) {
		this.outputResource = outputResource;
	}
	
	
}
