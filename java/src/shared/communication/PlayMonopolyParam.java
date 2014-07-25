package shared.communication;

import shared.definitions.ResourceType;

public class PlayMonopolyParam {

	private String type;
	private int playerIndex;
	private String resource;
	
	public PlayMonopolyParam(String type, int playerIndex, ResourceType resource) {
		super();
		this.type = type;
		this.playerIndex = playerIndex;
		if(resource==ResourceType.BRICK){
                     this.resource="brick";
                }
                else if(resource==ResourceType.ORE){
                    this.resource="ore";
                }
                else if(resource==ResourceType.SHEEP){
                    this.resource="sheep";
                }
                else if(resource==ResourceType.WHEAT){
                    this.resource="wheat";
                }
                else if(resource==ResourceType.WOOD){
                    this.resource="wood";
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

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
}
